package serviceMethodInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Product;

public interface serviceInterface {
	public Product sendProductByName(String name);
	public List<Product> sendProductsByName(List<String> names);
	
}
