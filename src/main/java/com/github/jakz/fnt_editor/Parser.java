package com.github.jakz.fnt_editor;

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
}
