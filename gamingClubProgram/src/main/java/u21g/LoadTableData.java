package u21g;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;
import java.util.List;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;


public class LoadTableData {

    static void loadModelData(User user, FrameandCardHolder mainFrame){

        Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
        
        Connection connection = Connect.connect();
        String sql="SELECT game_name, available_copies FROM game_title"; //Retreive data from database
        try {
            Statement stmt = connection.createStatement(); //connect to databasese library
            stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            
            String available = "Available";
            String checkedOut = "Currently checked out";

            while(rs.next()) {
                Vector<Object> gameEntry = new Vector<Object>();
                gameEntry.add(rs.getObject(1));
                int availCops = rs.getInt(2);
                if (availCops > 0){
                    gameEntry.add(available);
                }
                else{
                    gameEntry.add(checkedOut);
                }
                tableData.add(gameEntry);
            }
        }catch (SQLException e1) {
            // TODO Auto-generated catch block
             JOptionPane.showMessageDialog(null, e1);
        }             

            Vector <String> columnNames = new Vector<String>();
            columnNames.add("Game Title");
            columnNames.add("Availability");

            mainFrame.model = new DefaultTableModel(tableData, columnNames);
    }
 
}
