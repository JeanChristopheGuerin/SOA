package infraInterface;
import entities.*;
/**
 * Factory Interface of the different Class in the project
 * 
 * */

public interface FactoryI {
	/**
	 * 
	 * @param name : name of the product
	 * @param desc : description of the product with a String
	 * @param price : price of the product
	 * 
	 * @return Product : instance of the class Product
	 * */
	public Product createProduct(String name, String desc,float price);
	/**
	 * 
	 * @param name : name of the product
	 * @param desc : description of the product with a ProductSheet class
	 * @param price : price of the product
	 * 
	 * @return Product : instance of the class Product
	 * */
	public Product createProduct(String name, ProductSheet desc, float price);
	/**
	 * Create a product without description
	 * 
	 * @param name : name of the product
	 * @param price : price of the product
	 * */
	public Product createProduct(String name, float price);
	
	/**
	 * 
	 * @param name : name of the supplier
	 * 
	 * @return Supplier : instance of the class Supplier
	 * */
	public Supplier createSupplier(String name);
	
	/**
	 * 
	 * @param desc : textual description in the ProductSheet with a String
	 * 
	 * @return ProductSheet : instance of the class ProductSheet
	 * */
	public ProductSheet createProductSheet(String description);
	
	
}
