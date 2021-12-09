package u21g;

import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

/**
 * Does various function regarding private members numerator and denominator
 * @param F_ticketID JTextField object - Holds user entered ticket ID
 * @param mainFrame FrameandCardHolder object - Makes sure the instance of the return feature window is it's standalone window
 * @param uniqueCopyIDint Integer - Used to convert derived copy ID that's retreived as a string and converted into an int for later use
 * @param titlenumINT Integer - Used to convert derived title ID that's retreived as a string and converted into an int for later user
 * @param outcome Integer - Keeps track of what the result of the user's input into the tickt ID text field was. Primarily used for testing 
 * @param test Boolean - Identifies if the instance the BindButtonClickToReturn object is created in is for testing purposes
 */

public class BindButtonClickToReturn implements ActionListener, KeyListener{

    JTextField F_ticketID;
    FrameandCardHolder mainFrame;
    int uniqueCopyIDint;
    int titlenumINT;
    int outcome;
    User user;
    Boolean test = false;

    /**
     * Overloaded constructor that's initiated for testing purposes
     *
     */

    BindButtonClickToReturn(JTextField F_ticketID, FrameandCardHolder mainFrame, User user, Boolean test){
        this.F_ticketID = F_ticketID;
        this.mainFrame = mainFrame;
        this.test = test;
        this.user = user;

        if(test){
            databaseUpdaterOnReturn(mainFrame, user);  
        }
        else{
            this.test = false;
        }
    }

    /**
     * Standard contstructor for BindButtonClickToReturn
     *
     */
    BindButtonClickToReturn(JTextField F_tickerID, FrameandCardHolder mainFrame){
        this.F_ticketID = F_tickerID;
        this.mainFrame = mainFrame;
    }

    /**
     * If button is pressed, execute databaseUpdaterOnReturn(); 
     */

    public void actionPerformed(ActionEvent e){ 
        databaseUpdaterOnReturn(mainFrame, user);          
    }

    /**
     * If enter is pressed, execute databaseUpdaterOnReturn(); 
     */

    @Override
    public void keyPressed(KeyEvent e) {
    if (e.getKeyCode()==KeyEvent.VK_ENTER){
        databaseUpdaterOnReturn(mainFrame, user);  
        }
    }

    /**
     * These two functions are needed as the class implements ActionListener
     * and KeyListener 
     */
    public void keyReleased(KeyEvent e){
    }

    public void keyTyped(KeyEvent e){
    }

    /**
     * Keeps track of what the result of the user's input into the tickt ID text field was. Primarily used for testing 
     * @return Result of user input. (0) if empty input, (1) if wrong input, (2) if right input
     */
    public int getOutcome(){
        return outcome;
    }

    /**
     * Processes the string that the user entered
     * Order of processes:
     * 1. Checks if anything was entered, if not displays error message
     * 2. Checks if ticket is in the database, if not display error message
     * 3. Display message notifying user that return was sucessfull
     * 4. Update the copy status to 1, to signify that copy is now available again
     * 5. If the title's availability status was 0 prior to return, change to 1 as it is now available again
     * 6. Increase available copies of the title by 1
     */

    public void databaseUpdaterOnReturn(FrameandCardHolder mainFrame, User user){
        String ticketIDString = F_ticketID.getText();


        // If nothing entered
        if(ticketIDString.equals("")) {
            outcome = 0;
            if(test == false){
            JOptionPane.showMessageDialog(null,"Please enter ticket number");
            }
            
        }
        // If a string is entered
        else{
            String ticketpwsql = "SELECT * FROM tickets WHERE ticket_num= ?";
            int ticketID = Integer.parseInt(ticketIDString);  
            try (Connection conn = Connect.connect();
            PreparedStatement pstmt = conn.prepareStatement(ticketpwsql)){
                pstmt.setInt(1, ticketID);
                try(ResultSet rs = pstmt.executeQuery()){

                    // If ticket is not in system
                    if(rs.next() == false){
                        outcome = 1;
                        if(test == false){
                            JOptionPane.showMessageDialog(null,"Ticket not in system");
                        }
                    }
                    // If ticket is in system
                    else{
                        outcome = 2;
                        if(test == false){
                            JOptionPane.showMessageDialog(null ,"Successful return!");
                        
                            // Update copy status to 1

                            //change status of ticket
                            // Get copy_ID of user input ticket
                            String newCommand = "UPDATE tickets SET currentlyOut = '0' WHERE ticket_num = ?";
                            try(PreparedStatement ticketpstmt2 = conn.prepareStatement(newCommand)){
                                ticketpstmt2.setInt(1, ticketID);
                                ResultSet rs3 = ticketpstmt2.executeQuery();
                                    
                            }catch(SQLException se){
                                System.out.println(se.getMessage());
                            }

                                // Get copy_ID of user input ticket
                            String ticketIDpwsql = "SELECT copy_id FROM tickets WHERE ticket_num= ?";
                            try(PreparedStatement ticketpstmt = conn.prepareStatement(ticketIDpwsql)){
                                ticketpstmt.setInt(1, ticketID);
                                ResultSet rs2 = ticketpstmt.executeQuery();
                                
                                    // Set value of copy_ID to a string
                                String uniqueCopyID = rs2.getString("copy_id");
                                uniqueCopyIDint = Integer.valueOf(uniqueCopyID);
                                    
                            }catch(SQLException se){
                                System.out.println(se.getMessage());
                            }
                                // Execute database update statement
                            String updatepwsql = "UPDATE GAME_COPY SET is_available = '1' WHERE copy_id = ?";
                            try(PreparedStatement copyIDUpdatepstmt = conn.prepareStatement(updatepwsql)){
                                copyIDUpdatepstmt.setInt(1, uniqueCopyIDint);
                                copyIDUpdatepstmt.executeUpdate();
                            }catch(SQLException se){
                                System.out.println(se.getMessage());
                            }

                            // Increase available copies by 1
                                // Get title_num from copy_ID
                            String titlenumpwsql = "SELECT title_num FROM GAME_COPY WHERE copy_id= ?";
                            try(PreparedStatement titlenumpstmt = conn.prepareStatement(titlenumpwsql)){
                                titlenumpstmt.setInt(1, uniqueCopyIDint);
                                ResultSet rs3 = titlenumpstmt.executeQuery();

                                String titlenum = rs3.getString("title_num");
                                titlenumINT = Integer.valueOf(titlenum);
                            }catch(SQLException se){
                                System.out.println(se.getMessage());
                            }

           
                                // Insrease Available Copies
                            String copiespwsql = "UPDATE game_title SET available_copies = available_copies + 1 WHERE title_num = ?";
                            try(PreparedStatement copiesUPDATEpstmt = conn.prepareStatement(copiespwsql)){
                                copiesUPDATEpstmt.setInt(1, titlenumINT);
                                copiesUPDATEpstmt.executeUpdate();
                            }catch(SQLException se){
                                System.out.println(se.getMessage());
                            }
                        }
                        }
                    }
                catch(SQLException se){
                    System.out.println(se.getMessage());
                }


            } catch (SQLException se){
                System.out.println(se.getMessage());
            }
        }
    }
}