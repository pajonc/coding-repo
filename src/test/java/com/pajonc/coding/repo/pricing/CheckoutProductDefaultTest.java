package com.pajonc.coding.repo.pricing;

import static com.pajonc.coding.repo.pricing.factory.ProductFactory.*;
import com.pajonc.coding.repo.pricing.model.Price;
import com.pajonc.coding.repo.pricing.model.SpecialPrice;
import org.junit.After;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CheckoutProductDefaultTest {

    private CheckoutProduct checkoutProduct = new CheckoutProductDefault();
    private List<String> sessions = Arrays.asList("session1", "session2", "session3");
    private Basket basket = new Basket();

    @After
    public void tearDown() throws Exception {
        basket = new Basket();
    }

    @Test
    public void shouldCalculateTotalPrice() throws Exception {
        //when
        basket.addProduct(sessions.get(0), createProduct("ball", 5.0, 8, 1, 1));
        basket.addProduct(sessions.get(0), createProduct("umbrella", 1.0, 3, 0, 2));
        basket.addProduct(sessions.get(0), createProduct("t-shirt", 8.0, 20, 1, 1));
        //given
        Double total = checkoutProduct.totalPrice(sessions.get(0), basket);
        //then
        assertEquals(new Double(47), total);
    }

    @Test
    public void shouldReturnPriceIfCounterGreaterThanZero() throws Exception {
        //when
        basket.addProduct(sessions.get(0), createProduct("t-shirt", 8.0, 20, 1, 1));
        basket.addProduct(sessions.get(0), createProduct("ball", 5.0, 8, 1, 1));
        basket.addProduct(sessions.get(0), createProduct("umbrella", 1.0, 3, 0, 2));
        //given
        Price tShirt = checkoutProduct.actualPrice(sessions.get(0), basket, "t-shirt");
        SpecialPrice umbrella = checkoutProduct.specialPrice(sessions.get(0), basket, "umbrella");
        //then
        assertNotNull(tShirt);
        assertEquals(new Double(8), tShirt.getValue());
        assertEquals(new Integer(1), tShirt.getCounter());
        assertEquals(DEFAULT_CURRENCY, tShirt.getCurrency());
        assertNotNull(tShirt);
        assertEquals(new Double(3), umbrella.getPrice().getValue());
        assertTrue(umbrella.getAmount()>1);

    }

    @Test
    public void shouldReturnNullIfCounterZero() throws Exception {
        //when
        basket.addProduct(sessions.get(0), createProduct("vodka", 4.0, 20, 1, 0));
        basket.addProduct(sessions.get(0), createProduct("ball", 5.0, 8, 1, 1));
        basket.addProduct(sessions.get(0), createProduct("umbrella", 1.0, 3, 0, 2));
        //given
        Price umbrella = checkoutProduct.actualPrice(sessions.get(0), basket, "umbrella");
        SpecialPrice tShirt = checkoutProduct.specialPrice(sessions.get(0), basket, "vodka");
        //then
        assertNull(umbrella);
        assertNull(tShirt);
    }

    @Test
    public void shouldReturnNullForActualPriceWhenEmptyProductInBasket() {
        basket.addProduct(sessions.get(2), null);
        assertNull(checkoutProduct.totalPrice(sessions.get(0), basket));
        assertNull(checkoutProduct.actualPrice(sessions.get(0), basket, "vodka"));
        assertNull(checkoutProduct.specialPrice(sessions.get(0), basket, "vodka"));
    }


}