# Principles of Programming: Assignment 01

## Objective

Implement the functions in `src/main/scala/Main.scala`, which are currently left blank as `???`

**WARNING: For the pedagological purpose, we highly recommend to follow the restrctions below.**

- Do not use the keyword `var`. Use `val` and `def` instead.
- Do not use any library functions or data structures like `List`, `Array`, `Range` (`1 to n`, `1 until n` ...), `fold`, `map`, `reduce` or etc.
- You can only use tuples, `scala.annotation.tailrec`, and `scala.util.control.TailCalls._` from the library.
- Do not use any looping syntax of Scala (`for`, `while`, `do-while`, `yield`, ...)

Note that these rules will be gradually relaxed through the next
assignments.

For problems except problem 1-1, some test cases will require optimizations (i.e., large inputs) including tail call optimizations and the others will not (i.e., small inputs).

See /src/test/scala/TestSuite.scala for some test cases (grading will be done based on more testcases). 

## How to test your code

```bash
sbt test # compile and run TestSuite.scala
```

If `sbt test` does not work, then your code is wrong.

Fix your code until it works, or send a question to the TA.
