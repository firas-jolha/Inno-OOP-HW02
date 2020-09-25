package main.shop;

import main.clients.Consumer;
import main.clients.Customer;
import main.inventories.ExpirableInventory;
import main.inventories.Inventory;
import main.utils.Location;
import main.utils.ProductCategory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Represents the system of shopping
 */
public class Shop {

    /**
     * name of shop
     */
    private String name;

    /**
     * location including address and map coordinates of the shop
     */
    private Location location;

    /**
     * list of shop customers
     */
    private final ArrayList<Customer> customers = new ArrayList<>();

    /**
     * list of shop consumers
     */
    private final ArrayList<Consumer> consumers = new ArrayList<>();

    /**
     * list of shop orders
     */
    private final ArrayList<Order> orders = new ArrayList<>();

    /**
     * list of shop stocks
     */
    private final ArrayList<Stock> stocks = new ArrayList<>();

    // Initialization block for creating stock for each product category
    {
        for (ProductCategory category : ProductCategory.values()) {
            stocks.add(new Stock(category));// create stock according to category and add it to list of stocks
        }
    }

    /**
     * Creates an instance of shop
     *
     * @param name     name of shop
     * @param location location of shop
     */
    public Shop(String name, Location location) {
        setName(name);
        setLocation(location);
    }

    /**
     * Getter for name attribute
     *
     * @return name of shop
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name attribute
     *
     * @param name name of shop
     */
    public void setName(String name) {
        //Contracts
        Assertive.require(name, IsNull.notNullValue()); // name is not null
        Assertive.require(!name.equals("")); // name is not empty
        this.name = name;
    }

    /**
     * Getter for location attribute
     *
     * @return the location of shop
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Setter for location attribute
     *
     * @param location the location of shop
     */
    public void setLocation(Location location) {
        //Contracts
        Assertive.require(location, IsNull.notNullValue()); // location is not null
        this.location = location;
    }

    /**
     * Get a consumer by index
     * @return The consumer of requested index
     */
    public Consumer getConsumer(int index) {
        return consumers.get(index);
    }

    /**
     * Get a Customer by index
     * @return The customer of requested index
     */
    public Customer getCustomer(int index) {
        return customers.get(index);
    }


    /**
     * Get an order by index
     * @return The order of requested index
     */
    public Order getOrder(int index) {
        return orders.get(index);
    }

    /**
     * Returns the num of consumers
     * @return int
     */
    public int getConsumersSize(){
        return consumers.size();
    }

    /**
     * Returns the num of customers
     * @return int
     */
    public int getCustomersSize(){
        return customers.size();
    }

    /**
     * Returns the num of orders
     * @return int
     */
    public int getOrdersSize(){
        return orders.size();
    }



    /**
     * Get a stock by index
     * @return The stock of requested index
     */
    public Stock getStock(int index) {
        return stocks.get(index);
    }

    /**
     * Adds a customer to the list of customers in the shop
     *
     * @param customer the new customer
     */
    public void addCustomer(Customer customer) {
        //Contracts
        Assertive.require(customer, IsNull.notNullValue()); // customer is not null
        customers.add(customer);
    }

    /**
     * Adds a consumer to the list of consumers in the shop
     *
     * @param consumer the new consumer
     */
    public void addConsumer(Consumer consumer) {
        //Contracts
        Assertive.require(consumer, IsNull.notNullValue()); // consumer is not null
        consumers.add(consumer);
    }

    /**
     * Adds an inventory to the list of inventories in the shop
     *
     * @param inventory the new inventory
     * @param amount    the amount of inventory
     */
    public void addInventory(Inventory inventory, Integer amount) {
        //Contracts
        Assertive.require(inventory, IsNull.notNullValue()); // inventory is not null
        Assertive.require(amount > 0); // amount > 0

        ProductCategory category = inventory.getCategory();
        IntStream.range(0, stocks.size()). // iterate through stocks
                filter(i -> stocks.get(i).getCategory() == category). // find out to which stock category belongs the inventory
                findFirst(). // the first found stock where the inventory can be stored
                ifPresent(i -> stocks.get(i).addInventory(inventory, amount)); // add the inventory to the stock
    }

