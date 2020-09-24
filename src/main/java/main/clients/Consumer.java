package main.clients;

import main.inventories.Inventory;
import main.shop.Order;
import main.utils.Gender;

/**
 * Represents consumer of products, which is a user
 */
public class Consumer extends User {

    /**
     * Creates instance of consumer
     *
     * @param name   name of consumer
     * @param age    age of consumer
     * @param gender gender of consumer
     */
    public Consumer(String name, Integer age, Gender gender) {
        super(name, age, gender);
    }

    /**
     * The consumer can make orders
     *
     * @param inventory the requested inventory
     * @param customer  the customer of the order
     * @param amount    the requested amount of inventory
     * @return the created order
     */
    public Order makeOrder(Inventory inventory, Customer customer, Integer amount) {
        return new Order(inventory, amount, this, customer);
    }

}
