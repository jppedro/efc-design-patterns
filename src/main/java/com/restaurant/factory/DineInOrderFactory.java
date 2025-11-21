package com.restaurant.factory;

import com.restaurant.model.Order;
import com.restaurant.model.DineInOrder;

/**
 * PADRÃO FACTORY METHOD - Concrete Creator
 * 
 * Factory concreta para criar pedidos presenciais (Dine-In)
 */
public class DineInOrderFactory extends OrderFactory {
    
    @Override
    public Order createOrder(int orderId, String customerName) {
        return new DineInOrder(orderId, customerName);
    }
}
