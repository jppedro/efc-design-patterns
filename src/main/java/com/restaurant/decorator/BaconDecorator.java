package com.restaurant.decorator;

import com.restaurant.model.MenuItem;

/**
 * PADRÃO DECORATOR - Concrete Decorator
 * 
 * Adiciona bacon ao item do menu
 */
public class BaconDecorator extends MenuItemDecorator {
    private static final double BACON_PRICE = 5.00;

    public BaconDecorator(MenuItem item) {
        super(item);
    }

    @Override
    public String getName() {
        return wrappedItem.getName() + " + Bacon";
    }

    @Override
    public String getDescription() {
        return wrappedItem.getDescription() + ", com bacon crocante";
    }

    @Override
    public double getPrice() {
        return wrappedItem.getPrice() + BACON_PRICE;
    }
}
