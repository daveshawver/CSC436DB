package u21g;


import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;


public class LoginScreen {

    public static void main(String[] args){
        FrameandCardHolder mainFrame = new FrameandCardHolder("URI GC Item Rental Database");
        loginScreen(mainFrame, args[0]);
    }
    public static void loginScreen(FrameandCardHolder mainFrame, String dbPath) {

        DatabasePath newPathInstance = DatabasePath.getInstance();
        newPathInstance.setPath(dbPath);
        
        mainFrame.loginScreenCard.removeAll();
        mainFrame.loginScreenCard.setLayout(new BoxLayout(mainFrame.loginScreenCard, BoxLayout.LINE_AXIS));
        mainFrame.loginScreenCard.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12)); 

        
        JLabel l1,l2;  
        l1=new JLabel("Username");  //Create label Username      
        l2=new JLabel("Password");  //Create label Password
        
        JPanel loginLabelPanel = new JPanel();

        loginLabelPanel.setLayout(new BoxLayout(loginLabelPanel, BoxLayout.PAGE_AXIS));
        loginLabelPanel.add(l1);
        loginLabelPanel.add(Box.createRigidArea(new Dimension(0,13)));
        loginLabelPanel.add(l2);

        JTextField F_user = new JTextField(); //Create text field for username
        F_user.setPreferredSize(new Dimension(150,25));
        JPasswordField F_pass=new JPasswordField(); //Create text field for password
        F_pass.setPreferredSize(new Dimension(120,25));

        JPanel loginTextFieldPanel = new JPanel();
        loginTextFieldPanel.setLayout(new BoxLayout(loginTextFieldPanel, BoxLayout.PAGE_AXIS));
        loginTextFieldPanel.add(F_user);
        loginTextFieldPanel.add(Box.createRigidArea(new Dimension(0,13)));
        loginTextFieldPanel.add(F_pass);


        JButton login_but=new JButton("Login");//creating instance of JButton for Login Button
        JButton register_but=new JButton("Register");

        BindButtonclicktoLoginInput clickKeyListener = new BindButtonclicktoLoginInput(F_user, F_pass, mainFrame);

        login_but.addActionListener(clickKeyListener);
        
        login_but.addKeyListener(clickKeyListener);

        register_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new UserRegistration(mainFrame);
            }
        });

        JPanel horizPanel = new JPanel();
        horizPanel.setLayout(new BoxLayout(horizPanel, BoxLayout.LINE_AXIS));
        horizPanel.add(loginLabelPanel); //add password
        horizPanel.add(Box.createRigidArea(new Dimension(13,0)));
        horizPanel.add(loginTextFieldPanel);
        horizPanel.add(Box.createRigidArea(new Dimension(13,0)));

        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.add(horizPanel);
        outerPanel.add(Box.createRigidArea(new Dimension(0,13)));
        outerPanel.add(login_but);
        outerPanel.add(Box.createRigidArea(new Dimension(0,9)));
        outerPanel.add(register_but);
        mainFrame.loginScreenCard.add(outerPanel);
        mainFrame.loginScreenCard.revalidate();
        mainFrame.loginScreenCard.repaint();
        mainFrame.showCard(mainFrame.LOGINSCREENCARD);
        mainFrame.getRootPane().setDefaultButton(login_but);
        mainFrame.setVisible(true);//making the frame visible 
    }
}


    /*

    //Create scanner objects
    Scanner input = new Scanner(System.in);
    Scanner credentials = new Scanner(System.in);

    //Ask user if they are a returning user
    System.out.println("Welcome to the URI Game Club! \n");
    System.out.println("Enter selection:");
    System.out.println("1 - Log In");
    System.out.println("2 - Register");

    int initialInput = input.nextInt();

    //Using this print statement to indicate a new screen
    System.out.println("\n \n \n -------- New Screen ------ \n \n \n");

    // If statement depending on if user wants to log in
        if(initialInput == 1){
            System.out.println("Enter username:");
            String username = credentials.nextLine();

            System.out.println("Enter password:");
            String password = credentials.nextLine();
        }
        else{
        
            System.out.println("Enter first name");
            String firstName = credentials.nextLine();
            System.out.println("Enter last name");
            String lastName = credentials.nextLine();
            System.out.println("Enter URI ID");
            String uriID = credentials.nextLine();
            int uri_ID=Integer.parseInt(uriID);  
            System.out.println(uri_ID);
            System.out.println("Enter username");
            String userName = credentials.nextLine();
            System.out.println("Enter password");
            String password = credentials.nextLine();
            System.out.println("Enter email");
            String email = credentials.nextLine();

            User newUser = new User(uri_ID,userName,password,email,firstName,lastName);

            AddUser newAdd = new AddUser();

            newAdd.insert(newUser);

            System.out.println("\n\nThank you for registering! Press any key to return to the login screen");
            // This is the part I'm having trouble on. Not sure how to have users return to inital screen after putting information
            // I tried a labeled break but it doesn't seem to be working

        }

    }
}
*/