package main.shop;

import main.clients.Consumer;
import main.clients.Customer;
import main.inventories.Inventory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import static org.valid4j.Assertive.*;
import static org.hamcrest.core.IsNull.*;
import java.time.LocalDate;

public class Order {
    private LocalDate date;
    private Inventory inventory;
    private Consumer consumer;
    private Customer customer;
    private Double price;
    private Integer amount;
//    private final Shop shop;

    public Order(Inventory inventory, Integer amount, Consumer consumer, Customer customer) {
//        require(shop, notNullValue());
//        require(date, notNullValue());
        require(consumer, notNullValue());
        require(customer, notNullValue());
//        require(shop.isAvailable(inventory, amount));
//        this.shop = shop;
        this.date = LocalDate.now();
        this.inventory = inventory;
        this.consumer = consumer;
        this.customer = customer;
        this.amount = amount;
        this.price = inventory.getPrice()*amount;
    }

    public void setPrice(Double price) {
        Assertive.require(price>0.0);
        this.price = price;
    }

    public void setAmount(Integer amount) {
        Assertive.require(amount>0);
        this.amount = amount;
    }

    public void setConsumer(Consumer consumer) {
        require(consumer, IsNull.notNullValue());
        this.consumer = consumer;
    }

    public void setCustomer(Customer customer) {
        require(customer, IsNull.notNullValue());
        this.customer = customer;
    }

    public void setDate(LocalDate date) {
        require(date, IsNull.notNullValue());
        this.date = date;
    }

    public void setInventory(Inventory inventory) {
        require(inventory, IsNull.notNullValue());
        this.inventory = inventory;
    }

    public Double getPrice() {
        return price;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Integer getAmount() {
        return amount;
    }

    public Inventory getInventory() {
        return inventory;
    }
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "date=" + date +
                ", inventory=" + inventory +
                ", consumer=" + consumer +
                ", customer=" + customer +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
