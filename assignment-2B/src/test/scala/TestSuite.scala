// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
import scala.annotation.tailrec

import pp202502.assign2b.Assignment2B.{
  solveSudoku
}
import pp202502.assign2b.{IOption, SudokuWfChecker}

class TestSuite extends munit.FunSuite {
  import IOption.*

  test("problem 3 - sudoku easy") {
    val board0 = Array(
      1, 2, 3, 4, 5, 6, 7, 8, 9,
      4, 5, 6, 7, 8, 9, 1, 2, 3,
      7, 8, 9, 1, 2, 3, 4, 5, 6,
      2, 3, 4, 5, 6, 7, 8, 9, 1,
      5, 6, 7, 8, 9, 1, 2, 3, 4,
      8, 9, 1, 2, 3, 4, 5, 6, 7,
      3, 4, 5, 6, 7, 8, 9, 1, 2,
      6, 7, 8, 9, 1, 2, 3, 4, 5,
      9, 1, 2, 3, 4, 5, 6, 7, 8,
    )
    val resultBoard0 = solveSudoku(board0)
    assert(SudokuWfChecker.isWellFormed(resultBoard0)
                && resultBoard0.isSome)

    val board1 = Array(
      0, 5, 9, 0, 0, 3, 8, 0, 7,
      0, 0, 1, 8, 0, 0, 0, 9, 3,
      7, 3, 0, 9, 0, 0, 4, 2, 1,
      0, 0, 0, 0, 3, 0, 0, 0, 6,
      9, 8, 0, 0, 0, 1, 2, 7, 4,
      6, 4, 0, 7, 0, 8, 0, 0, 5,
      0, 1, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 3, 2, 6, 7, 0, 0,
      8, 2, 6, 5, 0, 7, 3, 4, 9,
    )
    val resultBoard1 = solveSudoku(board1)
    assert(SudokuWfChecker.isWellFormed(resultBoard1)
                && resultBoard1.isSome)

    val board2 = Array(
      0, 8, 4, 5, 0, 3, 9, 0, 7,
      3, 0, 0, 0, 0, 0, 2, 0, 8,
      0, 2, 7, 9, 0, 0, 0, 4, 3,
      2, 1, 5, 3, 9, 0, 4, 0, 0,
      4, 6, 0, 2, 0, 0, 0, 0, 0,
      0, 0, 0, 6, 5, 0, 0, 1, 2,
      6, 3, 2, 0, 0, 0, 7, 0, 1,
      7, 4, 0, 1, 3, 9, 0, 2, 5,
      0, 0, 1, 0, 0, 0, 0, 0, 4,
    )
    val resultBoard2 = solveSudoku(board2)
    assert(SudokuWfChecker.isWellFormed(resultBoard2)
                && resultBoard2.isSome)

    val board3 = Array(
      2, 9, 7, 0, 0, 0, 5, 0, 0,
      0, 6, 0, 0, 5, 0, 3, 0, 9,
      3, 5, 8, 0, 7, 0, 2, 1, 0,
      0, 0, 6, 0, 4, 0, 9, 0, 2,
      0, 4, 5, 0, 2, 0, 0, 0, 0,
      0, 7, 2, 3, 0, 6, 0, 0, 0,
      6, 1, 4, 8, 0, 0, 0, 2, 0,
      5, 2, 0, 7, 0, 0, 8, 4, 6,
      0, 0, 3, 4, 6, 0, 1, 9, 0, 
    )
    val resultBoard3 = solveSudoku(board3)
    assert(SudokuWfChecker.isWellFormed(resultBoard3) 
                && resultBoard3.isSome)
  }

  test("problem 3 - sudoku medium") {
    val board1 = Array(
      0, 0, 0, 0, 1, 4, 0, 0, 0,
      1, 4, 9, 6, 5, 0, 0, 2, 7,
      0, 0, 0, 2, 7, 9, 0, 0, 4,
      0, 0, 0, 0, 9, 0, 0, 8, 5,
      0, 9, 0, 0, 0, 0, 0, 3, 0,
      0, 8, 7, 0, 0, 6, 9, 0, 0,
      0, 0, 3, 8, 0, 2, 0, 0, 9,
      0, 0, 0, 7, 3, 5, 0, 0, 8,
      0, 0, 0, 0, 0, 1, 0, 0, 0,
    )
    val resultBoard1 = solveSudoku(board1)
    assert(SudokuWfChecker.isWellFormed(resultBoard1) 
                && resultBoard1.isSome)

    val board2 = Array(
      5, 2, 3, 0, 4, 0, 0, 0, 0,
      0, 1, 0, 2, 0, 0, 3, 0, 0,
      9, 0, 6, 0, 0, 0, 0, 0, 0,
      1, 3, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 3, 7, 0, 0, 9, 0,
      0, 6, 9, 0, 0, 8, 4, 3, 1,
      0, 0, 1, 0, 5, 0, 7, 0, 0,
      3, 4, 0, 0, 1, 2, 0, 5, 0,
      0, 9, 0, 0, 8, 0, 0, 1, 0,
    )
    val resultBoard2 = solveSudoku(board2)
    assert(SudokuWfChecker.isWellFormed(resultBoard2)
                && resultBoard2.isSome)

    val board3 = Array(
      0, 0, 8, 0, 0, 0, 0, 9, 7,
      7, 2, 0, 0, 6, 0, 0, 0, 5,
      5, 6, 9, 0, 0, 0, 3, 0, 4,
      0, 7, 6, 9, 0, 0, 0, 0, 0,
      0, 8, 5, 0, 0, 0, 7, 6, 9,
      2, 0, 0, 0, 0, 7, 0, 5, 0,
      9, 0, 0, 0, 0, 6, 0, 7, 0,
      0, 0, 0, 0, 9, 1, 5, 0, 2,
      0, 0, 0, 0, 0, 0, 9, 0, 0,
    )
    val resultBoard3 = solveSudoku(board3)
    assert(SudokuWfChecker.isWellFormed(resultBoard3) 
                && resultBoard3.isSome)
  }

