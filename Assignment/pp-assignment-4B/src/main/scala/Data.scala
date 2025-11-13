/// =========================================
/// ===== !!!DO NOT FIX THIS FILE!!! ========
/// =========================================
/// If you fix this file, your code will not be compiled.

package pp202402.assign4b
import scala.language.implicitConversions

object Data:
  enum Priority:
    case Urgent
    case Normal

  enum IOption[+A]:
    case ISome(a: A)
    case INone

    override def toString: String = this match
      case ISome(value) => s"Some($value)"
      case INone        => "None"

  enum IList[+A]:
    case INil
    case ICons(head: A, tail: IList[A])
  
  val EMPTY_LOG = "EMPTY"

object TypeClass:
  import Data._

  trait Queue[Q, A]:
    def empty: Q
    def enqueue(q : Q, elem: A): Q
    def dequeue(q : Q): (IOption[A], Q)

  trait Item[A]:
    extension (self: A)
      def info(): String
      def calculatePriority(): IOption[Priority]
      def execute(): A

  trait Task[I, A: Item]:
    def log(i: I): String
    def getPriority(i: I): IOption[Priority]
    def getItem(i: I): IOption[A]
    def process(i: I): I

  type curry[F[_,_],A1] = ([X] =>> F[X,A1])

  trait Box[S[_]]:
    type Data
    val * : Data
    given DI: S[Data]
  
  object Box:
    implicit // needed for implicit conversion of D into Box[S]
    def apply[S[_],D](d: D)(implicit i: S[D]): Box[S] = new {
      type Data = D
      val * = d
      val DI = i
    }

/** Example of how typeclasses are used */
object Examples:
  import Data._
  import TypeClass._

  given iListQueue[A]: Queue[IList[A], A] with
    def empty: IList[A] = IList.INil

    def enqueue(q: IList[A], elem: A): IList[A] =
      def append(list: IList[A], elem: A): IList[A] = list match
        case IList.INil => IList.ICons(elem, IList.INil)
        case IList.ICons(head, tail) => IList.ICons(head, append(tail, elem))
      append(q, elem)

    def dequeue(q: IList[A]): (IOption[A], IList[A]) =
      q match
        case IList.INil =>
          (IOption.INone, IList.INil)
        case IList.ICons(head, tail) =>
          (IOption.ISome(head), tail)

  def empty[Q, A](using QUEUE: Queue[Q, A]): Q = QUEUE.empty
  def enqueue[Q, A](q: Q, elem: A)(using QUEUE: Queue[Q, A]): Q = QUEUE.enqueue(q, elem)
  def dequeue[Q, A](q: Q)(using QUEUE: Queue[Q, A]): (IOption[A], Q) = QUEUE.dequeue(q)

  given Item[Int] with
    extension (self: Int)
      def info(): String = "#%d".format(self)
      def calculatePriority(): IOption[Priority] =
        if self <= 0 then IOption.INone
        else if self % 2 == 0 then IOption.ISome(Priority.Normal)
        else IOption.ISome(Priority.Urgent)
      def execute(): Int =
        self - 1

  given intTask: Task[Int, Int] with
    def log(i: Int): String = s"Int: ${i.info()}"
    def getPriority(i: Int): IOption[Priority] = i.calculatePriority()
    def getItem(i: Int): IOption[Int] = 
      if i == 0 then IOption.INone
      else IOption.ISome(i)
    def process(i: Int): Int = i.execute()

  given listTask[A: Item]: Task[IList[A], A] with
    def log(i: IList[A]): String = i match
        case IList.ICons(head, _) => s"List: ${head.info()}"
        case IList.INil => "List: Empty"
    def getPriority(i: IList[A]): IOption[Priority] = i match
      case IList.ICons(head, _) => head.calculatePriority()
      case IList.INil => IOption.INone
    def getItem(i: IList[A]): IOption[A] = i match
      case IList.ICons(head, _) => IOption.ISome(head)
      case IList.INil => IOption.INone
    def process(i: IList[A]): IList[A] = i match
      case IList.ICons(head, tail) =>
        val newHead = head.execute()
        newHead.calculatePriority() match
          case IOption.INone => tail
          case IOption.ISome(_) => IList.ICons(newHead, tail)
      case IList.INil => IList.INil