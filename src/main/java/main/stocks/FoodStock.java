package main.stocks;

import main.utils.ProductCategory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

/**
 * Represents a separate stock for food products
 */
public class FoodStock extends Stock{
    /**
     * Constructor assigns the category of stock
     */
    public FoodStock() {
        super(ProductCategory.FOOD);
    }

}
