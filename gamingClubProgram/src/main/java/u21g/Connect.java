
package u21g;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection connect() {

        //String url = "jdbc:sqlite:/mnt/c/newJavaProject/new.db";
        //String url = "jdbc:sqlite:sampledatabase.db";
		String url = "jdbc:sqlite:/home/david/Fall2021/u21g/sampledatabase.db";
        Connection conn = null;

            try {
                conn = DriverManager.getConnection(url);
            }  catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                return conn;
    }
}