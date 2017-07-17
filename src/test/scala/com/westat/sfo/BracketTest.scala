package com.westat.sfo

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class BracketSFOTests extends FunSuite with BeforeAndAfter {

  var svg: String = _

  before {
    svg = SFOReader("BracketSFO.xml").readFromFile
  }

  test("svg uses Verdana font") {
    assert(svg.contains("Verdana"))
  }

  test("svg title is correct") {
    assert(svg.contains("Rendering engine bracket test"))
  }

//  find left, top, right, bottom
}
