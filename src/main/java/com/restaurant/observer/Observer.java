package com.restaurant.observer;

import com.restaurant.model.Order;

/**
 * PADRÃO OBSERVER - Interface Observer
 * 
 * Define a interface para objetos que devem ser notificados sobre mudanças no Subject
 */
public interface Observer {
    void update(Order order);
}
