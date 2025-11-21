package com.restaurant.decorator;

import com.restaurant.model.MenuItem;

/**
 * PADRÃO DECORATOR - Concrete Decorator
 * 
 * Adiciona porção extra ao item do menu (dobra o tamanho)
 */
public class ExtraPortionDecorator extends MenuItemDecorator {
    private static final double PORTION_MULTIPLIER = 1.5; // 50% a mais no preço

    public ExtraPortionDecorator(MenuItem item) {
        super(item);
    }

    @Override
    public String getName() {
        return wrappedItem.getName() + " (Porção Extra)";
    }

    @Override
    public String getDescription() {
        return wrappedItem.getDescription() + " - PORÇÃO DOBRADA";
    }

    @Override
    public double getPrice() {
        return wrappedItem.getPrice() * PORTION_MULTIPLIER;
    }
}
