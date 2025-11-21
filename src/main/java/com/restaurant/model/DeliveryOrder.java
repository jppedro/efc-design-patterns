package com.restaurant.model;

/**
 * PADRÃO FACTORY METHOD - Concrete Product
 * 
 * Pedido de delivery
 * Possui taxa de entrega baseada na distância
 */
public class DeliveryOrder extends Order {
    private static final double BASE_DELIVERY_FEE = 5.00;
    private static final double MINIMUM_FOR_FREE_DELIVERY = 50.00;
    private String deliveryAddress;
    
    public DeliveryOrder(int orderId, String customerName, String deliveryAddress) {
        super(orderId, customerName);
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public double calculateDeliveryFee() {
        // Entrega grátis para pedidos acima de R$ 50
        if (basePrice >= MINIMUM_FOR_FREE_DELIVERY) {
            return 0.0;
        }
        return BASE_DELIVERY_FEE;
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.DELIVERY;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    @Override
    public String toString() {
        String baseString = super.toString();
        return baseString.replace("===\n", "===\nEndereço: " + deliveryAddress + "\n");
    }
}
