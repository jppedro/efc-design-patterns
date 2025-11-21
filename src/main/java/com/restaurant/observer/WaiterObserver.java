package com.restaurant.observer;

import com.restaurant.model.Order;
import com.restaurant.model.OrderStatus;

/**
 * PADRÃO OBSERVER - Concrete Observer
 * 
 * Observador que representa um garçom
 * Recebe notificações sobre pedidos prontos para servir
 */
public class WaiterObserver implements Observer {
    private String waiterName;

    public WaiterObserver(String waiterName) {
        this.waiterName = waiterName;
    }

    @Override
    public void update(Order order) {
        if (order.getStatus() == OrderStatus.READY) {
            System.out.println("\n[Garçom " + waiterName + "] Notificação recebida:");
            System.out.println("  Pedido #" + order.getOrderId() + " está pronto!");
            System.out.println("  Cliente: " + order.getCustomerName());
            System.out.println("  🔔 Preparar para servir/entregar");
        }
    }

    public String getWaiterName() {
        return waiterName;
    }
}
