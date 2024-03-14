package org.example.entities;

import java.util.List;

public class Order {
    private final Costumer customer;
    private final List<Product> products;

    public Order(Costumer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public Costumer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Double getTotal() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", products=" + products +
                '}';
    }
}
