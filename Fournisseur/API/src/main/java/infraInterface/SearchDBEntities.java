package infraInterface;

import java.sql.SQLException;
import java.util.List;

import entities.Product;
/**
 * Interface which search in the db in the Infrastructure
 * 
 * */
public interface SearchDBEntities {
	
	/**
	 * @return Product : the product find by the db
	 * @param name : name of the product wanted
	 * */
	public Product findProduct(String name) throws SQLException;
	/**
	 * @return List<Product> : All the product find by the db
	 * @param names : The name of all the products wanted
	 * */
	public List<Product> findProducts(List<String> names) throws SQLException;
	/**
	 * @return List<Product> : All the product in the db
	 * 
	 * */
	public List<Product> findAllProducts() throws SQLException;
}
