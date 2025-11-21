# Integrantes
João Pedro Rodrigues da Costa - 23015736
Taylor Burgues - 23016129

# Sistema de Gerenciamento de Pedidos de Restaurante

## 📋 Descrição do Projeto

Sistema desenvolvido em Java que simula o gerenciamento de pedidos de um restaurante, implementando 5 padrões de projeto clássicos do livro "Design Patterns: Elements of Reusable Object-Oriented Software" (Gang of Four). O projeto demonstra a aplicação prática desses padrões em um contexto real e familiar.

## 🎯 Objetivo

Demonstrar a implementação e os benefícios de múltiplos padrões de projeto em uma aplicação Java funcional, atendendo aos requisitos de um sistema de gerenciamento de pedidos com diferentes tipos de entrega, customização de itens, estratégias de precificação e notificações em tempo real.

## 🏗️ Padrões de Projeto Implementados

### 1. **Factory Method** (Padrão Criacional)

**Localização no código:**
- `src/main/java/com/restaurant/factory/OrderFactory.java` - Creator abstrato
- `src/main/java/com/restaurant/factory/DineInOrderFactory.java` - Concrete Creator
- `src/main/java/com/restaurant/factory/TakeawayOrderFactory.java` - Concrete Creator
- `src/main/java/com/restaurant/factory/DeliveryOrderFactory.java` - Concrete Creator
- `src/main/java/com/restaurant/model/Order.java` - Product abstrato
- `src/main/java/com/restaurant/model/DineInOrder.java` - Concrete Product
- `src/main/java/com/restaurant/model/TakeawayOrder.java` - Concrete Product
- `src/main/java/com/restaurant/model/DeliveryOrder.java` - Concrete Product

**Por que foi escolhido:**
O Factory Method é ideal para criar diferentes tipos de pedidos (presencial, takeaway, delivery) sem que o código cliente precise conhecer as classes concretas. Cada tipo de pedido tem comportamentos específicos (como cálculo de taxa de entrega).

**Qual problema ele resolve:**
- Elimina a necessidade de usar `new` diretamente no código cliente
- Permite adicionar novos tipos de pedidos sem modificar código existente (Open/Closed Principle)
- Centraliza a lógica de criação de objetos complexos

**Quais benefícios ele traz:**
- **Flexibilidade:** Novos tipos de pedidos podem ser adicionados facilmente
- **Manutenibilidade:** Mudanças na criação de pedidos ficam isoladas nas factories
- **Testabilidade:** Facilita a criação de mocks para testes
- **Desacoplamento:** Cliente não depende de classes concretas

**Como o código seria diferente sem o padrão:**
```java
// SEM Factory Method - código acoplado e difícil de manter
Order order;
if (type.equals("dine-in")) {
    order = new DineInOrder(id, customer);
} else if (type.equals("takeaway")) {
    order = new TakeawayOrder(id, customer);
} else if (type.equals("delivery")) {
    order = new DeliveryOrder(id, customer, address);
}
// Código cliente precisa conhecer todas as classes concretas
// Adicionar novo tipo requer modificar este código em vários lugares
```

---

### 2. **Observer** (Padrão Comportamental)

**Localização no código:**
- `src/main/java/com/restaurant/observer/Subject.java` - Interface Subject
- `src/main/java/com/restaurant/observer/Observer.java` - Interface Observer
- `src/main/java/com/restaurant/model/Order.java` - Concrete Subject
- `src/main/java/com/restaurant/observer/KitchenObserver.java` - Concrete Observer
- `src/main/java/com/restaurant/observer/WaiterObserver.java` - Concrete Observer
- `src/main/java/com/restaurant/observer/PaymentSystemObserver.java` - Concrete Observer

**Por que foi escolhido:**
Em um restaurante, múltiplos sistemas precisam ser notificados sobre mudanças no status dos pedidos (cozinha, garçons, sistema de pagamento). O Observer permite essa comunicação sem acoplamento direto.

