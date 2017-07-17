
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class NLocTests extends FunSuite with BeforeAndAfter {

  var loc : NLoc = _
  var locclone : NLoc = _

  before {
    loc = NewLoc(5, 10, 70, 90)
    locclone = loc.copyOf
  }

  test("loc initialized correctly") {
    assert((loc.left != 15) && (loc.top != 18))
  }

  test("loc starts at 5,10") {
    assert((loc.left == 5) && (loc.top == 10))
  }

  test("loc right is 75") {
    assert(loc.right == 75)
  }

  test("loc clone is same") {
    assert(loc.rectString == locclone.rectString)
  }

  test("loc moveLeft 5 shifts start to 0 and ends at 70") {
    loc = loc.moveLeft(5)
    //    println("moveLeft "+loc.rectString)
    assert((loc.left == 0) && (loc.right == 70))
  }

  test("loc moveRight 5 shifts start to 10 and ends at 80") {
    loc = loc.moveRight(5)
//    println("moveRight "+loc.rectString)
    assert((loc.left == 10) && (loc.right == 80))
  }

  test("loc moveUp 5 shifts start to 5 and ends at 95") {
    loc = loc.moveUp(5)
    //    println("moveUp "+loc.rectString)
    assert((loc.top == 5) && (loc.bottom == 95))
  }

  test("loc moveDown 5 shifts start to 15 and ends at 105") {
    loc = loc.moveDown(5)
//    println("moveDown "+loc.rectString)
    assert((loc.top == 15) && (loc.bottom == 105))
  }

  test("loc shrinkWidth 30 shifts start to 35 and ends at 75") {
    loc = loc.shrinkWidth(30)
    //    println("moveDown "+loc.rectString)
    assert((loc.left == 35) && (loc.right == 75))
  }

  test("loc shrinkHeight 30 shifts start to 40 and ends at 100") {
    loc = loc.shrinkHeight(30)
    //    println("moveDown "+loc.rectString)
    assert((loc.top == 40) && (loc.bottom == 100))
  }
}
