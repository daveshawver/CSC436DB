package u21g;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.lang.String;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class UserLoader{

    public static User loadUser(String uriid){
		User user = new User();
		String sqlString = "SELECT * FROM user WHERE uriid = " + uriid;
		List<User> fromDB = loadUsersFromDB(sqlString);
		user = fromDB.get(0);
		return user;
	}

	public static List<User> loadAllUsers(){
		List<User> users;
		String sqlString = "SELECT * FROM user";
		List<User> fromDB = loadUsersFromDB(sqlString);
		users = fromDB; // fromDB exists so we can check it for errors if need be, but I haven't done that yet here
		return users;
	}



	private static List<User> loadUsersFromDB(String sqlString){
		List<User> users = new ArrayList();
        try (Connection conn=Connect.connect();
            PreparedStatement pstmt = conn.prepareStatement(sqlString)){
                try ( ResultSet RS = pstmt.executeQuery()){
                    while (RS.next()) {        
						User user = new User(RS.getInt("uriid"),  
                                             RS.getString("firstname"),
                                             RS.getString("lastname"),
                                             RS.getString("username"),
                                             RS.getString("password"),
                                             RS.getString("email")
						                     );
						users.add(user);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    
        return users;
   }
}
