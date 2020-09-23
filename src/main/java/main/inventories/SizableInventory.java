package main.inventories;

import main.utils.ProductCategory;
import org.valid4j.Assertive;

import java.time.LocalDate;

public class SizableInventory extends Inventory implements IHasSizeInventory {

    private Double size;

    public SizableInventory(String name, LocalDate createDate, Double price, ProductCategory category, Double size) {
        super(name, createDate, price, category);
        setSize(size);
    }

    @Override
    public void setSize(Double size) {
        Assertive.require(size>0.0);
        this.size = size;
    }

    @Override
    public Double getSize() {
        return size;
    }
}
