package serviceMethod;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;


import entities.Product;
import methodService.methodService;


@WebService(endpointInterface = "serviceMethod.ServiceExpo")
public class ServiceExpoImpl implements ServiceExpo {
	methodService m;
	
	public ServiceExpoImpl(){
		m = new methodService();
	}
	@WebMethod(operationName = "sendProduct")
	@Override
	public Product sendProduct(String name) {
		return m.sendProductByName(name);
	}
	@WebMethod(operationName = "sendProducts")
	@Override
	public List<Product> sendProducts(List<String> names) {
		return m.sendProductsByName(names);
	}
	@WebMethod(operationName = "sendAllProducts")
	@Override
	public List<Product> sendAllProducts() {
		return m.sendAllProducts();
	}
	
	
}
