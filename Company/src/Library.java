import java.sql.*;
//import java.util.ArrayList;
import java.util.Scanner;

public class Library {
	public static void main(String[] args) {
		Database db = new Database();
		db.connect();
		try {			
			LibraryQueries queries = new LibraryQueries(db.getConnection());
            Scanner scanner = new Scanner(System.in);
			db.deleteBook("1635577926");
			System.out.println("------CS-364 Database Project-------");
			System.out.println("------Create and Read Example-------");
			scanner.nextLine();
			System.out.println("db.bookLookup(\"1635577926\");");
			scanner.nextLine();
			ResultSet rs = db.bookLookup("1635577926");
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(meta.getColumnLabel(i) + ": " + rs.getString(i) + "\t");
				}
				System.out.println();
			}
			scanner.nextLine();
			Book newBook = new Book("1635577926", "A Day of Fallen Night", 4.33, 47566, 880, 2022);
			System.out.println("Book newBook = new Book(\"1635577926\", \"A Day of Fallen Night\", 4.33, 47566, 880, 2022);\n");
			db.insertBook(newBook);
			System.out.println("db.insertBook(newBook);\n");
			scanner.nextLine();
			System.out.println("db.bookLookup(\"1635577926\");");
			scanner.nextLine();
			rs = db.bookLookup("1635577926");
			meta = rs.getMetaData();
			columnCount = meta.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(meta.getColumnLabel(i) + ": " + rs.getString(i) + "\t");
				}
				System.out.println();
			}
			scanner.nextLine();
			Book updateBook = new Book("1635577926", "A Day of Fallen Night", 4.33, 47566, 880, 2023);
			System.out.println("Book updateBook = new Book(\"1635577926\", \"A Day of Fallen Night\", 4.33, 47566, 880, 2023);\n");
			db.updateBook(updateBook, "1635577926");
			System.out.println("db.updateBook(updateBook, \"1635577926\");\n");
			scanner.nextLine();
			System.out.println("db.bookLookup(\"1635577926\");");
			scanner.nextLine();
			rs = db.bookLookup("1635577926");
			meta = rs.getMetaData();
			columnCount = meta.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(meta.getColumnLabel(i) + ": " + rs.getString(i) + "\t");
				}
				System.out.println();
			}
			scanner.nextLine();
			System.out.println("db.deleteBook(\"1635577926\");\n");
			scanner.nextLine();
			db.deleteBook("1635577926");
			scanner.nextLine();
			System.out.println("db.bookLookup(\"1635577926\");");
			scanner.nextLine();
			rs = db.bookLookup("1635577926");
			meta = rs.getMetaData();
			columnCount = meta.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(meta.getColumnLabel(i) + ": " + rs.getString(i) + "\t");
				}
				System.out.println();
			}
			scanner.nextLine();
			
			System.out.println("---------Queries---------");

			scanner.nextLine();
			System.out.println("Group 1: aggregate functions, LIKE, GROUP BY, ORDER BY, LIMIT");
			scanner.nextLine();
            runQuery("Count total number of checked-out books:",
                queries.countCheckedOutBooks(), scanner);

            runQuery("Get all books in the 'History' genre:",
                queries.getBooksByGenre("History"), scanner);

            runQuery("Find the average number of pages by genre:",
                queries.averagePagesByGenre(), scanner);

            runQuery("Books purchased over $50:",
                queries.booksOverPrice(50.0f), scanner);

            runQuery("Patrons with overdue books:",
                queries.patronsWithOverdueBooks(), scanner);
			System.out.println("Group 2: HAVING, OFFSET, outer join, joining four or more tables");
			scanner.nextLine();
            runQuery("Find authors in the 'Science Fiction' genre:",
                queries.authorsByGenre("Science Fiction"), scanner);

            runQuery("Check if a book is being borrowed:",
                queries.borrowedBooks(), scanner);

            runQuery("List genres with more than 100 books:",
                queries.genresWithMinBooks(100), scanner);
			System.out.println("Group 3: subqueries, IN, set operators, any additional functionality");
			scanner.nextLine();
            runQuery("Find books that are not being borrowed:",
                queries.booksNotBorrowed(), scanner);

            runQuery("Find authors who have not written in the 'Science Fiction' genre:",
                queries.authorsNotInGenre("Science Fiction"), scanner);

            runQuery("Find books not borrowed in the last 6 months:",
                queries.booksNotBorrowedInMonths(6), scanner);

            scanner.close();

        } catch(SQLException e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }

        db.disconnect();
    }

    private static void runQuery(String description, ResultSet rs, Scanner scanner) throws SQLException {
        System.out.println("\n=== " + description + " ===");
        System.out.println("");
        scanner.nextLine();

        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(meta.getColumnLabel(i) + ": " + rs.getString(i) + "\t");
            }
            System.out.println();
        }
        System.out.println("continue...");
        scanner.nextLine();
    }
}