package pp202402.assign4b

import scala.annotation.tailrec
import scala.util.control.TailCalls._

/** Principles of Programming: Assignment 4B.
  *
  * Implement given functions, which are currently left blank. (???) **WARNING:
  * Please read the restrictions below carefully.**
  *
  * If you do not follow these, **your submission will not be graded.**
  *
  *   - Do not use the keyword `var`. Use `val` and `def` instead.
  *   - Do not use any library functions or data structures like `LazyListist`,
  *     `Array`, `Range` (`1 to n`, `1 until n` ...), `fold`, `map`, `reduce` or
  *     etc.
  *   - If you want to use a data structure, create new one instead of using the
  *     library ones.
  *   - You can only use tuples, `scala.annotation.tailrec`, and
  *     `scala.util.control.TailCalls._` from the library.
  *   - Do not use any looping syntax of Scala (`for`, `while`, `do-while`,
  *     `yield`, ..
  *
  * Again, your score will be zero if you do not follow these rules.
  *
  * Note that these rules will be gradually relaxed through the next
  * assignments.
  *
  * We do not require tail-recursion explicitly for this assignment.
  *
  * Timeout: 30 sec.
  */
object Assignment4B:
  import Data.*
  import TypeClass.*

  /** Implement a priority task queue.
    *
    * A priority tasks queue is a data structure that stores tasks and processes them in a
    * specific order. You are going to implement a priority tasks queue that
    * processes tasks based on the priority (`Urgent` or `Normal`) of their
    * internal tasks.
    * 
    * Note: We use a term `Task` to represent a typeclass that a task queue can process.
    * We use a term `Item` to represent a typeclass that a task can contain.
    * Terms may be confusing, so please check `Data.scala` for their exact
    * definitions.
    * 
    * A priority of tasks is determined by the priority of its internal item
    * that should be executed at the moment. Thus, the priority of tasks can 
    * change over time.
    * 
    * The queue should process tasks in the following manner:
    *     
    * - Tasks should be enqueued with the `addTask` method. 
    *   If a given task is urgent, it should be enqueued to the urgent priority queue. 
    *   If a given task is normal, it should be enqueued to the normal priority queue. 
    *   If a given task does not have a priority, it should not be enqueued.
    * 
    * - Tasks should be dequeued and enqueued again when necessary with the `processTask` method.
    *   
    *   Processing a task regarding its priority:
    *   - All the existing urgent priority tasks should be processed before
    *     normal priority tasks.
    *   - Among the urgent priority tasks, a task that is added first
    *     should be processed first.
    *   - If there are no urgent priority a task, normal priority a task should be
    *     processed.
    *   - Among the normal priority a task, a task that are added
    *     first should be processed first.
    *   Returning a log from the task:
    *   - You should return a log that represents an item that a task should process at the moment. (**THIS IS USED FOR GRADING**)
    *     Do the logging, then make a process.
    *   - If there are no a task to process when `processTask` is called, return `EMPTY_LOG` defined in `Data.scala`.
    *   Re-enqueueing a task:
    *   - If a task is not completed, meaning, if a new task given after processing the task has a priority,
    *     it should be enqueued again to the corresponding priority queue.
    * 
    * Auxiliary: We are making assumptions that all the tasks are "cooperative". 
    *            That is, they will tell the queue their priority when they are asked to.
    */

  // TODO: Implement `addTask`.
  def addTask[Q, I, A](uQ: Q, nQ: Q, task: I)
    (implicit QUEUE: Queue[Q, I], TASK: Task[I, A]): (Q, Q) = ???
    
  // TODO: Implement `processTask`.
  def processTask[Q, I, A](uQ: Q, nQ: Q)
    (implicit QUEUE: Queue[Q, I], TASK: Task[I, A]): (String, Q, Q) = ???
    