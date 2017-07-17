package com.westat.sfo

import com.westat.{Length, Location}
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
/*
area stacking, left, top, width, height, name
findLocation(name) : Option[Location]
computeDimensions(par : Loc) : Location
if you get par telling you we have width, height starting at left, top
figure out what our available are is...

area can have list of areas, or children
*/
class AreaTests extends FunSuite with BeforeAndAfter {

  var area: Area = _
  var parLoc: Location = _

  before {
    area = Area("top", null, Length.dimension("1in"), null, Length.dimension(".15in"), "toptest")
//    area = Area("top", null, Length.dimension("1in"), null, Length.dimension(".15in"), "toptest")
    parLoc = Location.create("1in", "1in", "6in", "2in")
  }
/*
  test("new area has empty location") {
    assert(area.location.isEmpty)
  }
*/
  test("area after computing starts at 1,1") {
    area.computeDimensions(parLoc)
    println(s"area after computing starts ${area.location}")
    assert(area.location.left.equals("1.0in"))
    assert(area.location.top.equals("1.0in"))
  }

  test ("area after computing ends at 7,1.15") {
    area.computeDimensions(parLoc)
    println(s"area after computing ends is ${area.location}")
    assert(area.location.right.equals("7.0in"))
    assert(area.location.bottom.equals("1.15in"))
  }

}
/*
region left, top, right, botton, width, height, transform, isback
findLoc
computeDim(pagemaster)
computes our loc, then does all child areas
*/
class RegionTests extends FunSuite with BeforeAndAfter {
  
  var region: Region = _
  var pm : PageMaster = _

  before {
    region = Region(Length.dimension("1in"), Length.dimension("2in"), Length.dimension("0fu"), Length.dimension("0fu"),
      Length.dimension("4in"), Length.dimension("5in"), "no", "no background")
    //)left : Length, top : Length, right : Length, bottom : Length,
    //                  width : Length, height : Length, transform : String, isBackground : String) {
    pm = PageMaster("test", "white", Length.dimension("8.5in"), Length.dimension("11in"))
    }

  test("new region has zero right") {
    println(s"new region right is ${region.location.right.asInchesString}")
    assert(region.right.equals("0fu"))
//    region.right.asDeviceUnits should be (0)
  }

  test("region left after computing sb 1") {
    region.computeDimensions(pm)
    println(s"region left after comp is ${region.location.left.asInchesString}")
    assert(region.location.left.equals("1.0in"))
  }

  test("region top after computing sb 2") {
    region.computeDimensions(pm)
       println(s"region top after comp is ${region.location.top.asInchesString}")
    assert(region.location.top.equals("2.0in"))
  }

  test("region right after computing sb 5") {
   // pizza.addTopping(Topping("green olives"))
    region.computeDimensions(pm)
    println(s"region right after comp is ${region.location.right.asInchesString}")
    assert(region.location.right.equals("5.0in"))
  }

  test("region bottom after computing sb 7") {
    region.computeDimensions(pm)
    println(s"region bottom after comp is ${region.location.bottom.asInchesString}")
    assert(region.location.bottom.equals("7.0in"))
  }

  // mark that you want a test here in the future
//  test ("test pizza pricing") (pending)

}
/*
left="2937053fu" top="0fu" width="56538266fu" height="3671316fu">
  <sfo:area height="3671316fu" stacking="top" content-name="null header"/></sfo:region>
<sfo:region left="2937053fu" top="77097636fu" width="56538266fu" height="3671316fu">
  <sfo:area height="3671316fu" stacking="top" content-name="null footer"/></sfo:region>
<sfo:region left="0fu" top="0fu" width="2937053fu" height="80768952fu">
  <sfo:area width="2937053fu" stacking="left" content-name="null left sidebar"/></sfo:region>
<sfo:region left="59475319fu" top="0fu" width="2937053fu" height="80768952fu">
  <sfo:area width="2937053fu" stacking="left" content-name="null right sidebar"/></sfo:region>

<sfo:region left="2937053fu" top="3671316fu" width="56538266fu" height="73426320fu">
  <sfo:area height="73426320fu" stacking="top" content-name="form ASCO-L3_P01_08 - page 0">
*/
class PageMasterTests extends FunSuite with BeforeAndAfter {
  var pm: PageMaster = _
  var header: Region = _
  var footer: Region = _
  var leftbar: Region = _
  var rightbar: Region = _
  var content: Region = _

