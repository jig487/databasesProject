import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Database {
	private String url = "jdbc:mysql://138.49.184.47:3306/wateski3978_library?user=wateski3978&password=";

	private Connection connection;
	
	public Database() {
		String password = "v*hNkwLm74cpFqRj";
		String encodedPassword;
		try {
			encodedPassword = URLEncoder.encode(password, StandardCharsets.UTF_8.toString());
			url = url + encodedPassword;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
	/* 
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
	*/
}
