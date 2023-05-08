package test;

import java.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;


import model.*;

public class ControllerTest {

    private Controller controller;
    private List<Product> products;
    private List<Order> orders;

    @Before
    public void setup() {
        controller = new Controller();
        products = new ArrayList<>();

        products.add(new Product("Producto 1", "Descripción 1", 10.0, 5, Category.BEAUTY_AND_PERSONAL_CARE));
        products.add(new Product("Producto 2", "Descripción 2", 15.0, 10, Category.BOOKS));
        products.add(new Product("Producto 3", "Descripción 3", 20.0, 8, Category.ELECTRONICS));
        products.add(new Product("Producto 4", "Descripción 4", 12.0, 12, Category.FOOD_AND_DRINKS));
    }

    public void setup2() {
        orders = new ArrayList<>();

        List<Product> products1 = new ArrayList<>();
        products1.add(new Product("Producto1", "Descripción1", 10.0, 5, Category.BEAUTY_AND_PERSONAL_CARE));
        products1.add(new Product("Producto2", "Descripción2", 15.0, 10, Category.BOOKS));
        Order order1 = new Order("Comprador1", products1, new Date(2023, 5, 1));
        orders.add(order1);

        List<Product> products2 = new ArrayList<>();
        products2.add(new Product("Producto3", "Descripción3", 20.0, 8, Category.ELECTRONICS));
        products2.add(new Product("Producto4", "Descripción4", 12.0, 12, Category.STATIONERY));
        Order order2 = new Order("Comprador2", products2, new Date(2023, 5, 2));
        orders.add(order2);

        List<Product> products3 = new ArrayList<>();
        products3.add(new Product("Producto5", "Descripción5", 18.0, 6, Category.FOOD_AND_DRINKS));
        products3.add(new Product("Producto6", "Descripción6", 25.0, 15, Category.TOYS_AND_GAMES));
        Order order3 = new Order("Comprador3", products3, new Date(2023, 5, 3));
        orders.add(order3);

        List<Product> products4 = new ArrayList<>();
        products4.add(new Product("Producto7", "Descripción7", 30.0, 10, Category.BOOKS));
        products4.add(new Product("Producto8", "Descripción8", 40.0, 20, Category.ELECTRONICS));
        Order order4 = new Order("Comprador4", products4, new Date(2023, 5, 4));
        orders.add(order4);
    }

    // Registrar productos

    @Test
    public void testRegisterProducts1() {
        Product product = new Product("Libro para niños", "Un emocionante libro de aventuras para todas las edades", 29.99, 50, Category.BOOKS);
        Controller controller = new Controller();
    
        controller.addProduct(product);
        List<Product> products = controller.getProducts();
   
        Assert.assertEquals(1, products.size());
    
        Product registeredProduct = products.get(0);
        Assert.assertEquals("Libro para niños", registeredProduct.getName());
        Assert.assertEquals("Un emocionante libro de aventuras para todas las edades", registeredProduct.getDescription());
        Assert.assertEquals(29.99, registeredProduct.getPrice(), 0.01);
        Assert.assertEquals(50, registeredProduct.getQuantityAvailable());
        Assert.assertEquals(Category.BOOKS, registeredProduct.getCategory());
        Assert.assertEquals(0, registeredProduct.getTimesPurchased());
    }

    @Test
    public void testRegisterProducts2() {
        Controller controller = new Controller();
    
        try {
            Product product = new Product("Camiseta", "Camiseta de algodon", -20000.56, 10, Category.CLOTHING_AND_ACCESSORIES);
            controller.addProduct(product);
            Assert.fail("Excepcion debido al precio negativo");
        } catch (IllegalArgumentException e) {

            Assert.assertEquals("El precio del producto no puede ser negativo", e.getMessage());
        }
    
        List<Product> products = controller.getProducts();
        Assert.assertEquals(0, products.size());
    }

    @Test
    public void testAddProduct3() {
        List<Product> initialProducts = new ArrayList<>(products);
        Product existingProduct = products.get(0); 
    
        controller.addProduct(existingProduct);
    
        Assert.assertEquals(initialProducts.size(), products.size());
        Assert.assertTrue(products.containsAll(initialProducts));
    }

