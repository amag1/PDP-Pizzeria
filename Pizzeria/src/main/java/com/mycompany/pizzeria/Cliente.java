package com.mycompany.pizzeria;

import java.util.ArrayList;
import java.util.Random;



/**
 * Clase para representar clientes
 * Cada cliente tiente constantes nombre y comidas y bebidas favoritas
 * Tambien tiene un atributo de instancia indicando si esta atendido
 * @author andres
 */
abstract class Cliente {
    
    protected final String id; 
    protected boolean atendido; 
    protected String comidaFavorita;
    protected String bebidaFavorita;

    /**
     * Crea un cliente dadas sus comidas favoritas y nombre
     * @param id Nombre del cliente
     * @param comidaFavorita String que representa la comida favorita
     * @param bebidaFavorita String que representa la bebida favorita
     * @param atendido Boolean que indica si esta atendido o no
     */
    public Cliente(String id, String comidaFavorita, String bebidaFavorita) {
        this.id = id;
        this.comidaFavorita = comidaFavorita;
        this.bebidaFavorita = bebidaFavorita;
        this.atendido = false;
    }
    
    
    
    public String getId() {
        return id;
    }

    public boolean isAtendido() {
        return atendido;
    }


    public String getComidaFavorita() {
        return comidaFavorita;
    }

    public String getBebidaFavorita() {
        return bebidaFavorita;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }
    
    
    
    
    
}
