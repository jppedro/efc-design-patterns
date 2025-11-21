package com.restaurant.strategy;

import com.restaurant.model.Order;

/**
 * PADRÃO STRATEGY - Concrete Strategy
 * 
 * Estratégia de preço regular (sem descontos)
 */
public class RegularPricingStrategy implements PricingStrategy {
    
    @Override
    public double calculateFinalPrice(Order order) {
        return order.getTotalPrice();
    }

    @Override
    public String getStrategyName() {
        return "Preço Regular";
    }
}
