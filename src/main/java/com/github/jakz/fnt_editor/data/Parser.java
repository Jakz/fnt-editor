package com.github.jakz.fnt_editor.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.jakz.fnt_editor.App;
import com.pixbits.lib.lang.Pair;

public class Parser
{
  public static char parseCharValue(String text)
  {
    try
    {
      if (text.startsWith("0x"))
        return (char)Integer.parseInt(text.substring(2), 16);
      else
        return (char)Integer.parseInt(text);
    }
    catch (Exception e)
    {
      return 0;
    }
  }
  
  public static List<Pair<String, String>> parseLine(String text)
  {
    String[] tokens = text.split(" ");
    List<Pair<String, String>> grouped = new ArrayList<>();
    String key = "", current = "";
    boolean inQuote = false;
    
    for (int i = 0; i < tokens.length; ++i)
    {
      String token = tokens[i];

      if (inQuote)
      {
        if (token.endsWith("\""))
        {
          current = current + token.substring(0, token.length()-1);
          grouped.add(new Pair<>(key, current));
          current = "";
          inQuote = false;
        }
        else
          current = current + token;
      }
      else
      {
        
        if (token.indexOf("=") == -1)
          grouped.add(new Pair<>(token, ""));
        else
        {
          String[] split = token.split("=");
          
          int quoteIndex = token.indexOf('\"');
          int lastQuoteIndex = token.lastIndexOf('\"');
          inQuote = quoteIndex != -1;
          
          if (inQuote)
          {
            if (quoteIndex != lastQuoteIndex)
            {
              grouped.add(new Pair<>(split[0], split[1].substring(1,  split[1].length() - 1)));
              inQuote = false;
            }
            else
            {
              key = split[0];
              current = split[1].substring(1,  split[1].length());
            }
          }
          else
          {
            grouped.add(new Pair<>(split[0], split[1]));
          }
        }
      }
    }
    
    String groupedString = grouped.stream()
        .map(p -> "(" + p.first + "," + p.second + ")")
        .collect(Collectors.joining(",", "[", "]"));
    
    App.log("Parsed '%s' into %s", text, groupedString);
    
    return grouped;
  }
}
