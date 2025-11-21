package com.restaurant.service;

import com.restaurant.model.Order;
import com.restaurant.strategy.PricingStrategy;
import com.restaurant.strategy.RegularPricingStrategy;

/**
 * Serviço para gerenciar operações de pedidos
 * Utiliza o padrão Strategy para cálculo de preços
 */
public class OrderService {
    private PricingStrategy pricingStrategy;

    public OrderService() {
        this.pricingStrategy = new RegularPricingStrategy();
    }

    public OrderService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    /**
     * Permite trocar a estratégia de precificação em tempo de execução
     */
    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    /**
     * Calcula o preço final do pedido usando a estratégia atual
     */
    public double calculateFinalPrice(Order order) {
        return pricingStrategy.calculateFinalPrice(order);
    }

    /**
     * Exibe o resumo do pedido com a estratégia de preço aplicada
     */
    public void displayOrderSummary(Order order) {
        System.out.println(order.toString());
        
        double originalPrice = order.getTotalPrice();
        double finalPrice = calculateFinalPrice(order);
        
        if (finalPrice != originalPrice) {
            System.out.println("Estratégia de Preço: " + pricingStrategy.getStrategyName());
            System.out.println("Desconto aplicado: R$ " + 
                             String.format("%.2f", originalPrice - finalPrice));
            System.out.println("PREÇO FINAL: R$ " + String.format("%.2f", finalPrice));
        }
        System.out.println("=====================================\n");
    }
}
