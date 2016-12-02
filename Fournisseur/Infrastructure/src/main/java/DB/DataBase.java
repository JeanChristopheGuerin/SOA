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
	FactoryI factory;
	
	/**
	 * Constructeur of the DB ,this constructeur create a factory for the various entities present in the project 
	 * */
	public DataBase() {
		factory  = new CreateInstanceBoutique();
	    
	}
	/**
	 * Method which create a connection with the database (or create one if it doesn't exit)
	 * 
	 * @param dbUrl : You can choose the url of the database 
	 * 
	 * */
	public void connectDB(String dbUrl) throws SQLException{
		// -------------------------------------------
	    // URL format is
	    // jdbc:derby:<local directory to save data>
	    // -------------------------------------------
		conn = DriverManager.getConnection("jdbc:derby:"+dbUrl+";create=true");
	}
	/**
	 * Method which create a connection with the database (or create one if it doesn't exit)
	 * The Default url will be bddSupplier in the Infrastructure
	 * 
	 * 
	 * */
	public void connectDB() throws SQLException{
		// -------------------------------------------
	    // URL format is
	    // jdbc:derby:<local directory to save data>
	    // -------------------------------------------
		conn = DriverManager.getConnection("jdbc:derby:bddSupplier;create=true");
	}
	
	
	/**
	 * Method which create a DB with 3 Tables (Supplier, Description and Product)
	 * 
	 * */
	public void createTables() throws SQLException {
		Statement stmt = conn.createStatement();
		
		//drop table before 
		this.dropTables();
		// create table
	    stmt.executeUpdate("Create table SUPPLIER (idSupplier INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, name varchar(50) )");
	    stmt.executeUpdate("Create table DESCRIPTION (idDescription INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, DescriptionText varchar(255) )");
	    stmt.executeUpdate("Create table PRODUCT (idProduct INTEGER  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, name varchar(50), price FLOAT, idDescription INTEGER REFERENCES DESCRIPTION (idDescription), idSupplier INTEGER REFERENCES SUPPLIER (idSupplier))");

	    // exemple de remplissage de table
	    
	    
	    addProduct(factory.createProduct("a", "des trucs en description", 10));
	    addProduct(factory.createProduct("b", "description de choses et d'autres", 1));
	    addProduct(factory.createProduct("c", "produit excellent", 190));
	    addProduct(factory.createProduct("d", "produit excellentissime", 1900));
	}
	/**
	 * Method which drop the tables Product, Supplier and Description 
	 * */
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
	/**
	 * Method unused which can permit to add a Supplier
	 * 
	 * 
	 * */
	@Override
	public void addSupplier(Supplier s) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("insert into SUPPLIER values("+s.getName()+")");
		
	}
	
	/**
	 * Method which can permit to add a Product
	 * 
	 * 
	 * */
	@Override
	public void addProduct(Product p) throws SQLException{
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT idDescription FROM DESCRIPTION WHERE DescriptionText ='"+p.getDescription().getDescriptionText().replaceAll("'", "")+"'");
		
		if(rs.next() != false){
			int idDescription = rs.getInt(1);
			stmt.executeUpdate("insert into PRODUCT (name,price,idDescription) values ('"+p.getName()+"',"+p.getPrice()+","+idDescription+")");
		}else{
			stmt.executeUpdate("insert into DESCRIPTION (DescriptionText) values('"+p.getDescription().getDescriptionText().replaceAll("'", "")+"')");
			rs = stmt.executeQuery("SELECT idDescription FROM DESCRIPTION WHERE DescriptionText ='"+p.getDescription().getDescriptionText().replaceAll("'", "")+"'");
			rs.next();
			int idDescription = rs.getInt(1);

			stmt.executeUpdate("insert into PRODUCT (name,price,idDescription) values ('"+p.getName()+"',"+p.getPrice()+","+idDescription+")");
		}
		/*stmt.executeUpdate("insert into PRODUCT values ("+p.getName()+","+,'tom')");
	    stmt.executeUpdate("insert into users values (2,'peter')");*/
		
		
	}
	
	@Override
	public void addDescription(ProductSheet d) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public Product findProduct(String name) throws SQLException{
		
		Statement stmt = conn.createStatement();
		 ResultSet res = stmt.executeQuery("SELECT name,price,descriptiontext FROM Product NATURAL JOIN DESCRIPTION WHERE name ='"+name+"'");
		 if(res.next()){
			return factory.createProduct(res.getObject(1).toString(),res.getString(3),res.getFloat(2)); 
		 }
		 
		 
		 return null; 
	}
	@Override
	public List<Product> findProducts(List<String> names) throws SQLException{
		Statement stmt = conn.createStatement();
		List<Product> res = new ArrayList<Product>();
		ResultSet resultset;
				
		for (String each : names){
			resultset = stmt.executeQuery("SELECT name,price FROM Product WHERE name ='"+each+"'");
			if(resultset.next() != false){
				res.add(factory.createProduct(resultset.getString(1),resultset.getString(3),resultset.getFloat(2))); 
			 }
		}
		return res;
	}


	@Override
	public List<Product> findAllProducts() throws SQLException {
List<Product> lp = new ArrayList<Product>();
		
		Statement stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery("SELECT PRODUCT.name,PRODUCT.price,DESCRIPTION.DescriptionText FROM PRODUCT NATURAL JOIN DESCRIPTION ");
	
		while(res.next()){
			lp.add(factory.createProduct(res.getString(1),res.getString(3),res.getFloat(2)));
		}
		return lp;
	}
	
	
}
