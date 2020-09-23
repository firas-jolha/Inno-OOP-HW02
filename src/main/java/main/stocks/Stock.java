package main.stocks;

import main.inventories.ExpirableInventory;
import main.utils.ProductCategory;
import main.inventories.Inventory;
import org.hamcrest.core.Is;
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
     * @param category the specified category
     */
    public Stock(ProductCategory category) {
        this.category = category;
    }

    /**
     * Getter method for category of stock
     * @return the category of stock
     */
    public ProductCategory getCategory() {
        return category;
    }

    public Map<Inventory, Integer> getInventories() {
        return inventories;
    }

    /**
     * Checks the availability of the amount of inventory in stock
     * @param inventory the requested inventory
     * @param amount the requested amount
     * @return status of inventory availability
     */
    public Map.Entry<Inventory, Integer> isAvailable(Inventory inventory, Integer amount){
        Assertive.require(inventory, IsNull.notNullValue());
        Assertive.require(amount > 0);

        for(Map.Entry<Inventory, Integer> current:inventories.entrySet()){
            if (current.getKey().getName().equals(inventory.getName()) &&
                    current.getValue() >= amount)
                return current;
        }
        return null;
    }

    public boolean isInventoryExpired(Inventory inventory){
        Assertive.require(inventory, IsNull.notNullValue());

        for(Map.Entry<Inventory, Integer> current:inventories.entrySet()) {
            if (current.getKey().getName().equals(inventory.getName())) {
                try {
                    Assertive.require(inventory, Is.isA(ExpirableInventory.class));
                    ExpirableInventory inventory1 = (ExpirableInventory) inventory;
                    return  LocalDate.now().isAfter(inventory1.getExpirationDate());
                }catch (Exception ex){
                    System.out.println("Casting error");
                }
            }
        }
        return true;
    }


    /**
     * Adds the specified amount of inventory to the stock
     * @param inventory the inventory
     * @param amount the amount of inventory
     */
    public void addInventory(Inventory inventory, Integer amount){
        Map.Entry<Inventory, Integer> inv = isAvailable(inventory, amount);
        if(inv != null){// the inventory is existed so just increase the amount
            inv.setValue(inv.getValue()+amount);
        }else {// the inventory is not existed so add new inventory
            inventories.put(inventory, amount);
        }
    }

    // TODO: handle removing procedure
    public void removeInventory(Inventory inventory, Integer amount){
        Map.Entry<Inventory, Integer> inv = isAvailable(inventory, amount);
        if(inv != null){// the inventory is existed so just decrease the amount
            inv.setValue(inv.getValue()-amount);
        }else {// the inventory is not existed so just decrease the amount
            inventories.remove(inventory, amount);
        }
    }

    @Override
    public String toString() {
        return "Stock{" +
                "category=" + category +
                ", inventories=" + inventories +
                '}';
    }
}
