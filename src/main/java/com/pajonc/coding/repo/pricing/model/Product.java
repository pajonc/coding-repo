package com.pajonc.coding.repo.pricing.model;


import java.util.Objects;

public class Product {

    private String item;
    private Price price;
    private SpecialPrice specialPrice;

    public Product(String item) {
        this.item = item;
    }

    public Product buildPrice(Price price) {
        this.price = price;
        return this;
    }

    public Product buildSpecialPrice(SpecialPrice specialPrice) {
        this.specialPrice = specialPrice;
        return this;
    }

    public String getItem() {
        return item;
    }

    public Price getPrice() {
        return price;
    }

    public SpecialPrice getSpecialPrice() {
        return specialPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(item, product.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }
}
