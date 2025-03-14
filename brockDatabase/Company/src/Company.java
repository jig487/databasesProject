import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Company {

	public static void main(String[] args) {

		Database db = new Database();
		/* STEP THREE: See if the connection worked. */
		db.connect();

		try {
			
			/* STEP FOUR: Run a select and get the results */
			
			String query = "SELECT * FROM Employee";
			ResultSet results = db.runQuery(query);
			 
			ArrayList<Employee> lst = new ArrayList<>();
			
			while(results.next()) {
				String ssn = results.getString("SSN");
				double salary = results.getDouble("Salary");
				String firstName = results.getString("FirstName");
				String middleName = results.getString("MiddleName");
				String lastName = results.getString("LastName");
				
				Employee e = new Employee(ssn, salary, firstName, middleName, lastName);
				
				lst.add(e);
			}
			
			for(Employee e : lst) {
				System.out.println(e);
			}
			  
			/* END STEP FOUR */

			/* STEP FIVE: Encapsulation and prepareStatements */
			/* 
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
			 */
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
