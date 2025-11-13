package pp202402.assign3a

import scala.annotation.tailrec
import scala.util.control.TailCalls._

/** Principles of Programming: Assignment 3A.
  *
  * Implement given functions, which are currently left blank. (???) **WARNING:
  * Please read the restrictions below carefully.**
  *
  * If you do not follow these, **your submission will not be graded.**
  *
  *   - Do not use the keyword `var`. Use `val` and `def` instead.
  *   - Do not use any library functions or data structures like `LazyListist`,
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
object Assignment3A:
  import Data.*
  /*
   * Problem 1
   *
   * List decimal fractional parts of sqrt(n), which means nonnegative k such that k^2 = n. Digits should be truncated by `len`.
   * 
   * Input will be given as LazyList. For simplicity, we restrict the input with 0.0 <= input < 10.0
   * 
   * You don't need to worry about overflow in Long Type.
   *
   * e.g. sqrt(2) = 1.41421...
   *      decSqrt([2], 5) = [1, 4, 1, 4, 2, 1]
   * 
   *      sqrt(0.7) = 0.83666...
   *      decSqrt([0, 7], 5) = [0, 8, 3, 6, 6, 6]
   * 
   *      sqrt(1.44) = 1.2
   *      decSqrt([1, 4, 4], 5) = [1, 2]
   * 
   * Appending extra zeros at the last part of the answer is allowed. 
   * For example, [1, 2], [1, 2, 0] and [1, 2, 0, 0] will be regarded as same answer.
   *
   * See https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Digit-by-digit_calculation for the algorithm.
   * 
   * e.g.
   *    for n = 2, 
   *    start 1st iteration with p = 0
   *    greatest integer x s.t. y = x*(20*p+x)<= 2 is 1 
   * 
   *    now 100 * (2-y) = 100 and new p = 1
   *    greatest integer x s.t. y = x*(20*p+x)<= 100 is 4 
   * 
   *    now 100 * (100-y) = 400 and new p = 14
   *    greatest integer x s.t. y = x*(20*p+x)<= 400 is 1 
   * 
   *    now 100 * (400-y) = 12000 and new p = 141
   *    greatest integer x s.t. y = x*(20*p+x)<= 12000 is 4 
   * 
   *    now 100 * (12000-y) = 720 and new p = 1414
   *    ...
   * 
   * e.g.
   *    for n = 1.44, 
   *    start 1st iteration with p = 0
   *    greatest integer x s.t. y = x*(20*p+x)<= 1 is 1 
   * 
   *    now 100 * (1-y) + 44 = 44 and new p = 1
   *    greatest integer x s.t. y = x*(20*p+x)<= 44 is 2
   * 
   *    now 100 * (44-y) = 0, so the iteration ends with new p = 12.
   *
   */

  def decSqrt(n: LazyList[Int], len: Int): LazyList[Int] = ???
