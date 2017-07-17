package com.westat

import java.io._
import java.util.zip._

/**
 * Created by Owner on 6/5/2017.
 */
object ZipUtilities {

  def decodeExpandFile(filename: String) = {
    val inStream = new FileInputStream(new java.io.File(filename))
    val outFile = new FileOutputStream(new java.io.File("Converted-"+filename))

    val inflater = new InflaterOutputStream( outFile ) //OutputStream()

    StringUtilities.base64StreamDecode(inStream, inflater)// outFile)
    inflater.flush()
    outFile.flush()
    inStream.close()
    inflater.close()
    outFile.close()
  }

  def inflateString(compressedStr : String) : String = {
    val bais = new ByteArrayInputStream(compressedStr.getBytes)
    val baos = new ByteArrayOutputStream()
    val iis = new InflaterInputStream(bais)
    val buf = new Array[Byte](1024)

      iis.read(buf, 0, 4) //get rid of length prefix
      var count = iis.read(buf)
      while (count != -1) {
        baos.write(buf, 0, count)
        count = iis.read(buf)
      }
    new String(baos.toByteArray)
    }

  def expandString(inputStr : String) : String = {
    val inflater = new Inflater()
    inflater.setInput(inputStr.getBytes)
    var result = new Array[Byte](100000)
    val cnt = inflater.inflate(result)
    inflater.end()
    val len = inflater.getBytesRead
    println(s"inflater found input len of $len")
    println(s"$cnt bytes were read for "+result)
    println(result.toString())
    result.toString()
  }
}



