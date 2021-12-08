package u21g;

import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;



public class BindButtonclicktoLoginInput implements ActionListener, KeyListener {

    JTextField F_user;
    JPasswordField F_pass;
    FrameandCardHolder mainFrame;

    BindButtonclicktoLoginInput(JTextField F_user, JPasswordField F_pass, FrameandCardHolder mainFrame){
        this.F_user = F_user;
        this.F_pass = F_pass;
        this.mainFrame = mainFrame;
    }

    public void actionPerformed(ActionEvent e){ 
        userNameandPasswordCheckerNavigator();          
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    if (e.getKeyCode()==KeyEvent.VK_ENTER){
        userNameandPasswordCheckerNavigator();
        }
    }

    public void keyReleased(KeyEvent e){
    }

    public void keyTyped(KeyEvent e){
    }

    public void userNameandPasswordCheckerNavigator(){
        String username = F_user.getText(); //Store username entered by the user in the variable "username"
        char[] charPassword = F_pass.getPassword(); //Store password entered by the user in the variable "password"
        String password = String.valueOf(charPassword); 

        if(username.equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter username"); //Display dialog box with the message
        } 

        else if(password.equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter password"); //Display dialog box with the message
        }

        else { //If both the fields are present then to login the user, check wether the user exists already
        
            String unamepwsql = "SELECT * FROM userTableLogin WHERE username= ? AND password= ?";

        try (Connection conn=Connect.connect();
            PreparedStatement pstmt = conn.prepareStatement(unamepwsql)){
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if(rs.next()==false) { //Move pointer below
                        int a=JOptionPane.showConfirmDialog(mainFrame ,"Incorrect username and password combination\n\n Would you like to register?");  
                        if(a==JOptionPane.YES_OPTION){  
                           UserRegistration R = new UserRegistration(mainFrame);   //Display Message
                        }
                    }
                    else {
                        //JOptionPane.showMessageDialog(null,"Correct username and password!!");

                        User user = UserLoader.loadUser(rs.getString("uriid"));

                        if(user.getAdmin()==1) {
                            AdminMenu.adminMenu();
                        }

                        else {
                            UserMenu.userMenu(user, mainFrame);
                        }
                    }

                } catch (SQLException se) {
                    System.out.println(se.getMessage());
                }
            } catch (SQLException se) {
                    System.out.println(se.getMessage());
                }              
            }   
    }
}