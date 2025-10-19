package pp202402.midterm1

import scala.annotation.tailrec
import scala.util.control.TailCalls._

/** Principles of Programming: Midterm problem 1.
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
  *     `scala.util.control.TailCalls._`, `Math._` from the library.
  *   - Do not use any looping syntax of Scala (`for`, `while`, `do-while`,
  *     `yield`, ...)
  *
  * Again, your score will be zero if you do not follow these rules.
  *
  * assignments.
  *
  * We do not require tail-recursion explicitly for the midterm except problem 2.
  *
  * Timeout: 30 sec.
  */
object Midterm1:
  import IList.*
  import BinTree.*

  /** Problem 1: Fold (30 Points)
    * 
    * For algebraic datatypes (expecially recursive/inductive ones), 
    * we can define 'fold' function and use it to define recursive function easily. 
    * 
    * Fold function receives functions (or initial values) as a rule to compute each constructors. 
    * 
    * Following subproblems will help you understand what it means. 
    */ 
    
  /** Problem 1-1: List Fold (10 Points)
    * 
    * Implement a function foldList, a fold function for type IList (in Data.scala). 
    * 
    * It's input is (x: B, f: (A,B) => B) and it's output is a function of type IList[A] => B. 
    * 
    * Initial value x means that we want to compute INil into x, 
    * and binary function f means that we want to compute ICons(h, t) into f(h, foldList(x,f)(t)). 
    * 
    * For example, let g be foldList(0, _+_). 
    * Then g(ICons(a, ICons(b, ICons(c, INil)))) = a + g(ICons(b, ICons(c, INil))) 
    * = a + b + g(ICons(c, INil)) = a + b + c + g(Inil) = a + b + c + 0 = a + b + c
    * 
    * After that, implement a function concat that concatenates two list 
    * (put second list at the end of the first list), using foldList.
    * 
    * Hint: It might be helpful to specify implicit type arguments A,B when you use polymorphic function.
    *   See TestSuite.scala file for examples. 
    * ex: ... = foldList[A,B](...)
    */ 
  def foldList[A,B](x: B, f: (A,B) => B): IList[A] => B = ???
  
  def concat[A](l1: IList[A], l2: IList[A]): IList[A] = ???

  /** Problem 1-2: Binary Tree Fold (20 Points)
    * 
    * Implement a function foldBinTree, a fold function for type Bintree (in Data.scala). 
    * 
    * It's input is (x: B, f: (A,B,B) => B) and it's output is a function of type BinTree[A] => B. 
    * 
    * Initial value x means that we want to compute Leaf into x, 
    * and binary function f means that we want to compute Node(v,l,r) into 
    * f(v, foldBinTree(x,f)(l), foldBinTree(x,f)(r)). 
    * 
    * For example, let g be foldBinTree(x, _+_*_) for integer x. 
    * Then g(Node(a, Node(b, Node(c, Leaf, Leaf), Leaf), Node(d, Leaf, Leaf))) 
    * = a + g(Node(b, Node(c, Leaf, Leaf), Leaf)) * g(Node(d, Leaf, Leaf))
    * = a + (b + g(Node(c, Leaf, Leaf)) * g(Leaf)) * (d + g(Leaf) * g(Leaf))
    * = a + (b + (c + g(Leaf) * g(Leaf)) * x) * (d + x^2)
    * = a + (b + (c + x^2) * x) * (d + x^2)
    * = x^5 + (c+d)*x^3 + b*x^2 + c*d*x + a+b*d
    * 
    * After that, implement a function isInBintree that checks whether the given value exists in the BinTree, using foldBinTree.
    */ 
  def foldBinTree[A,B] (x: B, f: (A,B,B) => B): BinTree[A] => B = ???

  def isInBinTree[A] (x: A, bt: BinTree[A]): Boolean = ???
  
