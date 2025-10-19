import scala.annotation.tailrec

import pp202402.midterm2.Midterm2.{
  reverseTail, foldListTail
}
import pp202402.midterm2.{IList}

class TestSuite extends munit.FunSuite {
  import IList.*

  test("problem 2-1") {
    def toIList[A](x: List[A]): IList[A] = 
      x match
        case Nil => INil
        case h::t => ICons(h, toIList(t))
    def reverseTest[A](x: List[A], y: List[A]) = 
      assertEquals(reverseTail(toIList(x)), toIList(y))
    // Function toIlist does not use tail recursion : 
    // reverseTest may cause stack overflow if input is large
    // even if reverseTail uses tail recursion properly.
    reverseTest(List(1,7,4,2,4,2,0,1), List(1,0,2,4,2,4,7,1))
    reverseTest(List(36,23,23,1,3,6,4,100), List(100,4,6,3,1,23,23,36))
  }

  test("problem 2-2") {
    assertEquals(foldListTail[Int,Int](0, _+_)(ICons(1, ICons(2, ICons(3, ICons(4, ICons(5, ICons(6, INil))))))), 21)
    assertEquals(foldListTail[Int,Int](1, _*_)(ICons(1, ICons(2, ICons(3, ICons(4, ICons(5, ICons(6, INil))))))), 720)
  }
}