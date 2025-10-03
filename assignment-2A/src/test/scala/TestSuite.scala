// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
import scala.annotation.tailrec

import pp202502.assign2a.Assignment2A.{
  lookup, insert, 
  optimize
}
import pp202502.assign2a.{IOption, BST, Expr}

class TestSuite extends munit.FunSuite {
  import IOption.*
  import BST.*
  import Expr.*

  def fromList[K,V](x: List[(K,V)], cmp: (K,K) => Int): BST[K,V] = 
      x match
        case head :: next => 
          insert(fromList(next, cmp), cmp, head._1, head._2)
        case Nil => Leaf
  def testBST[K,V](t: List[(K,V)], cmp: (K,K) => Int, x: K, r: IOption[V]) = 
    assertEquals(lookup(fromList(t, cmp), cmp, x), r)

  test("problem 1 - Int") {
    def cmpInt(x: Int, y: Int) = x - y
    def fromListInt[K,V] = (x) => fromList[Int,V](x, cmpInt)
    def testBSTInt[K,V] = (t,x,r) => testBST[Int,V](t, cmpInt, x, r)
      
    assertEquals(
      lookup(
        Node(2, "a", 
          Leaf, 
          Node(3, "b", Leaf, Leaf)), 
        cmpInt, 
        3
      ), 
      ISome("b"))
    assertEquals(
      fromListInt(List((6,"a"), (2,"b"), (4,"c"))), 
      Node(4, "c", 
          Node(2, "b", Leaf, Leaf), 
          Node(6, "a", Leaf, Leaf))
    )
    val t1 = List((1,"a"), (4,"b"), (2,"c"), (8,"d"), (5,"e"), (7,"f"))
    testBSTInt(t1, 3, INone)
    testBSTInt(t1, 8, ISome("d"))
  }

  test("problem 1 - String") {
    def cmpStr(x: String, y: String) = x.compareTo(y)
    def fromListStr[K,V] = (x) => fromList[String,V](x, cmpStr)
    def testBSTStr[K,V] = (t,x,r) => testBST[String,V](t, cmpStr, x, r)
      
    assertEquals(
      lookup(
        Node("ab", 3, 
          Leaf, 
          Node("abcd", 7, Leaf, Leaf)), 
        cmpStr, 
        "abcd"
      ), 
      ISome(7))
    assertEquals(
      fromListStr(List(("asdf",1), ("a",2), ("ad",3))), 
      Node("ad", 3, 
          Node("a", 2, Leaf, Leaf), 
          Node("asdf", 1, Leaf, Leaf))
    )
    val t1 = List(("a",1), ("bad",2), ("af",3), ("czq",4), ("bes",5), ("cs",6))
    testBSTStr(t1, "nop", INone)
    testBSTStr(t1, "czq", ISome(4))
  }

  test("problem 2 - constant folding") {
    val expr1 = Add(Num(2), Num(3))
    val optimizedExpr1 = optimize(expr1)
    assert(optimizedExpr1 == Num(5)) // 2 + 3 => 5
  
    val expr2 = Mul(Num(2), Num(3))
    val optimizedExpr2 = optimize(expr2)
    assert(optimizedExpr2 == Num(6)) // 2 * 3 => 6
  
    val expr3 = Div(Num(6), Num(3))
    val optimizedExpr3 = optimize(expr3)
    assert(optimizedExpr3 == Num(2)) // 6 / 3 => 2
  
    val expr4 = Sub(Num(6), Num(3))
    val optimizedExpr4 = optimize(expr4)
    assert(optimizedExpr4 == Num(3)) // 6 - 3 => 3

    val expr5 = Div(Num(2), Add(Num(3), Num(4)))
    val optimizedExpr5 = optimize(expr5) 
    assert(optimizedExpr5 == Num(0)) // 2 / (3 + 4) => 0
  }

  test("problem 2 - simplification") {
    val expr1 = Add(Var("x"), Num(0))
    val optimizedExpr1 = optimize(expr1)
    assertEquals(optimizedExpr1, Var("x")) // x + 0 => x
  
    val expr2 = Add(Num(0), Var("x"))
    val optimizedExpr2 = optimize(expr2)
    assertEquals(optimizedExpr2, Var("x")) // 0 + x => x
  
    val expr3 = Mul(Var("x"), Num(1))
    val optimizedExpr3 = optimize(expr3)
    assertEquals(optimizedExpr3, Var("x")) // x * 1 => x
  
    val expr4 = Mul(Num(1), Var("x"))
    val optimizedExpr4 = optimize(expr4)
    assertEquals(optimizedExpr4, Var("x")) // 1 * x => x
  
    val expr5 = Mul(Var("x"), Num(0))
    val optimizedExpr5 = optimize(expr5)
    assertEquals(optimizedExpr5, Num(0)) // x * 0 => 0
  
    val expr6 = Div(Var("x"), Num(1))
    val optimizedExpr6 = optimize(expr6)
    assertEquals(optimizedExpr6, Var("x")) // x / 1 => x
  }

  test("problem 2 - complex expression optimization") {
    // (0 + (1 * x)) => x
    val expr1 = Add(Num(0), Mul(Num(1), Var("x")))
    assertEquals(optimize(expr1), Var("x"))

    // (x * 0) + (3 + (5 * 0)) => 3
    val expr2 = Add(Mul(Var("x"), Num(0)), Add(Num(3), Mul(Num(5), Num(0))))
    assertEquals(optimize(expr2), Num(3))

    // (2 + (3 * 1)) - (4 * 0) => 5
    val expr3 = Sub(Add(Num(2), Mul(Num(3), Num(1))), Mul(Num(4), Num(0)))
    assertEquals(optimize(expr3), Num(5))

    // ((1 * x) + (3 - 3)) * (y / 1) => x * y
    val expr4 = Mul(Add(Mul(Num(1), Var("x")), Sub(Num(3), Num(3))), Div(Var("y"), Num(1)))
    assertEquals(optimize(expr4), Mul(Var("x"), Var("y")))

    // (5 + 3) / (1 * (2 + 2)) => 2
    val expr5 = Div(Add(Num(5), Num(3)), Mul(Num(1), Add(Num(2), Num(2))))
    assertEquals(optimize(expr5), Num(2))

    // ((0 * x) + (5 - 3)) * (0 + 2) => 4
    val expr6 = Mul(Add(Mul(Num(0), Var("x")), Sub(Num(5), Num(3))), Add(Num(0), Num(2)))
    assertEquals(optimize(expr6), Num(4))

    // Complex expression with division: (1 * (x + 0)) / (y * (3 - 3)) => Division by zero exception
    val expr7 = Div(Mul(Num(1), Add(Var("x"), Num(0))), Mul(Var("y"), Sub(Num(3), Num(3))))
    intercept[ArithmeticException] {
      optimize(expr7)
    }
  }

}