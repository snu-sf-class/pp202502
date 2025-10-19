/// !!!DO NOT FIX THIS FILE!!!  If you fix this file, your code will not be compiled.

package pp202402.midterm1

/** Note: enum is new feature of Scala 3 which alters `sealed abstract class`
  * to define case classes and abstract data types.
  *
  * e.g.)
  * ```
  * abstract class Expr
  * case class Number(n: Long) extends Expr
  * ```
  *
  * is same as
  *
  * ```
  * enum Expr:
  *   case Number(n: Long)
  * ```
  */

/// =========================================
/// ===== !!!DO NOT FIX THIS FILE!!! ========
/// =========================================

enum IList[+A]:
  case INil
  case ICons(head: A, tail: IList[A])

enum BinTree[+A]:
  case Leaf
  case Node(v: A, l: BinTree[A], r: BinTree[A])



