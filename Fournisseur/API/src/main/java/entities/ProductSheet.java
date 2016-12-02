package entities;

/**
 * Class wich represents a description of the product
 * 
 * */

public class ProductSheet {
	
	/*protected String productName;*/
	
	protected String descriptionText;
	
	public ProductSheet() {
		descriptionText = "";
	}

	public ProductSheet(String description) {
		descriptionText = description;
	}

	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
}
