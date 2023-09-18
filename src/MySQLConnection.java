import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            var con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mmsdb", "root", "admin");
            System.out.println("Successfully connected");
            return con;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
