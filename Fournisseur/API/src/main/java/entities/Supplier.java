package entities;
/**
 * Class unused which represents the supplier
 * 
 * */
public class Supplier {

	private String name;
	
	public Supplier(){
		name = null;
	}
	public Supplier(String n){
		this.name = n;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

