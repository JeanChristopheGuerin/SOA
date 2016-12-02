package methodService;

import java.sql.SQLException;
import java.util.List;

import DB.DataBase;
import entities.Product;
import serviceMethodInterface.serviceInterface;

public class methodService implements serviceInterface {
	DataBase db;
	public methodService(){
		db = new DataBase();
		try {
			db.connectDB();
			db.createTables();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public Product sendProductByName(String name) {
		
		try {
			return db.findProduct(name);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public List<Product> sendProductsByName(List<String> names) {
		
		try {
			;
			return db.findProducts(names);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> sendAllProducts() {
		try {
			return db.findAllProducts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
