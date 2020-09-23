package main.inventories;

import main.utils.ProductCategory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.time.LocalDate;


public abstract class Inventory {
    private String name;
    private LocalDate createDate;
    private Double price;
    private ProductCategory category;
    // expirationDate and Size are implemented in separate classes

    protected Inventory(String name, LocalDate createDate, Double price, ProductCategory category) {
        setName(name);
        setPrice(price);
        setCategory(category);
        setCreateDate(createDate);
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setName(String name) {
        Assertive.require(name!=null && !name.equals(""));
        this.name = name;
    }

    public void setCreateDate(LocalDate createDate) {
        Assertive.require(createDate!=null);
        this.createDate = createDate;
    }

    public void setPrice(Double price) {
        Assertive.require(price > 0.0);
        this.price = price;
    }

    public void setCategory(ProductCategory category) {
        Assertive.require(category, IsNull.notNullValue());
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + getName() + '\'' +
                ", createDate=" + getCreateDate() +
                ", price=" + getPrice() +
                ", category=" + getCategory() +
                '}';
    }
}
