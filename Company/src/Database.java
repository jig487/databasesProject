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
	
	public ResultSet bookLookup(String isbn) throws SQLException {
		String query = "SELECT * FROM Book WHERE ISBN = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, isbn);
		ResultSet results = stmt.executeQuery();
		return results;
	}

	public ResultSet genreLookup(int gid) throws SQLException {
		if (gid == -1) {
			String query = "SELECT * FROM Genre";
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet results = stmt.executeQuery();
			return results;			
		}
		String query = "SELECT * FROM Genre WHERE GID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, gid);
		ResultSet results = stmt.executeQuery();
		return results;
	}

	public ResultSet authorLookup(int aid) throws SQLException {
		String query = "SELECT * FROM Author WHERE AID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, aid);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	public void insertBook(Book e) throws SQLException {
		String sql = "INSERT INTO Book (ISBN, BookName, Rating, RatingCount, Pages, PublishYear) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, e.getIsbn());
		stmt.setString(2, e.getBookName());
		stmt.setDouble(3, e.getRating());
		stmt.setInt(4, e.getRatingCount());
		stmt.setInt(5, e.getPages());
		stmt.setInt(5, e.getPublishYear());
		int numRowsAffected = stmt.executeUpdate();
		insertIsGenre(e);
		System.out.println("Number of rows affected: " + numRowsAffected);
	}

	public void insertIsGenre(Book e) {
		try {
			ResultSet result = genreLookup(-1);
		Finally 
		
		}
	}
	
	public void updateRating(Book e, double rating) throws SQLException {
		String sql = "UPDATE Book SET Rating = ? WHERE ISBN = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setDouble(1, rating);
		stmt.setString(2, e.getIsbn());
		stmt.executeUpdate();
		e.setRating(rating);
	}
	
	public void updateRatingCount(Book e, int ratingCount) throws SQLException {
		String sql = "UPDATE Book SET RatingCount = ? WHERE ISBN = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, ratingCount);
		stmt.setString(2, e.getIsbn());
		stmt.executeUpdate();
		e.setRatingCount(ratingCount);
	}

	public boolean deleteEmployee(Book e) throws SQLException {
		String sql = "DELETE FROM Book WHERE ISBN = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, e.getIsbn());
		int numRowsAffected = stmt.executeUpdate();
		return numRowsAffected > 0;
	}
}