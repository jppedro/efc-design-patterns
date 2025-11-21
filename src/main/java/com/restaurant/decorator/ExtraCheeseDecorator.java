package com.restaurant.decorator;

import com.restaurant.model.MenuItem;

/**
 * PADRÃO DECORATOR - Concrete Decorator
 * 
 * Adiciona queijo extra ao item do menu
 */
public class ExtraCheeseDecorator extends MenuItemDecorator {
    private static final double EXTRA_CHEESE_PRICE = 3.50;

    public ExtraCheeseDecorator(MenuItem item) {
        super(item);
    }

    @Override
    public String getName() {
        return wrappedItem.getName() + " + Queijo Extra";
    }

    @Override
    public String getDescription() {
        return wrappedItem.getDescription() + ", com queijo extra";
    }

    @Override
    public double getPrice() {
        return wrappedItem.getPrice() + EXTRA_CHEESE_PRICE;
    }
}
