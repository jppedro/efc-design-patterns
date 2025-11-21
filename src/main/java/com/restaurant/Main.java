package com.restaurant;

import com.restaurant.decorator.*;
import com.restaurant.factory.*;
import com.restaurant.model.*;
import com.restaurant.observer.*;
import com.restaurant.service.OrderService;
import com.restaurant.singleton.RestaurantConfig;
import com.restaurant.strategy.*;

/**
 * Classe principal que demonstra o uso de todos os padrões de projeto
 * 
 * Padrões implementados:
 * 1. Factory Method - Criação de diferentes tipos de pedidos
 * 2. Observer - Notificação de mudanças de status dos pedidos
 * 3. Strategy - Diferentes estratégias de cálculo de preço
 * 4. Decorator - Adição de extras aos itens do menu
 * 5. Singleton - Configurações globais do restaurante
 */
public class Main {
    
    public static void main(String[] args) {

        // ===== PADRÃO SINGLETON =====
        demonstrateSingleton();
        
        // ===== PADRÃO OBSERVER =====
        Observer kitchen = new KitchenObserver("Cozinha Principal");
        Observer waiter = new WaiterObserver("João");
        Observer paymentSystem = new PaymentSystemObserver();
        
        // ===== PADRÃO FACTORY METHOD + DECORATOR =====
        demonstrateFactoryAndDecorator(kitchen, waiter, paymentSystem);
        
        // ===== PADRÃO STRATEGY =====
        demonstrateStrategy();
    }
    
    /**
     * Demonstra o uso do padrão SINGLETON
     */
    private static void demonstrateSingleton() {
        RestaurantConfig config = RestaurantConfig.getInstance();
        config.displayConfig();
        
        // Demonstra que sempre retorna a mesma instância
        RestaurantConfig config2 = RestaurantConfig.getInstance();
        System.out.println(" Verificação: config == config2? " + (config == config2));
    }
    
    /**
     * Demonstra o uso dos padrões FACTORY METHOD, DECORATOR e OBSERVER
     */
    private static void demonstrateFactoryAndDecorator(Observer kitchen, Observer waiter, Observer paymentSystem) {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│  PADRÕES: Factory Method + Decorator + Observer        │");
        System.out.println("└─────────────────────────────────────────────────────────┘\n");
        
        RestaurantConfig config = RestaurantConfig.getInstance();
        
        // === PEDIDO 1: Dine-In (Presencial) ===
        System.out.println("--- CRIANDO PEDIDO PRESENCIAL ---");
        OrderFactory dineInFactory = new DineInOrderFactory();
        Order order1 = dineInFactory.processNewOrder(
            config.generateOrderId(), 
            "Maria Silva"
        );
        
        // Registra observers
        order1.attach(kitchen);
        order1.attach(waiter);
        order1.attach(paymentSystem);
        
        // Cria itens com DECORATOR
        MenuItem burger = new BasicMenuItem("Hambúrguer Artesanal", "Pão, carne, alface, tomate", 25.00);
        MenuItem burgerWithExtras = new BaconDecorator(
            new ExtraCheeseDecorator(burger)
        );
        
        MenuItem fries = new BasicMenuItem("Batata Frita", "Batatas crocantes", 12.00);
        MenuItem largeFries = new ExtraPortionDecorator(fries);
        
        order1.addItem(burgerWithExtras);
        order1.addItem(largeFries);
        
        System.out.println("\n" + order1);
        
        // Simula mudanças de status (aciona OBSERVER)
        System.out.println("--- MUDANÇAS DE STATUS (Padrão Observer em ação) ---");
        order1.setStatus(OrderStatus.PREPARING);
        order1.setStatus(OrderStatus.READY);
        order1.setStatus(OrderStatus.DELIVERED);
        
        // === PEDIDO 2: Delivery ===
        System.out.println("\n\n--- CRIANDO PEDIDO DELIVERY ---");
        OrderFactory deliveryFactory = new DeliveryOrderFactory("Rua das Flores, 456");
        Order order2 = deliveryFactory.processNewOrder(
            config.generateOrderId(),
            "João Santos"
        );
        
        order2.attach(kitchen);
        order2.attach(waiter);
        order2.attach(paymentSystem);
        
        // Itens com múltiplos decorators
        MenuItem pizza = new BasicMenuItem("Pizza Margherita", "Molho, queijo, manjericão", 35.00);
        MenuItem customPizza = new SpecialSauceDecorator(
            new ExtraCheeseDecorator(pizza),
            "Barbecue"
        );
        
        MenuItem soda = new BasicMenuItem("Refrigerante", "Lata 350ml", 5.00);
        
        order2.addItem(customPizza);
        order2.addItem(soda);
        
        System.out.println("\n" + order2);
        
        System.out.println("--- MUDANÇAS DE STATUS ---");
        order2.setStatus(OrderStatus.PREPARING);
        order2.setStatus(OrderStatus.READY);
        order2.setStatus(OrderStatus.DELIVERED);
        
        // === PEDIDO 3: Takeaway ===
        System.out.println("\n\n--- CRIANDO PEDIDO PARA VIAGEM ---");
        OrderFactory takeawayFactory = new TakeawayOrderFactory();
        Order order3 = takeawayFactory.processNewOrder(
            config.generateOrderId(),
            "Ana Costa"
        );
        
        order3.attach(kitchen);
        
        MenuItem pasta = new BasicMenuItem("Macarrão à Carbonara", "Massa, bacon, queijo, ovos", 28.00);
        order3.addItem(pasta);
        
        System.out.println("\n" + order3);
        
        System.out.println("--- MUDANÇAS DE STATUS ---");
        order3.setStatus(OrderStatus.PREPARING);
        order3.setStatus(OrderStatus.READY);
    }
    
