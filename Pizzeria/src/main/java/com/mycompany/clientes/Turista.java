/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientes;

/**
 * Subclase de Cliente para instanciar Turistas
 * @author longh
 */
public class Turista extends Cliente {
    /**
     * Constructor para turista.
     */
    public Turista (){
        super();
        this.id = "Turista";
        this.comidaFavorita = "Pizza";
        this.bebidaFavorita = "Cerveza";
    }
}
