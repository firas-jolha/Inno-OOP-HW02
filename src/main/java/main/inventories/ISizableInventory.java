package main.inventories;

/**
 * Represents the size property of sizable inventory
 */
public interface ISizableInventory {
    /**
     * Abstract setter for setting size
     *
     * @param size size of inventory
     */
    void setSize(Double size);

    /**
     * Abstract getter for getting size of inventory
     *
     * @return the size of inventory
     */
    Double getSize();
}
