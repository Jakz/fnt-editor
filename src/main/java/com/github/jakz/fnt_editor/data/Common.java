package com.github.jakz.fnt_editor.data;

import com.pixbits.lib.lang.Size;

public class Common
{
  int lineHeight;
  int base;
  Size.Int textureSize;
  int pages;
  /* packed, alphaChnl, redChnl .. */
  
  public Common(String line)
  {
    int sw = 0, sh = 0;
    String[] tokens = line.split(" ");
    
    for (String token : tokens)
    {
      try
      {
        String[] keyValue = token.split("=");
        
        if (keyValue.length == 2)
        {
          switch (keyValue[0])
          {
            case "lineHeight": lineHeight = Integer.parseInt(keyValue[1]); break;
            case "base": base = Integer.parseInt(keyValue[1]); break;
            case "scaleW": sw = Integer.parseInt(keyValue[1]); break;
            case "scaleH": sh = Integer.parseInt(keyValue[1]); break;
            case "pages": pages = Integer.parseInt(keyValue[1]); break;
          }
        }       
      }
      catch (Exception e)
      {
        /* silence */
      }
    }
    
    textureSize = new Size.Int(sw,sh);
  }
}
