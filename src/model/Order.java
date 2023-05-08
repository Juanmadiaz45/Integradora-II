package model;

import java.util.Date;
import java.util.List;

public class Order {
    private String buyerName;
    private List<Product> products;
    private double totalPrice;
    private Date date;

    public Order(String buyerName, List<Product> products, Date date) {
        this.buyerName = buyerName;
        this.products = products;
        this.date = date;

        for (Product product : products) {
            product.decreaseQuantity(1);
            product.increaseTimesPurchased();
        }

        this.totalPrice = calculateTotalPrice();
    }

    public void addProductQuantity(String productName, int quantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.decreaseQuantity(quantity);
                for(int i = 0; i < quantity; i++){
                    product.increaseTimesPurchased();
                }
                break;
            }
        }
        calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }

        return totalPrice;
    }

    public String getBuyerName() {
        return this.buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
