package com.fonet.realestate.model;

public enum PropertyType {
    RENT("Kiralık"), SALE("Satılık");
    private final String displayValue;

    PropertyType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}