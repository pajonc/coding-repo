package com.pajonc.coding.repo.pricing.model;

public class SpecialPrice {

    private Price price;
    private Integer amount;

    public SpecialPrice(Price price, Integer amount) {
        this.price = price;
        this.amount = amount;
    }

    public Price getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

}
