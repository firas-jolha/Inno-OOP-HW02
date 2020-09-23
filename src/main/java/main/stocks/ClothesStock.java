package main.stocks;

import main.utils.ProductCategory;

/**
 * Represents a separate stock for clothes' products
 */
public class ClothesStock  extends Stock{
    /**
     * Constructor assigns the category of stock
     */
    public ClothesStock() {
        super(ProductCategory.CLOTHES);
    }

}
