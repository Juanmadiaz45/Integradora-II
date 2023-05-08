package ui;

import model.*;
import java.util.Scanner;
import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main{

    public static void main(String[] args) {
        Controller controller = new Controller();
        boolean exit = false;
        Scanner reader = new Scanner(System.in);

        while(!exit){
            System.out.println(" ¡ Bienvenidos a Mercado Libre ! ");
            
            System.out.println("\n1. Agregar producto.");
            System.out.println("2. Agregar pedido.");
            System.out.println("3. Agregar mas cantidades del prodcuto de una orden.");
            System.out.println("4. Buscador.");
            System.out.println("5. Salir.");

            System.out.print("\nSelecciona una opcion: ");
            int option = reader.nextInt();
            reader.nextLine();

            switch(option){
                
                case 1:
                    System.out.print("\nNombre del producto: ");
                    String name = reader.nextLine();

                    System.out.print("Descripcion del producto: ");
                    String description = reader.nextLine();

                    System.out.print("Precio del producto: ");
                    double price = reader.nextDouble();

                    reader.nextLine();

                    System.out.print("Cantidad disponible: ");
                    int quantityAvailable = reader.nextInt();

                    reader.nextLine();

                    System.out.print("\n** Categorias **");

                    System.out.println("\n1. Libros");
                    System.out.println("2. Electronica.");
                    System.out.println("3. Ropa y accesorios.");
                    System.out.println("4. Alimentos y bebidas.");
                    System.out.println("5. Papeleria.");
                    System.out.println("6. Deportes");
                    System.out.println("7. Productos de belleza y cuidado personal.");
                    System.out.println("8. Juguetes y juegos.");

                    System.out.print("Categoria del producto (escriba el numero que corresponde): ");
                    int category = reader.nextInt();

                    Category cat = getCategory(category);

                    Product product = new Product(name, description, price, quantityAvailable, cat);
                    controller.addProduct(product);

                    System.out.println("\nProducto agregado exitosamente.\n");
                    break;

                case 2:
                    System.out.print("\nNombre del comprador: ");
                    String buyerName = reader.nextLine();

                    List<Product> products = controller.getProducts();
                    List<Product> selectedProducts = new ArrayList<>();

                    Order order = new Order(buyerName, selectedProducts, new Date());

                    System.out.println("\nAgrega productos al pedido. Para finalizar de agregar, escribe Stop.");

                    controller.listAllProducts();

                    while (true) {
                        System.out.print("Nombre del producto (o Stop para dejar de agregar productos): ");
                        String productName = reader.nextLine();

                        if (productName.equalsIgnoreCase("stop")) {
                            break;
                        }

                        product = findProductByName(products, productName);

                        if (product != null) {
                            if (product.getQuantityAvailable() > 0) {
                                selectedProducts.add(product);
                                System.out.println("Producto agregado a la orden.");

                                product.decreaseQuantity(1);
                                product.increaseTimesPurchased();
                            } else {
                                System.out.println("La cantidad del producto que deseas no esta disponible en este momento.");
                            }
                        } else {
                            System.out.println("Producto no encontrado en la lista de productos disponibles.");
                        }
                    }

                    System.out.println("\nOrden creada exitosamente.\n");
                    controller.addOrder(order);
                    break;

                case 3:

                    products = controller.getProducts();
                    List<Order> orders = controller.getOrders();

                    System.out.print("\nNombre del comprador: ");
                    buyerName = reader.nextLine();

                    order = findOrderByName(orders, buyerName);

                    if (order != null){
                        controller.listAllProducts();
                
                        System.out.print("Nombre del producto: ");
                        String productName = reader.nextLine();

                        product = findProductByName(products, productName);

                        if( product != null){
                            System.out.print("Cantidad adicional a agregar: ");
                            int quantityToAdd = reader.nextInt();
                            reader.nextLine();

                            if (product.getQuantityAvailable() > 0) {
                                products.add(product);
                                System.out.println("Producto agregado a la orden.");

                                product.decreaseQuantity(1);
                                product.increaseTimesPurchased();
                            } else {
                                System.out.println("La cantidad del producto que deseas no esta disponible en este momento.");
                            }
                
                            controller.increaseOrderProductQuantity(buyerName, productName, quantityToAdd);
                
                            System.out.println("\nCantidad de productos en el pedido aumentada exitosamente.\n");
                        } else{
                            System.out.println("El producto que desas agregar no se encuentra en Mercado Libre.");
                        }
                
                    } else{
                        System.out.println("La orden no se encuentra registrada.");
                    }

                    controller.saveData();

                    break;

                
                case 4:

                    boolean exit2 = false;
                    while(!exit2){
                        System.out.println("\n-------Bienvenido a nuestro buscador.-------");
                        System.out.println("1. Buscar producto. (Necesitas nombre, precio, categoria y numero de veces comprado.)");
                        System.out.println("2. Buscar pedido. (Necesitas nombre del vendedor, precio total, fecha de compra.)");
                        System.out.println("3. Buscar productos intervalo de nombres.");
                        System.out.println("4. Buscar productos por rango de precio.");
                        System.out.println("5. Buscar productos por categoria.");
                        System.out.println("6. Buscar productos por rango de numero de veces comprado.");
                        System.out.println("7. Buscar pedido por intervalo de nombre de comprador.");
                        System.out.println("8. Buscar pedido por rango de precio total.");
                        System.out.println("9. Buscar pedido por fecha de compra.");
                        System.out.println("0. Salir del buscador.");

                        System.out.print("\nSelecciona una opcion: ");
                        int option2 = reader.nextInt();

                        switch(option2){
                            case 1:
                                System.out.print("\nNombre del producto: ");
                                name = reader.nextLine();
        
                                System.out.print("Precio del producto: ");
                                price = reader.nextDouble();
        
                                reader.nextLine();
        
                                System.out.print("Numero de veces comprado: ");
                                quantityAvailable = reader.nextInt();
        
                                reader.nextLine();
        
                                System.out.print("\n** Categorias **");
        
                                System.out.println("\n1. Libros");
                                System.out.println("2. Electronica.");
                                System.out.println("3. Ropa y accesorios.");
                                System.out.println("4. Alimentos y bebidas.");
                                System.out.println("5. Papeleria.");
                                System.out.println("6. Deportes");
                                System.out.println("7. Productos de belleza y cuidado personal.");
                                System.out.println("8. Juguetes y juegos.");
        
                                System.out.print("Categoria del producto (escriba el numero que corresponde): ");
                                category = reader.nextInt();
                                cat = getCategory(category);

                                controller.searchProducts(name, price, cat, quantityAvailable);
                                break;

                            case 2:
                                System.out.print("\nNombre del comprador: ");
                                buyerName = reader.nextLine();

                                System.out.print("Precio total del pedido: ");
                                double totalPrice = reader.nextDouble();

                                System.out.print("Ingrese la fecha de compra en el formato (dd/mm/yyyy): ");
                                String dateInput = reader.nextLine();

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = null;
                                try {

                                    date = dateFormat.parse(dateInput);
                    
                                } catch (ParseException e) {

                                }

                                controller.searchOrders(buyerName, totalPrice, date);
                                break;

                            case 3:
                                
                                System.out.print("Ingrese el prejifo de inicio: ");
                                String startPrefix = reader.next();

                                System.out.print("Ingrese el prefijo de fin: ");
                                String endPrefix = reader.next();

                                System.out.print("Desea ver el orden de manera (escriba: true (para ascendente) o false (para descendente)): ");
                                boolean ascendingOrder = reader.nextBoolean();

                                products = controller.getProducts();

                                controller.searchByNameInterval(products, startPrefix, endPrefix, ascendingOrder);

                                break;

                            case 4:
                                
                                System.out.print("\nIngrese el precio minimo de busqueda: ");
                                double minPrice = reader.nextDouble();

                                System.out.print("Ingrese el precio maximo de busqueda: ");
                                double maxPrice = reader.nextDouble();

                                System.out.print("Desea ver el orden de manera (escriba: true (para ascendente) o false (para descendente)): ");
                                ascendingOrder = reader.nextBoolean();

                                products = controller.getProducts();

                                controller.searchByPriceRange(products, minPrice, maxPrice, ascendingOrder);

                                break;

                            case 5:
                                System.out.print("\n** Categorias **");
        
                                System.out.println("\n1. Libros");
                                System.out.println("2. Electronica.");
                                System.out.println("3. Ropa y accesorios.");
                                System.out.println("4. Alimentos y bebidas.");
                                System.out.println("5. Papeleria.");
                                System.out.println("6. Deportes");
                                System.out.println("7. Productos de belleza y cuidado personal.");
                                System.out.println("8. Juguetes y juegos.");
    
                                System.out.print("Categoria del producto (escriba el numero que corresponde): ");
                                category = reader.nextInt();
                                cat = getCategory(category);

                                System.out.print("Desea ver el orden de manera (escriba: true (para ascendente) o false (para descendente)): ");
                                ascendingOrder = reader.nextBoolean();

                                controller.searchProductsByCategory(cat, ascendingOrder);

                                break;
                            case 6:

                                System.out.print("\nIngrese el precio minimo de busqueda: ");
                                int minTime = reader.nextInt();

                                System.out.print("Ingrese el precio maximo de busqueda: ");
                                int maxTime = reader.nextInt();

                                System.out.print("Desea ver el orden de manera (escriba: true (para ascendente) o false (para descendente)): ");
                                ascendingOrder = reader.nextBoolean();

                                products = controller.getProducts();

                                controller.searchByTimesPurchasedRange(products, minTime, maxTime, ascendingOrder);
                                
                                
                                break;
                            case 7:

                                System.out.print("Ingrese el prejifo de inicio: ");
                                startPrefix = reader.next();

                                System.out.print("Ingrese el prefijo de fin: ");
                                endPrefix = reader.next();

                                System.out.print("Desea ver el orden de manera (escriba: true (para ascendente) o false (para descendente)): ");
                                ascendingOrder = reader.nextBoolean();

                                orders = controller.getOrders();

                                controller.searchByBuyerNameInterval(orders, startPrefix, endPrefix, ascendingOrder);
                                
                                
                                break;
                            case 8:
                                System.out.print("\nIngrese el precio minimo de busqueda: ");
                                minPrice = reader.nextDouble();

                                System.out.print("Ingrese el precio maximo de busqueda: ");
                                maxPrice = reader.nextDouble();

                                System.out.print("Desea ver el orden de manera (escriba: true (para ascendente) o false (para descendente)): ");
                                ascendingOrder = reader.nextBoolean();

                                orders = controller.getOrders();

                                controller.searchByTotalPriceRange(orders, minPrice, maxPrice, ascendingOrder);

                                break;
                            case 9:

                                System.out.print("Ingrese la fecha de compra en el formato (dd/mm/yyyy): ");
                                dateInput = reader.nextLine();

                                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                date = null;
                                try {

                                    date = dateFormat.parse(dateInput);
                    
                                } catch (ParseException e) {

                                }

                                controller.searchOrdersByDate(date);

                                break;
                            case 0:
                                exit2 = true;
                                break;
                        }


                    }
                
                case 5:

                    System.out.println("\nGracias por usar nuestra plataforma, !Hasta luego¡ ");
                    exit = true;
                    
                    break;
            }
        }

        controller.saveData();
    }

    private static Product findProductByName(List<Product> products, String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    private static Order findOrderByName(List<Order> orders, String buyerName) {
        for (Order order : orders) {
            if (order.getBuyerName().equalsIgnoreCase(buyerName)) {
                return order;
            }
        }
        return null;
    }

    public static Category getCategory(int category){
        switch(category){
            case 1:
                return Category.BOOKS;
            case 2:
                return Category.ELECTRONICS;
            case 3:
                return Category.CLOTHING_AND_ACCESSORIES;
            case 4:
                return Category.FOOD_AND_DRINKS;
            case 5:
                return Category.STATIONERY;
            case 6:
                return Category.SPORTS;
            case 7:
                return Category.BEAUTY_AND_PERSONAL_CARE;
            case 8:
                return Category.TOYS_AND_GAMES;
            default:
                return null;
        }
    }

}