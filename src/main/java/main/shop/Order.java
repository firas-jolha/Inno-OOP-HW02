package main.shop;

import main.clients.Consumer;
import main.clients.Customer;
import main.stocks.Inventory;
import static org.valid4j.Assertive.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsNull.*;
import java.time.LocalDate;

public class Order {
    private LocalDate date;
    private Inventory inventory;
    private Consumer consumer;
    private Customer customer;
    private Double price;
    private Integer amount;

    private final Shop shop;

    public Order(Shop shop, LocalDate date, Inventory inventory, Consumer consumer, Customer customer, Double price, Integer amount) {
        require(shop, notNullValue());
        require(date, notNullValue());
        require(consumer, notNullValue());
        require(customer, notNullValue());
        require(shop.isAvailable(inventory, amount));
        this.shop = shop;
        this.date = date;
        this.inventory = inventory;
        this.consumer = consumer;
        this.customer = customer;
        this.price = price;
        this.amount = amount;
    }

    public Order(Order order){
        this(order.shop, order.date, order.inventory, order.consumer, order.customer, order.price, order.amount);
    }
}
