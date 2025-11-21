package com.restaurant.factory;

import com.restaurant.model.Order;
import com.restaurant.model.OrderType;

/**
 * PADRÃO FACTORY METHOD - Creator abstrato
 * 
 * Define a interface para criação de objetos Order
 * Permite que subclasses decidam qual classe instanciar
 */
public abstract class OrderFactory {
    
    /**
     * Factory Method - método abstrato que será implementado pelas subclasses
     */
    public abstract Order createOrder(int orderId, String customerName);
    
    /**
     * Método template que usa o Factory Method
     * Demonstra como o padrão pode ser usado em um fluxo de negócio
     */
    public Order processNewOrder(int orderId, String customerName) {
        Order order = createOrder(orderId, customerName);
        System.out.println("✅ Novo pedido criado: " + order.getOrderType().getDescription());
        return order;
    }
}
