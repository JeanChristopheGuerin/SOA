package infraInterface;

import java.sql.SQLException;
import java.util.List;

import entities.Product;

public interface SearchDBEntities {
	public Product findProduct(String name) throws SQLException;
	
	public List<Product> findProducts(List<String> names) throws SQLException;
}
