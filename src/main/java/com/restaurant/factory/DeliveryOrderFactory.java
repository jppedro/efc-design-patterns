package com.restaurant.factory;

import com.restaurant.model.Order;
import com.restaurant.model.DeliveryOrder;

/**
 * PADRÃO FACTORY METHOD - Concrete Creator
 * 
 * Factory concreta para criar pedidos de delivery
 */
public class DeliveryOrderFactory extends OrderFactory {
    private String deliveryAddress;
    
    public DeliveryOrderFactory(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    
    @Override
    public Order createOrder(int orderId, String customerName) {
        return new DeliveryOrder(orderId, customerName, deliveryAddress);
    }
}
