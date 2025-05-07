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
		stmt.setInt(6, e.getPublishYear());
		stmt.executeUpdate();
	}

	public int insertAuthor(String authorName) throws SQLException {
		String checkSql = "SELECT AID FROM Author WHERE AuthorName = ?";
		PreparedStatement checkStmt = connection.prepareStatement(checkSql);
		checkStmt.setString(1, authorName);
		ResultSet rs = checkStmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("AID");
		}
		String maxSql = "SELECT MAX(AID) AS maxAid FROM Author";
		PreparedStatement maxStmt = connection.prepareStatement(maxSql);
		ResultSet maxRs = maxStmt.executeQuery();
		int newAid = 1;
		if (maxRs.next() && maxRs.getInt("maxAid") != 0) {
			newAid = maxRs.getInt("maxAid") + 1;
		}
		String insertSql = "INSERT INTO Author (AID, AuthorName) VALUES (?, ?)";
		PreparedStatement insertStmt = connection.prepareStatement(insertSql);
		insertStmt.setInt(1, newAid);
		insertStmt.setString(2, authorName);
		insertStmt.executeUpdate();
		return newAid;
	}

	public int insertGenre(String genreName) throws SQLException {
		String checkSql = "SELECT GID FROM Genre WHERE GenreName = ?";
		PreparedStatement checkStmt = connection.prepareStatement(checkSql);
		checkStmt.setString(1, genreName);
		ResultSet rs = checkStmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("GID");
		}
		String maxSql = "SELECT MAX(GID) AS maxGid FROM Genre";
		PreparedStatement maxStmt = connection.prepareStatement(maxSql);
		ResultSet maxRs = maxStmt.executeQuery();
		int newGid = 1;
		if (maxRs.next() && maxRs.getInt("maxGid") != 0) {
			newGid = maxRs.getInt("maxGid") + 1;
		}
		String insertSql = "INSERT INTO Genre (GID, GenreName) VALUES (?, ?)";
		PreparedStatement insertStmt = connection.prepareStatement(insertSql);
		insertStmt.setInt(1, newGid);
		insertStmt.setString(2, genreName);
		insertStmt.executeUpdate();
		return newGid;
	}

	public int insertPatron(String firstName, String lastName) throws SQLException {
		String checkSql = "SELECT PID FROM Patron WHERE FirstName = ? AND LastName = ?";
		PreparedStatement checkStmt = connection.prepareStatement(checkSql);
		checkStmt.setString(1, firstName);
		checkStmt.setString(2, lastName);
		ResultSet rs = checkStmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("PID");
		}
		String maxSql = "SELECT MAX(PID) AS maxPid FROM Patron";
		PreparedStatement maxStmt = connection.prepareStatement(maxSql);
		ResultSet maxRs = maxStmt.executeQuery();
		int newPid = 1;
		if (maxRs.next() && maxRs.getInt("maxPid") != 0) {
			newPid = maxRs.getInt("maxPid") + 1;
		}
		String insertSql = "INSERT INTO Patron (PID, FirstName, LastName) VALUES (?, ?, ?)";
		PreparedStatement insertStmt = connection.prepareStatement(insertSql);
		insertStmt.setInt(1, newPid);
		insertStmt.setString(2, firstName);
		insertStmt.setString(3, lastName);
		insertStmt.executeUpdate();
		return newPid;
	}

	public int insertOwner(String firstName, String lastName) throws SQLException {
		String checkSql = "SELECT OID FROM Owner WHERE FirstName = ? AND LastName = ?";
		PreparedStatement checkStmt = connection.prepareStatement(checkSql);
		checkStmt.setString(1, firstName);
		checkStmt.setString(2, lastName);
		ResultSet rs = checkStmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("OID");
		}
		String maxSql = "SELECT MAX(OID) AS maxOid FROM Owner";
		PreparedStatement maxStmt = connection.prepareStatement(maxSql);
		ResultSet maxRs = maxStmt.executeQuery();
		int newOid = 1;
		if (maxRs.next() && maxRs.getInt("maxOid") != 0) {
			newOid = maxRs.getInt("maxOid") + 1;
		}
		String insertSql = "INSERT INTO Owner (OID, FirstName, LastName) VALUES (?, ?, ?)";
		PreparedStatement insertStmt = connection.prepareStatement(insertSql);
		insertStmt.setInt(1, newOid);
		insertStmt.setString(2, firstName);
		insertStmt.setString(3, lastName);
		insertStmt.executeUpdate();
		return newOid;
	}
	
	public void insertWrote(String isbn, int aid) throws SQLException {
		String sql = "INSERT INTO Wrote (ISBN, AID) VALUES (?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, isbn);
		stmt.setInt(2, aid);
		stmt.executeUpdate();
	}

	public void insertIsGenre(String isbn, int gid) throws SQLException {
		String sql = "INSERT INTO IsGenre (ISBN, GID) VALUES (?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, isbn);
		stmt.setInt(2, gid);
		stmt.executeUpdate();
	}	

	public void insertBorrows(String isbn, int pid) throws SQLException {
		String sql = "INSERT INTO Borrows (ISBN, PID) VALUES (?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, isbn);
		stmt.setInt(2, pid);
		stmt.executeUpdate();
	}

	public void insertOwns(String isbn, int oid) throws SQLException {
		String sql = "INSERT INTO Owns (ISBN, AID) VALUES (?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, isbn);
		stmt.setInt(2, oid);
		stmt.executeUpdate();
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