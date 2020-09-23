package main.stocks;

import main.utils.ProductCategory;

/**
 * Represents a separate stock for pharmacy products
 */
public class PharmacyStock  extends Stock{
    /**
     * Constructor assigns the category of stock
     */
    public PharmacyStock() {
        super(ProductCategory.PHARMACY);
    }
}
