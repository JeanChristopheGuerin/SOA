package serviceMethod;

import javax.xml.ws.Endpoint;



public class ServiceExpoPublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9000/ws/test", new ServiceExpoImpl());
	
	}
}
