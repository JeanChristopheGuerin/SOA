import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import entities.Product;
import serviceMethod.ServiceExpo;
import serviceMethod.ServiceExpoImpl;

public class TestServiceExpo {
	@Test
	public void testSendproduct(){
		ServiceExpo s = new ServiceExpoImpl();
		Product p1 = s.sendProduct("a");
		assert p1.getDescription().getDescriptionText()=="a";
		System.out.println(p1.getDescription().getDescriptionText());
	}
	
	@Test
	public void testSendproducts(){
		ServiceExpo s = new ServiceExpoImpl();
		List<Product> p = s.sendProducts(Arrays.asList("a","b","c"));
		assert p.size()==3;
		System.out.println(p.get(2).getDescription().getDescriptionText());
	}
	
	@Test
	public void testSendAllProducts(){
		ServiceExpo s = new ServiceExpoImpl();
		List<Product> products= s.sendAllProducts();
		assert products.size()>0;
		System.out.println(products.get(3).getDescription().getDescriptionText());
	}
}
