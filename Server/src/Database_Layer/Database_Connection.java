package Database_Layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database_Connection {
	private final String URL = "jdbc:mysql://remotemysql.com:3306/LEp00HQGCZ";
	private final String user = "LEp00HQGCZ";
	private final String password = "16LSl4oIgG";

	private Connection connection;

	public Statement statement;

	public ResultSet resultSet;

	private static Database_Connection instance;

	private Database_Connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, user, password);
		} catch (SQLException e) {
			System.out.println("Cannot Connect");
		} catch (ClassNotFoundException ex) {
			System.out.println("Class not Found");
		}
	}

	public boolean modify(String query) {
		try {
			statement = connection.createStatement();

			int result = statement.executeUpdate(query);

			boolean status = (result > 0);

			return status;

		} catch (SQLException e) {
			System.out.println("Not Connected");
			return false;
		}
	}

	public void query(String query) {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

		} catch (SQLException e) {
			System.out.println("Not Connected");
		}
	}

	public static Database_Connection getSingleConnection() {
		try {
			if (instance == null) {
				instance = new Database_Connection();
				return instance;
			} else if (instance.connection.isClosed()) {
				instance = new Database_Connection();
				return instance;
			} else {
				return instance;
			}
		} catch (SQLException e) {
			System.out.println("Somthing went wrong with Server.");
			return null;
		}
	}
}
