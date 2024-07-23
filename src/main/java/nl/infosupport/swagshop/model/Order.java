package nl.infosupport.swagshop.model;

import java.util.List;

public record Order(int id, Klant klant, List<OrderLine> orderLines) {
}
