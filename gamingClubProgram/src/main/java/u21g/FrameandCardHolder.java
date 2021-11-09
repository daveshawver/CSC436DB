package u21g;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FrameandCardHolder extends JFrame {

    final public String LOGINSCREENCARD = "Login";
    final public String USERMENUCARD = "User Menu";
    final public String SEARCHBROWSETORENT = "Search/Browse to Rent";
    final public String RENTANITEMCARD = "Rent an Item";
    final public String ADMINMENUCARD = "Admin Menu";
    final public String REGISTRATIONCARD = "Registration Card";
    final public String RETURNITEMCARD = "Return Item";
    public String database_path;
    public JPanel loginScreenCard, userMenuCard, searchBrowsetoRentCard, 
    rentAnItemCard, cardHolderPanel, registrationCard, returnItemCard;
    public JTable searchBrowseTable;
    public TableRowSorter sorter;

    FrameandCardHolder(String title){

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginScreenCard = new JPanel();


        userMenuCard = new JPanel(new FlowLayout());
        searchBrowsetoRentCard = new JPanel();
        rentAnItemCard = new JPanel();
        registrationCard = new JPanel();
        cardHolderPanel = new JPanel(new MyCardLayout());        
        returnItemCard = new JPanel();

        cardHolderPanel.add(loginScreenCard, LOGINSCREENCARD);
        cardHolderPanel.add(userMenuCard, USERMENUCARD);
        cardHolderPanel.add(rentAnItemCard, RENTANITEMCARD);
        cardHolderPanel.add(searchBrowsetoRentCard, SEARCHBROWSETORENT);
        cardHolderPanel.add(registrationCard, REGISTRATIONCARD);
        cardHolderPanel.add(returnItemCard, RETURNITEMCARD);
    }

    void showCard(String cardIdentifier) {
        MyCardLayout cl = (MyCardLayout)(cardHolderPanel.getLayout());
        cl.show(cardHolderPanel, cardIdentifier);
        add(cardHolderPanel);
        pack();
        setLocationRelativeTo(null);
    }
}