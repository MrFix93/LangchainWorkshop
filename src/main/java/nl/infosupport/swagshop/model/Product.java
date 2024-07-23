package nl.infosupport.swagshop.model;

import java.util.List;

//public enum Product {
//    CAR(500),
//    BIKE(10),
//    BASKET(5.50),
//    BOAT(1000),
//    HELM(17.50),
//    SWORD(19.99);
//
//    final double price;
//
//    Product(double price) {
//        this.price = price;
//    }
//}

public record Product(int id, String name, double price) {
}
