package main.inventories;

import main.utils.ProductCategory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.time.LocalDate;

/**
 * Represents the inventory in shop
 */
public abstract class Inventory {
    /**
     * name of inventory
     */
    private String name;

    /**
     * created date of inventory
     */
    private LocalDate createDate;

    /**
     * unit price of inventory
     */
    private Double price;

    /**
     * Category of inventory
     */
    private ProductCategory category;

    // NOTE: expirationDate and Size are implemented in separate classes

    /**
     * Creates an instance of inventory
     *
     * @param name       name of inventory
     * @param createDate created date of inventory
     * @param price      unit price of inventory
     * @param category   category of inventory
     */
    protected Inventory(String name, LocalDate createDate, Double price, ProductCategory category) {
        setName(name);
        setPrice(price);
        setCategory(category);
        setCreateDate(createDate);
    }

    /**
     * Getter for name attribute
     *
     * @return name of inventory
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for price attribute
     *
     * @return unit price of inventory
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Getter for createDate attribute
     *
     * @return created date of inventory
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * Getter for category of inventory
     *
     * @return category of inventory
     */
    public ProductCategory getCategory() {
        return category;
    }

    /**
     * Setter for name attribute
     *
     * @param name name of inventory
     */
    public void setName(String name) {
        Assertive.require(name != null && !name.equals(""));
        this.name = name;
    }

    /**
     * Setter for createDate attribute
     *
     * @param createDate created date of inventory
     */
    public void setCreateDate(LocalDate createDate) {
        Assertive.require(createDate != null);
        this.createDate = createDate;
    }

    /**
     * Setter for price attribute
     *
     * @param price the unit price of inventory
     */
    public void setPrice(Double price) {
        Assertive.require(price > 0.0);
        this.price = price;
    }

    /**
     * Setter for category of inventory
     *
     * @param category the category of inventory
     */
    public void setCategory(ProductCategory category) {
        Assertive.require(category, IsNull.notNullValue());
        this.category = category;
    }

    /**
     * String representation of Inventory for printing info about it
     *
     * @return string
     */
    @Override
    public String toString() {
        return "\n          {" +
                "name='" + getName() + '\'' +
                ", createDate=" + getCreateDate() +
                ", price=" + getPrice() +
                ", category=" + getCategory() +
                '}';
    }
}
