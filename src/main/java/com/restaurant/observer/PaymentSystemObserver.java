package com.restaurant.observer;

import com.restaurant.model.Order;
import com.restaurant.model.OrderStatus;

/**
 * PADRÃO OBSERVER - Concrete Observer
 * 
 * Observador que representa o sistema de pagamento
 * Recebe notificações sobre pedidos entregues para processar pagamento
 */
public class PaymentSystemObserver implements Observer {
    
    @Override
    public void update(Order order) {
        if (order.getStatus() == OrderStatus.DELIVERED) {
            System.out.println("\n[Sistema de Pagamento] Notificação recebida:");
            System.out.println("  Pedido #" + order.getOrderId() + " foi entregue");
            System.out.println("  💰 Processando pagamento de R$ " + 
                             String.format("%.2f", order.getTotalPrice()));
            System.out.println("  Cliente: " + order.getCustomerName());
        } else if (order.getStatus() == OrderStatus.CANCELLED) {
            System.out.println("\n[Sistema de Pagamento] Notificação recebida:");
            System.out.println("  Pedido #" + order.getOrderId() + " foi cancelado");
            System.out.println("  ❌ Estornando pagamento se necessário");
        }
    }
}
