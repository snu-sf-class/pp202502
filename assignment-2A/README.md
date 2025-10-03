# Principles of Programming Assignment 2A

## Objective

Implement the functions in `src/main/scala/Main.scala`, which are currently left blank as `???`

**WARNING: For the pedagological purpose, we highly recommend to follow the restrctions below.**

- **DO NOT FIX Data.Scala**
- Do not use the keyword `var`. Use `val` and `def` instead.
- Do not use any library functions or data structures like `List`, `Array`, `Range` (`1 to n`, `1 until n` ...), `fold`, `map`, `reduce` or etc.
- You can only use tuples, `scala.annotation.tailrec`, and `scala.util.control.TailCalls._` from the library.
- If you want to use a data structure, create new one instead of using the library ones.
- Do not use any looping syntax of Scala (`for`, `while`, `do-while`, `yield`, ...)

Note that these rules will be gradually relaxed through the next assignments.

We do not require tail-recursion explicitly for this assignment.

Timeout: 30 sec.

## How to test your code

```bash
sbt test # compile and run TestSuite.scala
```

If `sbt test` does not work, then your code is wrong.

Fix your code until it works, or send a question to the TA.

## How to Submit

- For all assignments, we will test your code only with the given `Main.scala` files in `src/main/scala` directory. **All the additional files you've created will be lost!!**
- We will check git logs of the main branch, and grade the latest commit before the due date.

```bash
git add src
git commit -m 'Write some meaningful message'
git push
```