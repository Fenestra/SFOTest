package com.westat.sfo

/**
 * Created by Owner on 8/2/2017.
 */
import com.westat.gids.GidsFont
import com.westat.{Length, Location}
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import scala.collection.mutable.ListBuffer

trait OutputLineTests {

  var textList: ListBuffer[InlineText] = _
  var lineFactory: LineListFactory = _
  var lineList: List[OutputLine] = _
  val font = GidsFont("Arial", "black", "", Length.dimension("10pt"))
  val boldfont = GidsFont("Arial", "black", "700", Length.dimension("10pt"))
  val ufont = GidsFont("Univers LT 55", "black", "", Length.dimension("10pt"))
  val uboldfont = GidsFont("Univers LT 55", "black", "700", Length.dimension("10pt"))
  def weRequestText : List[InlineText] = {
    val list = new ListBuffer[InlineText]
    list += InlineText("We request your cooperation in completing the ", null)
    list += InlineText("2008 Report of Organizations, ", uboldfont)
    list += InlineText("Form ", null)
    list.toList
  }
  def pleaseRespondText : List[InlineText] = {
    val list = new ListBuffer[InlineText]
    list += InlineText("Please respond within 30 days after receipt.", uboldfont)
    list += InlineText(" You may use reasonable estimates if book figures are not", null)
    list.toList
  }
  def requiresYourResponseText : List[InlineText] = {
    val list = new ListBuffer[InlineText]
    list += InlineText("Title 13, United States Code, ", null)
    list += InlineText("requires your response ", uboldfont)
    list += InlineText("to this inquiry and guarantees that your response is confidential", null)
    list.toList
  }
}

  class WeRequestTest extends FunSuite with BeforeAndAfter with OutputLineTests {
  before {
    lineFactory = LineListFactory(weRequestText, Length.dimension("6in"), ufont, TextAlignments.taLeft)
  }
  test("1.75in wide line has 4 lines") {
    lineList = lineFactory.lines(Length.dimension("1.75in"))
    //   println(s"2.5in line has ${lineList.length} lines "+lineList.foreach(ol => println(ol.contents)))
    assert(lineList.length.equals(4))
  }
  test("2in wide line should have 4 lines") {
    lineList = lineFactory.lines(Length.dimension("2in"))
//        println(s"2in line has ${lineList.length} lines and expects 4")
//        lineList.foreach(ol => println(ol.contents))
    assert(lineList.length.equals(4))
  }
  test("2.5in wide line has 3 lines") {
    lineList = lineFactory.lines(Length.dimension("2.5in"))
    //    println(s"2.5in line has ${lineList.length} lines and expects 3 ")
    //    lineList.foreach(ol => println(ol.contents))
    assert(lineList.length.equals(3))
  }
  test("6in wide line should have 1 line") {
    lineList = lineFactory.lines(Length.dimension("6in"))
//    println(s"6in line has ${lineList.length} lines ")
//    lineList.foreach(ol => println(ol.contents))
    assert(lineList.length.equals(1))
  }
}

class PleaseRespondTest extends FunSuite with BeforeAndAfter with OutputLineTests {
  before {
    lineFactory = LineListFactory(pleaseRespondText, Length.dimension("6in"), ufont, TextAlignments.taLeft)
  }
  test("1.75in wide line has 5 lines") {
    lineList = lineFactory.lines(Length.dimension("1.75in"))
//    println(s"1.75in line has ${lineList.length} lines and expects 5")
//    lineList.foreach(ol => println(ol.contents))
    assert(lineList.length.equals(5))
  }
  test("2.5in wide line has 4 lines") {
    lineList = lineFactory.lines(Length.dimension("2.5in"))
//    println(s"2.5in line has ${lineList.length} lines ")
//    lineList.foreach(ol => println(ol.contents))
    assert(lineList.length.equals(4))
  }
  test("6in wide line should have 2 lines") {
    lineList = lineFactory.lines(Length.dimension("6in"))
//     println(s"6in line has ${lineList.length} lines ")
//     lineList.foreach(ol => println(ol.contents))
    assert(lineList.length.equals(2))
  }
}

class RequiresYourTest extends FunSuite with BeforeAndAfter with OutputLineTests {
  before {
    lineFactory = LineListFactory(requiresYourResponseText, Length.dimension("6in"), ufont, TextAlignments.taLeft)
  }
  test("1.75in wide line has 5 lines") {
    lineList = lineFactory.lines(Length.dimension("1.75in"))
//        println(s"1.75in line has ${lineList.length} lines ")
//        lineList.foreach(ol => println(ol.contents))
    assert(lineList.length.equals(5))
  }
  test("2.5in wide line has 4 lines") {
    lineList = lineFactory.lines(Length.dimension("2.5in"))
//        println(s"2.5in line has ${lineList.length} lines and expects 4")
//        lineList.foreach(ol => println(ol.contents))
    assert(lineList.length.equals(4))
  }
  test("6in wide line should have 2 lines") {
    lineList = lineFactory.lines(Length.dimension("6in"))
//         println(s"6in line has ${lineList.length} lines ")
//         lineList.foreach(ol => println(ol.contents))
    assert(lineList.length.equals(2))
  }
}
