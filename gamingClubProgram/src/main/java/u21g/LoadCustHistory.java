package u21g;

import java.sql.*;
import javax.swing.table.*;
import java.util.Vector;

public class LoadCustHistory {

    public static void loadCustHistory(User user, FrameandCardHolder mainFrame){

    Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
        
    String sql="select firstname, lastname, username, ticket_num, game_name,"+
    " currentlyOut, checkout_date, due_date from user natural join tickets "+
    "natural join game_copy natural join game_title where uriid= ?;";
      // check to make sure game is available.
            try (Connection conn=Connect.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){ 
                System.out.println("hello")    ;
                pstmt.setInt(1, user.getUriID());
                try (ResultSet rs =pstmt.executeQuery()) {
                    // String yes = "yes";
                    // String  no = "no";
                    while(rs.next()){
                        Vector<Object> custHistory = new Vector<Object>();
                        custHistory.addElement(rs.getObject(1));
                        custHistory.addElement(rs.getObject(2));
                        custHistory.addElement(rs.getObject(3));
                        custHistory.addElement(rs.getInt(4));
                        custHistory.addElement(rs.getObject(5));
                        custHistory.addElement(rs.getObject(6));
                        custHistory.addElement(rs.getObject(7));
                        custHistory.addElement(rs.getObject(8));
                        tableData.addElement(custHistory);                
                    }
    
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            Vector <String> columnNames = new Vector<String>();

            columnNames.add("First Name");
            columnNames.add("Last Name");
            columnNames.add("Username");
            columnNames.add("Ticket Number");
            columnNames.add("Game Name");
            columnNames.add("Currently Out");
            columnNames.add("Checkout Date");
            columnNames.add("Due Date");

            mainFrame.custHistModel = new DefaultTableModel(tableData, columnNames);
    }
    catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
}