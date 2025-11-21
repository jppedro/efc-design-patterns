package com.restaurant.singleton;

/**
 * PADRÃO SINGLETON
 * 
 * Gerencia as configurações globais do restaurante
 * Garante que existe apenas uma instância das configurações em toda a aplicação
 * 
 * Implementação Thread-Safe usando Initialization-on-demand holder idiom
 */
public class RestaurantConfig {
    // Configurações do restaurante
    private String restaurantName;
    private String address;
    private String phone;
    private boolean isOpen;
    private int nextOrderId;

    /**
     * Holder estático interno - carregado apenas quando getInstance() é chamado
     * Garante thread-safety sem necessidade de sincronização
     */
    private static class SingletonHolder {
        private static final RestaurantConfig INSTANCE = new RestaurantConfig();
    }

    /**
     * Construtor privado - previne instanciação externa
     */
    private RestaurantConfig() {
        this.restaurantName = "Restaurante Padrões de Projeto";
        this.address = "Rua dos Devs, 999";
        this.phone = "(99) 99999-9999";
        this.isOpen = true;
        this.nextOrderId = 1;
    }

    /**
     * Método público para obter a única instância
     */
    public static RestaurantConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Gera o próximo ID de pedido de forma thread-safe
     */
    public synchronized int generateOrderId() {
        return nextOrderId++;
    }

    // Getters e Setters
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void displayConfig() {
        System.out.println("\n=== Configurações do Restaurante ===");
        System.out.println("Nome: " + restaurantName);
        System.out.println("Endereço: " + address);
        System.out.println("Telefone: " + phone);
        System.out.println("Status: " + (isOpen ? "ABERTO" : "FECHADO"));
        System.out.println("Próximo ID de Pedido: " + nextOrderId);
    }

    /**
     * Previne clonagem da instância Singleton
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Não é possível clonar um Singleton");
    }
}
