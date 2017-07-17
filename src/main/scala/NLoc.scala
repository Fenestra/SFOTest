/**
 * Created by Owner on 7/13/2017.
 */
trait NLoc {
  def left : Int
  def top : Int
  def right : Int
  def bottom : Int
  def width : Int
  def height : Int
  def rectString : String
  def copyOf : NLoc
  def moveLeft(delta : Int) : NLoc
  def moveRight(delta : Int) : NLoc
  def moveUp(delta : Int) : NLoc
  def moveDown(delta : Int) : NLoc
  // shifts area over by the amount that was used == available remaining area
  def shrinkWidth(delta : Int) : NLoc
  def shrinkHeight(delta : Int) : NLoc
}

case class NewLoc(left : Int, top : Int, width : Int, height : Int) extends NLoc {
  def right : Int = left + width
  def bottom : Int = top + height
  def rectString : String = s"rect ($left, $top)  ($right, $bottom)  ($width, $height)"
  def copyOf : NLoc = NewLoc(left, top, width, height)
  def moveLeft(delta : Int) : NLoc = NewLoc(left - delta, top, width, height)
  def moveRight(delta : Int) : NLoc = NewLoc(left + delta, top, width, height)
  def moveUp(delta : Int) : NLoc = NewLoc(left, top - delta, width, height)
  def moveDown(delta : Int) : NLoc = NewLoc(left, top + delta, width, height)
  def shrinkWidth(delta : Int) : NLoc = NewLoc(left + delta, top, width - delta, height)
  def shrinkHeight(delta : Int) : NLoc = NewLoc(left, top + delta, width, height - delta)
}

