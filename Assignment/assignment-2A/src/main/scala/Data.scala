/// =========================================
/// ===== !!!DO NOT FIX THIS FILE!!! ========
/// =========================================
/// If you fix this file, your code will not be compiled.

package pp202502.assign2a

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

// Binary Search Tree
enum BST[+K,+V]: 
  case Leaf
  case Node(key: K, value: V, left: BST[K,V], right: BST[K,V])

// Simple Arithmetic Expression
enum Expr:
  // single number
  case Num(n: Long)
  // f1 + f2
  case Add(f1: Expr, f2: Expr)
  // f1 - f2
  case Sub(f1: Expr, f2: Expr)
  // f1 & f2
  case Mul(f1: Expr, f2: Expr)
  // f1 / f2 (use regular scala division)
  case Div(f1: Expr, f2: Expr)
  // variable
  case Var(x: String)

enum IOption[+A]:
  case ISome(a: A)
  case INone

  def isSome: Boolean = this match
    case ISome(_) => true
    case _ => false
  
  def isNone: Boolean = !isSome
