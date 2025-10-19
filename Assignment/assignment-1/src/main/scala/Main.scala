package pp202502.assign1

import scala.annotation.tailrec
import scala.util.control.TailCalls._

/** Principles of Programming: Assignment 01.
  *
  * Implement the given functions, which are currently left blank. (???)
  *
  * **WARNING:Please read the restrictions below carefully.**
  * If you do not follow these, **your submission will not be graded.**
  *
  *   - Do not use the keyword `var`. Use `val` and `def` instead.
  *   - Do not use any library functions or data structures like `List`,
  *     `Array`, `Range` (`1 to n`, `1 until n`, ...), `fold`, `map`, `reduce` etc.
  *   - You may only use tuples, `scala.annotation.tailrec`,
  *     `scala.util.control.TailCalls._` from the library.
  *   - Do not use any looping syntax of Scala (`for`, `while`, `do-while`,
  *     `yield`, etc.).
  *
  * Again, your score will be zero if you do not follow these rules.
  *
  * Note that these rules will be gradually relaxed in future assignments.
  *
  * For problems except problem 1-1, some test cases will require 
  * optimizations (i.e., large inputs) including tail call optimizations 
  * and the others will not (i.e., small inputs).
  * 
  * So, you will get partial point if you submit a correct program
  * without proper optimizations.
  * 
  * See /src/test/scala/TestSuite.scala for some test cases (grading will be done based on more testcases). 
  */
object Assignment1:
  /** Problem 1: Iteration of a Function.
   */
  
  /** Problem 1-1.
    *
    * Given a function f and integers n, x,
    * compute f^n(x) as: if n>=1, then f^(n-1) (f(x)); otherwise x
    * without using tail recursion.
    * 
    * Here n is restricted by n<=50.
    */
  def iteration(f: Long => Long, n: Long, x: Long): Long = ???

  /** Problem 1-2.
    *
    * Given a function f and integers n, x,
    * compute f^n(x) as: if n>=1, then f^(n-1) (f(x)); otherwise x
    * using tail recursion.
    */
  def iterationTail(f: Long => Long, n: Long, x: Long): Long = ???
  
  /** Problem 2: Computing Combinations.
    *
    * Implement a function that computes the binomial coefficient.
    *
    * See https://en.wikipedia.org/wiki/Combination.
    * 
    * Some test caes will have large n and i, so naive implementation may cause timeout or overflow. 
    *
    * Hint 1: combination(n, i) = combination(n, n-i).
    * Hint 2: reordering multiplications and divisions can prevent overflow.
    */
  def combination(n: Long, i: Long): Long = ???

  /** Problem 3: Finding Prime Numbers.
    */

  /** Problem 3-1.
    *
    * Find out whether the given natural number is a prime number.
    */
  def isPrime(p: Long): Boolean = ???

  /** Problem 3-2.
    *
    * Given an integer n, find the n-th twin prime.
    *
    * That is, find the n-th smallest prime number p such that p+2 is also a prime number.
    *
    * For example, the 1st twin prime is 3 (since 5 is also a prime number) and
    * the 5th twin prime is 29 (since 31 is also a prime number).
    *
    * See the website below for more information.
    *   - https://en.wikipedia.org/wiki/Twin_prime
    */
  def isTwinPrime(p: Long): Boolean = ???

  def nthTwinPrime(n: Long): Long = ???

  /** Problem 3-3.
    *
    * Given an integer n, find two prime numbers whose sum is n.
    * If such numbers exist, return a pair of prime numbers (a, b) with the smallest a.
    * If no such numbers exist, throw an error: NoExistException(n)
    *
    * It is known that such prime numbers exist for all even number n where 2 < n <= 4 * 10^18.
    *
    * For example, 14 = 3 + 11, 58 = 11 + 37, and 158 = 31 + 127.
    *
    *
    * See the website below for more information.
    *   - https://en.wikipedia.org/wiki/Goldbach%27s_conjecture
    */
  class NoExistException(val arg: Long) extends Exception

  def primePair(n: Long): (Long, Long) = ???

