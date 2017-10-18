package com.pajonc.coding.repo.pricing.model;


public class Price {

    private Double value;
    private Integer counter;
    private String currency;

    public Price(Double value, Integer counter, String currency) {
        this.value = value;
        this.counter = counter;
        this.currency = currency;

    }

    public Double getValue() {
        return value;
    }

    public Integer getCounter() {
        return counter;
    }

    public String getCurrency() {
        return currency;
    }


}
