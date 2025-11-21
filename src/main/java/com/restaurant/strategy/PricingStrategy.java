package com.restaurant.strategy;

import com.restaurant.model.Order;

/**
 * PADRÃO STRATEGY - Interface Strategy
 * 
 * Define a interface para diferentes estratégias de cálculo de preço
 * Permite trocar o algoritmo de precificação em tempo de execução
 */
public interface PricingStrategy {
    double calculateFinalPrice(Order order);
    String getStrategyName();
}
