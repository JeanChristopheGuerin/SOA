package serviceMethod;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import entities.Product;
import methodService.methodService;


@WebService() 
public class serviceMethodExpo {
	
	methodService m;
	
	public serviceMethodExpo(){
		m = new methodService();
	}
	@WebMethod(operationName = "sendProduct")
	public @WebResult(name = "Product") Product sendProduct( String name) {
		return m.sendProductByName(name);
	}

	@WebMethod(operationName = "sendProducts")
	public  @WebResult(name = "Product") List<Product> sendProducts(@WebParam(name = "nameProducts") List<String> names) {
		return m.sendProductsByName(names);
	}
	
}
