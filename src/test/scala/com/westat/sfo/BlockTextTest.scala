package com.westat.sfo

import com.westat.gids.GidsFont
import com.westat.{Length, Location}
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import scala.collection.mutable.ListBuffer

/**
 * Created by Owner on 7/31/2017.
 */
class LineListTests extends FunSuite with BeforeAndAfter {

  var textList: ListBuffer[InlineText] = _
  var lineFactory : LineListFactory = _
  var lineList: List[OutputLine] = _
  val font = GidsFont("Arial", "black", "", Length.dimension("10pt"))
  val boldfont = GidsFont("Arial", "black", "700", Length.dimension("10pt"))

  before {
    textList = new ListBuffer[InlineText]
    textList += InlineText("U.S. Corporation is one of, if not the very", font)
    textList += InlineText("best", boldfont)
    textList += InlineText("companies ever, you know.", font)
    lineFactory = LineListFactory(textList.toList, Length.dimension("2.5in"), font, TextAlignments.taLeft)
    lineList = lineFactory.lines()
  }
  /*
    test("new area has empty location") {
      assert(area.location.isEmpty)
    }
  */

  test("2.5in wide line should have 2 lines") {
    println(s"2.5in line has ${lineList.length} lines")
    assert(lineList.length.equals(2))
  }
  test("2in wide line should have 3 lines") {
    lineList = lineFactory.lines(Length.dimension("2in"))
    println(s"2in line has ${lineList.length} lines")
    assert(lineList.length.equals(3))
  }
  test("8in wide line should have 1 line") {
    lineList = lineFactory.lines(Length.dimension("8in"))
    println(s"8in line has ${lineList.length} lines")
    assert(lineList.length.equals(1))
  }
}
