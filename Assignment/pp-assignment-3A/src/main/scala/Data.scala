/// =========================================
/// ===== !!!DO NOT FIX THIS FILE!!! ========
/// =========================================
/// If you fix this file, your code will not be compiled.

package pp202402.assign3a

object Data {
  
  sealed abstract class LazyList[A] {
    def get : Option[(A,LazyList[A])]
    def append(lst: LazyList[A]) : LazyList[A]
  }
  class LNil[A]() extends LazyList[A] {
    def get = None
    def append(lst: LazyList[A]) =  lst
  }
  class LCons[A](hd: A, _tl: => LazyList[A]) extends LazyList[A] {
    private lazy val tl = _tl
    def get = Some(hd,tl)
    def append(lst: LazyList[A]) = LCons(hd, tl.append(lst))
  }

}
