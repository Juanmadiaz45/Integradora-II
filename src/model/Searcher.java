package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Searcher {
    private List<Product> products;
    private List<Order> orders;

    public Searcher(List<Product> products, List<Order> orders) {
        this.products = products;
        this.orders = orders;
    }

    public List<Product> searchProducts(String name, double price, Category category, int timesPurchased) {
        List<Product> results = new ArrayList<>();

        for (Product product : products) {
            if ((name == null || product.getName().toLowerCase().contains(name.toLowerCase())) && (price == 0.0 || product.getPrice() >= price) && (category == null || product.getCategory().equals(category)) && (timesPurchased == 0 || product.getTimesPurchased() >= timesPurchased)) {
                results.add(product);
            }
        }

        return results;
    }

    public List<Order> searchOrders(String buyerName, double totalPrice, Date date) {
        List<Order> results = new ArrayList<>();

        for (Order order : orders) {
            if ((buyerName == null || order.getBuyerName().toLowerCase().contains(buyerName.toLowerCase())) && (totalPrice == 0.0 || order.getTotalPrice() >= totalPrice) && (date == null || order.getDate().equals(date))) {
                results.add(order);
            }
        }

        return results;
    }

    public List<Product> searchByNameInterval(List<Product> products, String startPrefix, String endPrefix, boolean ascendingOrder) {
        List<Product> results = new ArrayList<>();
    
        sortProducts(1, ascendingOrder);
    
        int firstIndex = findFirstIndexByName(products, startPrefix);
        int lastIndex = findLastIndexByName(products, endPrefix);
    
        if (firstIndex >= 0 && lastIndex >= 0) {
            for (int i = firstIndex; i <= lastIndex; i++) {
                results.add(products.get(i));
            }
        }
    
        return results;
    }
    
    private int findFirstIndexByName(List<Product> products, String startPrefix) {
        int left = 0;
        int right = products.size() - 1;
        int result = -1;
    
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String productName = products.get(mid).getName();
    
            if (productName.compareToIgnoreCase(startPrefix) >= 0) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    
        return result;
    }
    
    private int findLastIndexByName(List<Product> products, String endPrefix) {
        int left = 0;
        int right = products.size() - 1;
        int result = -1;
    
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String productName = products.get(mid).getName();
    
            if (productName.compareToIgnoreCase(endPrefix) <= 0) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    
        return result;
    }

    public List<Product> searchByPriceRange(List<Product> products, double minPrice, double maxPrice, boolean ascendingOrder) {
        List<Product> results = new ArrayList<>();
    
        sortProducts(2, ascendingOrder);
    
        int firstIndex = findFirstIndex(products, minPrice);
        int lastIndex = findLastIndex(products, maxPrice);
    
        if (firstIndex >= 0 && lastIndex >= 0) {
            for (int i = firstIndex; i <= lastIndex; i++) {
                results.add(products.get(i));
            }
        }
    
        return results;
    }
    
    private int findFirstIndex(List<Product> products, double minPrice) {
        int left = 0;
        int right = products.size() - 1;
        int result = -1;
    
        while (left <= right) {
            int mid = left + (right - left) / 2;
            double price = products.get(mid).getPrice();
    
            if (price >= minPrice) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    
        return result;
    }
    
    private int findLastIndex(List<Product> products, double maxPrice) {
        int left = 0;
        int right = products.size() - 1;
        int result = -1;
    
        while (left <= right) {
            int mid = left + (right - left) / 2;
            double price = products.get(mid).getPrice();
    
            if (price <= maxPrice) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    
        return result;
    }

    public List<Product> searchByTimesPurchasedRange(List<Product> products, int minTimesPurchased, int maxTimesPurchased, boolean ascendingOrder) {
        List<Product> results = new ArrayList<>();
    
        sortProducts(4, ascendingOrder);
    
        int firstIndex = findFirstIndexByTimesPurchased(products, minTimesPurchased);
        int lastIndex = findLastIndexByTimesPurchased(products, maxTimesPurchased);
    
        if (firstIndex >= 0 && lastIndex >= 0) {
            for (int i = firstIndex; i <= lastIndex; i++) {
                results.add(products.get(i));
            }
        }
    
        return results;
    }
    
    private int findFirstIndexByTimesPurchased(List<Product> products, int minTimesPurchased) {
        int left = 0;
        int right = products.size() - 1;
        int result = -1;
    
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int timesPurchased = products.get(mid).getTimesPurchased();
    
            if (timesPurchased >= minTimesPurchased) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    
        return result;
    }
    
    private int findLastIndexByTimesPurchased(List<Product> products, int maxTimesPurchased) {
        int left = 0;
        int right = products.size() - 1;
        int result = -1;
    
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int timesPurchased = products.get(mid).getTimesPurchased();
    
            if (timesPurchased <= maxTimesPurchased) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    
        return result;
    }

    public List<Order> searchByBuyerNameInterval(List<Order> orders, String startPrefix, String endPrefix, boolean ascendingOrder) {
        List<Order> results = new ArrayList<>();
    
        sortOrders(1, ascendingOrder);
    
        int firstIndex = findFirstIndexByBuyerName(orders, startPrefix);
        int lastIndex = findLastIndexByBuyerName(orders, endPrefix);
    
        if (firstIndex >= 0 && lastIndex >= 0) {
            for (int i = firstIndex; i <= lastIndex; i++) {
                results.add(orders.get(i));
            }
        }
    
        return results;
    }
    
    private int findFirstIndexByBuyerName(List<Order> orders, String startPrefix) {
        int left = 0;
        int right = orders.size() - 1;
        int result = -1;
    
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String buyerName = orders.get(mid).getBuyerName();
    
            if (buyerName.compareToIgnoreCase(startPrefix) >= 0) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    
        return result;
    }
    
    private int findLastIndexByBuyerName(List<Order> orders, String endPrefix) {
        int left = 0;
        int right = orders.size() - 1;
        int result = -1;
    
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String buyerName = orders.get(mid).getBuyerName();
    
            if (buyerName.compareToIgnoreCase(endPrefix) <= 0) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    
        return result;
    }

    public List<Product> searchProductsByCategory(Category category, boolean ascendingOrder) {
        List<Product> results = new ArrayList<>();

        sortProducts(3, ascendingOrder);
    
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                results.add(product);
            }
        }
    
        return results;
    }

    public void sortProducts(int variableToSort, boolean ascendingOrder) {
        Comparator<Product> comparator = null;
        switch (variableToSort) {
            case 1:
                comparator = Comparator.comparing(Product::getName);
                break;
            case 2:
                comparator = Comparator.comparing(Product::getPrice);
                break;
            case 3:
                comparator = Comparator.comparing(Product::getCategory);
                break;
            case 4:
                comparator = Comparator.comparing(Product::getTimesPurchased);
                break;
            default:
                throw new IllegalArgumentException("Variable no válida.");
        }
        if (!ascendingOrder) {
            comparator = comparator.reversed();
        }
        products.sort(comparator);
    }
    
    public void sortOrders(int variableToSort, boolean ascendingOrder) {
        Comparator<Order> comparator = null;
        switch (variableToSort) {
            case 1:
                comparator = Comparator.comparing(Order::getBuyerName);
                break;
            case 2:
                comparator = Comparator.comparing(Order::getTotalPrice);
                break;
            default:
                throw new IllegalArgumentException("Variable no válida.");
        }
        if (!ascendingOrder) {
            comparator = comparator.reversed();
        }
        orders.sort(comparator);
    }
    
}