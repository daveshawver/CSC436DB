package u21g;

import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;



public class BindButtonclicktoExitingRegistration implements ActionListener, KeyListener {
    
    FrameandCardHolder mainFrame;
    
    
    BindButtonclicktoExitingRegistration(FrameandCardHolder mainFrame){
        this.mainFrame = mainFrame;
    }

    public void actionPerformed(ActionEvent e){ 
        LoginScreen.loginScreen(mainFrame);          
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    if (e.getKeyCode()==KeyEvent.VK_ENTER){
        LoginScreen.loginScreen(mainFrame);
        }
    }

    public void keyReleased(KeyEvent e){
    }

    public void keyTyped(KeyEvent e){
    }

}
