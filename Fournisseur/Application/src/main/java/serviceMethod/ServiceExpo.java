package serviceMethod;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import entities.Product;



@WebService() 
public interface ServiceExpo {
	
	
	@WebMethod(operationName = "sendProduct")
	public @WebResult(name = "Product") Product sendProduct( String name);
	

	@WebMethod(operationName = "sendProducts")
	public  @WebResult(name = "Product") List<Product> sendProducts(@WebParam(name = "nameProducts") List<String> names);
}
