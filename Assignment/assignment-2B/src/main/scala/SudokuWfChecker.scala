/** Sudoku Well-formedness Checker
 * 
 * !!!DO NOT FIX THIS FILE!!!
 * If you fix this file, your test code runs will not be correct.
 * Caveat: Using this well-formed test code will not give you any points for the problem 3
 * because it extensively uses the library functions.
 */

package pp202502.assign2b

object SudokuWfChecker:
  def isFilled(board: Array[Int]): Boolean = 
      board.forall(_ != 0)

  def checkRow(board: Array[Int], row: Int): Boolean =
    val rowValues = for i <- 0 until 9 yield board(row * 9 + i)
    rowValues.distinct.length == rowValues.length
  
  def checkColumn(board: Array[Int], col: Int): Boolean =
    val colValues = for i <- 0 until 9 yield board(i * 9 + col)
    colValues.distinct.length == colValues.length

  def checkGrid(board: Array[Int], row: Int, col: Int): Boolean =
    val squareValues = for
      i <- 0 until 3
      j <- 0 until 3
    yield board((row + i) * 9 + col + j)
    squareValues.distinct.length == squareValues.length
  
  def isWellFormed(board: IOption[Array[Int]]): Boolean =
    board match
      case IOption.INone => true
      case IOption.ISome(b) =>
        val rows = for i <- 0 until 9 yield checkRow(b, i)
        val cols = for i <- 0 until 9 yield checkColumn(b, i)
        val grids = for
          i <- 0 until 3
          j <- 0 until 3
        yield checkGrid(b, i * 3, j * 3)
        rows.forall(identity) && cols.forall(identity) && grids.forall(identity) && isFilled(b)
