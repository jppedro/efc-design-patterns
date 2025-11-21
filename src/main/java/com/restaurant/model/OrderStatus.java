package com.restaurant.model;

/**
 * Enum representando os possíveis status de um pedido
 */
public enum OrderStatus {
    PENDING("Pendente"),
    PREPARING("Em Preparação"),
    READY("Pronto"),
    DELIVERED("Entregue"),
    CANCELLED("Cancelado");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
