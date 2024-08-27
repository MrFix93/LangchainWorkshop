package nl.infosupport.swagshop.model;

import java.util.ArrayList;
import java.util.List;

public record Order(int id, Customer customer, List<OrderLine> orderLines) {

    public Order addOrderLine(OrderLine orderLine) {
        ArrayList<OrderLine> lines = new ArrayList<>(this.orderLines);
        lines.add(orderLine);
        return new Order(this.id, this.customer, List.copyOf(lines));
    }
}
