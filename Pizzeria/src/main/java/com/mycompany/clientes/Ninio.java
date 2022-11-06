/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientes;

/**
 * Subclase de Cliente para instanciar Ninios
 * @author longh
 */
public class Ninio extends Cliente {
    /**
     * Construye un ninio generico.
     */
    public Ninio (){
        super();
        this.id = "Ninio";
        this.comidaFavorita = "Hamburguesa";
        this.bebidaFavorita = "Gaseosa";
    }
}
