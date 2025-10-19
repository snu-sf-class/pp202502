import scala.annotation.tailrec

import pp202402.midterm1.Midterm1.{
  foldList, concat, 
  foldBinTree, isInBinTree
}
import pp202402.midterm1.{IList, BinTree}

class TestSuite extends munit.FunSuite {
  import IList.*
  import BinTree.* 

  test("problem 1-1") {
    assertEquals(foldList[Int,Int](0, _+_)(ICons(1, ICons(2, ICons(3, ICons(4, ICons(5, ICons(6, INil))))))), 21)
    assertEquals(foldList[Int,Int](1, _*_)(ICons(1, ICons(2, ICons(3, ICons(4, ICons(5, ICons(6, INil))))))), 720)
    assertEquals(
      concat(
        ICons(1, ICons(3, ICons(5, INil))), 
        ICons(2, ICons(4, ICons(6, INil)))
      ), 
      ICons(1, ICons(3, ICons(5, ICons(2, ICons(4, ICons(6, INil)))))))
  }

  test("problem 1-2") {
    assertEquals(foldBinTree[Int,Int](10, _+_*_)(Node(4, Node(3, Node(2, Leaf, Leaf), Leaf), Node(1, Leaf, Leaf))), 103327)
    assert(isInBinTree(2, Node(4, Node(3, Node(2, Leaf, Leaf), Leaf), Node(1, Leaf, Leaf))))
    assert(isInBinTree(5, Node(4, Node(3, Node(2, Leaf, Leaf), Leaf), Node(1, Leaf, Leaf))) == false)
  }
}