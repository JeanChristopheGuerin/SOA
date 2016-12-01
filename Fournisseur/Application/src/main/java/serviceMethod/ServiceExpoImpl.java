package serviceMethod;

import java.util.List;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlType;

import entities.Product;
import methodService.methodService;


@WebService(endpointInterface = "serviceMethod.ServiceExpo")
public class ServiceExpoImpl implements ServiceExpo {
	methodService m;
	
	public ServiceExpoImpl(){
		m = new methodService();
	}

	@Override
	public Product sendProduct(String name) {
		return m.sendProductByName(name);
	}

	@Override
	public List<Product> sendProducts(List<String> names) {
		return m.sendProductsByName(names);
	}
	
	
}
