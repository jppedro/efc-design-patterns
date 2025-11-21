package com.restaurant.strategy;

import com.restaurant.model.Order;

/**
 * PADRÃO STRATEGY - Concrete Strategy
 * 
 * Estratégia de preço com desconto de Happy Hour (20% de desconto)
 */
public class HappyHourPricingStrategy implements PricingStrategy {
    private static final double DISCOUNT_PERCENTAGE = 0.20; // 20% de desconto
    
    @Override
    public double calculateFinalPrice(Order order) {
        double totalPrice = order.getTotalPrice();
        double discount = totalPrice * DISCOUNT_PERCENTAGE;
        return totalPrice - discount;
    }

    @Override
    public String getStrategyName() {
        return "Happy Hour (20% OFF)";
    }
}
