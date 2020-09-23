package main.inventories;

import main.utils.ProductCategory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.time.LocalDate;

public class ExpirableInventory extends Inventory implements IHasExiprationDate {

    private LocalDate expirationDate;

    public ExpirableInventory(String name, LocalDate createDate, Double price, ProductCategory category, LocalDate expirationDate) {
        super(name, createDate, price, category);
        setExpirationDate(expirationDate);
    }

    @Override
    public void setExpirationDate(LocalDate expirationDate) {
        Assertive.require(expirationDate, IsNull.notNullValue());
        this.expirationDate = expirationDate;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
