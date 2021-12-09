package u21g;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

public class BindTextChangetoSearchandFilter implements DocumentListener {

    JTextField jtf;
    TableRowSorter<DefaultTableModel> sorter;

    BindTextChangetoSearchandFilter (JTextField jtf, TableRowSorter<DefaultTableModel> sorter) {
        this.jtf = jtf;
        this.sorter = sorter;

    }
    @Override
    public void insertUpdate(DocumentEvent e) {
       search(jtf.getText());
    }
    @Override
    public void removeUpdate(DocumentEvent e) {
       search(jtf.getText());
    }
    @Override
    public void changedUpdate(DocumentEvent e) {
       search(jtf.getText());
    }
    public void search(String str) {
       if (str.length() == 0) {
          sorter.setRowFilter(null);
       } else {
          sorter.setRowFilter(RowFilter.regexFilter(str));
       }
    }
}

