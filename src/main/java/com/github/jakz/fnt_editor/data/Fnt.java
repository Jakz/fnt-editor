package com.github.jakz.fnt_editor.data;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.github.jakz.fnt_editor.App;
import com.pixbits.lib.ui.table.DataSource;

public class Fnt implements DataSource<Glyph>
{
  private final Map<Character, Glyph> glyphs;
  private final ArrayList<Glyph> array;
  private final List<Page> pages;
  
  public Fnt()
  {
    glyphs = new TreeMap<>();
    array = new ArrayList<>();
    pages = new ArrayList<>();
  }
  
  private void cacheMapToArray()
  {
    array.clear();
    array.ensureCapacity(glyphs.size());
    glyphs.values().stream().forEach(array::add);
  }
  
  public static Fnt load(Path fntPath) throws IOException
  {
    Fnt fnt = new Fnt();
    
    Files.lines(fntPath).forEach(line -> {
      if (line.startsWith("char "))
      {
        Glyph glyph = Glyph.parse(line);
        
        if (glyph != null)
          fnt.glyphs.put(glyph.utf16, glyph);
      }
      else if (line.startsWith("page "))
      {
        Page page = Page.parse(line);
        
        if (page != null)
          fnt.pages.add(page);
      }
    });
    
    fnt.cacheMapToArray();
   
    App.log("Loaded %s with %d glyphs in %d pages", fntPath.getFileName().toString(), fnt.glyphs.size(), fnt.pages.size());
    
    return fnt;
  }


  @Override public Iterator<Glyph> iterator() { return array.iterator(); }
  @Override public Glyph get(int index) { return array.get(index); }
  @Override public int size() { return array.size(); }
  @Override public int indexOf(Glyph object) { return array.indexOf(object); }
}
