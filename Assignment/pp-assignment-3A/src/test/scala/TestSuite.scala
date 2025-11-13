// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
import scala.annotation.tailrec
import scala.math._

import pp202402.assign3a.Assignment3A.{
  decSqrt
}
import pp202402.assign3a.{
  Data
}

class TestSuite extends munit.FunSuite {
  import Data.*

  def zeros(n: Int): List[Int] = if n<=0 then Nil else 0 :: zeros(n-1)
  def getN[A](a: LazyList[Int], n: Int): List[Int] =
    if (n <= 0) Nil
    else {
      a.get match 
        case None => zeros(n)
        case Some(h,t) => h :: getN(t, n - 1)
      }

  test("problem 1") {
    assertEquals(getN(decSqrt(LCons(2, LNil()), 5), 7), List(1,4,1,4,2,1,0))
    assertEquals(getN(decSqrt(LCons(0, LCons(7, LNil())), 5), 7), List(0,8,3,6,6,6,0))
    assertEquals(getN(decSqrt(LCons(1, LCons(4, LCons(4, LNil()))), 5), 7), List(1,2,0,0,0,0,0))
  }

}
