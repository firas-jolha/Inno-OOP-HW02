package main.shop;

import main.inventories.ExpirableInventory;
import main.inventories.Inventory;
import main.utils.ProductCategory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * The stock of inventories, the category of stock is specified by category property
 */
public class Stock {
    /**
     * Category of stock
     */
    private final ProductCategory category;
    /**
     * List of pair of inventories and amount of each inventory.
     */
    protected final Map<Inventory, Integer> inventories = new HashMap<>();

    /**
     * Creates an instance of stock according to the specified category
     *
     * @param category the specified category
     */
    public Stock(ProductCategory category) {
        this.category = category;
    }

    /**
     * Getter method for category of stock
     *
     * @return the category of stock
     */
    public ProductCategory getCategory() {
        return category;
    }

    /**
     * Getter for list of pairs of inventory and amount in the stock
     *
     * @return map (list of pairs) of inventory and amount in the stock
     */
    public Map<Inventory, Integer> getInventories() {
        return inventories;
    }

    /**
     * Checks the availability of the amount of inventory in stock
     *
     * @param inventory the requested inventory
     * @param amount    the requested amount
     * @return status of inventory availability
     */
    public Map.Entry<Inventory, Integer> isAvailable(Inventory inventory, Integer amount) {
        //Contracts
        Assertive.require(inventory, IsNull.notNullValue());// inventory is not null
        Assertive.require(amount > 0);// amount > 0

        // iterate through all inventories in stock
        for (Map.Entry<Inventory, Integer> current : inventories.entrySet()) {
            if (current.getKey().getName().equals(inventory.getName()) && // we found the inventory but
                    current.getValue() >= amount) // we also check the available amount in stock
                return current; // executed when we found the inventory and exists available amount of it in stock
        }
        return null;// not found or less items found
    }

    /**
     * Checks if Expirable Inventory expired or not
     *
     * @param inventory the Expirable Inventory
     * @return status of availability of inventory
     */
    public boolean isInventoryExpired(ExpirableInventory inventory) {
        //Contract
        Assertive.require(inventory, IsNull.notNullValue()); // inventory is not null

        // iterate through all inventories in stock
        for (Map.Entry<Inventory, Integer> current : inventories.entrySet()) {
            if (current.getKey().getName().equals(inventory.getName())) { // we found inventory
                return LocalDate.now().isAfter(inventory.getExpirationDate()); // check if it's expired or not
            }
        }
        return true;// item not found or expired
    }


    /**
     * Adds the specified amount of inventory to the stock
     *
     * @param inventory the inventory
     * @param amount    the amount of inventory
     */
    public void addInventory(Inventory inventory, Integer amount) {
        // we don't need contracts here, because parameters will be checked in isAvailable method
        Map.Entry<Inventory, Integer> inv = isAvailable(inventory, amount);// checks availability of inventory
        if (inv != null) {// the inventory is existed so just increase the amount
            inv.setValue(inv.getValue() + amount);
        } else {// the inventory is not existed so add new inventory
            inventories.put(inventory, amount);
        }
    }

    /**
     * Removes the specified amount of inventory from the stock or remove
     * the inventory if the requested amount >= available inventory amount.
     *
     * @param inventory the inventory
     * @param amount    the amount of inventory
     */
    public void removeInventory(Inventory inventory, Integer amount) {
        // we don't need contracts here, because parameters will be checked in isAvailable method
        Map.Entry<Inventory, Integer> inv = isAvailable(inventory, amount);// checks availability of inventory
        if (inv != null && amount < inv.getValue()) {// the inventory is existed and requested amount not greater than amount in stock, so just decrease the amount
            inv.setValue(inv.getValue() - amount);
        } else {// the inventory is not existed or no available amount found, so remove the inventory
            inventories.remove(inventory);
        }
    }

    /**
     * A string representation pf Stock class
     *
     * @return string representation of Stock class
     */
    @Override
    public String toString() {
        return "\n  Stock{" +
                " category=" + category +
                ",\n      inventories=" + inventories +
                '}';
    }
}
