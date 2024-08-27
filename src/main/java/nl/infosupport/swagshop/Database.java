package nl.infosupport.swagshop;

import nl.infosupport.swagshop.model.Customer;
import nl.infosupport.swagshop.model.Order;
import nl.infosupport.swagshop.model.OrderLine;
import nl.infosupport.swagshop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class Database {
    private final List<Order> orders = new ArrayList<>();
    private final List<Customer> customer = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final AtomicInteger orderId = new AtomicInteger(1);
    private final AtomicInteger customerId = new AtomicInteger(1);
    private final AtomicInteger productId = new AtomicInteger(1);

    static Database init() {
        Database database = new Database();

        database.addProduct("Hoodie", 45);
        database.addProduct("Poster", 29.99);
        database.addProduct("Sleutelhanger", 10);
        database.addProduct("T-shirt", 25);
        database.addProduct("USB-stick", 10);

        return database;
    }

    Customer createNewCustomer(String name) {
        Customer customer = new Customer(customerId.getAndIncrement(), name);
        this.customer.add(customer);
        return customer;
    }

    Customer getCustomerByName(String name) {
        return customer.stream()
                .filter(customer -> customer.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow();
    }

    Customer getCustomerById(int id) {
        return customer.stream()
                .filter(customer -> customer.id() == id)
                .findFirst()
                .orElseThrow();
    }

    Product getProductByName(String name) {
        return products.stream()
                .filter(product -> product.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow();
    }

    Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.id() == id)
                .findFirst()
                .orElseThrow();
    }


    int createNewOrder(Customer customer, OrderLine orderLine) {
        int id = orderId.getAndIncrement();
        orders.add(new Order(id, customer, List.of(orderLine)));
        return id;
    }

    boolean updateOrder(int orderId, OrderLine orderLine) {
        Order order = orders.stream()
                .filter(o -> o.id() == orderId)
                .findFirst()
                .orElseThrow();

        orders.remove(order);
        Order newOrder = order.addOrderLine(orderLine);
        return orders.add(newOrder);
    }

    Order getOrderById(int orderId) {
        return orders.stream()
                .filter(order -> order.id() == orderId)
                .findFirst()
                .orElseThrow();
    }

    private int addProduct(String productName, double price) {
        int id = productId.getAndIncrement();
        products.add(new Product(id, productName, price));
        return id;
    }

    List<Customer> getKlants() {
        return List.copyOf(customer);
    }

    List<Order> getOrders() {
        return List.copyOf(orders);
    }

    List<Product> getAllProducts() {
        return List.copyOf(products);
    }
}
