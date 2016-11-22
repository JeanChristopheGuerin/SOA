package factoriesInterface;
import entities.*;
public interface FactoryI {

	public Product createProduct(String name, String Desc, float price);
	public Fournisseur createSupplier(String name,);

}
