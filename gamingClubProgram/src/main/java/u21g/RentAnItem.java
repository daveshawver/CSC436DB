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

    public static void rentAnItem(String gameTitle, User user, FrameandCardHolder mainFrame) {

        avail = true;

        // check to make sure game is available.
        String retrieveGameStatus = "SELECT game_status FROM games WHERE game_name = ?";
        try (Connection conn=Connect.connect();
             PreparedStatement pstmt = conn.prepareStatement(retrieveGameStatus)){     
            pstmt.setString(1, gameTitle);
                try (ResultSet rs =pstmt.executeQuery()) {
                    int game_status = rs.getInt(1);
                    if (game_status == 0){
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
        
       String updateAvailableCopies = "UPDATE games SET available_copies =  CASE WHEN available_copies >'0' THEN available_copies-1 ELSE '0' END WHERE game_name = ?";
         
       String updateFirstAvailableGameCopy = "UPDATE GAME_COPY SET copy_status = '0' WHERE copy_id = (SELECT copy_id FROM (SELECT GAME_COPY.copy_id,"+
         " row_number() OVER (ORDER BY GAME_COPY.copy_id) AS RANK FROM GAME_COPY INNER JOIN games ON "+
          "GAME_COPY.title_num=games.title_num AND games.game_name= ? AND GAME_COPY.copy_status=1) WHERE RANK=1)"; 

       String updateGameStatus = "UPDATE games SET game_status = CASE WHEN available_copies < '1' THEN '0' ELSE '1' END WHERE game_name = ?";

        try (Connection conn1=Connect.connect()){
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

                try (PreparedStatement pstmt3 = conn1.prepareStatement(updateGameStatus))
                {
                    pstmt3.setString(1, gameTitle);
                    pstmt3.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            //
        JOptionPane.showMessageDialog(mainFrame, "Item rented to user "+user.getFirstName()+user.getLastName());//+"Item due on"+dateDue);
        System.out.println("System updated successfully.");
        }

    }
        //code to indicate an update is needed.
        //register a contorller class to listen to when data in model has changed
        //controller class will tell UI to update ... depends on when table
}