package Assignment;

public class TV extends Product {

	private String screen;
	private String resolution;
	private double size;
	
	public TV (String name, double price, int quantity, int number, String screen, String resolution, double size )
	{
		super(name, price, quantity, number);
		this.screen= screen;
		this.resolution = resolution;
		this.size = size;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
	
	public double total_value_TV()
	{
		return super.getQuantity() * super.getPrice();
 	}
	
	@Override
	public String toString()
	{
		return "Item number\t\t\t: " + super.getNumber() + "\n" +
                "Product name\t\t\t: " + super.getName() + "\n" +
                "Screen Type\t\t\t: " + getScreen() + "\n" +
                "Resolution\t\t\t: " + getResolution() + "\n" +
                "Display size\t\t\t: " + getSize() + "\n" +
                "Quantity available\t\t: " + super.getQuantity() + "\n" +
                "Price (RM)\t\t\t: " + super.getPrice() + "\n" +
                "Inventory value (RM)\t: " + total_value_TV() + "\n" +
                "Product status\t\t\t: " + (super.isStatus() ? "Active" : "Discontinued") + "\n";
	}
}
