package Assignment;

public class Refrigerator extends Product {

	private String doorDesign;
	private String color;
	private double capacity;
	
	public Refrigerator (String name, double price, int quantity, int number, String doorDesign, String color, double capacity )
	{
		super(name, price, quantity, number);
		this.doorDesign= doorDesign;
		this.color = color;
		this.capacity = capacity;
	}

	public String getDoorDesign() {
		return doorDesign;
	}

	public void setDoorDesign(String doorDesign) {
		this.doorDesign = doorDesign;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public double total_value_ref()
	{
		return super.getQuantity() * super.getPrice();
	}
	
	@Override
	public String toString()
	{
		return "Item number\t\t\t: " + super.getNumber() + "\n" +
                "Product name\t\t\t: " + super.getName() + "\n" +
                "Door design\t\t\t: " + getDoorDesign() + "\n" +
                "Color\t\t\t\t: " + getColor() + "\n" +
                "Capacity (in Litres)\t\t: " + getCapacity() + "\n" +
                "Quantity available\t\t: " + super.getQuantity() + "\n" +
                "Price (RM)\t\t\t: " + super.getPrice() + "\n" +
                "Inventory value (RM)\t: " + total_value_ref() + "\n" +
                "Product status\t\t\t: " + (super.isStatus() ? "Active" : "Discontinued") + "\n";
                
		
	}
	
}