  test("problem 3 - sudoku hard") {
    val board1 = Array(
      0, 0, 0, 0, 2, 0, 5, 0, 0,
      0, 8, 1, 0, 0, 0, 0, 0, 7,
      0, 0, 0, 0, 9, 0, 0, 0, 6,
      0, 5, 0, 0, 0, 9, 0, 0, 2,
      0, 0, 0, 0, 7, 2, 0, 0, 0,
      0, 7, 0, 0, 0, 1, 0, 0, 0,
      0, 0, 0, 2, 0, 0, 0, 0, 0,
      0, 6, 0, 9, 0, 7, 0, 0, 5,
      7, 0, 0, 0, 0, 5, 0, 0, 0,
    )
    val resultBoard1 = solveSudoku(board1)
    assert(SudokuWfChecker.isWellFormed(resultBoard1) 
                && resultBoard1.isSome)

    val board2 = Array(
      0, 0, 0, 1, 0, 5, 7, 0, 6,
      0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 9, 0, 0, 5,
      0, 0, 0, 0, 0, 2, 9, 0, 3,
      0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 8, 0, 0, 0, 7, 0, 5, 0,
      6, 7, 0, 0, 0, 0, 0, 0, 0,
      8, 3, 0, 0, 7, 0, 5, 0, 0,
      0, 2, 0, 8, 0, 0, 1, 0, 0,
    )
    val resultBoard2 = solveSudoku(board2)
    assert(SudokuWfChecker.isWellFormed(resultBoard2)
                && resultBoard2.isSome)

    val board3 = Array(
      0, 0, 0, 1, 2, 0, 0, 0, 8,
      0, 0, 0, 0, 8, 7, 1, 0, 0,
      7, 0, 8, 0, 3, 0, 2, 0, 0,
      0, 0, 0, 0, 0, 5, 0, 8, 0,
      0, 8, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 4, 3, 0,
      0, 0, 3, 0, 0, 6, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 6, 0,
      0, 5, 0, 0, 0, 0, 8, 0, 7,
    )
    val resultBoard3 = solveSudoku(board3)
    assert(SudokuWfChecker.isWellFormed(resultBoard3) 
                && resultBoard3.isSome)
  }

  test("problem 3 - sudoku unsolvable") {
    val board1 = Array(
      1, 1, 1, 1, 1, 1, 1, 1, 1,
      2, 2, 2, 2, 2, 2, 2, 2, 2,
      3, 3, 3, 3, 3, 3, 3, 3, 3,
      4, 4, 4, 4, 4, 4, 4, 4, 4,
      5, 5, 5, 5, 5, 5, 5, 5, 5,
      6, 6, 6, 6, 6, 6, 6, 6, 6,
      7, 7, 7, 7, 7, 7, 7, 7, 7,
      8, 8, 8, 8, 8, 8, 8, 8, 8,
      9, 9, 9, 9, 9, 9, 9, 9, 9,
    )
    val resultBoard1 = solveSudoku(board1)
    assert(resultBoard1.isNone)

    val board2 = Array(
      2, 5, 6, 0, 4, 9, 8, 3, 7,
      1, 8, 3, 5, 0, 7, 9, 6, 4,
      9, 7, 4, 3, 8, 6, 2, 5, 1,
      8, 4, 9, 1, 6, 2, 3, 7, 5,
      5, 6, 2, 7, 9, 3, 4, 1, 8,
      7, 3, 1, 4, 5, 8, 6, 2, 9,
      6, 9, 7, 8, 3, 1, 5, 4, 2,
      4, 2, 8, 6, 7, 5, 1, 9, 3,
      3, 1, 5, 9, 2, 4, 7, 8, 6,
    )
    val resultBoard2 = solveSudoku(board2)
    assert(resultBoard2.isNone)
  }
}