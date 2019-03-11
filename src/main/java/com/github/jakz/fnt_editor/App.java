package com.github.jakz.fnt_editor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.jakz.fnt_editor.data.Fnt;
import com.github.jakz.fnt_editor.ui.GlyphTablePanel;
import com.pixbits.lib.ui.UIUtils;
import com.pixbits.lib.ui.WrapperFrame;

/**
 * Hello world!
 *
 */
public class App 
{ 
  public static void main( String[] args )
  {  
    try
    {
      Path fntPath = Paths.get("smalltooltip.fnt");
      Fnt fnt = Fnt.load(fntPath);
      
      UIUtils.setNimbusLNF();
      GlyphTablePanel table = new GlyphTablePanel();
      table.setData(fnt);
      WrapperFrame<?> frame = UIUtils.buildFrame(table, "Glyphs");
      frame.exitOnClose();
      frame.setVisible(true);
    } 
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void log(String text, Object... args)
  {
    System.out.println(String.format(text, args));
  }
}
