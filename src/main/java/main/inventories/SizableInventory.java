package main.inventories;

import main.utils.ProductCategory;
import main.utils.ProductSize;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.time.LocalDate;

/**
 * Represents all sizable inventories or which have size attribute
 * This class is a inventory and implements ISizableInventory interface
 */
public class SizableInventory extends Inventory implements ISizableInventory {

    /**
     * size of sizable inventory
     */
    private ProductSize size;

    /**
     * Creates an instance of sizable inventory
     *
     * @param name       name of inventory
     * @param createDate created date of inventory
     * @param price      unit price of inventory
     * @param category   category of inventory
     * @param size       size of sizable inventory
     */
    public SizableInventory(String name, LocalDate createDate, Double price, ProductCategory category, ProductSize size) {
        super(name, createDate, price, category);
        setSize(size);// calling setter of size attribute
    }

    /**
     * Setter for size attribute
     *
     * @param size size of sizable Inventory
     */
    @Override
    public void setSize(ProductSize size) {
        Assertive.require(size, IsNull.notNullValue());
        this.size = size;
    }

    /**
     * Getter for size attribute
     *
     * @return the size of sizable inventory
     */
    @Override
    public ProductSize getSize() {
        return size;
    }
}
