package pp202502.assign2a

import scala.annotation.tailrec
import scala.util.control.TailCalls._

/** Principles of Programming: Assignment 02A.
  *
  * Implement given functions, which are currently left blank. (???) **WARNING:
  * Please read the restrictions below carefully.**
  *
  * If you do not follow these, **your submission will not be graded.**
  *
  *   - Do not use the keyword `var`. Use `val` and `def` instead.
  *   - Do not use any library functions or data structures like `List`,
  *     `Array`, `Range` (`1 to n`, `1 until n` ...), `fold`, `map`, `reduce` or
  *     etc.
  *   - If you want to use a data structure, create new one instead of using the
  *     library ones.
  *   - You can only use tuples, `scala.annotation.tailrec`, and
  *     `scala.util.control.TailCalls._` from the library.
  *   - Do not use any looping syntax of Scala (`for`, `while`, `do-while`,
  *     `yield`, ...)
  *
  * Again, your score will be zero if you do not follow these rules.
  *
  * Note that these rules will be gradually relaxed through the next
  * assignments.
  *
  * We do not require tail-recursion explicitly for this assignment.
  *
  * Timeout: 30 sec.
  */
object Assignment2A:
  import IOption.*
  import BST.*
  import Expr.*

  /** Problem 1: Polymorphic Binary Search Tree
   * 
   * Binary Search Tree and its implementation has been covered in the class. 
   * 
   * Here, implement functions for a polymorphic version, where type of the keys is K, type of the values is V.
   * 
   * Compare function 'cmp' is given as input with following semantic : 
   *    cmp(x,y) > 0 if x > y
   *    cmp(x,y) == 0 if x == y
   *    cmp(x,y) < 0 if x < y
   */ 

  /** Problem 1-1: Lookup in BST
   */
  @tailrec
  def lookup[K,V](t: BST[K,V], cmp: (K,K) => Int, key: K): IOption[V] = ???
    
  /** Problem 1-2: Insert in BST
   */
  def insert[K,V](t: BST[K,V], cmp: (K,K) => Int, key: K, value: V): BST[K,V] = ???

  /** Problem 2: Expression Optimization
    * 
    * Write an optimization function for arithmetic expressions represented by the `Expr` enum.
    * The goal is to simplify expressions by applying algebraic rules 
    * and performing constant folding where applicable.
    * 
    * You need to write the function `optimize` which simplifies an expression based on the following rules:
    * 
    * 1. **Constant Folding**: If both operands of an operation are numbers, evaluate the operation.
    *    - For example: `Add(Num(2), Num(3))` becomes `Num(5)`.
    * 
    * 2. **Simplification Rules**:
    *    - `x + 0 = x`, `0 + x = x`
    *    - `x - 0 = x`
    *    - `x * 1 = x`, `1 * x = x`
    *    - `x * 0 = 0`, `0 * x = 0`
    *    - `x / 1 = x`
    *    - Division by zero should raise an error.
    * 
    * Example:
    * Consider the expression: `Add(Num(0), Mul(Num(1), Var("x")))`
    * 
    * The `optimize` function should simplify this expression to just `Var("x")` since:
    * - `0 + x` simplifies to `x`
    * - `1 * x` simplifies to `x`
    * 
    * Additional Instructions:
    * - You must handle all cases defined in the `Expr` enum.
    * - Catch and handle division by zero cases appropriately. (See test cases for hint.)
    */
  def optimize(expr: Expr): Expr = ???
  
