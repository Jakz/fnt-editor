package com.github.jakz.fnt_editor.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.github.jakz.fnt_editor.data.Glyph;
import com.pixbits.lib.ui.table.ColumnSpec;
import com.pixbits.lib.ui.table.DataSource;
import com.pixbits.lib.ui.table.TableModel;

public class GlyphTablePanel extends JPanel
{
  private JTable table;
  private TableModel<Glyph> model;
  private DataSource<Glyph> data;
  
  public GlyphTablePanel()
  {    
    table = new JTable();
    table.setAutoCreateRowSorter(true);
    model = new TableModel<>(table, DataSource.empty());
    
    setLayout(new BorderLayout());
    add(new JScrollPane(table), BorderLayout.CENTER);
    
    model.addColumn(new ColumnSpec<>("Char", String.class, glyph -> "" + (int)glyph.utf16 + " (" + glyph.utf16 + ")"));
  }
  
  public void setData(DataSource<Glyph> data)
  {
    this.data = data;
    model.setData(data);
    model.fireTableDataChanged();
  }
}
