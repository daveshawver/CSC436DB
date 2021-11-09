
package u21g;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection connect() {

        //String url = "jdbc:sqlite:/mnt/c/newJavaProject/new.db";
        //String url = "jdbc:sqlite:sampledatabase.db";

        DatabasePath newInstance = DatabasePath.getInstance();
        System.out.println(newInstance.getPath());

		String url = "jdbc:sqlite:"+newInstance.getPath();
        Connection conn = null;

            try {
                conn = DriverManager.getConnection(url);
            }  catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                return conn;
    }
}