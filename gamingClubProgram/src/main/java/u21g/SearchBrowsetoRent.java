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


public class SearchBrowsetoRent {
    private JTextField jtf;
    private JLabel searchLbl;
    private TableRowSorter<DefaultTableModel> sorter;
    private JScrollPane jsp;

      private static final int PADDING = 3;
      public SearchBrowsetoRent(DefaultTableModel model, User user, FrameandCardHolder mainFrame) {
         mainFrame.searchBrowsetoRentCard.removeAll();
         mainFrame.searchBrowsetoRentCard.setLayout(new FlowLayout(FlowLayout.CENTER));

         JLabel instructionMsg = new JLabel("To rent a game, doubleclick on it...", JLabel.CENTER);
         instructionMsg.setFont(new Font("Veranda", Font.BOLD, 24));
         instructionMsg.setPreferredSize(new Dimension(500,40));

         GridBagConstraints gbc = new GridBagConstraints();
         JPanel pan = new JPanel(new GridBagLayout());
         gbc.weightx = 1;
         gbc.weighty = 1;
         gbc.gridx = 0;
         gbc.gridy = 0;
         pan.add(instructionMsg, gbc);

         JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.LEFT, PADDING, PADDING));
         searchBar.setPreferredSize(new Dimension(500,40));
   
         searchLbl = new JLabel("Search:  ", JLabel.RIGHT);
         searchLbl.setFont(new Font("Veranda", Font.BOLD, 24));
         searchLbl.setPreferredSize(new Dimension(136,40));
         searchBar.add(searchLbl);
         jtf = new JTextField(15);
         jtf.setPreferredSize(new Dimension(300,40));
         jtf.setFont(new Font("Veranda", Font.PLAIN, 24));

         searchBar.add(jtf);
         gbc.gridy = 1;
         pan.add(Box.createRigidArea(new Dimension(0,20)), gbc);

         gbc.gridx = 0;
         gbc.gridy = 2;
         gbc.ipady = 1;

         pan.add(searchBar, gbc);
         gbc.gridy = 3;
         pan.add(Box.createRigidArea(new Dimension(0,20)), gbc);
         
         sorter = new TableRowSorter<DefaultTableModel>(model);
         
         if (mainFrame.searchBrowseTable == null) {mainFrame.searchBrowseTable = new JTable(model){
               public boolean editCellAt(int row, int column, java.util.EventObject e) {
                  return false;
               }
            };
         mainFrame.searchBrowseTable.setRowSorter(sorter);
         mainFrame.sorter = sorter;
         JTableHeader header = mainFrame.searchBrowseTable.getTableHeader();
         header.setFont(new Font("Veranda", Font.PLAIN, 20));
         mainFrame.searchBrowseTable.setPreferredScrollableViewportSize(new Dimension(300,500));
         mainFrame.searchBrowseTable.setRowHeight(40);
         mainFrame.searchBrowseTable.setFont(new Font("Veranda", Font.PLAIN, 20));
         List<RowSorter.SortKey> sortKeys = new ArrayList<>();
         int columnIndexToSort = 0;
         sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
         sorter.setSortKeys(sortKeys);
         }
         else {
            mainFrame.searchBrowseTable.setModel(model);
         }

         mainFrame.searchBrowseTable.addMouseListener(new BindDoubleClicktoRent(mainFrame, user));
         
         if (jsp == null) {
         jsp = new JScrollPane(mainFrame.searchBrowseTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
         JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         jsp.setPreferredSize(new Dimension(500, 450));
         mainFrame.sorter.sort();
         jtf.getDocument().addDocumentListener(new BindTextChangetoSearchandFilter(jtf, sorter));
         }

         gbc.gridy = 4;
         pan.add(Box.createRigidArea(new Dimension(0,20)), gbc);
         gbc.gridy = 5;
         pan.add(jsp, gbc);
      JButton returnToUserMenu=new JButton("Return to user menu");
      returnToUserMenu.setPreferredSize(new Dimension(150, 35));
      
      returnToUserMenu.addActionListener(new ActionListener() { //Perform action
                  public void actionPerformed (ActionEvent e) {
                        UserMenu.userMenu(user,mainFrame);
                   }});
      gbc.gridy = 6;
      pan.add(Box.createRigidArea(new Dimension(0,30)), gbc);
      gbc.gridy = 7;
      pan.add(returnToUserMenu, gbc);
      mainFrame.searchBrowsetoRentCard.add(pan);
      mainFrame.searchBrowsetoRentCard.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
      mainFrame.searchBrowsetoRentCard.revalidate();
      mainFrame.searchBrowsetoRentCard.repaint();
      mainFrame.showCard(mainFrame.SEARCHBROWSETORENT);

    }
}

