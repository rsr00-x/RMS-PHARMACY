//package models;

public class Medicine extends Entity {
    private double price;
    private int quantity;

	public Medicine(){}
    
    public Medicine(int id, String name, double price, int quantity) {
        super(id, name);
        setPrice(price);
        setQuantity(quantity);
    }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    
    public void display() {
        super.display();
		System.out.println("Price: " + price);
		System.out.println("Quantity: " + quantity);
    }
}
