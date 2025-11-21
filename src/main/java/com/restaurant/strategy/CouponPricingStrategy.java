package com.restaurant.strategy;

import com.restaurant.model.Order;

/**
 * PADRÃO STRATEGY - Concrete Strategy
 * 
 * Estratégia de preço com cupom de desconto fixo
 */
public class CouponPricingStrategy implements PricingStrategy {
    private double discountAmount;
    private String couponCode;
    
    public CouponPricingStrategy(String couponCode, double discountAmount) {
        this.couponCode = couponCode;
        this.discountAmount = discountAmount;
    }
    
    @Override
    public double calculateFinalPrice(Order order) {
        double totalPrice = order.getTotalPrice();
        double finalPrice = totalPrice - discountAmount;
        return Math.max(finalPrice, 0.0); // Não pode ser negativo
    }

    @Override
    public String getStrategyName() {
        return "Cupom " + couponCode + " (R$ " + String.format("%.2f", discountAmount) + " OFF)";
    }
}
