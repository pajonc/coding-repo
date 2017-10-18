package com.pajonc.coding.repo.pricing;

import com.pajonc.coding.repo.pricing.model.Price;
import com.pajonc.coding.repo.pricing.model.Product;
import com.pajonc.coding.repo.pricing.model.SpecialPrice;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class CheckoutProductDefault implements CheckoutProduct {


    @Override
    public Double totalPrice(String session, Basket<String, Product> basket) {
        if(basket.getProductsBySession(session)==null) return null;

        double priceSum = basket.getProductsBySession(session).stream()
                .filter(p -> p.getPrice() != null)
                .map(p -> p.getPrice())
                .collect(Collectors.summarizingDouble(p -> (p.getValue() * Double.valueOf(p.getCounter())))).getSum();

        double specialPriceSum = basket.getProductsBySession(session).stream()
                .filter(p -> p.getSpecialPrice() != null)
                .map(p -> p.getSpecialPrice())
                .collect(Collectors.summarizingDouble(p -> (p.getPrice().getValue() * Double.valueOf(p.getPrice().getCounter())))).getSum();

        return priceSum + specialPriceSum;
    }

    @Override
    public Price actualPrice(String session, Basket<String, Product> basket, String item) {
        Product productFromBasket = getProductFromBasket(session, basket, item);
        return productFromBasket != null ? productFromBasket.getPrice() : null;
    }

    @Override
    public SpecialPrice specialPrice(String session, Basket<String, Product> basket, String item) {
        Product productFromBasket = getProductFromBasket(session, basket, item);
        return productFromBasket != null ? productFromBasket.getSpecialPrice() : null;

    }

    private Product getProductFromBasket(String session, Basket<String, Product> basket, String item) {
        Product product = null;
        try {
            if(basket.getProductsBySession(session)!=null) {
                product = basket.getProductsBySession(session).stream().filter(p -> p.getItem().equals(item)).findFirst().get();
            }
        } catch (NoSuchElementException e) {
            //debug only
            System.out.println(item + " not found in the basket for this session " + session);
        }
        return product;
    }


}
