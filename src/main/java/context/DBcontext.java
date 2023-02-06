package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBcontext {

	public Connection getConnection() throws Exception {
		String dbURL = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName;
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
	}

	private final String serverName = "localhost";
	private final String portNumber = "3306";
	private final String dbName = "cafeshoponlinedemo";
	private String dbUserName = "root";
	private String dbPassword = "123456";
}