  before {
    header = Region(Length.dimension("1in"), Length.dimension("0fu"), Length.dimension("0fu"), Length.dimension("0fu"), Length.dimension("6.5in"), Length.dimension("1in"), "no", "no background")
    header.areas = List(Area("top", Length.dimension("1in"), Length.dimension("0fu"), null, Length.dimension("1in"), "header"))
    footer = Region(Length.dimension("1in"), Length.dimension("10in"), Length.dimension("0fu"), Length.dimension("0fu"), Length.dimension("6.5in"), Length.dimension("1in"), "no", "no background")
    footer.areas = List(Area("top", Length.dimension("1in"), Length.dimension("10in"), null, Length.dimension("1in"), "footer"))
    leftbar = Region(Length.dimension("0fu"), Length.dimension("0fu"), Length.dimension("0fu"), Length.dimension("0fu"), Length.dimension("1in"), Length.dimension("11in"), "no", "no background")
    leftbar.areas = List(Area("left", Length.dimension("0in"), Length.dimension("0fu"), Length.dimension("1in"), null, "leftbar"))
    rightbar = Region(Length.dimension("6.5in"), Length.dimension("0fu"), Length.dimension("0fu"), Length.dimension("0fu"), Length.dimension("1in"), Length.dimension("11in"), "no", "no background")
    rightbar.areas = List(Area("left", Length.dimension("0in"), Length.dimension("0fu"), Length.dimension("1in"), null, "rightbar"))
    content = Region(Length.dimension("1in"), Length.dimension("1in"), Length.dimension("0fu"), Length.dimension("0fu"), Length.dimension("6.5in"), Length.dimension("9in"), "no", "no background")
    content.areas = List(Area("top", Length.dimension("1in"), Length.dimension("1in"), null, Length.dimension("1in"), "title"),
      Area("top", Length.dimension("1in"), Length.dimension("2in"), null, Length.dimension("5in"), "largearea")
    )
    pm = PageMaster("test", "white", Length.dimension("8.5in"), Length.dimension("11in"))
    pm.regions = List(header, footer, leftbar, rightbar, content)
  }

  test("new pagemaster has right=width") {
    assert(pm.location.right.equals("8.5in"))
  }

  test("pagemaster has right after computing") {
    pm.computeDimensions
    assert(pm.location.right.equals("8.5in"))
  }

  // mark that you want a test here in the future
  test("pagemaster has largearea") {
    pm.computeDimensions
    var loc: Location = null
    //  pm.findLocation("largearea") match {
    //    case Some[Location](f) => loc = f
    //    case None =>
    //  }
    loc = pm.findLocation("largearea")
    println("largearea was " + loc)
    assert(loc != null)
  }
}

/*  <sfo:region left="1.0in" top="1.0in" width="6.5in" height="9.0in">
    <sfo:area stacking="top" height="0.5in" content-name="title"/>
    <sfo:area stacking="top" height="1.5in" content-name="topband">
      <sfo:area stacking="left" width="0.6in" content-name="firstbox">
        <sfo:area stacking="top" height="1.0in" content-name="default-bracket"/>
        <sfo:area stacking="top" height="0.3in" content-name="default-bracket-caption"/>
      </sfo:area>
      <sfo:area stacking="left" width="1.0in" content-name="secondbox"/>
      <sfo:area stacking="left" width="0.6in" content-name="thirdbox">
        <sfo:area stacking="top" height="1.0in" content-name="east-bracket"/>
        <sfo:area stacking="top" height="0.3in" content-name="east-bracket-caption"/>
      </sfo:area>*/
