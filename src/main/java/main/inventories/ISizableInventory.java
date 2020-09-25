package main.inventories;

import main.utils.ProductSize;

/**
 * Represents the size property of sizable inventory
 */
public interface ISizableInventory {
    /**
     * Abstract setter for setting size
     *
     * @param size size of inventory
     */
    void setSize(ProductSize size);

    /**
     * Abstract getter for getting size of inventory
     *
     * @return the size of inventory
     */
    ProductSize getSize();
}
