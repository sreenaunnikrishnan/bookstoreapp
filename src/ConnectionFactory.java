
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ConnectionFactory {

	private static Connection connection = null;

	public static Connection getConnection() {
		InputStream is = ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String driverName = properties.getProperty("db.driverName");
		String userName = properties.getProperty("db.userName");
		String password = properties.getProperty("db.password");
		String url = properties.getProperty("db.url");
		try {
			Class.forName(driverName);
			System.out.println("driver is loaded..");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// connection object

		try {
			connection = DriverManager.getConnection(url, userName, password);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return connection;
	}
}