class LayoutBandTests extends FunSuite with BeforeAndAfter {
  var pm : PageMaster = _
  var content : Region = _
  var title : Area = _
  var topBand : Area = _
  var firstBox : Area = _
  var secondBox : Area = _
  var thirdBox : Area = _
  var eastBox : Area = _

  before {
    content = Region(Length.dimension("1in"), Length.dimension("1in"), Length.dimension("0fu"), Length.dimension("0fu"), Length.dimension("6.5in"), Length.dimension("9in"), "no", "no background")
    title = Area("top", Length.dimension("1in"), Length.dimension("1in"), null, Length.dimension("0.5in"), "title")
    topBand = Area("top", Length.dimension("1in"), Length.dimension("1.5in"), null, Length.dimension("1.5in"), "topband")
    firstBox = Area("left", Length.dimension("1in"), Length.dimension("1.5in"), Length.dimension(".6in"), null, "firstbox")
    secondBox = Area("left", Length.dimension("1.6in"), Length.dimension("1.5in"), Length.dimension("1in"), null, "secondbox")
    thirdBox = Area("left", Length.dimension("2.6in"), Length.dimension("1.5in"), Length.dimension(".6in"), null, "thirdbox")
    eastBox = Area("top", Length.dimension("2.6in"), Length.dimension("1.5in"), null, Length.dimension(".5in"), "eastbox")
    thirdBox.areas = List( eastBox )
    topBand.areas = List( firstBox, secondBox, thirdBox )
    content.areas = List( title, topBand)
    pm = PageMaster("test", "white", Length.dimension("8.5in"), Length.dimension("11in"))
    pm.regions = List(content)
  }

  test("new layoutband has right=width") {
    assert(pm.location.right.equals("8.5in"))
  }

//  these tests failed since I fixed the blockbox computeRectangle routine.
//  althoguh it now says bottom has loc of 2,1.5  4.8,2.5  wh of 2.2,1
//  so that would make bottom 2.5 instead of 3.5
//  for some reason these are Areas are not making new locs for their personal use, but making changes to parent

  test("page has right after computing") {
    pm.computeDimensions
    assert(pm.location.right.equals("8.5in"))
  }

  test ("topband has left of 1") {
    pm.computeDimensions
    val loc = pm.findLocation("topband")
    println("topband left was "+loc)
    assert(loc.left.equals("1.0in"))
  }
  test ("topband has top of 1.5") {
    pm.computeDimensions
    val loc = pm.findLocation("topband")
    println("topband top was "+loc)
    assert(loc.top.equals("1.5in"))
  }
  test ("topband has right of 7.5") {
    pm.computeDimensions
    val loc = pm.findLocation("topband")
    println("topband right was "+loc)
    assert(loc.right.equals("7.5in"))
  }
  test ("topband has bottom of 3.5") {
    pm.computeDimensions
    val loc = pm.findLocation("topband")
    println("topband bottom was "+loc)
    assert(loc.bottom.equals("3.5in"))
  }
/*
      <sfo:area stacking="left" width="0.2in" content-name="1.4"/>
      <sfo:area stacking="left" width="0.6in">
        <sfo:area stacking="top" height="1.0in" content-name="north-bracket"/>
        <sfo:area stacking="top" height="0.3in" content-name="north-bracket-caption"/>
      </sfo:area>
      <sfo:area stacking="left" width="0.2in"/>
      <sfo:area stacking="left" width="0.6in">
        <sfo:area stacking="top" height="1.0in" content-name="west-bracket"/>
        <sfo:area stacking="top" height="0.3in" content-name="west-bracket-caption"/>
      </sfo:area>
      <sfo:area stacking="left" width="0.2in"/>
      <sfo:area stacking="left" width="0.6in">
        <sfo:area stacking="top" height="1.0in" content-name="south-bracket"/>
        <sfo:area stacking="top" height="0.3in" content-name="south-bracket-caption"/>
      </sfo:area>
    </sfo:area>
*/
}
