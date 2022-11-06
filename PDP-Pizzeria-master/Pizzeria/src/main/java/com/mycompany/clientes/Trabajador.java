package com.mycompany.clientes;

/**
 * Clase que simula un cliente de tipo trabajador
 * @author andres
 */
public class Trabajador extends Cliente{
    /**
     * Constructor para trabajador
     */
    public Trabajador (){
        super();
        this.id = "Trabajador";
        this.comidaFavorita = "Pasta";
        this.bebidaFavorita = "Gaseosa";
    }
}
