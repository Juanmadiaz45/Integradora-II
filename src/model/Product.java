package model;

public class Product {
    private String name;
    private String description;
    private double price;
    private int quantityAvailable;
    private Category category;
    private int timesPurchased;

    public Product(String name, String description, double price, int quantityAvailable, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.category = category;
        this.timesPurchased = 0;
    }

    public void increaseQuantity(int amount) {
        this.quantityAvailable += amount;
    }

    public void decreaseQuantity(int amount) {
        this.quantityAvailable -= amount;
    }

    public void increaseTimesPurchased() {
        this.timesPurchased++;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return this.quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getTimesPurchased() {
        return this.timesPurchased;
    }

    public void setTimesPurchased(int timesPurchased) {
        this.timesPurchased = timesPurchased;
    }
    
}
