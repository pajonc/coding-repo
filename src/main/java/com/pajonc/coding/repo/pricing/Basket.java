package com.pajonc.coding.repo.pricing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Generic class for holding user session (j_sessionId for example) and corresponding products.
 * @param <T> session identifier
 * @param <U> product
 */
public class Basket<T, U> {

    private HashMap<T, Set<U>> items = new HashMap<>();

    public void addProduct(T session, U product) {
        Set<U> productsBySession = getProductsBySession(session);
        if (productsBySession == null) {
            Set<U> products = new HashSet<>();
            products.add(product);
            items.put(session, products);
        } else {
            productsBySession.add(product);
        }
    }

    public Set<U> getProductsBySession(T session) {
        return items.get(session);
    }

}
