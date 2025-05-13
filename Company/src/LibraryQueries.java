import java.sql.*;

public class LibraryQueries {
    private final Connection connection;

    public LibraryQueries(Connection connection) {
        this.connection = connection;
    }

    public ResultSet countCheckedOutBooks() throws SQLException {
        String sql = "SELECT COUNT(*) AS CheckedOutCount FROM Borrows WHERE ReturnDate = '1111-11-11'";
        PreparedStatement stmt = connection.prepareStatement(sql);
        return stmt.executeQuery();
    }

    public ResultSet getBooksByGenre(String genre) throws SQLException {
        String sql = "SELECT b.BookName, g.GenreName " +
                     "FROM Book AS b " +
                     "JOIN IsGenre ig ON b.ISBN = ig.ISBN " +
                     "JOIN Genre g ON ig.GID = g.GID " +
                     "WHERE g.GenreName = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, genre);
        return stmt.executeQuery();
    }

    public ResultSet averagePagesByGenre() throws SQLException {
        String sql = "SELECT g.GenreName, AVG(b.Pages) AS AvgPages " +
                     "FROM Book AS b " +
                     "JOIN IsGenre ig ON b.ISBN = ig.ISBN " +
                     "JOIN Genre g ON ig.GID = g.GID " +
                     "GROUP BY g.GenreName";
        return connection.prepareStatement(sql).executeQuery();
    }

    public ResultSet booksOverPrice(float price) throws SQLException {
        String sql = "SELECT b.BookName, p.PurchasePrice " +
                     "FROM Purchase AS p " +
                     "JOIN Book AS b ON p.ISBN = b.ISBN " +
                     "WHERE p.PurchasePrice > ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setFloat(1, price);
        return stmt.executeQuery();
    }

    public ResultSet patronsWithOverdueBooks() throws SQLException {
        String sql = "SELECT p.FirstName, p.LastName, b.ISBN, b.BorrowedDate " +
                     "FROM Patron AS p " +
                     "JOIN Borrows AS b ON p.PID = b.PID " +
                     "WHERE b.Overdue = 1";
        return connection.prepareStatement(sql).executeQuery();
    }

    public ResultSet authorsByGenre(String genre) throws SQLException {
        String sql = "SELECT DISTINCT Author.AuthorName, Genre.GenreName " +
                     "FROM Author " +
                     "JOIN Wrote ON Author.AID = Wrote.AID " +
                     "JOIN Book ON Wrote.ISBN = Book.ISBN " +
                     "JOIN IsGenre ON Book.ISBN = IsGenre.ISBN " +
                     "JOIN Genre ON IsGenre.GID = Genre.GID " +
                     "WHERE Genre.GenreName = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, genre);
        return stmt.executeQuery();
    }

    public ResultSet borrowedBooks() throws SQLException {
        String sql = "SELECT bk.BookName AS Title " +
                     "FROM Book AS bk " +
                     "LEFT JOIN Borrows AS b ON bk.ISBN = b.ISBN " +
                     "WHERE b.BorrowedDate IS NOT NULL " +
                     "GROUP BY bk.BookName";
        return connection.prepareStatement(sql).executeQuery();
    }

    public ResultSet genresWithMinBooks(int minCount) throws SQLException {
        String sql = "SELECT g.GenreName, COUNT(*) AS BookCount " +
                     "FROM Book AS b " +
                     "JOIN IsGenre ig ON b.ISBN = ig.ISBN " +
                     "JOIN Genre g ON ig.GID = g.GID " +
                     "GROUP BY g.GenreName " +
                     "HAVING COUNT(*) > ? " +
                     "ORDER BY BookCount DESC";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, minCount);
        return stmt.executeQuery();
    }

    public ResultSet booksNotBorrowed() throws SQLException {
        String sql = "SELECT bk.BookName " +
                     "FROM Book AS bk " +
                     "WHERE bk.ISBN NOT IN (SELECT DISTINCT ISBN FROM Borrows)";
        return connection.prepareStatement(sql).executeQuery();
    }

    public ResultSet authorsNotInGenre(String genre) throws SQLException {
        String sql = "SELECT a.AID, a.AuthorName " +
                     "FROM Author AS a " +
                     "WHERE a.AID NOT IN ( " +
                     "SELECT DISTINCT w.AID " +
                     "FROM Wrote w " +
                     "JOIN Book bk ON w.ISBN = bk.ISBN " +
                     "JOIN IsGenre ig ON bk.ISBN = ig.ISBN " +
                     "JOIN Genre g ON ig.GID = g.GID " +
                     "WHERE g.GenreName = ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, genre);
        return stmt.executeQuery();
    }

    public ResultSet booksNotBorrowedInMonths(int months) throws SQLException {
        String sql = "SELECT bk.ISBN, bk.BookName " +
                     "FROM Book AS bk " +
                     "WHERE bk.ISBN NOT IN ( " +
                     "SELECT DISTINCT ISBN " +
                     "FROM Borrows " +
                     "WHERE BorrowedDate >= DATE_SUB(CURDATE(), INTERVAL ? MONTH))";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, months);
        return stmt.executeQuery();
    }
}