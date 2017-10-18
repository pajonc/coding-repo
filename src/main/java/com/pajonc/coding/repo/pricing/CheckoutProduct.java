package com.pajonc.coding.repo.pricing;


import com.pajonc.coding.repo.pricing.model.Price;
import com.pajonc.coding.repo.pricing.model.Product;
import com.pajonc.coding.repo.pricing.model.SpecialPrice;

/**
 * Draft strategy for product checkout.
 */
public interface CheckoutProduct {

    Double totalPrice(String session, Basket<String,Product> basket);

    Price actualPrice(String session, Basket<String,Product> basket, String item);

    SpecialPrice specialPrice(String session, Basket<String,Product> basket, String item);

}
