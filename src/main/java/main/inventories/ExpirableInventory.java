package main.inventories;

import main.utils.ProductCategory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.time.LocalDate;

/**
 * Represents all expirable inventories or which have expirationDate attribute
 * This class is a inventory and implements IExpirableInventory interface
 */
public class ExpirableInventory extends Inventory implements IExpirableInventory {

    /**
     * expiration date of expirable inventory
     */
    private LocalDate expirationDate;

    /**
     * Creates an instance of expirable inventory
     *
     * @param name           name of inventory
     * @param createDate     created date of inventory
     * @param price          unit price of inventory
     * @param category       category of inventory
     * @param expirationDate expiration date of inventory
     */
    public ExpirableInventory(String name, LocalDate createDate, Double price, ProductCategory category, LocalDate expirationDate) {
        super(name, createDate, price, category);
        setExpirationDate(expirationDate);
    }

    /**
     * Setter for expirationDate attribute
     *
     * @param expirationDate expiration date of inventory
     */
    @Override
    public void setExpirationDate(LocalDate expirationDate) {
        Assertive.require(expirationDate, IsNull.notNullValue());
        this.expirationDate = expirationDate;
    }

    /**
     * Getter for expirationDate attribute
     *
     * @return expiration date of inventory
     */
    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
