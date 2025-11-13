// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
import scala.annotation.tailrec
import scala.math._

import pp202402.assign4b.Assignment4B._

class TestSuite extends munit.FunSuite {
  import pp202402.assign4b.Data._
  import pp202402.assign4b.TypeClass._
  import pp202402.assign4b.Examples.given
  import pp202402.assign4b.Examples._

  test("a small task set") {
    // Create an empty queue
    val uQ = empty[IList[Int], Int]
    val nQ = empty[IList[Int], Int]

    // Create a task set
    val task1 = 3
    val task2 = 2
    val task3 = 1
    val task4 = 0

    // Add tasks
    val (uQ0, nQ0) = addTask(uQ, nQ, task1)
    val (uQ1, nQ1) = addTask(uQ0, nQ0, task2)
    val (uQ2, nQ2) = addTask(uQ1, nQ1, task3)
    val (uQ3, nQ3) = addTask(uQ2, nQ2, task4)
    
    assertEquals(uQ3, IList.ICons(3, IList.ICons(1, IList.INil)))
    assertEquals(nQ3, IList.ICons(2, IList.INil))
    assertEquals(uQ2, uQ3)
    assertEquals(nQ2, nQ3)

    // Process tasks
    val (log1, uQ4, nQ4) = processTask(uQ3, nQ3)
    assertEquals(log1, "Int: #3")
    assertEquals(uQ4, IList.ICons(1, IList.INil))
    assertEquals(nQ4, IList.ICons(2, IList.ICons(2, IList.INil)))
    
    val (log2, uQ5, nQ5) = processTask(uQ4, nQ4)
    assertEquals(log2, "Int: #1")
    assertEquals(uQ5, IList.INil)
    assertEquals(nQ5, IList.ICons(2, IList.ICons(2, IList.INil)))

    val (log3, uQ6, nQ6) = processTask(uQ5, nQ5)
    assertEquals(log3, "Int: #2")
    assertEquals(uQ6, IList.ICons(1, IList.INil))
    assertEquals(nQ6, IList.ICons(2, IList.INil))

    val (log4, uQ7, nQ7) = processTask(uQ6, nQ6)
    assertEquals(log4, "Int: #1")
    assertEquals(uQ7, IList.INil)
    assertEquals(nQ7, IList.ICons(2, IList.INil))

    val (log5, uQ8, nQ8) = processTask(uQ7, nQ7)
    assertEquals(log5, "Int: #2")
    assertEquals(uQ8, IList.ICons(1, IList.INil))
    assertEquals(nQ8, IList.INil)

    val (log6, uQ9, nQ9) = processTask(uQ8, nQ8)
    assertEquals(log6, "Int: #1")
    assertEquals(uQ9, IList.INil)
    assertEquals(nQ9, IList.INil)

    assertEquals(processTask(uQ9, nQ9), (EMPTY_LOG, uQ9, nQ9))
  }

  // NOTE: during the actual grading, we will use the similar size of the task set as below.
  // However, we may use tasks with different types with different `calculatePriority` method.
  test("a large task set") {
    val (uQ, nQ) = (1 to 100).toList.foldLeft((empty[IList[Int], Int], empty[IList[Int], Int])) {
      case ((uQ, nQ), task) => addTask(uQ, nQ, task)
    }

    var (uQ1, nQ1) = (uQ, nQ)
    for (i <- 1 to 100 by 2) {
      val (log, tempUQ, tempNQ) = processTask(uQ1, nQ1)
      uQ1 = tempUQ
      nQ1 = tempNQ
      assertEquals(log, "Int: #%d".format(i))
    }
    assertEquals(uQ1, IList.INil)

    for (i <- 0 to 100) {
      for (j <- 2 to 100-i by 2) {
        val (log1, tempUQ1, tempNQ1) = processTask(uQ1, nQ1)
        uQ1 = tempUQ1
        nQ1 = tempNQ1
        assertEquals(log1, "Int: #%d".format(j))

        val (log2, tempUQ2, tempNQ2) = processTask(uQ1, nQ1)
        uQ1 = tempUQ2
        nQ1 = tempNQ2
        assertEquals(log2, "Int: #%d".format(j-1))
        assertEquals(uQ1, IList.INil)
      }
    }

    assertEquals(processTask(uQ1, nQ1), (EMPTY_LOG, uQ1, nQ1))
  }

  test("a multi-item task set") {
    val task = IList.ICons(3, IList.ICons(2, IList.ICons(1, IList.INil)))
    val (uQ, nQ) = addTask(empty[IList[IList[Int]], IList[Int]], empty[IList[IList[Int]], IList[Int]], task)

    val (log1, uQ1, nQ1) = processTask(uQ, nQ)
    assertEquals(log1, "List: #3")
    assertEquals(uQ1, IList.INil)
    assertEquals(nQ1, IList.ICons(IList.ICons(2, IList.ICons(2, IList.ICons(1, IList.INil))), IList.INil))
    
    val (log2, uQ2, nQ2) = processTask(uQ1, nQ1)
    assertEquals(log2, "List: #2")
    assertEquals(uQ2, IList.ICons(IList.ICons(1, IList.ICons(2, IList.ICons(1, IList.INil))), IList.INil))
    assertEquals(nQ2, IList.INil)

    val (log3, uQ3, nQ3) = processTask(uQ2, nQ2)
    assertEquals(log3, "List: #1")
    assertEquals(uQ3, IList.INil)
    assertEquals(nQ3, IList.ICons(IList.ICons(2, IList.ICons(1, IList.INil)), IList.INil))

    val (log4, uQ4, nQ4) = processTask(uQ3, nQ3)
    assertEquals(log4, "List: #2")
    assertEquals(uQ4, IList.ICons(IList.ICons(1, IList.ICons(1, IList.INil)), IList.INil))
    assertEquals(nQ4, IList.INil)

    val (log5, uQ5, nQ5) = processTask(uQ4, nQ4)
    assertEquals(log5, "List: #1")
    assertEquals(uQ5, IList.ICons(IList.ICons(1, IList.INil), IList.INil))
    assertEquals(nQ5, IList.INil)

    val (log6, uQ6, nQ6) = processTask(uQ5, nQ5)
    assertEquals(log6, "List: #1")
    assertEquals(uQ6, IList.INil)
    assertEquals(nQ6, IList.INil)

    assertEquals(processTask(uQ6, nQ6), (EMPTY_LOG, uQ6, nQ6))
  }
}
