import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private String url = "jdbc:mysql://138.49.184.47:3306/wateski3978_library?user=wateski3978&password=";

	private Connection connection;
	
	public Database() {
		String password = "v*hNkwLm74cpFqRj";
		String encodedPassword;
		try {
			encodedPassword = URLEncoder.encode(password, StandardCharsets.UTF_8.toString());
			url = url + encodedPassword;
			System.out.println(url);
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

	public Connection getConnection() {
		return this.connection;
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

	public int insertPatron(String firstName, String lastName, String email, String phone, String address, String membershipStartDate) throws SQLException {
		String checkSql = "SELECT PID FROM Patron WHERE FirstName = ? AND LastName = ? and Phone = ?";
		PreparedStatement checkStmt = connection.prepareStatement(checkSql);
		checkStmt.setString(1, firstName);
		checkStmt.setString(2, lastName);
		checkStmt.setString(3, phone);
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
		String insertSql = "INSERT INTO Patron (PID, FirstName, LastName, Email, Phone, Address, MembershipStartDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStmt = connection.prepareStatement(insertSql);
		insertStmt.setInt(1, newPid);
		insertStmt.setString(2, firstName);
		insertStmt.setString(3, lastName);
		insertStmt.setString(4, email);
		insertStmt.setString(5, phone);
		insertStmt.setString(6, address);
		insertStmt.setString(7, membershipStartDate);
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

	public void insertBorrows(String isbn, int pid, String borrowedDate, String returnDate, int borrowedLength, boolean overdue, String condition) throws SQLException {
		String sql = "INSERT INTO Borrows (ISBN, PID, BorrowedDate, ReturnDate, BorrowedLength, Overdue, Condition) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, isbn);
		stmt.setInt(2, pid);
		stmt.setString(3, borrowedDate);
		stmt.setString(4, returnDate);
		stmt.setInt(5, borrowedLength);
		stmt.setBoolean(6, overdue);
		stmt.setString(7, condition);
		stmt.executeUpdate();
	}
	
	public void insertPurchase(String isbn, int oid, String purchaseDate, float purchasePrice) throws SQLException {
		String sql = "INSERT INTO Purchase (ISBN, OID, PurchaseDate, PurchasePrice) VALUES (?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, isbn);
		stmt.setInt(2, oid);
		stmt.setString(3, purchaseDate);
		stmt.setFloat(4, purchasePrice);
		stmt.executeUpdate();
	}
	

	public Book getBook(String isbn) throws SQLException {
		String sql = "SELECT * FROM Book WHERE ISBN = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, isbn);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return new Book(
				rs.getString("ISBN"),
				rs.getString("BookName"),
				rs.getDouble("Rating"),
				rs.getInt("RatingCount"),
				rs.getInt("Pages"),
				rs.getInt("PublishYear")
			);
		}
		return null;
	}

	public Author getAuthor(int aid) throws SQLException {
		String sql = "SELECT * FROM Author WHERE AID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, aid);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return new Author(aid, rs.getString("AuthorName"));
		}
		return null;
	}

	public Genre getGenre(int gid) throws SQLException {
		String sql = "SELECT * FROM Genre WHERE GID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, gid);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return new Genre(gid, rs.getString("GenreName"));
		}
		return null;
	}

	public Patron getPatron(int pid) throws SQLException {
		String sql = "SELECT * FROM Patron WHERE PID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, pid);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return new Patron(pid, rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"), rs.getString("Phone"), rs.getString("Address"), rs.getString("MembershipStartDate"));
		}
		return null;
	}

	public Owner getOwner(int oid) throws SQLException {
		String sql = "SELECT * FROM Owner WHERE OID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, oid);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return new Owner(oid, rs.getString("FirstName"), rs.getString("LastName"));
		}
		return null;
	}

	public void updateBook(Book b, String isbn) throws SQLException {
		String sql = "UPDATE Book SET BookName = ?, Rating = ?, RatingCount = ?, Pages = ?, PublishYear = ? WHERE ISBN = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, b.getBookName());
		stmt.setDouble(2, b.getRating());
		stmt.setInt(3, b.getRatingCount());
		stmt.setInt(4, b.getPages());
		stmt.setInt(5, b.getPublishYear());
		stmt.setString(6, isbn);
		stmt.executeUpdate();
	}

	public void updateAuthor(int aid, String newName) throws SQLException {
		String sql = "UPDATE Author SET AuthorName = ? WHERE AID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, newName);
		stmt.setInt(2, aid);
		stmt.executeUpdate();
	}

	public void updateGenre(int gid, String newName) throws SQLException {
		String sql = "UPDATE Genre SET GenreName = ? WHERE GID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, newName);
		stmt.setInt(2, gid);
		stmt.executeUpdate();
	}

	public void updatePatron(int pid, String newFirstName, String newLastName, String email, String phone, String address, String membershipStartDate ) throws SQLException {
		String sql = "UPDATE Patron SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, Address = ?, MembershipStartDate = ? WHERE PID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, newFirstName);
		stmt.setString(2, newLastName);
		stmt.setString(3, email);
		stmt.setString(4, phone);
		stmt.setString(5, address);
		stmt.setString(6, membershipStartDate);
		stmt.setInt(7, pid);
		stmt.executeUpdate();
	}

	public void updateOwner(int oid, String newFirstName, String newLastName) throws SQLException {
		String sql = "UPDATE Owner SET FirstName = ?, LastName = ? WHERE OID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, newFirstName);
		stmt.setString(2, newLastName);
		stmt.setInt(3, oid);
		stmt.executeUpdate();
	}
	
	public boolean deleteBook(String isbn) throws SQLException {
		String sql = "DELETE FROM Book WHERE ISBN = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, isbn);
		int numRowsAffected = stmt.executeUpdate();
		return numRowsAffected > 0;
	}

	public boolean deleteAuthor(int aid) throws SQLException {
		String sql = "DELETE FROM Author WHERE AID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, aid);
		return stmt.executeUpdate() > 0;
	}

	public boolean deleteGenre(int gid) throws SQLException {
		String sql = "DELETE FROM Genre WHERE GID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, gid);
		return stmt.
		executeUpdate() > 0;
	}

	public boolean deletePatron(int pid) throws SQLException {
		String sql = "DELETE FROM Patron WHERE PID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, pid);
		return stmt.executeUpdate() > 0;
	}

	public boolean deleteOwner(int oid) throws SQLException {
		String sql = "DELETE FROM Owner WHERE OID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, oid);
		return stmt.executeUpdate() > 0;
	}
}