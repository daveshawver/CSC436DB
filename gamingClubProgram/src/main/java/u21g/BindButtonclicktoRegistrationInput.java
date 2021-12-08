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



public class BindButtonclicktoRegistrationInput implements ActionListener, KeyListener {
    
    JTextField firstNameTextField, lnameTextField, unameTextField, uriidTextField, emailTextField;
    String firstName, lastName, userName, password, email, uriTextID;
    int uriid;
    JPasswordField pwField;
    FrameandCardHolder mainFrame;
    


    BindButtonclicktoRegistrationInput(JTextField firstNameTextField, JTextField lnameTextField, 
    JTextField unameTextField, JPasswordField pwTextField, JTextField uriidTextField, JTextField 
    emailTextField, FrameandCardHolder mainFrame){
        this.firstNameTextField = firstNameTextField;
        this.lnameTextField = lnameTextField;
        this.unameTextField = unameTextField; ;
        this.pwField = pwTextField;
        this.uriidTextField = uriidTextField;
        this.emailTextField = emailTextField;
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



        boolean uriidGood = false, lnameGood = false, pwGood = false, fnameGood = false, emailGood = false, unameGood = false;

            try {                
            firstName = firstNameTextField.getText();
            lastName = lnameTextField.getText();
            userName = unameTextField.getText();
            char[] charPassword = pwField.getPassword(); //Store password entered by the user in the variable "password"
            password = String.valueOf(charPassword);
            email = emailTextField.getText();
            uriTextID = uriidTextField.getText();
            uriid = Integer.parseInt(uriTextID);


            uriidGood = false;
            lnameGood = false;
            pwGood = false;
            fnameGood = false; 
            emailGood = false; 
            unameGood = false;

        if (!(RegistUserInputValidator.validateUriID(uriTextID))){

            JOptionPane.showMessageDialog(mainFrame, "Please enter a valid number for URIID");
    
        } else {uriidGood = true;}

        if (!(RegistUserInputValidator.validateLastName(lastName))){

            JOptionPane.showMessageDialog(mainFrame, "Please enter a valid last name");
            
        } else {lnameGood = true;}

        if (!(RegistUserInputValidator.validateFirstName(firstName))){
            JOptionPane.showMessageDialog(mainFrame, "Please enter a valid first name");
            
        } else { fnameGood = true;}

        if (!(RegistUserInputValidator.validateEmail(email))){

            JOptionPane.showMessageDialog(mainFrame, "Please enter a valid email");
            
        } else {emailGood = true;}

        if (!(RegistUserInputValidator.validateUserName(userName))){

            JOptionPane.showMessageDialog(mainFrame, "Please enter a valid user name");
           
        } else {unameGood = true;}

        if (!(RegistUserInputValidator.validatePassword(password))){

            JOptionPane.showMessageDialog(mainFrame, "Please enter a valid password");
            
        } else {pwGood = true;}
} catch(NumberFormatException ex){  
    JOptionPane.showMessageDialog(mainFrame, "Please enter a valid uriid");
}


    if (uriidGood && lnameGood && pwGood && fnameGood && emailGood && unameGood){

        User newUser = new User(uriid, firstName, lastName, userName, password, email);
        AddUser.insert(newUser);
        ToastMessage message = new ToastMessage("User successfully registered!!", mainFrame);
        message.display();
        UserMenu.userMenu(newUser, mainFrame);
    }
}

}
