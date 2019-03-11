package com.github.jakz.fnt_editor.data;

import com.pixbits.lib.lang.Point;
import com.pixbits.lib.lang.Rect;

public class Glyph
{
  public char utf16;
  public Rect texCoords;
  public Point.Int offset;
  public int xadvance;
  public Page page;
  public String description;
  
  public static Glyph parse(String line)
  {
    if (line.startsWith("char "))
    {
      String[] tokens = line.split(" ");
      int x = 0, y = 0, w = 0, h = 0, xo = 0, yo = 0;
      Glyph glyph = new Glyph();
      
      for (String token : tokens)
      {
        try
        {
          String[] keyValue = token.split("=");
          
          if (keyValue.length == 2)
          {
            switch (keyValue[0])
            {
              case "id": glyph.utf16 = Parser.parseCharValue(keyValue[1]); break;
              case "xoffset": xo = Integer.parseInt(keyValue[1]); break;
              case "yoffset": yo = Integer.parseInt(keyValue[1]); break;
              case "x": x = Integer.parseInt(keyValue[1]); break;
              case "y": y = Integer.parseInt(keyValue[1]); break;
              case "width": w = Integer.parseInt(keyValue[1]); break;
              case "height": h = Integer.parseInt(keyValue[1]); break;
              case "xadvance": glyph.xadvance = Integer.parseInt(keyValue[1]); break;
            }
          }       
        }
        catch (Exception e)
        {
          /* silence */
          e.printStackTrace();
        }
      }
      
      glyph.offset = new Point.Int(xo, yo);
      glyph.texCoords = new Rect(x, y, w, h);
      return glyph;
    }
    
    return null;
  }
  
  public String toString()
  {
    return String.format("char id=%d x=%d y=%d width=%d height=%d xoffset=%d yoffset=%d xadvance=%d page=%d", 
        (int)utf16, texCoords.x, texCoords.y, texCoords.w, texCoords.h, offset.x, offset.y, xadvance, page);
  }
}
