import pp202402.assign3b.Assignment3B._
import pp202402.assign3b.Data._
import scala.reflect.ClassTag
import pp202402.assign3b.UI
import pp202402.assign3b.ViewController._
import pp202402.assign3b.file_prefix

// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html

class TestSuite extends munit.FunSuite {
  def test_omok(test_file: String, arrayBuilder: ArrayBuilder): Unit = {
    val omok = new OmokRule
    val state = new OmokState(arrayBuilder.make(5 * 5, omok.initValue()), Player.Player1)
    val game = new GameController(omok, state, viewController = new OmokViewController)
    val view = new UI.FileView[String](test_file)
    view.run(game)

    val output = scala.io.Source.fromFile(f"$file_prefix/output/$test_file.txt").getLines().toList
    val outputTrimmed = output.filterNot(_.isEmpty)
    val answer = scala.io.Source.fromFile(f"$file_prefix/answer/$test_file.txt").getLines().toList
    val answerTrimmed = answer.filterNot(_.isEmpty)
    
    assertEquals(outputTrimmed, answerTrimmed)
  }

  def test_sudoku(test_file: String, arrayBuilder: ArrayBuilder): Unit = {
    val sudoku = new SudokuRule
    var state = new SudokuState(new SimpleArrayBuilder().make(9 * 9, sudoku.initValue()), Player.Player1)
    val initLines = scala.io.Source.fromFile(f"$file_prefix/init/$test_file.txt").getLines()

    // Init test states
    for i <- 0 until 9 do {
      val line = initLines.next()
      val arr = line.split(" ")
      for j <- 0 until 9 do {
        if arr(j) != "0" then
          state = state.fillCell(i, j, SudokuCell.FixedValue(arr(j).toInt))
        else
          state = state.fillCell(i, j, SudokuCell.Empty)
      }
    }

    val game = new GameController(sudoku, state, viewController = new SudokuViewController)
    val view = new UI.FileView[SudokuCell](test_file)
    view.run(game)

    val output = scala.io.Source.fromFile(f"$file_prefix/output/$test_file.txt").getLines().toList
    val outputTrimmed = output.filterNot(_.isEmpty)
    val answer = scala.io.Source.fromFile(f"$file_prefix/answer/$test_file.txt").getLines().toList
    val answerTrimmed = answer.filterNot(_.isEmpty)

    assertEquals(outputTrimmed, answerTrimmed)
  }
  
  test("Omok - simple array builder test") {
    for i <- 1 to 4 do
      val test_file = f"omok$i"
      test_omok(test_file, new SimpleArrayBuilder)
    }

  test("Omok - strange array builder test") {
    for i <- 1 to 4 do
      val test_file = f"omok$i"
      test_omok(test_file, new StrangeArrayBuilder)
  }

  test("Sudoku - simple array builder test") {
    for i <- 1 to 3 do
      val test_file = f"sudoku$i"
      test_sudoku(test_file, new SimpleArrayBuilder)
  }

  test("Sudoku - strange array builder test") {
    for i <- 1 to 3 do
      val test_file = f"sudoku$i"
      test_sudoku(test_file, new StrangeArrayBuilder)
  }
}