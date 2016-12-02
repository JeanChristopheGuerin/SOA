package infraInterface;

import java.sql.SQLException;

import entities.Product;
import entities.ProductSheet;
import entities.Supplier;

/**
 * Interface use to change the DB
 * 
 * */
public interface CRUDI {
	/**
	 * @param p : A Product
	 * 
	 * */
	public void addProduct(Product p) throws SQLException;
	/**
	 * @param p : A Supplier
	 * 
	 * */
	public void addSupplier(Supplier s) throws SQLException;
	/**
	 * @param p : A ProductSheet (Description of a Product)
	 * 
	 * */
	public void addDescription(ProductSheet d) throws SQLException;
}
