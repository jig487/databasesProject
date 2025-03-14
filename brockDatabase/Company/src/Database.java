import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

	/*
	 * load SQL driver (JDBC: Java Database Connector/ODBC)
	 * - add to build path
	 * 
	 * set up our database (script)
	 * 
	 * connect to the database
	 * 
	 * insert/modify/delete data (Java)
	 * 
	 * query data (Java)
	 * 
	 * disconnect from the database
	 * 
	 */

	 /* SQLite connection to a local database */
//	private String url = "jdbc:sqlite:/Users/asauppe/Documents/teaching/cs364/Company.db";

	/* MySQL connection to a local database */
//	private String url = "jdbc:mysql://localhost/Company?user=example&password=abc";


	private String url = "jdbc:mysql://138.49.184.47:3306/hansen5841_Company_JDBC?user=hansen5841-student&password="; // password added in constructor

	private Connection connection;
	
	public Database() {
		String password = ""; //TODO: set this to your password
		url = url + password;
	}
	
	public void connect() {
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("Cannot connect!");
			System.out.println(e);
		}
	}
	
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Cannot disconnect!");
		}
	}
	
	public ResultSet runQuery(String query) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	public ResultSet employeeLookup(String ssn) throws SQLException {
		String query = "SELECT * FROM Employee WHERE SSN = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, ssn);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	public void insertEmployee(Employee e) throws SQLException {
		String sql = "INSERT INTO Employee (SSN, Salary, FirstName, MiddleName, LastName) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, e.getSsn());
		stmt.setDouble(2, e.getSalary());
		stmt.setString(3, e.getFirstName());
		stmt.setString(4, e.getMiddleName());
		stmt.setString(5, e.getLastName());
		int numRowsAffected = stmt.executeUpdate();
		System.out.println("Number of rows affected: " + numRowsAffected);
	}
	
	public void updateEmployeeSalary(Employee e, double salary) throws SQLException {
		String sql = "UPDATE Employee SET Salary = ? WHERE SSN = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setDouble(1, salary);
		stmt.setString(2, e.getSsn());
		stmt.executeUpdate();
		e.setSalary(salary);
	}
	
	public boolean deleteEmployee(Employee e) throws SQLException {
		String sql = "DELETE FROM Employee WHERE SSN = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, e.getSsn());
		int numRowsAffected = stmt.executeUpdate();
		return numRowsAffected > 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
