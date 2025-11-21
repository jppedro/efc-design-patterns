package com.restaurant.model;

/**
 * PADRÃO FACTORY METHOD - Concrete Product
 * 
 * Pedido para viagem (takeaway)
 * Possui uma pequena taxa de embalagem
 */
public class TakeawayOrder extends Order {
    private static final double PACKAGING_FEE = 2.00;
    
    public TakeawayOrder(int orderId, String customerName) {
        super(orderId, customerName);
    }

    @Override
    public double calculateDeliveryFee() {
        return PACKAGING_FEE; // Taxa de embalagem
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.TAKEAWAY;
    }
}
