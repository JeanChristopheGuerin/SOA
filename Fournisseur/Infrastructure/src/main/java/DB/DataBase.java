package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Product;

public class DataBase {
	Connection conn;
	
	public static void main(String[] args) throws SQLException {
		DataBase app = new DataBase();
	 
	    app.connectionToDerby();
	    app.normalDbUsage();
	 }
	public void connectionToDerby() throws SQLException {
	    // -------------------------------------------
	    // URL format is
	    // jdbc:derby:<local directory to save data>
	    // -------------------------------------------
	    String dbUrl = "jdbc:derby:bddSupplier;create=true";
	    conn = DriverManager.getConnection(dbUrl);
	}
	
	public void createTables() throws SQLException {
		Statement stmt = conn.createStatement();
		// create table
	    stmt.executeUpdate("Create table PRODUCT (id int primary key, name varchar(30),idDescription int REFERENCES DESCRIPTION (idDescription))");
	    stmt.executeUpdate("Create table SUPPLIER ()");
	    stmt.executeUpdate("Create table DESCRIPTION ()");

	}
	
	public void dropTables() throws SQLException{
		Statement stmt = conn.createStatement();
		// drop table
	    stmt.executeUpdate("Drop Table PRODUCT");
	    stmt.executeUpdate("Drop Table SUPPLIER");
	    stmt.executeUpdate("Drop Table DESCRIPTION");
	}
	
	public void addSupplier(){
		
	}
	public void addProduct(Product p) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("insert into PRODUCT values (1,'tom')");
	    stmt.executeUpdate("insert into users values (2,'peter')");
	}
	
	public void addDescrition(){
		
	}
	public void normalDbUsage() throws SQLException {
		Statement stmt = conn.createStatement();
	 
	    
	 
	    
	 
	    // insert 2 rows
	    
	 
	    // query
	    ResultSet rs = stmt.executeQuery("SELECT * FROM users");
	 
	    // print out query result
	    while (rs.next()) { 
	      System.out.printf("%d\t%s\n", rs.getInt("id"), rs.getString("name"));
	    }
	}
}
