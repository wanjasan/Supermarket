package supermarket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbcon {
	static final String JDBC_URL = "jdbc:mysql://localhost:3306/super";
    static final String USERNAME = "root";
    static final String PASSWORD = "Muthaigalow80!";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Establish a connection
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }
}
