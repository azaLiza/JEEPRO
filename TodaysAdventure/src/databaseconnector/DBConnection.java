package databaseconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Liza
 *This class initializes a connection to our database
 */
public class DBConnection {
	//driver's class name
	static final String jdbc_driver = "com.mysql.jdbc.Driver";
	//CONNECTION URL
	static final String db_url = "jdbc:mysql://localhost:3306/adv";
	//ATTRIBUTES
	static final String username = "root";
	static final String password = "root";

	private static Connection connection = null;

	//connect only one time
	public static Connection getInstance() {
		//surround with try and catch
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(db_url, username, password);
			System.out.println("Connected");
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			e.printStackTrace();
		} //DriverManager.getConnection failed
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connection;
	}
	
	//a function to close connection
	public static void closeconn() {
		try {
			if(connection != null) {
				connection.close();
				connection = null;
				//at this point we'll always have to use get instance after closing a connection
			}
		}catch(SQLException se){
			se.printStackTrace();
		}	
	}
}

