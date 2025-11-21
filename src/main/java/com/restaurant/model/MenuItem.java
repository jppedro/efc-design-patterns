package com.restaurant.model;

/**
 * Interface base para itens do menu
 * Utilizada no padrão Decorator
 */
public interface MenuItem {
    String getName();
    String getDescription();
    double getPrice();
}
