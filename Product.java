package Assignment;

public abstract class Product {

	private String name;
	private double price;
	private int quantity;
	private int number;
	private boolean status = true;
	
	//Default Constructor
	public Product()
	{
		this.name = "";
		this.price = 0.0;
		this.quantity = 0;
		this.number = 0;
	}
	
	public Product(String name, double price, int quantity, int number) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public double total_inventory_value()
	{
		return getQuantity()* getPrice();
	}
	
	public void add_inventory (int quantity)
	{
		if (status) this.quantity += quantity;
		else this.quantity=getQuantity();
		
	}
	
	public void deduct_inventory (int quantity)
	{
		if (this.quantity - quantity < 0) this.quantity = getQuantity() ;
		else this.quantity -= quantity;
	}
	
	
	@Override
	public String toString()
	{
		return String.format("%-25s", "Item number") + ": " + getNumber() + "\n" + 
		           String.format("%-25s", "Product name") + ": " + getName() +  "\n" + 
		           String.format("%-25s", "Quantity available") + ": " + getQuantity() +  "\n" + 
		           String.format("%-25s", "Price(RM)") + ": " + getPrice() +  "\n" + 
		           String.format("%-25s", "Inventory value(RM)") + ": " + total_inventory_value() +  "\n" + 
		           String.format("%-25s", "Product status") + ": " + (isStatus() ? "Active" : "Discontinued") + "\n";
	}
	
}
