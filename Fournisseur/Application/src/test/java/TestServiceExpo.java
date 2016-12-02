import org.junit.Test;

import entities.Product;
import serviceMethod.ServiceExpo;
import serviceMethod.ServiceExpoImpl;

public class TestServiceExpo {
	@Test
	public void test0(){
		ServiceExpo s = new ServiceExpoImpl();
		Product p1 = s.sendProduct("a");
		
		System.out.println(p1.getDescription().getDescriptionText());
	}
}
