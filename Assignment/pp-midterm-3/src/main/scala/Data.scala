/// !!!DO NOT FIX THIS FILE!!!  If you fix this file, your code will not be compiled.

package pp202402.midterm3

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

class DividedByZeroException(val arg: String) extends Exception
class NotAnIntegerException(val arg: String) extends Exception

enum Error:
  case DividedByZero
  case NotAnInteger

type Rational = (Int, Int)

enum Result[+T]:
  case Success(value: T)
  case Failure(err: Error)

enum Expr[T] : 
  case Num(n: T)
  case Add(e1: Expr[T], e2: Expr[T])
  case Sub(e1: Expr[T], e2: Expr[T])
  case Mul(e1: Expr[T], e2: Expr[T])
  case Div(e1: Expr[T], e2: Expr[T])