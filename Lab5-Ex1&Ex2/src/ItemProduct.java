import java.io.Serializable;

public class ItemProduct implements Serializable{
	private int itemProductID;
	private String name;
	private float price;
	
	public void setItemProductID(int itemProductId) {
		this.itemProductID = itemProductId;
	}
	
	public int getItemProductID() {
		return itemProductID;
	}
	
	public void setName(String Name) {
		this.name = Name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPrice(float Price) {
		this.price = Price;
	}
	
	public float getPrice() {
		return price;
	}
	
}
