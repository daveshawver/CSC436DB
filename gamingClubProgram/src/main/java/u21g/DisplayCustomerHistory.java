package u21g;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.RowFilter;


public class DisplayCustomerHistory {

   public void resizeColumnWidth(JTable table) {
      final TableColumnModel columnModel = table.getColumnModel();
      for (int column = 0; column < table.getColumnCount(); column++) {
          int width = 15; // Min width
          for (int row = 0; row < table.getRowCount(); row++) {
              TableCellRenderer renderer = table.getCellRenderer(row, column);
              Component comp = table.prepareRenderer(renderer, row, column);
              width = Math.max(comp.getPreferredSize().width +1 , width);
          }
          if(width > 300)
              width=300;
          columnModel.getColumn(column).setPreferredWidth(width);
      }
  }



    public FrameandCardHolder mainFrame;
    private JScrollPane jsp;

      private static final int PADDING = 3;
      public DisplayCustomerHistory(User user, FrameandCardHolder mainFrame) {




         this.mainFrame = mainFrame;

         mainFrame.displayHistoryCard.removeAll();
         mainFrame.displayHistoryCard.setLayout(new FlowLayout(FlowLayout.CENTER));

         JLabel instructionMsg = new JLabel("Customer History for "+user.getFirstName()
         + " "+user.getLastName(), JLabel.CENTER);

         instructionMsg.setFont(new Font("Veranda", Font.BOLD, 24));
         instructionMsg.setPreferredSize(new Dimension(500,40));

         GridBagConstraints gbc = new GridBagConstraints();
         JPanel pan = new JPanel(new GridBagLayout());
         gbc.weightx = 1;
         gbc.weighty = 1;
         gbc.gridx = 0;
         gbc.gridy = 0;
         pan.add(instructionMsg, gbc);
        gbc.gridy = 1;
        pan.add(Box.createRigidArea(new Dimension(0,20)), gbc);
        
         
        LoadCustHistory.loadCustHistory(user, mainFrame);


         mainFrame.custHistSorter = new TableRowSorter<DefaultTableModel>(mainFrame.custHistModel);
         
         if (mainFrame.custHistoryTable == null) {mainFrame.custHistoryTable = new JTable(mainFrame.custHistModel){

               public boolean editCellAt(int row, int column, java.util.EventObject e){
                  return false;
               }
            };  
               this.mainFrame.custHistoryTable.setRowSorter(mainFrame.custHistSorter);
  
               mainFrame.custHistSorter.setSortsOnUpdates(true);

               JTableHeader header = mainFrame.custHistoryTable.getTableHeader();
               header.setFont(new Font("Veranda", Font.PLAIN, 20));
               mainFrame.custHistoryTable.setPreferredScrollableViewportSize(new Dimension(1600,600));
               mainFrame.custHistoryTable.setRowHeight(40);
               resizeColumnWidth(mainFrame.custHistoryTable);
               mainFrame.custHistoryTable.setFont(new Font("Veranda", Font.PLAIN, 20));
               List<RowSorter.SortKey> sortKeys = new ArrayList<>();
               int columnIndexToSort = 4;
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               mainFrame.custHistSorter.setSortKeys(sortKeys);
               }
         else {
            
            mainFrame.custHistoryTable.setModel(mainFrame.custHistModel);
            mainFrame.custHistModel.fireTableDataChanged();
         }
         
         if (jsp == null) {
         jsp = new JScrollPane(mainFrame.custHistoryTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
         JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         jsp.setPreferredSize(new Dimension(1600, 600));
         mainFrame.custHistSorter.sort();

         gbc.gridy = 2;
         pan.add(Box.createRigidArea(new Dimension(0,20)), gbc);
         gbc.gridy = 3;
         pan.add(jsp, gbc);
     
        JButton returnToUserMenu=new JButton("Return to user menu");
      returnToUserMenu.setPreferredSize(new Dimension(150, 35));
      
      returnToUserMenu.addActionListener(new ActionListener() { //Perform action
                  public void actionPerformed (ActionEvent e) {
                        UserMenu.userMenu(user,mainFrame);
                   }});
      gbc.gridy = 4;
      pan.add(Box.createRigidArea(new Dimension(0,30)), gbc);
      gbc.gridy = 5;
      pan.add(returnToUserMenu, gbc);


      mainFrame.displayHistoryCard.add(pan);
      mainFrame.displayHistoryCard.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
      mainFrame.displayHistoryCard.revalidate();
      mainFrame.displayHistoryCard.repaint();
      mainFrame.showCard(mainFrame.DISPLAYHISTORY);
   }
}   
}
