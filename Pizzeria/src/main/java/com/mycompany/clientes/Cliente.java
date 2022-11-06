package com.mycompany.clientes;

/**
 * Clase abstracta para representar clientes
 * Cada cliente tiente constantes nombre y comidas y bebidas favoritas
 * Tambien tiene un atributo de instancia indicando si esta atendido
 * @author andres
 */
public abstract class Cliente {
    
    protected String id; 
    protected String comidaFavorita;
    protected String bebidaFavorita;
    
    public Cliente() {
    }

    public String getId() {
        return id;
    }

    public String getComidaFavorita() {
        return comidaFavorita;
    }

    public String getBebidaFavorita() {
        return bebidaFavorita;
    }

    
    
    
    
    
}
