package com.restaurant.observer;

/**
 * PADRÃO OBSERVER - Interface Subject
 * 
 * Define a interface para objetos que mantêm uma lista de observers
 * e os notificam sobre mudanças de estado
 */
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}
