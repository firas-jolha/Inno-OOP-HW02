package main.shop;

import main.clients.Consumer;
import main.clients.Customer;
import main.inventories.Inventory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.time.LocalDate;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.valid4j.Assertive.require;

/**
 * Represents the order of inventory made by consumer
 */
public class Order {

    /**
     * Date of order
     */
    private LocalDate date;


    /**
     * Requested inventory
     */
    private Inventory inventory;


    /**
     * Who makes the order
     */
    private Consumer consumer;

    /**
     * Who receives the product
     */
    private Customer customer;

    /**
     * The bill of the order = inventory price * amount
     */
    private Double price;

    /**
     * number of requested items from inventory
     */
    private Integer amount;

    /**
     * Creates orders
     *
     * @param inventory the requested inventory
     * @param amount    the requested amount
     * @param consumer  Who makes the order
     * @param customer  the owner of order
     */
    public Order(Inventory inventory, Integer amount, Consumer consumer, Customer customer) {
        require(consumer, notNullValue());
        require(customer, notNullValue());
        this.inventory = inventory;
        this.consumer = consumer;
        this.customer = customer;
        this.amount = amount;
        this.price = inventory.getPrice() * amount;
        this.date = LocalDate.now();// current date
    }

    /**
     * Setter for amount
     *
     * @param amount the new amount
     */
    public void setAmount(Integer amount) {
        Assertive.require(amount > 0);
        this.amount = amount;
    }

    /**
     * Setter for consumer
     *
     * @param consumer the new consumer
     */
    public void setConsumer(Consumer consumer) {
        require(consumer, IsNull.notNullValue());
        this.consumer = consumer;
    }


    /**
     * Setter for customer
     *
     * @param customer the new customer
     */
    public void setCustomer(Customer customer) {
        require(customer, IsNull.notNullValue());
        this.customer = customer;
    }

    /**
     * Setter for date of order
     *
     * @param date the updated date
     */
    public void setDate(LocalDate date) {
        require(date, IsNull.notNullValue());
        this.date = date;
    }

    /**
     * Setter for inventory
     *
     * @param inventory the new inventory
     */
    public void setInventory(Inventory inventory) {
        require(inventory, IsNull.notNullValue());
        this.inventory = inventory;
    }

    /**
     * Getter for price
     *
     * @return the bill of order
     */
    public Double getPrice() {
        price = amount * inventory.getPrice();
        return price;
    }

    /**
     * Getter for consumer
     *
     * @return the consumer
     */
    public Consumer getConsumer() {
        return consumer;
    }

    /**
     * Getter for customer
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Getter for amount
     *
     * @return the amount of requested inventories
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Getter for inventory
     *
     * @return the requested inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Getter for date of order
     *
     * @return date of order
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Represents the order as string
     *
     * @return string representation of the order
     */
    @Override
    public String toString() {
        return "\n    Order{" +
                "\n        date=" + date +
                ",\n        inventory=" + inventory +
                ",\n        consumer=" + consumer +
                ",\n        customer=" + customer +
                ",\n        price=" + price +
                ",\n        amount=" + amount +
                "}\n";
    }
}
