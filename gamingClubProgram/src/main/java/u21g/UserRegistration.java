package u21g;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;

public class UserRegistration {

    FrameandCardHolder mainFrame;
    JLabel title, firstNameLbl, lnameLbl, unameLbl, pwLbl, uriidLbl, emailLbl;
    JTextField firstNameTextField, lnameTextField, unameTextField, uriidTextField, emailTextField;
    JPasswordField pwField;
    JButton  registerButton, exitButton;
    
    private static final Insets WEST_INSETS = new Insets(6, 15, 6, 0);
    private static final Insets EAST_INSETS = new Insets(6, 0, 6, 15);

    public UserRegistration (FrameandCardHolder mainFrame){

        mainFrame.registrationCard.removeAll();

        //mainFrame.registrationCard.setPreferredSize(new Dimensionnew Dimension(1000, ));
        
        mainFrame.registrationCard.setLayout(new GridBagLayout());
        
        title = new JLabel("User Registration");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setPreferredSize(new Dimension(130, 30));
        //title.setLocation(180, 30);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10,10,20,10);
        mainFrame.registrationCard.add(title, gbc);
  
        firstNameLbl = new JLabel("First Name:");
        firstNameLbl.setFont(new Font("Arial", Font.PLAIN, 20));
        firstNameLbl.setPreferredSize(new Dimension(180, 20));
        //firstNameLbl.setLocation(100, 100);
        gbc.insets = WEST_INSETS;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
         mainFrame.registrationCard.add(firstNameLbl, gbc);
  
        firstNameTextField = new JTextField();
        firstNameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        firstNameTextField.setPreferredSize(new Dimension(300, 25));
        //firstNameTextField.setLocation(300, 100);
        gbc.insets = EAST_INSETS;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainFrame.registrationCard.add(firstNameTextField, gbc);
  
        lnameLbl = new JLabel("Last name:");
        lnameLbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lnameLbl.setPreferredSize(new Dimension(180, 20));
        //lnameLbl.setLocation(100, 300);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = WEST_INSETS;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        mainFrame.registrationCard.add(lnameLbl, gbc);
  
        lnameTextField = new JTextField();
        lnameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        lnameTextField.setPreferredSize(new Dimension(300, 25));
        //lnameTextField.setLocation(300, 300);
        gbc.insets = EAST_INSETS;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainFrame.registrationCard.add(lnameTextField, gbc);
  
        unameLbl = new JLabel("Desired Username:");
        unameLbl.setFont(new Font("Arial", Font.PLAIN, 20));
        unameLbl.setPreferredSize(new Dimension(180, 20));
        //unameLbl.setLocation(100, 180);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = WEST_INSETS;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        mainFrame.registrationCard.add(unameLbl, gbc);

        unameTextField = new JTextField();
        unameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        unameTextField.setPreferredSize(new Dimension(300, 25));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = EAST_INSETS;
        //unameTextField.setLocation(300, 180);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.gridwidth = 2;
        mainFrame.registrationCard.add(unameTextField, gbc);

        pwLbl = new JLabel("Desired Password:");
        pwLbl.setFont(new Font("Arial", Font.PLAIN, 20));
        pwLbl.setPreferredSize(new Dimension(180, 20));

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = WEST_INSETS;
        gbc.weightx = 1;
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        mainFrame.registrationCard.add(pwLbl, gbc);

        pwField = new JPasswordField();
        pwField.setFont(new Font("Arial", Font.PLAIN, 15));
        pwField.setPreferredSize(new Dimension(300, 25));
        //pwTextField.setLocation(300, 300);
        gbc.insets = EAST_INSETS;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.gridwidth = 2;
        mainFrame.registrationCard.add(pwField, gbc);

        uriidLbl = new JLabel("URI ID:");
        uriidLbl.setFont(new Font("Arial", Font.PLAIN, 20));
        uriidLbl.setPreferredSize(new Dimension(180, 20));
        //uriidLbl.setLocation(100, 300);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = WEST_INSETS;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        mainFrame.registrationCard.add(uriidLbl, gbc);

        uriidTextField = new JTextField();
        uriidTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        uriidTextField.setPreferredSize(new Dimension(300, 25));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = EAST_INSETS;
        //uriidTextField.setLocation(300, 300);
        gbc.gridwidth = 2;
        
        gbc.weightx = 1;
        gbc.gridx = 1;
        mainFrame.registrationCard.add(uriidTextField, gbc);

        emailLbl = new JLabel("Email address:");
        emailLbl.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLbl.setPreferredSize(new Dimension(180, 20));
        gbc.anchor = GridBagConstraints.WEST;
        //emailLbl.setLocation(100, 300);
        gbc.insets = WEST_INSETS;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy = 6;
        mainFrame.registrationCard.add(emailLbl, gbc);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        emailTextField.setPreferredSize(new Dimension(300, 25));
        //emailTextField.setLocation(300, 300);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = EAST_INSETS;
        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainFrame.registrationCard.add(emailTextField, gbc);

        registerButton = new JButton("Register New User");
        registerButton.setPreferredSize(new Dimension(60,30));
        BindButtonclicktoRegistrationInput registrationListener = new BindButtonclicktoRegistrationInput(firstNameTextField, 
        
        lnameTextField, unameTextField, pwField, uriidTextField, emailTextField, mainFrame);
        registerButton.addActionListener(registrationListener);
        registerButton.addKeyListener(registrationListener);
        
        exitButton = new JButton("Exit to main menu");
        registerButton.setPreferredSize(new Dimension(60,30));
        BindButtonclicktoExitingRegistration exitButtonListener= new BindButtonclicktoExitingRegistration(mainFrame);
        exitButton.addActionListener(exitButtonListener);
        exitButton.addKeyListener(exitButtonListener);


        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        mainFrame.registrationCard.add(registerButton, gbc);
        mainFrame.registrationCard.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        gbc.gridy = 8;
        mainFrame.registrationCard.add(Box.createRigidArea(new Dimension(0,13)), gbc);
        gbc.gridy = 9;
        mainFrame.registrationCard.add(exitButton, gbc);
        mainFrame.registrationCard.revalidate();
        mainFrame.registrationCard.repaint();
        mainFrame.showCard(mainFrame.REGISTRATIONCARD);
    }
}