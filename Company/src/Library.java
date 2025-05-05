import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Library {
	public static void main(String[] args) {
		Database db = new Database();
		db.connect();
		try {			
			/* STEP FOUR: Run a select and get the results */

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
			  
			/* STEP FIVE: Encapsulation and prepareStatements */
			 
			ResultSet results = db.employeeLookup("123-54-6799");
			if(results.next()) {
				String ssn = results.getString("SSN");
				double salary = results.getDouble("Salary");
				String firstName = results.getString("FirstName");
				String middleName = results.getString("MiddleName");
				String lastName = results.getString("LastName");
				
				Employee e = new Employee(ssn, salary, firstName, middleName, lastName);
				
				System.out.println(e.toString());
			}
		
			/* END STEP FIVE */


			/* STEP SIX: Database modification */
			/* 
			Employee e = new Employee("222-22-2222", 60000.00, "Edsger", "W.", "Dijkstra");
			db.insertEmployee(e);
			
			System.out.println();
			
			db.updateEmployeeSalary(e, 65000.00);
			 
			boolean result = db.deleteEmployee(e);
			System.out.println(result);
			*/
			/* END STEP SIX */
		} catch(SQLException e) {
			System.out.println("Something went wrong!");
			e.printStackTrace();
		}
		
		System.out.println("Successfully connected!");
		
		db.disconnect();
		
	}

}
