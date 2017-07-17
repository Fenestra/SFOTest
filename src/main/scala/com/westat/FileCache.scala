package com.westat

import java.io._
import scala.io.Source
//import com.westat.StringUtilities

/**
  * Created by lee on 6/19/17.
  *


function Utf8ToUtf16LE(const Utf8: TUtf8String): TUtf16LEString;
var
I: Integer;
J: Integer;
N: LongWord;
begin
SetLength(Result, Length(Utf8));
I := 0;
J := 0;
while I < Length(Utf8) do begin
N := 0;
Inc(I);
case Utf8[I] of
#$00..#$7F:
N := Ord(Utf8[I]);
#$C0..#$DF: begin
N := (Ord(Utf8[I]) and $1F) shl 6;
Inc(I);
N := N + (Ord(Utf8[I]) and $3F) end;
#$E0..#$EF: begin
N := (Ord(Utf8[I]) and $0F) shl 12;
Inc(I);
N := N + (Ord(Utf8[I]) and $3F) shl 6;
Inc(I);
N := N + (Ord(Utf8[I]) and $3F) end;
#$F0..#$F7: begin
N := (Ord(Utf8[I]) and $07) shl 18;
Inc(I);
N := N + (Ord(Utf8[I]) and $3F) shl 12;
Inc(I);
N := N + (Ord(Utf8[I]) and $3F) shl 6;
Inc(I);
N := N + (Ord(Utf8[I]) and $3F) end end;
Inc(J);
case N of
$0000..$FFFF:
Result[J] := WideChar(N);
$10000..$10FFFF: begin
Result[J] := WideChar(((N - $10000) shr 10) + $D800);
Inc(J);
Result[J] := WideChar(((N - $10000) and $3FF) + $DC00) end end end;
SetLength(Result, J) end;
*/

case class FileCache(directory: String, extension : String) {
  private def filename(id : String) : String = {
    directory + "/" + id + "." + extension
  }

  def find(id : String) : Option[String] = {
    var sf : Source = null
    try {
      sf = Source.fromFile(filename(id))
    } catch {
      case ex : Exception => return None
    }
    println(s"find has sf of $sf")
    val contents = sf.mkString
    println(s"find has contents of ${StringUtilities.shortString(contents)}")
    Some(contents)
//      val file = new File(filename(id))
//      val reader = new BufferedReader(new FileReader(file))
  }

  def write(id : String, contents : String) = {
    val file = new File(filename(id))
    val writer = new BufferedWriter(new FileWriter(file))
    writer.write(contents)
    writer.close()
  }

  /*
      def parseFile(filename : String) = {
      var tabs = new ListBuffer[Tab]
      var sf = Source.fromFile(filename).getLines()
      val file = new File(filename+".js")
      val writer = new BufferedWriter(new FileWriter(file))
  //    writer .write(asJsonList)

      // sections is one big long string crammed with section elements, so we need to parse them
      var sections = sf.filter(s => s.contains("<section id=")).next()
      var sl = sections.replaceAllLiterally("<section", "\n<section").split(newlineChar)
      for (i <- 1 to sl.length-1) {
        var line = sl(i)
        var pos = line.indexOf(" class=")

        if (i == 1)
          line = line.replaceFirst(" class=", " style=\"display:block\" class=")
        else
          line = line.replaceFirst(" class=", " style=\"display:none\" class=")

        line = line.replace("'", "\\'")
        writer.write("   +'\n"+line+"'")
        writer.newLine()
       println(line.substring(0, 60))
        val id = findID(line)
        val descr = findDescription(line)
        val deList = findDEs(line)
        tabs += Tab(i, id, descr, deList)
        //     println(i + " " + descr)
  //     println(deList)
  //     need to print out section in full, then put tab info into object
  //     when all sections processed, print out tab infor
      }
      writer.newLine()
      writer.write("var tabs = [")
      tabs.foreach(t => {
        writer.write(Json.toJson(t).toString()+",")
        writer.newLine()
      })
      writer.write("];")
      writer.close()
    }

     */

}
