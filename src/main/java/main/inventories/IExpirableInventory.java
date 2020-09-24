package main.inventories;

import java.time.LocalDate;

/**
 * Represents the expiration date property of expirable inventory
 */
public interface IExpirableInventory {
    /**
     * Abstract setter for setting expiration date
     *
     * @param date expiration date of inventory
     */
    void setExpirationDate(LocalDate date);

    /**
     * Abstract getter for getting expiration date
     *
     * @return the expiration date of inventory
     */
    LocalDate getExpirationDate();
}