    @Test
    public void testAddProduct4() {
        List<Product> initialProducts = new ArrayList<>(products);
        Product existingProduct = products.get(0);
    
        controller.addProduct(existingProduct);
    
        // Verificar que no se haya producido ningún cambio en la lista de productos
        Assert.assertEquals(initialProducts.size(), products.size());
        Assert.assertTrue(products.containsAll(initialProducts));
    }

    @Test
    public void testAddProduct_InvalidProduct_NullProduct_NotAddedToList() {
        Product nullProduct = null;
        int initialSize = products.size();
    
        controller.addProduct(nullProduct);
    
        Assert.assertEquals(initialSize, products.size());
    }

    // Registrar ordenes

    @Test
    public void testRegisterOrder1() {
        Controller controller = new Controller();
    
        Product product1 = new Product("Camiseta", "Camiseta de algodón", 55600.9, 10, Category.CLOTHING_AND_ACCESSORIES);
        Product product2 = new Product("Libro", "Novela de drama", 30899, 20, Category.BOOKS);
       
        controller.addProduct(product1);
        controller.addProduct(product2);
    
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
    
        Order order = new Order("Juan", products, new Date());
    
        controller.addOrder(order);
    
        List<Order> orders = controller.getOrders();
        Assert.assertEquals(1, orders.size());
        Assert.assertEquals(order, orders.get(0));
    
        Assert.assertEquals(9, product1.getQuantityAvailable());
        Assert.assertEquals(19, product2.getQuantityAvailable());
    }

    @Test
    public void testAddOrderWithEmptyProducts() {
        Controller controller = new Controller();
        ArrayList<Product> products = new ArrayList<>();
        Date date = new Date();
        Order order = new Order("Buyer 1", products, date);
        controller.addOrder(order);
        Assert.assertEquals(1, controller.getOrders().size());
        Assert.assertEquals(order, controller.getOrders().get(0));
    }

    @Test
    public void testAddOrder3() {
        Order newOrder = new Order("Comprador5", products, new Date(2023, 5, 5));
    
        controller.addOrder(newOrder);
    
        double expectedTotalPrice = 0;
        for (Product product : products) {
            expectedTotalPrice += product.getPrice();
        }
    
        Assert.assertEquals(expectedTotalPrice, newOrder.getTotalPrice(), 0.01);
    }

    @Test
    public void testAddOrder4() {
        setup2();
        List<Order> initialOrders = new ArrayList<>(orders); // Copia de la lista inicial de pedidos
        Order newOrder = new Order("Comprador5", new ArrayList<>(), new Date(2023, 5, 5));
    
        controller.addOrder(newOrder);
    
        // Verificar que la lista de pedidos no se haya modificado
        Assert.assertEquals(initialOrders.size(), orders.size());
        Assert.assertTrue(orders.containsAll(initialOrders));
    }


    // Buscar productos por nombre

