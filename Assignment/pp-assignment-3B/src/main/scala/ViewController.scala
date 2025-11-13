
package pp202402.assign3b

import pp202402.assign3b.Data._
import scala.reflect.ClassTag

/**
  * ViewController module.
  * 
  * The ViewController module contains the `ViewController` class, which is used to display the game board and the values on the board.
  * The `ViewController` class has two methods:
  * - `displayValue`: displays the value on the board.
  * - `displayBoardState`: displays the game board.
  * 
  * The ViewController module also contains two implementations of the `ViewController` class:
  * - `OmokViewController`: displays the Omok game board and the values on the board.
  * - `SudokuViewController`: displays the Sudoku game board and the values on the board.
  * 
  * You can check txt files in `src/test/scala/answer/` to see how each ViewController displays the game board.
  */

object ViewController:
  abstract class ViewController[A: ClassTag]:
    // Methods about how to display the game
    def displayValue(v: A): String
    def displayBoardState(state: State[A]): String

  class OmokViewController extends ViewController[String]:
    def displayValue(v: String): String = v

    def displayBoardState(state: State[String]): String =
      val nRows = state.getNRows()
      val nCols = state.getNCols()

      val sb = new StringBuilder()
      for i <- 0 until nRows do
        for j <- 0 until nCols do
          sb.append(displayValue(state.getCell(i, j)))
          if j < nCols - 1 then sb.append(" | ")
        sb.append("\n")
        if i < nRows - 1 then sb.append("-" * (nCols * 4 - 3)).append("\n")
      sb.toString()

  class SudokuViewController extends ViewController[SudokuCell]:
    def displayValue(v: SudokuCell): String = 
      v match
        case SudokuCell.Empty => "0"
        case SudokuCell.FixedValue(v) => v.toString
        case SudokuCell.UserValue(v) => v.toString
        
    def displayBoardState(state: State[SudokuCell]): String =
      val sb = new StringBuilder
      for (i <- 0 until 9) {
        for (j <- 0 until 9) {
          sb.append(displayValue(state.getCell(i, j)))

          // Add a vertical divider every 3 columns, except at the end
          if ((j + 1) % 3 == 0 && j < 8) sb.append(" | ")
          else if (j < 8) sb.append(" ")
        }
        sb.append("\n")
        
        // Add a horizontal divider every 3 rows, except at the end
        if ((i + 1) % 3 == 0 && i < 8) sb.append("-" * 21).append("\n")
      }
      sb.toString()