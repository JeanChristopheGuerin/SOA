package serviceMethodInterface;

import java.util.List;

import entities.Product;

public interface serviceInterface {
	public Product sendProductByName(String name);
	public List<Product> sendProductsByName(List<String> names);
}
