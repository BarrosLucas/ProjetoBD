package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static Connection getConnection() {

        Connection connection = null;

        String serverName = "localhost";
        String mydatabase = "athome_final";
        String url = "jdbc:mysql://"+serverName+"/"+mydatabase;
        String username = "root";
        String password = "password";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            System.out.println("Erro ao criar conexão com o banco.");
            ex.printStackTrace();
        }

        return connection;
    }
}
