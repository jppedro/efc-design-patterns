package com.restaurant.decorator;

import com.restaurant.model.MenuItem;

/**
 * PADRÃO DECORATOR - Decorator abstrato
 * 
 * Classe base para todos os decoradores de MenuItem
 * Mantém uma referência ao componente decorado
 */
public abstract class MenuItemDecorator implements MenuItem {
    protected MenuItem wrappedItem;

    public MenuItemDecorator(MenuItem item) {
        this.wrappedItem = item;
    }

    @Override
    public String getName() {
        return wrappedItem.getName();
    }

    @Override
    public String getDescription() {
        return wrappedItem.getDescription();
    }

    @Override
    public double getPrice() {
        return wrappedItem.getPrice();
    }
}
