package com.restaurant.decorator;

import com.restaurant.model.MenuItem;

/**
 * PADRÃO DECORATOR - Concrete Component
 * 
 * Representa um item básico do menu sem extras
 */
public class BasicMenuItem implements MenuItem {
    private String name;
    private String description;
    private double price;

    public BasicMenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
