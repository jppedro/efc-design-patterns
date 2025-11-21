package com.restaurant.model;

/**
 * PADRÃO FACTORY METHOD - Concrete Product
 * 
 * Pedido presencial (para consumo no restaurante)
 * Não possui taxa de entrega
 */
public class DineInOrder extends Order {
    
    public DineInOrder(int orderId, String customerName) {
        super(orderId, customerName);
    }

    @Override
    public double calculateDeliveryFee() {
        return 0.0; // Sem taxa para pedidos presenciais
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.DINE_IN;
    }
}
