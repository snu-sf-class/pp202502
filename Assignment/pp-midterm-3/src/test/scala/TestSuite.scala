import scala.annotation.tailrec

import pp202402.midterm3.Midterm3.{
  addInt, subInt, mulInt, divInt, 
  addDouble, subDouble, mulDouble, divDouble, 
  addRational, subRational, mulRational, divRational,
  eval,
}
import pp202402.midterm3.{
  DividedByZeroException, NotAnIntegerException, 
  Rational, Result, Error, Expr
}

class TestSuite extends munit.FunSuite {
  import Result.*
  import Error.*
  import Expr.*

  test("problem 3-1") {
    assertEquals(addInt(3, 5), 8)
    assertEquals(subInt(2, 7), -5)
    assertEquals(mulInt(-2, 7), -14)
    assertEquals(divInt(28, 7), 4)
    intercept[DividedByZeroException] {divInt(5,0)}
    intercept[NotAnIntegerException] {divInt(5,3)}
  }
  
  test("problem 3-2") {
    assertEquals(addDouble(3.2, 5.0), 8.2)
    assertEquals(subDouble(2.0, 7.6), -5.6)
    assertEquals(mulDouble(-2.5, 7.0), -17.5)
    assertEquals(divDouble(28, 3.5), 8.0)
    intercept[DividedByZeroException] {divDouble(5.0,0)}
  }

  test("problem 3-3") {
    assertEquals(addRational((2,3), (1,2)), (7,6))
    assertEquals(subRational((1,4), (1,2)), (-1,4))
    assertEquals(mulRational((7,9), (3,4)), (7,12))
    assertEquals(divRational((7,9), (4,3)), (7,12))
    intercept[DividedByZeroException] {divRational((6,7), (0,1))}
    intercept[DividedByZeroException] {mulRational((6,7), (1,0))}
  }

  test("problem 3-4 Int") {
    def evalInt(e: Expr[Int]): Result[Int] = 
      eval[Int](e, addInt, subInt, mulInt, divInt)
    def testInt(e: Expr[Int], r: Result[Int]) = 
      assertEquals(evalInt(e), r)
    testInt(
      Add(Num(5), Div(Num(3), Sub(Num(3), Num(2)))), 
      Success(8)
    )
    testInt(
      Add(Num(5), Div(Num(3), Sub(Num(2), Num(2)))), 
      Failure(DividedByZero)
    )
    testInt(
      Add(Num(5), Div(Num(3), Sub(Num(2), Num(0)))), 
      Failure(NotAnInteger)
    )
  }

  test("problem 3-4 Double") {
    def evalDouble(e: Expr[Double]): Result[Double] = 
      eval[Double](e, addDouble, subDouble, mulDouble, divDouble)
    def testDouble(e: Expr[Double], r: Result[Double]) = 
      assertEquals(evalDouble(e), r)
    testDouble(
      Sub(Num(5.0), Div(Num(28.0), Sub(Num(5.5), Num(2)))), 
      Success(-3.0)
    )
    testDouble(
      Add(Num(5.0), Div(Num(1.0), Sub(Num(2.0), Num(2.0)))), 
      Failure(DividedByZero)
    )
  }

  test("problem 3-4 Rational") {
    def evalRational(e: Expr[Rational]): Result[Rational] = 
      eval[Rational](e, addRational, subRational, mulRational, divRational)
    def testRational(e: Expr[Rational], r: Result[Rational]) = 
      assertEquals(evalRational(e), r)
    testRational(
      Sub(Num((1,6)), Div(Num((1,6)), Sub(Num((1,1)), Num((1,4))))), 
      Success((-1,18))
    )
    testRational(
      Sub(Num((2,3)), Div(Num((5,1)), Sub(Num((3,2)), Num((6,4))))), 
      Failure(DividedByZero)
    )
  }
}