package methodService;

import java.sql.SQLException;
import java.util.List;

import DB.DataBase;
import entities.Product;
import serviceMethodInterface.serviceInterface;

public class methodService implements serviceInterface {

	@Override
	public Product sendProductByName(String name) {
		
		DataBase db = new DataBase();
		try {
			db.connectDB();
			return db.findProduct(name);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> sendProductsByName(List<String> names) {
		DataBase db = new DataBase();
		try {
			db.connectDB();
			return db.findProducts(names);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
}