    @Test
    public void searchByNameInterval1() {
        List<Product> products = new ArrayList<>();
        String startPrefix = "A";
        String endPrefix = "Z";
        boolean ascendingOrder = true;
        Controller controller = new Controller();
    
        List<Product> results = controller.searchByNameInterval(products, startPrefix, endPrefix, ascendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void searchByNameInterval2() {
        List<Product> products = new ArrayList<>();
        
        products.add(new Product("A", "Description", 10.0, 5, Category.BOOKS));
        products.add(new Product("B", "Description", 15.0, 6, Category.CLOTHING_AND_ACCESSORIES));
        products.add(new Product("C", "Description", 5.0, 8, Category.SPORTS));
        products.add(new Product("O", "Description", 8.0, 8, Category.CLOTHING_AND_ACCESSORIES));
    
        String startPrefix = "A";
        String endPrefix = "C";
        boolean ascendingOrder = true;
        Controller controller = new Controller();

        List<Product> results = controller.searchByNameInterval(products, startPrefix, endPrefix, ascendingOrder);
    
        Assert.assertFalse(results.isEmpty());
    }

    @Test
    public void searchByNameInterval3() {
        List<Product> products = new ArrayList<>();
        
        products.add(new Product("A", "Description", 10.0, 7, Category.BEAUTY_AND_PERSONAL_CARE));
        products.add(new Product("B", "Description", 15.0, 7, Category.STATIONERY));
        products.add(new Product("C", "Description", 5.0, 2, Category.FOOD_AND_DRINKS));
        products.add(new Product("O", "Description", 8.0, 1, Category.TOYS_AND_GAMES));
    
        String startPrefix = "X";
        String endPrefix = "Z";
        boolean ascendingOrder = true;
        Controller controller = new Controller();
    
        List<Product> results = controller.searchByNameInterval(products, startPrefix, endPrefix, ascendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void searchByNameInterval4() {
        List<Product> products = new ArrayList<>();
        
        products.add(new Product("A", "Description", 10.0, 6, Category.SPORTS));
        products.add(new Product("B", "Description", 15.0, 2, Category.BOOKS));
        products.add(new Product("C", "Description", 5.0, 9, Category.ELECTRONICS));
        products.add(new Product("O", "Description", 8.0, 10, Category.BEAUTY_AND_PERSONAL_CARE));
    
        String startPrefix = "A";
        String endPrefix = "C";
        boolean ascendingOrder = false;
        Controller controller = new Controller();
    
        List<Product> results = controller.searchByNameInterval(products, startPrefix, endPrefix, ascendingOrder);
    
        Assert.assertFalse(results.isEmpty());
    }

    @Test
    public void searchByNameInterval5() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("A", "Description", 10.0, 1, Category.ELECTRONICS));
        products.add(new Product("B", "Description", 15.0, 5, Category.STATIONERY));
        products.add(new Product("C", "Description", 5.0, 9, Category.BEAUTY_AND_PERSONAL_CARE));
        products.add(new Product("O", "Description", 8.0, 12, Category.SPORTS));
    
        String startPrefix = "Z";
        String endPrefix = "A";
        boolean ascendingOrder = true;

        Controller controller = new Controller();
    
        List<Product> results = controller.searchByNameInterval(products, startPrefix, endPrefix, ascendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    // Buscar productos por rango de precio

    
    @Test
    public void testSearchByPriceRange2() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Libro", "Descripción A", 10, 5, Category.BOOKS));
        products.add(new Product("Lavadora", "Descripción B", 15, 2, Category.ELECTRONICS));
        products.add(new Product("Crema facial", "Descripción C", 18, 5, Category.BEAUTY_AND_PERSONAL_CARE));

        double minPrice = 30.0; 
        double maxPrice = 20.0;
        boolean ascendingOrder = true;

        Controller controller = new Controller();
        List<Product> searchResults = controller.searchByPriceRange(products, minPrice, maxPrice, ascendingOrder);

        Assert.assertTrue(searchResults.isEmpty());
    }

    // Buscar productos por categoria


    @Test
    public void testSearchProductsByCategory1() {
        Category category = null;
        boolean ascendingOrder = true;

        List<Product> products = new ArrayList<>();
        products.add(new Product("Producto A", "Descripción A", 10.0, 5, Category.ELECTRONICS));
        products.add(new Product("Producto B", "Descripción B", 15.0, 10, Category.ELECTRONICS));
        products.add(new Product("Producto C", "Descripción C", 20.0, 3, Category.ELECTRONICS));

        Controller controller = new Controller();
        List<Product> searchResults = controller.searchProductsByCategory(category, ascendingOrder);

        Assert.assertEquals(0, searchResults.size());
    }

    @Test
    public void testSearchProductsByCategory2() {
        Category category = Category.CLOTHING_AND_ACCESSORIES;
        boolean ascendingOrder = true;

        List<Product> products = new ArrayList<>();

        Controller controller = new Controller();
        List<Product> searchResults = controller.searchProductsByCategory(category, ascendingOrder);

        Assert.assertTrue(searchResults.isEmpty());
    }

    // Buscar productos por numero de veces comprado

    @Test
    public void testSearchByTimesPurchasedRange1() {
        List<Product> products = new ArrayList<>();
        int minPrice = 100;
        int maxPrice = 500;
        boolean ascendingOrder = true;
        
        Controller controller = new Controller();
        List<Product> results = controller.searchByTimesPurchasedRange(products, minPrice, maxPrice, ascendingOrder);

        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByTimesPurchasedRange() {
        setup();
        int minPrice = 30;
        int maxPrice = 40;
        boolean ascendingOrder = true;

        List<Product> results = controller.searchByTimesPurchasedRange(products, minPrice, maxPrice, ascendingOrder);

        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByTimesPurchasedRangeNoResults() {
        int minPrice = 25;
        int maxPrice = 30;
        boolean ascendingOrder = true;
    
        List<Product> results = controller.searchByTimesPurchasedRange(products, minPrice, maxPrice, ascendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByTimesPurchasedRangeNoResults3() {
        int minPrice = 25;
        int maxPrice = 50;
        boolean ascendingOrder = true;
    
        List<Product> results = controller.searchByTimesPurchasedRange(products, minPrice, maxPrice, ascendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByTimesPurchasedRange2() {
        int minTimesPurchased = 2;
        int maxTimesPurchased = 5;
        boolean ascendingOrder = true;

        List<Product> results = controller.searchByTimesPurchasedRange(products, minTimesPurchased, maxTimesPurchased, ascendingOrder);

        for (Product product : results) {
            int timesPurchased = product.getTimesPurchased();
            Assert.assertTrue(timesPurchased >= minTimesPurchased && timesPurchased <= maxTimesPurchased);
        }
    }

    @Test
    public void testSearchByTimesPurchasedRange3() {
        int minTimesPurchased = 10;
        int maxTimesPurchased = 20;
        boolean ascendingOrder = true;

        List<Product> results = controller.searchByTimesPurchasedRange(products, minTimesPurchased, maxTimesPurchased, ascendingOrder);

        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByTimesPurchasedRange4() {
        int minTimesPurchased = 1;
        int maxTimesPurchased = 10;
        boolean descendingOrder = false;
    
        List<Product> results = controller.searchByTimesPurchasedRange(products, minTimesPurchased, maxTimesPurchased, descendingOrder);
    
        int previousTimesPurchased = Integer.MAX_VALUE;
        for (Product product : results) {
            int timesPurchased = product.getTimesPurchased();
            Assert.assertTrue(timesPurchased <= previousTimesPurchased);
            previousTimesPurchased = timesPurchased;
        }
    }

    @Test
    public void testSearchByTimesPurchasedRange5() {
        int minTimesPurchased = 10;
        int maxTimesPurchased = 5;
        boolean ascendingOrder = true;
    
        List<Product> results = controller.searchByTimesPurchasedRange(products, minTimesPurchased, maxTimesPurchased, ascendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByTimesPurchasedRange6() {
        List<Product> emptyProducts = new ArrayList<>();
        int minTimesPurchased = 1;
        int maxTimesPurchased = 5;
        boolean ascendingOrder = true;
    
        List<Product> results = controller.searchByTimesPurchasedRange(emptyProducts, minTimesPurchased, maxTimesPurchased, ascendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    // Buscar pedido por nombre de comprador

    @Test
    public void testSearchByBuyerNameInterval2() {
        List<Order> emptyOrders = new ArrayList<>();
        String startPrefix = "Comp";
        String endPrefix = "dor";
        boolean ascendingOrder = true;
    
        List<Order> results = controller.searchByBuyerNameInterval(emptyOrders, startPrefix, endPrefix, ascendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByBuyerNameInterval3() {
        setup2();
        String startPrefix = "Comp";
        String endPrefix = "";
        boolean ascendingOrder = true;
    
        List<Order> results = controller.searchByBuyerNameInterval(orders, startPrefix, endPrefix, ascendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    // Buscar pedidio por precio total
    
    @Test
    public void testSearchByTotalPriceRange1() {
        setup2();
        double minTotalPrice = 20.0;
        double maxTotalPrice = 35.0;
        boolean ascendingOrder = true;
    
        List<Order> results = controller.searchByTotalPriceRange(orders, minTotalPrice, maxTotalPrice, ascendingOrder);
    
        Assert.assertFalse(results.isEmpty());
    
        for (Order order : results) {
            double totalPrice = order.getTotalPrice();
            Assert.assertTrue(totalPrice >= minTotalPrice && totalPrice <= maxTotalPrice);
        }
    }

    @Test
    public void testSearchByTotalPriceRange2() {
        setup2();
        double minTotalPrice = 10.0;
        double maxTotalPrice = 30.0;
        boolean descendingOrder = false;
    
        List<Order> results = controller.searchByTotalPriceRange(orders, minTotalPrice, maxTotalPrice, descendingOrder);
    
        Assert.assertFalse(results.isEmpty());
    
        double previousTotalPrice = Double.MAX_VALUE;
        for (Order order : results) {
            double totalPrice = order.getTotalPrice();
            Assert.assertTrue(totalPrice <= previousTotalPrice);
            previousTotalPrice = totalPrice;
        }
    }

    @Test
    public void testSearchByTotalPriceRange3() {
        List<Order> emptyOrders = new ArrayList<>();
        double minTotalPrice = 10.0;
        double maxTotalPrice = 20.0;
        boolean ascendingOrder = true;
    
        List<Order> results = controller.searchByTotalPriceRange(emptyOrders, minTotalPrice, maxTotalPrice, ascendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByTotalPriceRange4() {
        setup2();
        double minTotalPrice = 0.0;
        double maxTotalPrice = Double.MAX_VALUE;
        boolean ascendingOrder = true;
    
        List<Order> results = controller.searchByTotalPriceRange(orders, minTotalPrice, maxTotalPrice, ascendingOrder);
    
        Assert.assertFalse(results.isEmpty());
    
        Assert.assertEquals(orders.size(), results.size());
    }

    @Test
    public void testSearchByTotalPriceRange5() {
        setup2();
        double minTotalPrice = 30.0;
        double maxTotalPrice = 30.0;
        boolean ascendingOrder = true;
    
        List<Order> results = controller.searchByTotalPriceRange(orders, minTotalPrice, maxTotalPrice, ascendingOrder);
    
        for (Order order : results) {
            double totalPrice = order.getTotalPrice();
            Assert.assertEquals(minTotalPrice, totalPrice, 0.0);
        }
    }

    @Test
    public void testSearchByTotalPriceRange6() {
        setup2();
        double minTotalPrice = 100.0;
        double maxTotalPrice = 200.0;
        boolean descendingOrder = false;
    
        List<Order> results = controller.searchByTotalPriceRange(orders, minTotalPrice, maxTotalPrice, descendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByTotalPriceRange7() {
        setup2();
        double minTotalPrice = -50.0;
        double maxTotalPrice = -10.0;
        boolean ascendingOrder = true;
    
        List<Order> results = controller.searchByTotalPriceRange(orders, minTotalPrice, maxTotalPrice, ascendingOrder);
    
        Assert.assertTrue(results.isEmpty());
    }

    // Buscar pedido por fecha de compra

    @Test
    public void testSearchOrdersByDate1() {
        setup2();
        Date date = new Date(2023, 5, 2);
    
        List<Order> results = controller.searchOrdersByDate(date);
    
        for (Order order : results) {
            Date orderDate = order.getDate();
            Assert.assertEquals(date, orderDate);
        }
    }

    @Test
    public void testSearchOrdersByDate2() {
        Date date = new Date(2023, 4, 30); // Fecha que no existe en el conjunto de pedidos
    
        List<Order> results = controller.searchOrdersByDate(date);
    
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchOrdersByDate3() {
        setup2();
        List<Order> emptyOrders = new ArrayList<>();
        Date date = new Date(2023, 5, 1); // Fecha que existe, pero no hay pedidos
    
        List<Order> results = controller.searchOrdersByDate(date);
    
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchOrdersByDate4() {
        setup2();
        Date date = null; // Fecha nula
    
        List<Order> results = controller.searchOrdersByDate(date);
    
        // Verificar que la lista de resultados esté vacía
        Assert.assertTrue(results.isEmpty());
    }

}