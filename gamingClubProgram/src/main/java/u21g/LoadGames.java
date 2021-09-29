package u21g;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.lang.String;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class LoadGames {

    public static List<ItemTitle> loadGames() {

        String retrieveGames = "SELECT * FROM games";
         List<ItemTitle> itemTitles = new ArrayList<ItemTitle>();
        try (Connection conn=Connect.connect();
            PreparedStatement pstmt = conn.prepareStatement(retrieveGames);
            ResultSet gamesRS =pstmt.executeQuery()){     

                while (gamesRS.next()) { 
               
                    ItemTitle gameTitle = new ItemTitle(gamesRS.getInt("title_num"), gamesRS.getString("game_name"), 
                    gamesRS.getInt("total_copies"), gamesRS.getInt("game_status"), gamesRS.getInt("available_copies"));
            
                    itemTitles.add(gameTitle);
                }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return itemTitles;
    }
}