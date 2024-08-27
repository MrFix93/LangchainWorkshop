package nl.infosupport.swagshop;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import nl.infosupport.swagshop.model.Customer;
import nl.infosupport.swagshop.model.Order;
import nl.infosupport.swagshop.model.OrderLine;
import nl.infosupport.swagshop.model.Product;

import java.util.List;

class KlantTools {

    private static final Database database = Database.init();

    @Tool("Creates and returns a new customer")
    Customer createCustomer(@P("Customer name") String name) {
        return database.createNewCustomer(name);
    }

    @Tool("Searches and returns a customer")
    Customer getCustomerByName(@P("Customer name") String name) {
        return database.getCustomerByName(name);
    }

    @Tool("Searches for product by name")
    Product getProductByName(@P("Product name") String name) {
        return database.getProductByName(name);
    }

    @Tool("Create a new order for Customer")
    int createOrder(@P("Customer with ID and Name") Customer customer,
                    @P("Requested product") Product product,
                    int quantity) {
        return database.createNewOrder(customer, new OrderLine(quantity, product));
    }

    @Tool("Add a new orderline to an existing order")
    boolean updateOrder(@P("The existing order ID") int orderId,
                        @P("Requested product") Product product,
                        @P("The amount") int quantity) {
        return database.updateOrder(orderId, new OrderLine(quantity, product));
    }

    @Tool("Get details of an order")
    Order getOrderById(@P("Order ID")int id) {
        return database.getOrderById(id);
    }


    @Tool("Returns all customers in the database")
    List<Customer> getCustomers() {
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
