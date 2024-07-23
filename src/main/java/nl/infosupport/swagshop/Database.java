package nl.infosupport.swagshop;

import nl.infosupport.swagshop.model.Klant;
import nl.infosupport.swagshop.model.Order;
import nl.infosupport.swagshop.model.OrderLine;
import nl.infosupport.swagshop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class Database {
    private static List<Order> orders = new ArrayList<>();
    private static List<Klant> klants = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static AtomicInteger productId = new AtomicInteger(0);
    private static AtomicInteger klantId = new AtomicInteger(0);
    private static AtomicInteger orderId = new AtomicInteger(0);

    private static Database database;

    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    Database init() {
        database.addProduct("Bike", 500);
        database.addProduct("Car", 19000);

        return database;
    }

    Klant createNewKlant(String name) {
        Klant klant = new Klant(klantId.getAndIncrement(), name);
        klants.add(klant);
        return klant;
    }

    Klant getCustomerByName(String name) {
        return klants.stream()
                .filter(klant -> klant.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow();
    }

    Klant getCustomerById(int id) {
        return klants.stream()
                .filter(klant -> klant.id() == id)
                .findFirst()
                .orElseThrow();
    }

    Order getOrderById(int orderId) {
        return orders.stream()
                .filter(order -> order.id() == orderId)
                .findFirst()
                .orElseThrow();
    }

    List<Order> getOrdersByKlant(Klant klant) {
        return orders.stream()
                .filter(order -> order.klant().equals(klant))
                .toList();
    }

    List<Product> getAllProducts() {
        return products;
    }

    int addProduct(String productName, double price) {
        int id = productId.getAndIncrement();
        products.add(new Product(id, productName, price));
        return id;
    }

    int saveOrder(Klant klant, List<OrderLine> orderLines) {
        int id = orderId.getAndIncrement();
        orders.add(new Order(id, klant, orderLines));
        return id;
    }

    List<Klant> getKlants() {
        return List.copyOf(klants);
    }

    List<Order> getOrders() {
        return List.copyOf(orders);
    }
}
