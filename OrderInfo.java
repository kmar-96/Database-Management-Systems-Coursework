import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OrderInfo {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		String descr;
		int id, quantity;
		double price, total = 0;
		System.out.print("Get info of order with id: ");
		int order_id = input.nextInt();
		
		String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
                "databaseName=***;user=*****;password=**********;";
		Connection dbcon ;
		Statement stmt;
	  	ResultSet rs;
	  	
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
			rs = stmt.executeQuery("SELECT description, Product.product_id, "
					     		 + "Includes_Products.quantity, price "
					     		 + "FROM Includes_Products, Product "
					     		 + "WHERE order_id = " + order_id + " AND " 
					     		 + "Includes_Products.product_id = Product.product_id");
			
			System.out.println("=== Order Details ===");
			System.out.println();
			
			while (rs.next()) {

				descr = rs.getString("description");
				id = rs.getInt("product_id");
				quantity = rs.getInt("quantity");
				price = rs.getDouble("price");
				total += price * quantity;
				
				System.out.println("Product description: " + descr);
				System.out.println("Product id: " + id);
				System.out.println("Quantity Bought: " + quantity);
				System.out.println("Price of unit: " + price);
				System.out.println();
	    	}
			
			System.out.println("Total Cost: " + total);

			stmt.close();
			dbcon.close();
			
	  		} catch(SQLException e) {
	  		System.out.print("SQLException: ");
	  		System.out.println(e.getMessage());
	  		}
	}
}
