package nl.infosupport.swagshop;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import nl.infosupport.swagshop.model.Klant;
import nl.infosupport.swagshop.model.Order;
import nl.infosupport.swagshop.model.OrderLine;
import nl.infosupport.swagshop.model.Product;

import java.util.List;

class KlantTools {

    private static final Database database = Database.getDatabase()
            .init();

    @Tool("Creates and returns a new customer, requires customer name as input")
    Klant createCustomer(String name) {
        return database.createNewKlant(name);
    }

    @Tool("Get customer ID by name, requires customer name as input")
    int getCustomerId(String customerName) {
        return database.getCustomerByName(customerName).id();
    }

    @Tool("Get customer by ID, requires customer ID as input")
    Klant getCustomerById(int id) {
        return database.getCustomerById(id);
    }

    @Tool("get customer orders by customer name, requires customer name as input")
    List<Order> getCustomerOrders(String customerName) {
        return database.getOrdersByKlant(database.getCustomerByName(customerName));
    }

    @Tool("Create an order for a Klant, requires customer name, product and quantity as input, returns order ID")
    int createOrder(@P(value = "customer with ID and Name", required = true) Klant klant, Product product, int quantity) {
        return database.saveOrder(klant, List.of(new OrderLine(quantity, product)));
    }

    @Tool("Get order by ID, requires order ID as input")
    Order getOrderById(int orderId) {
        return database.getOrderById(orderId);
    }

    @Tool("Returns all customers in the database")
    List<Klant> getCustomers() {
        return database.getKlants();
    }

    @Tool("Returns all orders in the database")
    List<Order> getOrders() {
        return database.getOrders();
    }

    @Tool("Returns all Products in the database")
    List<Product> getProducts() {
        return database.getAllProducts();
    }
}
