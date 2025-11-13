/// =========================================
/// ===== !!!DO NOT FIX THIS FILE!!! ========
/// =========================================
/// If you fix this file, your code will not be compiled.

package pp202402.assign3b

import scala.reflect.ClassTag

/**
  * Data module.
  * 
  * The Data module contains the data structures and exceptions used in the game engine.
  * 
  * The Data module contains enums and classes.
  * - `Player`: represents the players in the game. Use `Player.Player1` and `Player.Player2` to represent the players in Omok.
  * - `SudokuCell`: represents the values that can be put in a Sudoku cell. 
  *   Use `SudokuCell.Empty`, `SudokuCell.UserValue(v)`, and `SudokuCell.FixedValue(v)` to represent the values in a Sudoku cell.
  *    `SudokuCell.Empty`: represents an empty cell.
  *   - `SudokuCell.UserValue(v)`: represents a cell with a user value `v`.
  *   - `SudokuCell.FixedValue(v)`: represents a cell with a pre-defined value `v`.
  * 
  * The Data module contains exceptions. Use these exceptions to handle errors in the game engine.
  * - We do not check the exact type of the exception, so you can use any exception that extends `Exception`.
  * - However, note that we do check if the game has terminated correctly, stopped in the middle during the game, 
  *   or ended with an exception. Use exception annotations in abstract classes as hints to implement the game correctly.
  * 
  * The Data module contains abstract classes.
  * - `MyArray`: represents an array with `get`, `update`, and `size` methods.
  * - `ArrayBuilder`: represents an array builder with a `make` method.
  * - `State`: represents the state of the game.
  * - `Rule`: represents the rules of the game.
  * 
  * `MyArray` and `ArrayBuilder` are used to represent the game board. A game board is represented as a **1D array**.
  * For a 2D game board, the array is flattened to a 1D array. The conversion of 2D indices to 1D indices is done by `i * nCols + j`.
  * 
  * The purpose of `MyArray` and `ArrayBuilder` is to make the data structure of the game board generic.
  * You can check the following concrete classes of `MyArray` and `ArrayBuilder` in the Data module:
  * - `SimpleArrayBuilder`: implements the `ArrayBuilder` interface with a simple array. (a Scala standard array)
  * - `StrangeArrayBuilder`: implements the `ArrayBuilder` interface with a strange array. 
  *   The `StrangeArrayBuilder` class shuffles the elements of the array. Also, it returns the first element if the index is out of bounds.
  *   It means the burden of handling the out-of-bounds exception is on the user of the `StrangeArrayBuilder` class unlike the `SimpleArrayBuilder` class.
  *   If not, game states may not be consistent.
  */

object Data:
  enum Player:
    case Player1, Player2

  enum SudokuCell:
    case Empty
    case UserValue(v: Int)
    case FixedValue(v: Int)

  class InvalidInputException(val arg: String) extends Exception
  class InvalidValueException(val arg: String) extends Exception
  class InvalidMoveException(val arg: String) extends Exception
  class BoardOutOfBoundsException(val arg: String) extends Exception
  class GenericException(val arg: String) extends Exception

  abstract class MyArray[A: ClassTag]:
    def get(i: Int): A
    def update(i: Int, v: A): MyArray[A]
    def size(): Int

  abstract class ArrayBuilder:
    def make[A: ClassTag](n: Int, init: A): MyArray[A]

  class SimpleArrayBuilder extends ArrayBuilder:
    def make[A: ClassTag](n: Int, init: A): MyArray[A] = new MyArray[A]:
      private val arr = Array.fill(n)(init)
      def get(i: Int): A = arr(i)
      def update(i: Int, v: A): MyArray[A] =
        arr(i) = v
        this
      def size(): Int = arr.length

  class StrangeArrayBuilder extends ArrayBuilder:
    def make[A: ClassTag](n: Int, init: A): MyArray[A] = new MyArray[A]:
      private val shuffler = scala.util.Random.shuffle((0 until n).toList)
      private val arr = Array.fill(n)(init)

      def get(i: Int): A = 
        if i < 0 || i >= arr.length then arr(0)
        else arr(shuffler(i))
      def update(i: Int, v: A): MyArray[A] =
        if i < 0 || i >= arr.length then this
        else {
          arr(shuffler(i)) = v
          this
        }

      def size(): Int = arr.length
  
  abstract class State[A: ClassTag](board: MyArray[A], currentPlayer: Player, nRows: Int, nCols: Int):
    def getPlayer(): Player = currentPlayer
    def getNRows(): Int = nRows
    def getNCols(): Int = nCols

    @throws(classOf[Exception])
    def getCell(i: Int, j: Int): A =
      if i < 0 || i >= nRows || j < 0 || j >= nCols then
        throw BoardOutOfBoundsException("Board out of bounds")
      board.get(i * nCols + j)

    @throws(classOf[Exception])
    def fillCell(i: Int, j: Int, v: A): State[A]
    
    def nextTurn(): State[A]

  abstract class Rule[A: ClassTag]:
    // Basic rule about the game
    def initMessage(): String

    // Methods about what to, and how to put the value in the game
    def initValue(): A
    @throws(classOf[Exception])
    def renderInput(input: Char): A

    // Methods about the validity of the game
    def isAvailableValue(v: A): Boolean
    def isStateValid(state: State[A]): Boolean
    def isNextMoveValid(state: State[A], i: Int, j: Int, v: A): Boolean    
    def isDone(state: State[A]): Boolean
