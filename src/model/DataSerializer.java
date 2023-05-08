package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class DataSerializer {
    private static final String FILENAME = "data.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void saveData(List<Product> products, List<Order> orders) {
        Map<String, Object> data = new HashMap<>();
        data.put("products", products);
        data.put("orders", orders);

        try (Writer writer = new FileWriter(FILENAME)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> loadData() {
        try (Reader reader = new FileReader(FILENAME)) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> data = GSON.fromJson(reader, type);

            // Deserializar explícitamente los objetos JSON en objetos de tipo Product
            List<Product> products = new ArrayList<>();
            for (Object product : (List<Object>) data.get("products")) {
                products.add(GSON.fromJson(GSON.toJson(product), Product.class));
            }
            data.put("products", products);

            // Deserializar explícitamente los objetos JSON en objetos de tipo Order
            List<Order> orders = new ArrayList<>();
            for (Object order : (List<Object>) data.get("orders")) {
                orders.add(GSON.fromJson(GSON.toJson(order), Order.class));
            }
            data.put("orders", orders);

            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}