**Qual problema ele resolve:**
- Evita dependências diretas entre o pedido e os sistemas que precisam ser notificados
- Permite adicionar ou remover observadores dinamicamente
- Implementa o princípio de responsabilidade única (cada observer tem sua lógica específica)

**Quais benefícios ele traz:**
- **Desacoplamento:** Pedidos não conhecem os observadores concretos
- **Extensibilidade:** Novos observadores podem ser adicionados sem modificar Order
- **Reatividade:** Mudanças são propagadas automaticamente
- **Flexibilidade:** Observers podem se registrar/desregistrar em tempo de execução

**Como o código seria diferente sem o padrão:**
```java
// SEM Observer - acoplamento forte e código rígido
public class Order {
    private Kitchen kitchen;
    private Waiter waiter;
    private PaymentSystem payment;
    
    public void setStatus(OrderStatus status) {
        this.status = status;
        // Chamadas diretas - acoplamento forte
        kitchen.notifyStatusChange(this);
        waiter.notifyStatusChange(this);
        payment.notifyStatusChange(this);
        // Adicionar novo sistema requer modificar esta classe
    }
}
```

---

### 3. **Strategy** (Padrão Comportamental)

**Localização no código:**
- `src/main/java/com/restaurant/strategy/PricingStrategy.java` - Interface Strategy
- `src/main/java/com/restaurant/strategy/RegularPricingStrategy.java` - Concrete Strategy
- `src/main/java/com/restaurant/strategy/HappyHourPricingStrategy.java` - Concrete Strategy
- `src/main/java/com/restaurant/strategy/CouponPricingStrategy.java` - Concrete Strategy
- `src/main/java/com/restaurant/strategy/LoyaltyPricingStrategy.java` - Concrete Strategy
- `src/main/java/com/restaurant/service/OrderService.java` - Context

**Por que foi escolhido:**
Restaurantes possuem diferentes estratégias de precificação (preço regular, happy hour, cupons, programa de fidelidade). O Strategy permite trocar o algoritmo de cálculo em tempo de execução.

**Qual problema ele resolve:**
- Elimina condicionais complexas (if/else ou switch) para diferentes cálculos
- Permite adicionar novas estratégias sem modificar código existente
- Facilita a troca de algoritmos em tempo de execução

**Quais benefícios ele traz:**
- **Flexibilidade:** Estratégias podem ser trocadas dinamicamente
- **Manutenibilidade:** Cada estratégia está isolada em sua própria classe
- **Testabilidade:** Cada estratégia pode ser testada independentemente
- **Clareza:** Código mais limpo sem condicionais complexas

**Como o código seria diferente sem o padrão:**
```java
// SEM Strategy - código com condicionais complexas
public double calculateFinalPrice(Order order, String discountType, 
                                  String coupon, int loyaltyPoints) {
    double price = order.getTotalPrice();
    
    if (discountType.equals("happy-hour")) {
        price = price * 0.80;
    } else if (discountType.equals("coupon")) {
        if (coupon.equals("BEMVINDO")) {
            price -= 10.00;
        } else if (coupon.equals("FIDELIDADE")) {
            price -= 15.00;
        }
    } else if (discountType.equals("loyalty")) {
        double discount = (loyaltyPoints / 100) * 0.05;
        price = price * (1 - Math.min(discount, 0.30));
    }
    // Adicionar nova estratégia requer modificar este método
    return price;
}
```

---

### 4. **Decorator** (Padrão Estrutural)

**Localização no código:**
- `src/main/java/com/restaurant/model/MenuItem.java` - Component interface
- `src/main/java/com/restaurant/decorator/BasicMenuItem.java` - Concrete Component
- `src/main/java/com/restaurant/decorator/MenuItemDecorator.java` - Decorator abstrato
- `src/main/java/com/restaurant/decorator/ExtraCheeseDecorator.java` - Concrete Decorator
- `src/main/java/com/restaurant/decorator/BaconDecorator.java` - Concrete Decorator
- `src/main/java/com/restaurant/decorator/SpecialSauceDecorator.java` - Concrete Decorator
- `src/main/java/com/restaurant/decorator/ExtraPortionDecorator.java` - Concrete Decorator

