/// =========================================
/// ===== !!!DO NOT FIX THIS FILE!!! ========
/// =========================================
/// If you fix this file, your code will not be compiled.

package pp202402.assign4a

object Data {  
  trait Iter[I[_],A]: 
    def get[A] (i: I[A]): Option[(A,I[A])]
  
  trait ListIF[L[_],A]: 
    def empty[A]: L[A]
    def cons[A] (elem: A, l: => L[A]): L[A]
    def iter[A]: Iter[L,A]
    def head[A] (l: L[A]): Option[A]
    def tail[A] (l: L[A]): L[A]

  // List
  implicit def listIter[A]: Iter[List,A] = new {
    def get[A] (l: List[A]) = 
      l match {
        case Nil => None
        case hd::tl => Some(hd,tl)
      }
  }

  implicit def listListIF[A]: ListIF[List,A] = new {
    def empty[A] = Nil
    def cons[A] (a: A, l: =>List[A]) = a::l
    def iter[A] = listIter[A]
    def head[A] (l: List[A]) = l.headOption
    def tail[A] (l: List[A]) = l.tail
  }
}