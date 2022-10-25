package com.mycompany.pizzeria;

import java.util.Random;



/**
 *
 * @author andres
 */
public class Cliente {
    
    private String id;
    private Comida comidaFavorita;
    private Bebida bebidaFavorita;
    private boolean atendido;
    private Menu menu;
    
    public Cliente(String id, Comida comidaFavorita, Bebida bebidaFavorita, boolean atendido) {
        this.id = id;
        this.comidaFavorita = comidaFavorita;
        this.bebidaFavorita = bebidaFavorita;
        this.atendido = atendido;
    }
    
    //obtiene una comida aleatoria del menu
    public Comida pedirComida(){
        
        Random rand = new Random();
        
        return this.menu.getListaComidas().get(rand.nextInt(this.menu.getListaComidas().size()));
    }
    
    //obtiene una bebida aleatoria del menu
    public Bebida pedirBebida(){
        
        Random rand = new Random();
        
        return this.menu.getListaBebidas().get(rand.nextInt(this.menu.getListaBebidas().size()));
    }
    
    public String getId() {
        return id;
    }

    public Comida getComidaFavorita() {
        return comidaFavorita;
    }

    public Bebida getBebidaFavorita() {
        return bebidaFavorita;
    }

    public boolean isAtendido() {
        return atendido;
    }
    
    
    
}