**Por que foi escolhido:**
Clientes frequentemente querem customizar pratos adicionando extras (queijo, bacon, molhos). O Decorator permite adicionar funcionalidades dinamicamente sem criar uma explosão de subclasses.

**Qual problema ele resolve:**
- Evita a criação de inúmeras subclasses para cada combinação possível
- Permite adicionar múltiplas funcionalidades de forma flexível
- Mantém o princípio Open/Closed (aberto para extensão, fechado para modificação)

**Quais benefícios ele traz:**
- **Flexibilidade:** Extras podem ser combinados de qualquer forma
- **Composição:** Decorators podem ser empilhados infinitamente
- **Reutilização:** Cada decorator é independente e reutilizável
- **Escalabilidade:** Novos extras não requerem modificar código existente

**Como o código seria diferente sem o padrão:**
```java
// SEM Decorator - explosão de subclasses
class Burger { }
class BurgerWithCheese extends Burger { }
class BurgerWithBacon extends Burger { }
class BurgerWithCheeseAndBacon extends Burger { }
class BurgerWithCheeseAndBaconAndSauce extends Burger { }
class BurgerWithCheeseAndSauce extends Burger { }
class BurgerWithBaconAndSauce extends Burger { }
// Cada combinação requer uma nova classe!
// Com 5 extras possíveis, teríamos 2^5 = 32 classes!
```

---

### 5. **Singleton** (Padrão Criacional)

**Localização no código:**
- `src/main/java/com/restaurant/singleton/RestaurantConfig.java` - Singleton

**Por que foi escolhido:**
As configurações do restaurante (nome, endereço, telefone, gerador de IDs) devem ser únicas e acessíveis globalmente. O Singleton garante uma única instância compartilhada.

**Qual problema ele resolve:**
- Garante que existe apenas uma instância das configurações
- Fornece um ponto de acesso global
- Controla a criação e o ciclo de vida da instância
- Garante thread-safety na geração de IDs sequenciais

**Quais benefícios ele traz:**
- **Consistência:** Todos acessam as mesmas configurações
- **Economia de recursos:** Apenas uma instância na memória
- **Controle:** Acesso centralizado e controlado
- **Thread-safety:** Implementação segura para ambientes concorrentes

**Como o código seria diferente sem o padrão:**
```java
// SEM Singleton - múltiplas instâncias e inconsistências
public class RestaurantConfig {
    private int nextOrderId = 1;
    
    public RestaurantConfig() { }
    
    public int generateOrderId() {
        return nextOrderId++;
    }
}

// Em diferentes partes do código:
RestaurantConfig config1 = new RestaurantConfig(); // nextOrderId = 1
RestaurantConfig config2 = new RestaurantConfig(); // nextOrderId = 1
// Ambos geram IDs duplicados! Inconsistência de dados!
// Configurações diferentes em cada instância
```
### Execução Direta (sem Maven)

```bash
# Compilar
javac -d target/classes -sourcepath src/main/java src/main/java/com/restaurant/Main.java

# Executar
java -cp target/classes com.restaurant.Main
```

### Princípios SOLID Aplicados

1. **Single Responsibility Principle (SRP)**
   - Cada classe tem uma única responsabilidade
   - Observers têm responsabilidades específicas (cozinha, garçom, pagamento)

2. **Open/Closed Principle (OCP)**
   - Sistema aberto para extensão (novos tipos de pedidos, estratégias, decorators)
   - Fechado para modificação (não precisa alterar código existente)

3. **Liskov Substitution Principle (LSP)**
   - Subclasses de Order podem substituir a classe base
   - Todas as estratégias implementam a mesma interface

4. **Interface Segregation Principle (ISP)**
   - Interfaces pequenas e específicas (Observer, Subject, PricingStrategy)

5. **Dependency Inversion Principle (DIP)**
   - Código depende de abstrações (interfaces), não de implementações concretas
