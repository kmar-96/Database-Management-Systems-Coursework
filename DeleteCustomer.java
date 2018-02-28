import java.sql.*;
import java.util.Scanner;

public class DeleteCustomer {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.print("Delete Customer with id: ");
		int customer_id = input.nextInt();
		
		String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
                "databaseName=***;user=*****;password=**********;";
		Connection dbcon ;
		Statement stmt;
	  	
	  	try {
	  		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	  		}
	  	catch (java.lang.ClassNotFoundException e) {
	  		
	  		System.out.print("ClassNotFoundException: ");
	  		System.out.println(e.getMessage());
	  		}
	  	
	  	try {
			dbcon = DriverManager.getConnection(url);
			stmt = dbcon.createStatement();
			String sql = "DELETE FROM Customer WHERE customer_id = " + customer_id;  
			stmt.executeUpdate(sql);

			stmt.close();
			dbcon.close();
			
	  		} catch(SQLException e) {
	  		System.out.print("SQLException: ");
	  		System.out.println(e.getMessage());
	  		}
	}
}