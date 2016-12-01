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
import infraInterface.FactoryI;
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
	
	
	
	public void createTables() throws SQLException {
		Statement stmt = conn.createStatement();
		
		//drop table before 
		this.dropTables();
		// create table
	    stmt.executeUpdate("Create table SUPPLIER (idSupplier INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, name varchar(50) )");
	    stmt.executeUpdate("Create table DESCRIPTION (idDescription INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, DescriptionText varchar(255) )");
	    stmt.executeUpdate("Create table PRODUCT (idProduct INTEGER  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, name varchar(50), price int, idDescription INTEGER REFERENCES DESCRIPTION (idDescription), idSupplier INTEGER REFERENCES SUPPLIER (idSupplier))");

	    // exemple de remplissage de table
	    FactoryI factory = new CreateInstanceBoutique();
	    
	    addProduct(factory.createProduct("a", "des trucs en description", 10));
	    addProduct(factory.createProduct("b", "description de choses et d'autres", 1));
	    addProduct(factory.createProduct("c", "produit excellent", 190));
	    addProduct(factory.createProduct("d", "produit excellentissime", 1900));
	}
	
	public void dropTables() throws SQLException{
		Statement stmt = conn.createStatement();
		// drop table
		ResultSet res;
		
		res = conn.getMetaData().getTables(null, "APP", "PRODUCT".toUpperCase(), null);
		if(res.next()){
			stmt.executeUpdate("Drop Table PRODUCT");
		}
	    
		res = conn.getMetaData().getTables(null, "APP", "SUPPLIER".toUpperCase(), null);
		if(res.next()){
			stmt.executeUpdate("Drop Table SUPPLIER");
		}
		
		res = conn.getMetaData().getTables(null, "APP", "DESCRIPTION".toUpperCase(), null);
		if(res.next()){
			stmt.executeUpdate("Drop Table DESCRIPTION");
		}
		
	  
	}
	
	public void addSupplier(Supplier s) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("insert into SUPPLIER values("+s.getName()+")");
		
	}
	public void addProduct(Product p) throws SQLException{
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT idDescription FROM DESCRIPTION WHERE DescriptionText ='"+p.getDescription().getDescriptionText().replaceAll("'", "")+"'");
		
		if(rs.next() != false){
			int idDescription = rs.getInt(0);
			stmt.executeUpdate("insert into PRODUCT values ("+p.getName()+","+p.getPrice()+","+idDescription+")");
		}else{
			stmt.executeUpdate("insert into DESCRIPTION (DescriptionText) values('"+p.getDescription().getDescriptionText().replaceAll("'", "")+"')");
			rs = stmt.executeQuery("SELECT idDescription FROM DESCRIPTION WHERE DescriptionText ='"+p.getDescription().getDescriptionText().replaceAll("'", "")+"'");
			int idDescription = rs.getInt(0);
			stmt.executeUpdate("insert into PRODUCT (name,price) values ("+p.getName()+","+p.getPrice()+","+idDescription+")");
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
		 if(res.next()){
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
