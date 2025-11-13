/// =========================================
/// ===== !!!DO NOT FIX THIS FILE!!! ========
/// =========================================
/// If you fix this file, your code will not be compiled.

package pp202402.assign3b

import pp202402.assign3b.Data._
import scala.reflect.ClassTag
import pp202402.assign3b.Assignment3B._
import scala.io.StdIn
import java.io.PrintWriter
import pp202402.assign3b.ViewController.SudokuViewController

val file_prefix = "src/test/scala"

/**
  * Introduction to UI module.
  * 
  * The UI module contains the `View` class, which is used to display the game board and the values on the board.
  * The `View` class has three methods:
  * - `init`: initializes the game.
  * - `display`: displays the game board and the values on the board.
  * - `run`: runs the game.
  * 
  * The UI module also contains two implementations of the `View` class:
  * - `TextView`: displays the game board and the values on the board in the console.
  * - `FileView`: reads the input from a file and writes the output to a file.
  * 
  * The `TextView` class is used to play the game in the console, 
  * while the `FileView` class is used to test the game with input and output files.
  * You can try the game in the console using the `TextView` class, 
  * and you can test the game with input and output files using the `FileView` class.
  */
object UI:
  abstract class View[A: ClassTag]:
    def init(game: GameController[A]): Unit
    def display(game: GameController[A]): Unit
    def run(game: GameController[A]): Unit

  class TextView[A: ClassTag] extends View[A]:
    def init(game: GameController[A]) : Unit = 
      println(game.init())

    def display(game: GameController[A]): Unit = println(game.display())

    def run(game: GameController[A]): Unit = {
      def run_inner(game: GameController[A]): Unit =
        if game.isDone() then
          println("Current board:")
          display(game)
          println("Game done!")
        else
          println("Current board:")
          display(game)
          println("Enter the row and column to fill (0-indexed):")
          print("Row: ")
          val i = StdIn.readInt()
          print("Column: ")
          val j = StdIn.readInt()
          print("Give the values you want to put: ")
          val v = StdIn.readChar()
          println()
          run_inner(game.makeMove(i, j, v))

      init(game)
      try {
        run_inner(game)
      } catch {
        case e: Exception => println(e.getMessage())
      }
    }

  class FileView[A: ClassTag](test_name: String) extends View[A]:
    val reader = scala.io.Source.fromFile(f"$file_prefix/input/$test_name.txt")
    val writer = new PrintWriter(f"$file_prefix/output/$test_name.txt")

    def init(game: GameController[A]) : Unit = ()

    def display(game: GameController[A]): Unit =
      writer.write(game.display())
      writer.write("\n")

    def run(game: GameController[A]): Unit =
      val lines = reader.getLines().toList.filterNot(_.isEmpty)
      reader.close()

      import scala.util.control.Breaks._

      var curGame = game
      var isFailed = false
      breakable {
        for (line <- lines) {
          val Array(i, j, v) = line.split(" ")
          try {
            curGame = curGame.makeMove(i.toInt, j.toInt, v(0))
          } catch {
            case e: Exception => {
              isFailed = true
              break
            }
          }
        }
      }
    
      if curGame.isDone() then
        writer.write("done\n")
        display(curGame)
        writer.close()
      else
        if isFailed then
          writer.write("failed\n")
        else
          writer.write("not done\n")
        display(curGame)
        writer.close()
