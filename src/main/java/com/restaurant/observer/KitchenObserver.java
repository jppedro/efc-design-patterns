package com.restaurant.observer;

import com.restaurant.model.Order;
import com.restaurant.model.OrderStatus;

/**
 * PADRÃO OBSERVER - Concrete Observer
 * 
 * Observador que representa a cozinha
 * Recebe notificações sobre novos pedidos e mudanças de status
 */
public class KitchenObserver implements Observer {
    private String kitchenName;

    public KitchenObserver(String kitchenName) {
        this.kitchenName = kitchenName;
    }

    @Override
    public void update(Order order) {
        System.out.println("\n[" + kitchenName + "] Notificação recebida:");
        System.out.println("  Pedido #" + order.getOrderId() + 
                         " - Status: " + order.getStatus().getDescription());
        
        if (order.getStatus() == OrderStatus.PENDING) {
            System.out.println("  ⚠️  Novo pedido para preparar!");
        } else if (order.getStatus() == OrderStatus.PREPARING) {
            System.out.println("  👨‍🍳 Pedido em preparação...");
        } else if (order.getStatus() == OrderStatus.READY) {
            System.out.println("  ✅ Pedido pronto para entrega!");
        }
    }

    public String getKitchenName() {
        return kitchenName;
    }
}
