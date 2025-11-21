package com.restaurant.strategy;

import com.restaurant.model.Order;

/**
 * PADRÃO STRATEGY - Concrete Strategy
 * 
 * Estratégia de preço para programa de fidelidade (desconto progressivo)
 */
public class LoyaltyPricingStrategy implements PricingStrategy {
    private int loyaltyPoints;
    private static final double DISCOUNT_PER_100_POINTS = 0.05; // 5% a cada 100 pontos
    private static final double MAX_DISCOUNT = 0.30; // Máximo 30% de desconto
    
    public LoyaltyPricingStrategy(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    
    @Override
    public double calculateFinalPrice(Order order) {
        double totalPrice = order.getTotalPrice();
        
        // Calcula desconto baseado nos pontos
        double discountPercentage = (loyaltyPoints / 100) * DISCOUNT_PER_100_POINTS;
        discountPercentage = Math.min(discountPercentage, MAX_DISCOUNT);
        
        double discount = totalPrice * discountPercentage;
        return totalPrice - discount;
    }

    @Override
    public String getStrategyName() {
        int discountPercent = (int)((loyaltyPoints / 100) * DISCOUNT_PER_100_POINTS * 100);
        discountPercent = Math.min(discountPercent, 30);
        return "Programa Fidelidade (" + loyaltyPoints + " pontos - " + discountPercent + "% OFF)";
    }
}
