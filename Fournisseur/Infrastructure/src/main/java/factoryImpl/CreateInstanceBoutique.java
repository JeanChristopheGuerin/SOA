package factoryImpl;

import entities.Product;
import entities.ProductSheet;
import entities.Supplier;
import infraInterface.FactoryI;

public class  CreateInstanceBoutique implements FactoryI {

	@Override
	public Product createProduct(String name, ProductSheet desc, float price) {
		return new Product(name, desc, price);
	}

	@Override
	public Supplier createSupplier(String name) {
		return new Supplier(name);
	}

	@Override
	public ProductSheet createProductSheet(String description) {
		return new ProductSheet(description);
	}

	@Override
	public Product createProduct(String name, String desc, float price) {
		return new Product(name,new ProductSheet(desc),price);
	}
	
	@Override
	public Product createProduct(String name, float price) {
		return new Product(name,price);
	}
}
