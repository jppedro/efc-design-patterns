package com.restaurant.factory;

import com.restaurant.model.Order;
import com.restaurant.model.TakeawayOrder;

/**
 * PADRÃO FACTORY METHOD - Concrete Creator
 * 
 * Factory concreta para criar pedidos para viagem (Takeaway)
 */
public class TakeawayOrderFactory extends OrderFactory {
    
    @Override
    public Order createOrder(int orderId, String customerName) {
        return new TakeawayOrder(orderId, customerName);
    }
}
