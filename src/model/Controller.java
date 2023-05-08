package model;
import java.util.*;
import java.util.Date;

public class Controller {
    
    private List<Product> products;
    private List<Order> orders;
    private Searcher searcher;
 
    public Controller() {
        Map<String, Object> data = DataSerializer.loadData();
        if (data == null) {
            products = new ArrayList<>();
            orders = new ArrayList<>();
        } else {
            products = (List<Product>) data.get("products");
            orders = (List<Order>) data.get("orders");
        }
        searcher = new Searcher(products, orders);
    }
 
    public void addProduct(Product product) {
        products.add(product);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Product> getProducts() {
        return products;
    }
    
}