package u21g;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.io.*;   

public class AddUser {

   public static void insert (User _user){
            
        String user_name = _user.getUserName();
        System.out.print(user_name);
        String first_name = _user.getFirstName();
        System.out.print(first_name);
        String last_name = _user.getLastName();
        String pass_word = _user.getPassword();
        int uri_id = _user.getUriID();
        System.out.println(uri_id);
        String email = _user.getEmail();
        int admin = _user.getAdmin();
  
        String sql = "INSERT INTO users (uriid,firstname, lastname, username, password, email, admin) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
           try (Connection conn = Connect.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, uri_id);
                pstmt.setString(2, first_name);
                pstmt.setString(3, last_name);
                pstmt.setString(4, user_name);
                pstmt.setString(5, pass_word);
                pstmt.setString(6, email);         
                pstmt.setInt(7, admin);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }            
}