    /**
     * Removes an inventory from the list of inventories in the shop
     *
     * @param inventory the new inventory
     * @param amount    the amount of inventory
     */
    public void removeInventory(Inventory inventory, Integer amount) {
        //Contracts
        Assertive.require(inventory, IsNull.notNullValue()); // inventory is not null
        Assertive.require(amount > 0); // amount > 0

        ProductCategory category = inventory.getCategory();
        IntStream.range(0, stocks.size())// iterate through stocks
                .filter(i -> stocks.get(i).getCategory() == category) // find out to which stock category belongs the inventory
                .findFirst() // the first found stock where the inventory can be stored
                .ifPresent(i -> stocks.get(i).removeInventory(inventory, amount));  // remove the specified amount of inventory from the stock
    }

    /**
     * Checks whether the inventory with the specified amount is available in stock
     *
     * @param inventory the inventory to check
     * @param amount    the specified amount of inventory
     * @return status of availability of inventory in stock
     */
    public boolean isAvailable(Inventory inventory, Integer amount) {
        //Contracts
        Assertive.require(inventory, IsNull.notNullValue()); // inventory is not null
        Assertive.require(amount > 0); // amount > 0

        return stocks.stream()
                .anyMatch(stock -> (stock.isAvailable(inventory, amount)) != null);
        // returns true if the inventory
        // is found in the shop (stocks of shop)
        // otherwise, returns false
    }

    /**
     * Checks whether expirable inventory is expired or not
     *
     * @param inventory the inventory under consideration
     * @return status of expiration of inventory
     */
    public boolean isInventoryExpired(ExpirableInventory inventory) {
        //Contracts
        Assertive.require(inventory, IsNull.notNullValue()); // inventory is not null

        return stocks.stream().
                filter(stock -> (stock.isAvailable(inventory, 1)) != null) // Checks availability of inventory at least 1 item of it
                .findFirst() // first found available inventory
                .map(stock -> stock.isInventoryExpired(inventory)) // check its expiration
                .orElse(true); // if the inventory isn't found so expired
    }

    /**
     * Creates order for a consumer
     *
     * @param inventory the requested inventory
     * @param amount    the requested amount
     * @param consumer  the consumer of order
     * @param customer  the customer of order
     */
    public void createOrder(Inventory inventory, Integer amount, Consumer consumer, Customer customer) {
        //Contracts
        Assertive.require(consumer, IsNull.notNullValue());
        Assertive.require(customer, IsNull.notNullValue());

        if (consumers.contains(consumer)) { // check if consumer is in the list of consumers
            if (isAvailable(inventory, amount)) { // check if inventory is available
                if (inventory instanceof ExpirableInventory &&
                        isInventoryExpired((ExpirableInventory) inventory)) {
                    // If the inventory is expirable like food and pharmacy
                    // and expired before the order is made, so refuse the order
                    System.out.println("The inventory is expired");
                    return;
                }
                // if the inventory is available and not expired
                Order order = consumer.makeOrder(inventory, customer, amount); // make an order
                if (order != null) { // creating order is successful
                    int size = orders.size();
                    orders.add(order); // add the order to list of orders
                    removeInventory(inventory, amount); // remove the requested inventory with amount from stock of shop
                    Assertive.ensure(orders.size() == size + 1); // Contract for ensuring that order is added to the list of orders
                } else { // creating order is failed
                    System.out.println(
                            "Cannot make order of " +
                                    inventory +
                                    " for the consumer " +
                                    consumer);
                }
            } else {// inventory not available or less amount exists than requested
                System.out.println("We don't have available inventories!");
            }
        } else {// Consumer is not in the system
            System.out.println("Consumer is not existed!");
        }
    }

    /**
     * Represents the shop as a string
     *
     * @return string of info about the shop
     */
    @Override
    public String toString() {
        return "Shop{" +
                "\nname='" + name + '\'' +
                ", \nlocation=" + location +
                ", \ncustomers=" + customers +
                ", \nconsumers=" + consumers +
                ", \norders=" + orders +
                ", \nstocks=" + stocks +
                "\n}";
    }
}
