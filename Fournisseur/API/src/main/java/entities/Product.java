package entities;


public class Product{
	
	protected String name;
	protected ProductSheet description;
	protected float price;
	
	public Product(){
		name = null;
		description = null;
		price = 0;
	}
	
	public Product(String name, ProductSheet desc, float price){
		this.name = name;
		this.description = desc;
		this.price = price;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductSheet getDescription() {
		return description;
	}
	public void setDescription(ProductSheet description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
