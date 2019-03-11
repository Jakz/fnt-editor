package com.github.jakz.fnt_editor.data;

import java.awt.image.BufferedImage;
import java.util.List;

import com.pixbits.lib.lang.Pair;

public class Page
{
  int id;
  String file;
  BufferedImage image;
  
  public String toString()
  {
    return String.format("page id=%d image=\"%s\"", id, file);
  }
  
  public static Page parse(String line)
  {
    List<Pair<String, String>> tokens = Parser.parseLine(line);
    
    try
    {
      if (tokens.size() == 3 && tokens.get(0).first.equals("page"))
      {
        Page page = new Page();
        
        for (Pair<String, String> token : tokens)
        {
          switch (token.first)
          {
            case "id":  page.id = Integer.parseInt(token.second); break;
            case "file": page.file = token.second; break;
          }
        }
        
        return page;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
    
    return null;
  }
}
