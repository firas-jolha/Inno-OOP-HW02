package main.utils;

import main.stocks.ClothesStock;
import main.stocks.FoodStock;
import main.stocks.PharmacyStock;

/**
 * An enumeration for Product categories, when a new product category
 * is added to the system, needs adding here.
 */
public enum ProductCategory {
    CLOTHES(ClothesStock.class),
    FOOD(FoodStock.class),
    PHARMACY(PharmacyStock.class);

    private Class stockClass;

    ProductCategory(Class stockClass) {
        this.stockClass = stockClass;
    }

    public Class getStockClass() {
        return stockClass;
    }
}
