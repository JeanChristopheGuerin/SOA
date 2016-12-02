package serviceMethodInterface;

import java.util.List;

import entities.Product;
/**
 * Interface wich represents the different methods send to the user who use this Service
 * */
public interface serviceInterface {
	/**
	 * @return Product : Product send by the Service
	 * @param name : name of the product wanted
	 * 
	 * */
	public Product sendProductByName(String name);
	/**
	 * @return Product : Products send by the Service
	 * @param name : names of the products wanted
	 * 
	 * */
	public List<Product> sendProductsByName(List<String> names);
	/**
	 * @return Product : All the products in this Service
	 * 
	 * 
	 * */
	public List<Product> sendAllProducts();
}
