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

    public List<Product> searchByNameInterval(List<Product> products, String startPrefix, String endPrefix, boolean ascendingOrder) {
        List<Product> results = searcher.searchByNameInterval(products, startPrefix, endPrefix, ascendingOrder);
        if (results.isEmpty()) {
            System.out.println("\nLo siento, el producto que busca no se encuentra registrado.");
        } else {
            for (Product product : results) {
                System.out.println("\nNombre: " + product.getName());
                System.out.println("Descripcion: " + product.getDescription());
                System.out.println("Precio: " + product.getPrice());
                System.out.println("Cantidad disponible: " + product.getQuantityAvailable());
                System.out.println("Categoria: " + product.getCategory());
                System.out.println("Veces comprado: " + product.getTimesPurchased());
                System.out.println("------------------------");
            }
        }
        
        return results;
    }

    public List<Product> searchByPriceRange(List<Product> products, double minPrice, double maxPrice, boolean ascendingOrder) {
        List<Product> results = searcher.searchByPriceRange(getProducts(), minPrice, maxPrice, ascendingOrder);
        if (results.isEmpty()) {
            System.out.println("\nLo siento, el producto que busca no se encuentra registrado.");
        } else {
            for (Product product : results) {
                System.out.println("\nNombre: " + product.getName());
                System.out.println("Descripcion: " + product.getDescription());
                System.out.println("Precio: " + product.getPrice());
                System.out.println("Cantidad disponible: " + product.getQuantityAvailable());
                System.out.println("Categoria: " + product.getCategory());
                System.out.println("Veces comprado: " + product.getTimesPurchased());
                System.out.println("------------------------");
            }
        }
        
        return results;
    }

    public List<Product> searchByTimesPurchasedRange(List<Product> products, int minPrice, int maxPrice, boolean ascendingOrder) {
        List<Product> results = searcher.searchByTimesPurchasedRange(products, minPrice, maxPrice, ascendingOrder);
        if (results.isEmpty()) {
            System.out.println("\nLo siento, el producto que busca no se encuentra registrado.");
        } else {
            for (Product product : results) {
                System.out.println("\nNombre: " + product.getName());
                System.out.println("Descripcion: " + product.getDescription());
                System.out.println("Precio: " + product.getPrice());
                System.out.println("Cantidad disponible: " + product.getQuantityAvailable());
                System.out.println("Categoria: " + product.getCategory());
                System.out.println("Veces comprado: " + product.getTimesPurchased());
                System.out.println("------------------------");
            }
        }
        
        return results;
    }

    public List<Order> searchByBuyerNameInterval(List<Order> orders, String startPrefix, String endPrefix, boolean ascendingOrder){
        List<Order> results =  searcher.searchByBuyerNameInterval(orders, startPrefix, endPrefix, ascendingOrder);
        if(orders.isEmpty()) {
            System.out.println("\nLo siento, el pedido que busca no se encuentra registrado.");
        } else{
            for(Order order : results) {
                System.out.println("\nNombre del comprador: " + order.getBuyerName());
                System.out.println("Precio total del pedido: " + order.getTotalPrice());
                System.out.println("Fecha de compra: " + order.getDate());
                System.out.println("------------------------");
            }
        }

        return results;
    }

    public List<Product> searchProductsByCategory(Category category, boolean ascendingOrder) {
        List<Product> results = searcher.searchProductsByCategory(category, ascendingOrder);
        if (results.isEmpty()) {
            System.out.println("\nLo siento, el producto que busca no se encuentra registrado.");
        } else {
            for (Product product : results) {
                System.out.println("\nNombre: " + product.getName());
                System.out.println("Descripcion: " + product.getDescription());
                System.out.println("Precio: " + product.getPrice());
                System.out.println("Cantidad disponible: " + product.getQuantityAvailable());
                System.out.println("Categoria: " + product.getCategory());
                System.out.println("Veces comprado: " + product.getTimesPurchased());
                System.out.println("------------------------");
            }
        }
        
        return results;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Product> getProducts() {
        return products;
    }
    
}