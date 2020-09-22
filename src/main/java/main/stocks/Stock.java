package main.stocks;

import main.utils.ProductCategory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Stock {
    private final ProductCategory category;
    protected final Map<Inventory, Integer> inventories = new HashMap<>();

    public Stock(ProductCategory category) {
        this.category = category;
    }

    public ProductCategory getCategory() {
        return category;
    }

    /**
     * Checks the availability of the amount of inventory in stock
     * @param inventory the requested inventory
     * @param amount the requested amount
     * @return status of inventory availability
     */
    public Map.Entry<Inventory, Integer> isAvailable(Inventory inventory, Integer amount){
        Assertive.require(inventory, IsNull.notNullValue());
        for(Map.Entry<Inventory, Integer> current:inventories.entrySet()){
            if (current.getKey().getName().equals(inventory.getName()) &&
                    current.getValue() >= amount)
                return current;
        }
        return null;
    }


    public void addInventory(Inventory inventory, Integer amount){
        Map.Entry<Inventory, Integer> inv = isAvailable(inventory, amount);
        if(inv != null){
            inv.setValue(inv.getValue()+amount);
        }else {
            inventories.put(inventory, amount);
        }
    }

    public void removeInventory(Inventory inventory, Integer amount){
        Map.Entry<Inventory, Integer> inv = isAvailable(inventory, amount);
        if(inv != null){
            inv.setValue(inv.getValue()-amount);
        }else {
            inventories.remove(inventory, amount);
        }
    }

}
