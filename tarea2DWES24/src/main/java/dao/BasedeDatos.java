package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BasedeDatos {
    protected Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/tarea2dwes?serverTimezone=Europe/Madrid";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }
}
