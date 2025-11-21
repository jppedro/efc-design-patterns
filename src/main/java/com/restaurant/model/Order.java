package com.restaurant.model;

import com.restaurant.observer.Subject;
import com.restaurant.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Classe abstrata representando um pedido
 * Implementa o padrão Observer como Subject
 */
public abstract class Order implements Subject {
    protected int orderId;
    protected String customerName;
    protected List<MenuItem> items;
    protected OrderStatus status;
    protected LocalDateTime orderTime;
    protected List<Observer> observers;
    protected double basePrice;

    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.orderTime = LocalDateTime.now();
        this.observers = new ArrayList<>();
        this.basePrice = 0.0;
    }

    public void addItem(MenuItem item) {
        items.add(item);
        basePrice += item.getPrice();
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
        basePrice -= item.getPrice();
    }

    // Método abstrato que será implementado pelas subclasses
    public abstract double calculateDeliveryFee();
    
    // Método abstrato para obter o tipo do pedido
    public abstract OrderType getOrderType();

    public double getTotalPrice() {
        return basePrice + calculateDeliveryFee();
    }

    public void setStatus(OrderStatus newStatus) {
        this.status = newStatus;
        notifyObservers();
    }

    // Implementação do padrão Observer
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Pedido #").append(orderId).append(" ===\n");
        sb.append("Tipo: ").append(getOrderType().getDescription()).append("\n");
        sb.append("Cliente: ").append(customerName).append("\n");
        sb.append("Status: ").append(status.getDescription()).append("\n");
        sb.append("Horário: ").append(orderTime).append("\n");
        sb.append("\nItens:\n");
        for (MenuItem item : items) {
            sb.append("  - ").append(item.getName())
              .append(" (R$ ").append(String.format("%.2f", item.getPrice())).append(")\n");
        }
        sb.append("\nSubtotal: R$ ").append(String.format("%.2f", basePrice)).append("\n");
        double deliveryFee = calculateDeliveryFee();
        if (deliveryFee > 0) {
            sb.append("Taxa de entrega: R$ ").append(String.format("%.2f", deliveryFee)).append("\n");
        }
        sb.append("Total: R$ ").append(String.format("%.2f", getTotalPrice())).append("\n");
        return sb.toString();
    }
}
