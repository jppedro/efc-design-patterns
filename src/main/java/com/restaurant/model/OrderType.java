package com.restaurant.model;

/**
 * Enum representando os tipos de pedido
 */
public enum OrderType {
    DINE_IN("Presencial"),
    TAKEAWAY("Para Viagem"),
    DELIVERY("Delivery");

    private final String description;

    OrderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
