package com.restaurant.decorator;

import com.restaurant.model.MenuItem;

/**
 * PADRÃO DECORATOR - Concrete Decorator
 * 
 * Adiciona molho especial ao item do menu
 */
public class SpecialSauceDecorator extends MenuItemDecorator {
    private static final double SAUCE_PRICE = 2.00;
    private String sauceName;

    public SpecialSauceDecorator(MenuItem item, String sauceName) {
        super(item);
        this.sauceName = sauceName;
    }

    @Override
    public String getName() {
        return wrappedItem.getName() + " + Molho " + sauceName;
    }

    @Override
    public String getDescription() {
        return wrappedItem.getDescription() + ", com molho " + sauceName;
    }

    @Override
    public double getPrice() {
        return wrappedItem.getPrice() + SAUCE_PRICE;
    }
}
