package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Product;
import entities.ProductSheet;
import entities.Supplier;
import infraInterface.CRUDI;
import infraInterface.SearchDBEntities;
import factoryImpl.CreateInstanceBoutique;

public class DataBase implements CRUDI, SearchDBEntities {
	Connection conn;
	public DataBase() {
		
	    
	}
	
	public void connectDB(String dbUrl) throws SQLException{
		// -------------------------------------------
	    // URL format is
	    // jdbc:derby:<local directory to save data>
	    // -------------------------------------------
		conn = DriverManager.getConnection(dbUrl);
	}
	
	public void connectDB() throws SQLException{
		// -------------------------------------------
	    // URL format is
	    // jdbc:derby:<local directory to save data>
	    // -------------------------------------------
		conn = DriverManager.getConnection("jdbc:derby:bddSupplier;create=true");
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
	@Override
	public void addDescription(ProductSheet d) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public Product findProduct(String name) throws SQLException{
		CreateInstanceBoutique factory = new CreateInstanceBoutique(); 
		Statement stmt = conn.createStatement();
		 ResultSet res = stmt.executeQuery("SELECT name,price FROM Product WHERE name ='"+name+"'");
		 if(res.next() != false){
			return factory.createProduct(res.getObject(0).toString(),Float.parseFloat(res.getObject(1).toString())); 
		 }
		 
		 
		 return null; 
	}
	@Override
	public List<Product> findProducts(List<String> names) throws SQLException{
		Statement stmt = conn.createStatement();
		List<Product> res = new ArrayList<Product>();
		ResultSet resultset;
		CreateInstanceBoutique factory = new CreateInstanceBoutique();
		
		for (String each : names){
			resultset = stmt.executeQuery("SELECT name,price FROM Product WHERE name ='"+each+"'");
			if(resultset.next() != false){
				res.add(factory.createProduct(resultset.getObject(0).toString(),Float.parseFloat(resultset.getObject(1).toString()))); 
			 }
		}
		return res;
	}

	
	
	
}
