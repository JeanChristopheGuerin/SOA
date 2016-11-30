package infraInterface;

import java.sql.SQLException;

import entities.Product;
import entities.ProductSheet;
import entities.Supplier;

public interface CRUDI {
	public void addProduct(Product p) throws SQLException;
	public void addSupplier(Supplier s) throws SQLException;
	public void addDescription(ProductSheet d) throws SQLException;
}
