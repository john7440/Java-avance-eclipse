package fr.exos.jdbc;

public class Article {
	private int id;
	private String description;
	private String brand;
	private double price;
	
	public Article(int id, String description, String brand, double price) {
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.price = price;
    }
	
	
	public Article(int id, String description, String brand) {
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.price = 0;
    }
	
	public Article(int id, String brand, double price) {
        this.id = id;
        this.description = "Unknown";
        this.brand = brand;
        this.price = price;
    }

	// Getters
    public int getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
}