    /**
     * Demonstra o uso do padrão STRATEGY
     */
    private static void demonstrateStrategy() {
        System.out.println("\n\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│  PADRÃO STRATEGY - Estratégias de Precificação         │");
        System.out.println("└─────────────────────────────────────────────────────────┘\n");
        
        RestaurantConfig config = RestaurantConfig.getInstance();
        
        // Cria um pedido de exemplo
        OrderFactory factory = new DineInOrderFactory();
        Order order = factory.createOrder(config.generateOrderId(), "Carlos Oliveira");
        
        MenuItem steak = new BasicMenuItem("Picanha Grelhada", "300g de picanha com acompanhamentos", 45.00);
        MenuItem juice = new BasicMenuItem("Suco Natural", "Laranja 500ml", 8.00);
        
        order.addItem(steak);
        order.addItem(juice);
        
        // Testa diferentes estratégias
        System.out.println("=== TESTANDO DIFERENTES ESTRATÉGIAS DE PREÇO ===\n");
        
        // 1. Preço Regular
        System.out.println("1️⃣  ESTRATÉGIA: Preço Regular");
        OrderService service1 = new OrderService(new RegularPricingStrategy());
        service1.displayOrderSummary(order);
        
        // 2. Happy Hour
        System.out.println("2️⃣  ESTRATÉGIA: Happy Hour");
        OrderService service2 = new OrderService(new HappyHourPricingStrategy());
        service2.displayOrderSummary(order);
        
        // 3. Cupom de Desconto
        System.out.println("3️⃣  ESTRATÉGIA: Cupom de Desconto");
        OrderService service3 = new OrderService(new CouponPricingStrategy("BEMVINDO", 10.00));
        service3.displayOrderSummary(order);
        
        // 4. Programa de Fidelidade
        System.out.println("4️⃣  ESTRATÉGIA: Programa de Fidelidade");
        OrderService service4 = new OrderService(new LoyaltyPricingStrategy(250));
        service4.displayOrderSummary(order);
        
        // Demonstra troca de estratégia em tempo de execução
        System.out.println("\n--- MUDANDO ESTRATÉGIA EM TEMPO DE EXECUÇÃO ---");
        OrderService dynamicService = new OrderService();
        System.out.println("Estratégia inicial: Regular");
        System.out.println("Preço: R$ " + String.format("%.2f", dynamicService.calculateFinalPrice(order)));
        
        dynamicService.setPricingStrategy(new HappyHourPricingStrategy());
        System.out.println("\nEstratégia alterada para: Happy Hour");
        System.out.println("Novo preço: R$ " + String.format("%.2f", dynamicService.calculateFinalPrice(order)));
    }
}
