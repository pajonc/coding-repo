package com.pajonc.coding.repo.pricing.factory;

import com.pajonc.coding.repo.pricing.model.Price;
import com.pajonc.coding.repo.pricing.model.Product;
import com.pajonc.coding.repo.pricing.model.SpecialPrice;

/**
 * Helper class for creating products. This factory is created only for test purposes!
 */
public class ProductFactory {

    public static String DEFAULT_CURRENCY = "USD";

    public static Product createProduct(String itemName, double priceValue, double specialPriceValue, int priceCounter, int specialPriceCounter) {
        Product product = new Product(itemName);
        if(priceValue>0 && priceCounter>0) {
            Price price = new Price(priceValue, priceCounter, DEFAULT_CURRENCY);
            product.buildPrice(price);
        }
        if(specialPriceValue>0 && specialPriceCounter>0) {
            SpecialPrice specialPrice = new SpecialPrice(new Price(specialPriceValue, specialPriceCounter, DEFAULT_CURRENCY),generateRandomInt(10,2));
            product.buildSpecialPrice(specialPrice);
        }

        return product;
    }

    private static int generateRandomInt(int range, int minimum) {
        return minimum + (int)(Math.random() * range);
    }
}
