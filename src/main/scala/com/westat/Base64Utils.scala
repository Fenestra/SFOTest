package com.westat

import sun.misc.BASE64Decoder

/**
 * Created by Owner on 6/7/2017.
 */
object Base64Utils {
  private val BASE64CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
  private val BASE64_PAD_CHAR = '='

  def stripWhitespace(s : String) : String = {
    var result = new StringBuilder()
    s.foreach(c => if (BASE64CHARSET.contains(c))
      result.append(c))
    result.toString()
  }

  def toOctet(s : String) : String = {
    var ss = stripWhitespace(s)
    if (ss.length != s.length)
      println(s"toOctet got diff length strs s:${s.length} and ss:${ss.length}")
    while (ss.length % 4 != 0) {
      ss = ss + BASE64_PAD_CHAR
    }
    val decoder = new BASE64Decoder()
    var result = decoder.decodeBuffer(ss).toString
    if ((ss.length > 0) && (ss.charAt(ss.length-1) == BASE64_PAD_CHAR)) {
       result = result.dropRight(1)
      if ((ss.length > 1) && (ss.charAt(ss.length-1) == BASE64_PAD_CHAR))
        result = result.dropRight(1)
    }
    result
  }
  /*  class function CBase64.ToOctet(const S: TBase64String): TOctetString;
  var
  Base64: IBase64Decoder;
  SS: TBase64String;
  begin
  Base64 := TBase64Decoder.Create;
  SS := StripWhitespace(S);
  while (Length(SS) mod 4) <> 0 do
    SS := SS + BASE64_PAD_CHAR;
  Base64.Append(SS);
  Result := Base64.Value;
  if (Length(SS) > 0) and (SS[Length(SS)] = BASE64_PAD_CHAR) then begin
    Result := Copy(Result, 1, Length(Result) - 1);
  if (Length(SS) > 1) and (SS[Length(SS) - 1] = BASE64_PAD_CHAR) then
  Result := Copy(Result, 1, Length(Result) - 1) end end;
*/

}
