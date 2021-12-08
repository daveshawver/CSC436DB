package u21g;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;
import java.util.List;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;




public class UserMenu {

public static void userMenu(User user, FrameandCardHolder mainFrame) {

    mainFrame.userMenuCard.removeAll();
     mainFrame.userMenuCard.setLayout(new FlowLayout());

    

    List <ItemTitle> itemTitles = LoadGames.loadGames();   
    Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();


    JButton rentItem=new JButton("Rent an Item");//creating instance of JButton  
    rentItem.setPreferredSize(new Dimension(160,25));//x axis, y axis, width, height 
    rentItem.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e){

            Connection connection = Connect.connect();
            String sql="SELECT game_name FROM game_title"; //Retreive data from database
            try {
                Statement stmt = connection.createStatement(); //connect to databasese librabry
                stmt=connection.createStatement();
                ResultSet rs=stmt.executeQuery(sql);

                while(rs.next()) {
                    Vector<Object> gameEntry = new Vector<Object>();
                    gameEntry.add(rs.getObject(1));
                    tableData.add(gameEntry);
                }
            }catch (SQLException e1) {
                // TODO Auto-generated catch block
                 JOptionPane.showMessageDialog(null, e1);
            }    

             connection = Connect.connect();
            sql="SELECT available_copies FROM game_title"; //Retreive data from database
            try {

                Statement stmt = connection.createStatement(); //connect to database librabry
                ResultSet rs=stmt.executeQuery(sql);
                Vector<Object> isAvailableColumn = new Vector<Object> ();

                Object obj = new Object();
                String available = "Available";
                String checkedOut = "Currently checked out";

                while (rs.next()) {
                    obj = rs.getObject(1);
                    if (!(obj.toString().equals("0"))) {
                        isAvailableColumn.add(available);
                    }
                    else {
                        isAvailableColumn.add(checkedOut);
                    }
                }

                for (int i=0; i < isAvailableColumn.size(); i++) {
                    tableData.get(i).add(isAvailableColumn.get(i));
                }
                


                Vector <String> columnNames = new Vector<String>();
                columnNames.add("Game Title");
                columnNames.add("Availability");

                DefaultTableModel gameTable = new DefaultTableModel(tableData, columnNames);

                SearchBrowsetoRent JTableSearch = new SearchBrowsetoRent(gameTable, user, mainFrame);

                       
            }catch (SQLException e1) {
                     JOptionPane.showMessageDialog(null, e1);
            }   
            }            
             
        }
    );

    JButton returnItem=new JButton("Return an Item");//creating instance of JButton  
    returnItem.setPreferredSize(new Dimension(160,25));//x axis, y axis, width, height 
    returnItem.addActionListener(new ActionListener() { //Perform action
        public void actionPerformed(ActionEvent e){

        mainFrame.returnItemCard.removeAll();

        JLabel ticket;
        ticket = new JLabel("Ticket Number");  // Create label Ticket Number  

        JButton returnToUserMenu=new JButton("Return to User Menu");
        returnToUserMenu.setPreferredSize(new Dimension(200,25));

        JPanel button_panel = new JPanel();

        returnToUserMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               userMenu(user, mainFrame);}});
       // UserMenu.userMenu(newUser, mainFrame);
        
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.PAGE_AXIS));
        button_panel.add(ticket);

        JTextField F_ticket = new JTextField(); // Create text field for ticket number
        F_ticket.setPreferredSize(new Dimension(150,25));

        JPanel returnTextFieldPanel = new JPanel();
        returnTextFieldPanel.setLayout(new BoxLayout(returnTextFieldPanel, BoxLayout.PAGE_AXIS));
        returnTextFieldPanel.add(F_ticket);


        JButton return_but=new JButton("Enter"); //creating instance of JButton for enter Button


        BindButtonClickToReturn clickKeyListener = new BindButtonClickToReturn(F_ticket, mainFrame);

        return_but.addActionListener(clickKeyListener);
        return_but.addKeyListener(clickKeyListener);
            
        JPanel horizPanel = new JPanel();
        horizPanel.setLayout(new BoxLayout(horizPanel, BoxLayout.LINE_AXIS));
        horizPanel.add(button_panel); //add password
        horizPanel.add(Box.createRigidArea(new Dimension(13,0)));
        horizPanel.add(returnTextFieldPanel);
        horizPanel.add(Box.createRigidArea(new Dimension(13,0)));

        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.add(horizPanel);
        outerPanel.add(Box.createRigidArea(new Dimension(0,13)));
        outerPanel.add(return_but);
        outerPanel.add(returnToUserMenu);
        mainFrame.returnItemCard.add(outerPanel);
        mainFrame.returnItemCard.revalidate();
        mainFrame.returnItemCard.repaint();
        mainFrame.showCard(mainFrame.RETURNITEMCARD);
        mainFrame.getRootPane().setDefaultButton(return_but);
        mainFrame.setVisible(true);//making the frame visible 
            
        }
    }
    );
     
    JButton printHistory=new JButton("Print rental history");
    printHistory.setPreferredSize(new Dimension(160,25));//x axis, y axis, width, height 
    printHistory.addActionListener(new ActionListener() { //Perform action

        public void actionPerformed (ActionEvent e) {
            DatabasePath newDBPathInstance = DatabasePath.getInstance();
            LoginScreen.loginScreen(mainFrame, newDBPathInstance.getPath());      
        }

    });

    JButton returnLogin=new JButton("Return to login screen");
    returnLogin.setPreferredSize(new Dimension(160,25));//x axis, y axis, width, height 
    returnLogin.addActionListener(new ActionListener() { //Perform action

        public void actionPerformed (ActionEvent e) {
            DatabasePath newDBPathInstance = DatabasePath.getInstance();
            LoginScreen.loginScreen(mainFrame, newDBPathInstance.getPath());      
        }

    });

    mainFrame.userMenuCard.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
    mainFrame.userMenuCard.add(rentItem); // add view books
    mainFrame.userMenuCard.add(Box.createRigidArea(new Dimension(5, 0)));
    mainFrame.userMenuCard.add(returnItem); //add my books
    mainFrame.userMenuCard.add(Box.createRigidArea(new Dimension(5, 0)));
    mainFrame.userMenuCard.add(printHistory);
    mainFrame.userMenuCard.add(Box.createRigidArea(new Dimension(5, 0)));
    mainFrame.userMenuCard.add(returnLogin);
    mainFrame.userMenuCard.revalidate();
    mainFrame.userMenuCard.repaint();
    mainFrame.showCard(mainFrame.USERMENUCARD);
    }
}