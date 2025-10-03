// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
import pp202502.assign1.Assignment1.*

class TestSuite extends munit.FunSuite {
  test("problem 1-1") {
    assertEquals(iteration(_ + 1, 3, 0), 3L)
    assertEquals(iteration(_ + 3, 10, 7), 37L)
    assertEquals(iteration(_ * 2, 20, 1), 1048576L)
    assertEquals(iteration(
      (x) => if x%2==0 then 4*x+1 else 2*(x/7),
      50, 
      8), 8L)
  }

  test("problem 1-2") {
    val f: Long => Long = (x) => 
      if x%3==0 then x/3+1
      else if x%2==0 then x/2+4
      else 2*x
    // 2, 5, 10, 9, 4, 6, 3, 2, ...
    assertEquals(iterationTail(f, 1000000000, 1), 6L) 
    assertEquals(iterationTail(f, 2000000000, 1), 4L) 
  }

  test("problem 2") {
    assertEquals(combination(0, 0), 1L)
    assertEquals(combination(1, 0), 1L)
    assertEquals(combination(2, 1), 2L)
    assertEquals(combination(3, 1), 3L)
    assertEquals(combination(4, 0), 1L)
    assertEquals(combination(7, 3), 35L)
    assertEquals(combination(5, 2), 10L)
    assertEquals(combination(7, 3), 35L)
    assertEquals(combination(10, 5), 252L)
    assertEquals(combination(59, 21), 5189902721473470L)
    assertEquals(combination(80, 18), 355214207837288800L)
    assertEquals(combination(141, 129), 79660372599980445L)
  }

  test("problem 3-1") {
    assert(isPrime(37))
    assert(isPrime(121) == false)
    assert(isPrime(937))
    assert(isPrime(4657))
    assert(isPrime(8077) == false)
    assert(isPrime(643264199L))
    assert(isPrime(6742446423643264193L)) 
    assert(isPrime(6742446423643264199L) == false)
    assert(isPrime(9142446473643264109L))
  }

  test("problem 3-2") {
    assertEquals(nthTwinPrime(1), 3L)
    assertEquals(nthTwinPrime(5), 29L)
    assertEquals(nthTwinPrime(100), 3821L)
    assertEquals(nthTwinPrime(80000), 14242997L) 
    assertEquals(nthTwinPrime(100000), 18409199L) 
    assertEquals(nthTwinPrime(200000), 40756211L) 
  }
  
  test("problem 3-3") {
    assertEquals(primePair(4), (2L,2L))
    assertEquals(primePair(9), (2L,7L))
    assertEquals(primePair(14), (3L,11L))
    assertEquals(primePair(58), (5L,53L))
    assertEquals(primePair(5742443423643264230L), (31L,5742443423643264199L)) 
    assertEquals(primePair(6742446423643264230L), (37L,6742446423643264193L)) 
    assertEquals(primePair(6742446423653264230L), (41L,6742446423653264189L)) 
    assertEquals(primePair(9142446473643264182L), (73L,9142446473643264109L)) 
    assertEquals(try {primePair(57)} catch {
      case e : NoExistException => e.arg
    }, (57L))
    assertEquals(try {primePair(3127)} catch {
      case e : NoExistException => e.arg
    }, (3127L))
  }

}
