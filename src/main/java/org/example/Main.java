package org.example;

import com.github.javafaker.Faker;
import org.example.entities.Costumer;
import org.example.entities.Order;
import org.example.entities.Product;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        // Sample data
        List<Product> products = Arrays.asList(
                new Product(faker.beer().name(), faker.file().extension(), (double) faker.number().numberBetween(5, 400)),
                new Product(faker.beer().name(), faker.file().extension() ,(double) faker.number().numberBetween(5, 400)),
                new Product(faker.beer().name(), faker.file().extension(), (double) faker.number().numberBetween(5, 400)),
                new Product(faker.beer().name(), faker.file().extension(),(double) faker.number().numberBetween(5, 400)),
                new Product(faker.beer().name(),faker.file().extension(), (double) faker.number().numberBetween(5, 400))
        );


        List<Costumer> customers = Arrays.asList(
                new Costumer(faker.name().name(), faker.number().numberBetween(1, 4)),
                new Costumer(faker.name().name(), faker.number().numberBetween(1, 4)),
                new Costumer(faker.name().name(), faker.number().numberBetween(1, 4)),
                new Costumer(faker.name().name(), faker.number().numberBetween(1, 4) ),
                new Costumer(faker.name().name(), faker.number().numberBetween(1, 4)),
                new Costumer(faker.name().name(), faker.number().numberBetween(1, 4)),
                new Costumer(faker.name().name(), faker.number().numberBetween(1, 4))
        );

        List<Order> orders = Arrays.asList(
                new Order(customers.get(0), Arrays.asList(products.get(0), products.get(1))),
                new Order(customers.get(1), Arrays.asList(products.get(2), products.get(3))),
                new Order(customers.get(0), Arrays.asList(products.get(4))),
                new Order(customers.get(2), Arrays.asList(products.get(1), products.get(3)))
        );

        // Exercise #1
        Map<Costumer, List<Order>> customerOrders = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));
        System.out.println("Customer orders: " + customerOrders);

        // Exercise #2
        Map<Costumer, Double> customerTotals = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer,
                        Collectors.summingDouble(Order::getTotal)));
        System.out.println("Customer totals: " + customerTotals);

        // Exercise #3
        List<Product> mostExpensiveProducts = products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Most expensive products: " + mostExpensiveProducts);


// Exercise #4
        Double averageOrderTotal = orders.stream()
                .mapToDouble(Order::getTotal)
                .average()
                .orElse(0.0);
        System.out.println("Average order total: " + averageOrderTotal);
    }
}
