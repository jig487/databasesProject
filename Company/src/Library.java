import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Library {
	public static void main(String[] args) {
		Database db = new Database();
		db.connect();
		try {			
			String query = "SELECT * FROM Book";
			ResultSet results = db.runQuery(query);
			 
			ArrayList<Book> lst = new ArrayList<>();
			while(results.next()) {
				String isbn = results.getString("ISBN");
				String bookName = results.getString("BookName");
				Double rating = results.getDouble("Rating");
				int ratingCount = results.getInt("RatingCount");
				int pages = results.getInt("Pages");
				int publishYear = results.getInt("PublishYear");

				Book e = new Book(isbn, bookName, rating, ratingCount, pages, publishYear);
				
				lst.add(e);
			}
			
			for(Book e : lst) {
				System.out.println(e);
			}
			  
		} catch(SQLException e) {
			System.out.println("Something went wrong!");
			e.printStackTrace();
		}
		
		System.out.println("Successfully connected!");
		
		db.disconnect();
	}
}