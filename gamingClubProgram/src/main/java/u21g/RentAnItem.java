package u21g;
import java.lang.String;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.util.List;
import java.util.ArrayList;

public class RentAnItem {
    static boolean avail;
    Ticket newTicket;

    public void rentAnItem(String gameTitle, User user, FrameandCardHolder mainFrame) {

        this.newTicket = new Ticket();

        avail = true;

        // check to make sure game is available.
        String retrieveGameStatus = "SELECT available_copies FROM game_title WHERE game_name = ?";
        try (Connection conn=Connect.connect();
             PreparedStatement pstmt = conn.prepareStatement(retrieveGameStatus)){     
            pstmt.setString(1, gameTitle);
                try (ResultSet rs =pstmt.executeQuery()) {
                    int available_copies = rs.getInt(1);
                    if (available_copies == 0){
                        System.out.println("No copies available");
                        ToastMessage message = new ToastMessage("Unavailable! Pick another game!", mainFrame);
                        message.display();
                        avail = false;
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } 
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        if (avail == true) {
        
   

       String getFirstAvailableCopy = "SELECT copy_id from game_copy natural join game_title where game_name=? and is_available > 0 limit 1;";
         
       String updateFirstAvailableGameCopy = "UPDATE GAME_COPY SET is_available = '0' WHERE copy_id = (SELECT copy_id FROM (SELECT GAME_COPY.copy_id,"+
         " row_number() OVER (ORDER BY GAME_COPY.copy_id) AS RANK FROM GAME_COPY INNER JOIN game_title ON "+
          "GAME_COPY.title_num=game_title.title_num AND game_title.game_name= ? AND GAME_COPY.is_available>=1) WHERE RANK=1)"; 

        String updateAvailableCopies = "UPDATE game_title SET available_copies =  CASE WHEN available_copies >'0' THEN available_copies-1 ELSE '0' END WHERE game_name = ?";



        try (Connection conn1=Connect.connect()){
            try (PreparedStatement pstmt1 = conn1.prepareStatement(getFirstAvailableCopy))
            {
                pstmt1.setString(1, gameTitle);
               try (ResultSet rs = pstmt1.executeQuery()){
                    if(rs.next() == false){ToastMessage message = new ToastMessage("Unavailable! Pick another game!", mainFrame);
                        new SearchBrowsetoRent(user, mainFrame);
                    }    
                    else {
                        newTicket.setCopyID(rs.getInt(1));
                    }
                    }catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            try (PreparedStatement pstmt1 = conn1.prepareStatement(updateAvailableCopies))
                {
                    pstmt1.setString(1, gameTitle);
                    pstmt1.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            try (PreparedStatement pstmt2 = conn1.prepareStatement(updateFirstAvailableGameCopy))
                {
                    pstmt2.setString(1, gameTitle);
                    pstmt2.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            //
        
            String addTicket = "INSERT into tickets (uriid, copy_id, checkout_date, currentlyOut) "+
            "values (?, ?, date('now','localtime'), '1');";

            String getTicketNum = "SELECT ticket_num from tickets where uriid=? and copy_id=?;";



            try (Connection conn1=Connect.connect()){
                try (PreparedStatement pstmt3 = conn1.prepareStatement(addTicket))
                    {
                        pstmt3.setInt(1, user.getUriID());
                        System.out.println(user.getUriID());
                        System.out.println(newTicket.getCopyID());
                        pstmt3.setInt(2, newTicket.getCopyID());
                        pstmt3.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                try (PreparedStatement pstmt4 = conn1.prepareStatement(getTicketNum))
                    {
                        pstmt4.setInt(1, user.getUriID());
                        pstmt4.setInt(2, newTicket.getCopyID());
                        try (ResultSet rs = pstmt4.executeQuery()){
                            if(rs.next() == false){ System.out.println("a problem occurred generating ticket.");
                            }    
                            else {
                                newTicket.setTicketID(rs.getInt(1));
                                System.out.println(newTicket.ticketID);
                            }
                            }catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
        
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
        
            JOptionPane.showMessageDialog(mainFrame, "Item rented to user "+user.getFirstName()+user.getLastName()+"\n your ticket number is"+newTicket.getTicketID());//+"Item due on"+dateDue);



        System.out.println("System updated successfully.");
        new SearchBrowsetoRent(user, mainFrame);
    }

    }
        //code to indicate an update is needed.
        //register a contorller class to listen to when data in model has changed
        //controller class will tell UI to update ... depends on when table
}