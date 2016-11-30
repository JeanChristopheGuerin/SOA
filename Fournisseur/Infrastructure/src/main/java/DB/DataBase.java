package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Product;
import entities.ProductSheet;
import entities.Supplier;
import infraInterface.CRUDI;

public class DataBase implements CRUDI  {
	Connection conn;
	
	public static void main(String[] args) throws SQLException {
		DataBase app = new DataBase();
	 
	    app.connectionToDerby();
	    app.normalDbUsage();
	 }
	private void connectionToDerby() throws SQLException {
	    // -------------------------------------------
	    // URL format is
	    // jdbc:derby:<local directory to save data>
	    // -------------------------------------------
	    String dbUrl = "jdbc:derby:bddSupplier;create=true";
	    conn = DriverManager.getConnection(dbUrl);
	}
	
	private void createTables() throws SQLException {
		Statement stmt = conn.createStatement();
		// create table
	    stmt.executeUpdate("Create table PRODUCT (idProduct int IDENTITY(1,1) primary key, name varchar(50),idDescription int REFERENCES DESCRIPTION (idDescription), idSupplier int REFERENCES DESCRIPTION (idSupplier)");
	    stmt.executeUpdate("Create table SUPPLIER (idSupplier int IDENTITY(1,1) primary key, name varchar(50) )");
	    stmt.executeUpdate("Create table DESCRIPTION (idDescription int IDENTITY(1,1) primary key, DescritionText varchar(255) )");

	}
	
	private void dropTables() throws SQLException{
		Statement stmt = conn.createStatement();
		// drop table
	    stmt.executeUpdate("Drop Table PRODUCT");
	    stmt.executeUpdate("Drop Table SUPPLIER");
	    stmt.executeUpdate("Drop Table DESCRIPTION");
	}
	
	public void addSupplier(Supplier s) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("insert into SUPPLIER values("+s.getName()+")");
		
	}
	public void addProduct(Product p) throws SQLException{
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT idDescription FROM DESCRIPTION WHERE name ="+p.getDescription().getDescriptionText());
		
		if(rs.next() != false){
			int idDescription = rs.getInt(0);
			stmt.executeUpdate("insert into PRODUCT values ("+p.getName()+","+idDescription+")");
		}else{
			
			//stmt.executeUpdate("insert into PRODUCT values ("+p.getName()+","+,'tom')");
		}
		/*stmt.executeUpdate("insert into PRODUCT values ("+p.getName()+","+,'tom')");
	    stmt.executeUpdate("insert into users values (2,'peter')");*/
		
		
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
	@Override
	public void addDescription(ProductSheet d) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public Product findProduct(String name){
		 Statement stmt = conn.createStatement();
		  stmt.executeQuery("SELECT name FROM Product WHERE name ='"+name+"'");
		 
		  
	 }
	
	
}
