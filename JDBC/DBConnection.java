package apps.Course.Jdbc.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/testDb";
            String userName = "root";
            String password = "Akshaya@888";
            return DriverManager.getConnection(url, userName, password);
    }
}
