package Assignment;

public class Laptop extends Product {
	
	private String graphicCard;
	private String processor;
	private int ram;
	
	public Laptop (String name, double price, int quantity, int number, String graphicCard, String processor, int ram )
	{
		super(name, price, quantity, number);
		this.graphicCard= graphicCard;
		this.processor = processor;
		this.ram = ram;
	}

	public String getGraphicCard() {
		return graphicCard;
	}

	public void setGraphicCard(String graphicCard) {
		this.graphicCard = graphicCard;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}
	
	public double total_value_Laptop()
	{
		return super.getQuantity() * super.getPrice();
 	}
	
	@Override
	public String toString()
	{
		return "Item number\t\t\t: " + super.getNumber() + "\n" +
                "Product name\t\t\t: " + super.getName() + "\n" +
                "Graphic card\t\t\t: " + getGraphicCard() + "\n" +
                "Processor\t\t\t\t: " + getProcessor() + "\n" +
                "RAM\t\t\t\t\t: " + getRam() + "\n" +
                "Quantity available\t\t: " + super.getQuantity() + "\n" +
                "Price (RM)\t\t\t: " + super.getPrice() + "\n" +
                "Inventory value (RM)\t: " + total_value_Laptop() + "\n" +
                "Product status\t\t\t: " + (super.isStatus() ? "Active" : "Discontinued") + "\n";
	}
}
