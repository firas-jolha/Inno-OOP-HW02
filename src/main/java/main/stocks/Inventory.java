package main.stocks;

import main.utils.ProductCategory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.time.LocalDate;


public class Inventory {
    private String name;
    private LocalDate createDate;
    private Double size;
    private LocalDate expirationDate;
    private Double price;
    private ProductCategory category;

    public Inventory(String name, LocalDate createDate, Double size, LocalDate expirationDate, Double price, ProductCategory category) {
        switch (category){
            case FOOD:case PHARMACY: Assertive.require(expirationDate, IsNull.notNullValue());break;
            case CLOTHES: Assertive.require(size>0.0); break;
        }
        Assertive.require(name!=null && !name.equals(""));
        Assertive.require(createDate!=null);
        this.name = name;
        this.createDate = createDate;
        this.size = size;
        this.expirationDate = expirationDate;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSize() {
        return size;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
