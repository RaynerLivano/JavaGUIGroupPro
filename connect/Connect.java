package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connect {

    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String DATABASE = "seruput_teh";
    private final String HOST = "localhost:3306";
    private final String CONNECTION_STRING = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

    private Connection connection;

    private static Connect instance = new Connect();

    private Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connect getInstance() {
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

	public ResultSet executeQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public PreparedStatement prepareStatement(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
