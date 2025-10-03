/// =========================================
/// ===== !!!DO NOT FIX THIS FILE!!! ========
/// =========================================
/// If you fix this file, your code will not be compiled.

package pp202502.assign2b

/** Note: enum is new feature of Scala 3 which alters `sealed abstract class`
  * to define case classes and abstract data types.
  *
  * See https://docs.scala-lang.org/scala3/reference/enums/enums.html
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
enum IOption[+A]:
  case ISome(a: A)
  case INone

  def isSome: Boolean = this match
    case ISome(_) => true
    case _ => false
  
  def isNone: Boolean = !isSome
