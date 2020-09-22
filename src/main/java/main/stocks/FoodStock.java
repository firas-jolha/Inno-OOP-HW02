package main.stocks;

import main.utils.ProductCategory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

public class FoodStock extends Stock{
    public FoodStock() {
        super(ProductCategory.FOOD);
    }

}
