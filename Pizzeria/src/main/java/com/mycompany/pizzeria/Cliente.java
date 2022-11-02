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
    
    protected String id; 
    protected boolean atendido; 
    protected String comidaFavorita;
    protected String bebidaFavorita;

    public Cliente() {
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
