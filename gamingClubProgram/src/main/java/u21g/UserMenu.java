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

    
    static User user;
    static FrameandCardHolder mainFrame;


public static void userMenu(User user, FrameandCardHolder mainFrame) {

    UserMenu.user = user;
    UserMenu.mainFrame = mainFrame;

    mainFrame.userMenuCard.removeAll();
     mainFrame.userMenuCard.setLayout(new FlowLayout());


    List <ItemTitle> itemTitles = LoadGames.loadGames();   


    JButton rentItem=new JButton("Rent an Item");//creating instance of JButton  
    rentItem.setPreferredSize(new Dimension(160,25));//x axis, y axis, width, height 
    rentItem.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e){
         
            new SearchBrowsetoRent(user, mainFrame);   
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
     
    JButton printHistory=new JButton("View my rental history");
    printHistory.setPreferredSize(new Dimension(160,25));//x axis, y axis, width, height 
    printHistory.addActionListener(new ActionListener() { //Perform action

        public void actionPerformed (ActionEvent e) {

            System.out.println("inside user menu");

            new DisplayCustomerHistory (user, mainFrame);
            // DatabasePath newDBPathInstance = DatabasePath.getInstance();
            // LoginScreen.loginScreen(mainFrame, newDBPathInstance.getPath());      
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