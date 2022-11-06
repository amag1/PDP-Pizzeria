/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientes;

/**
 * Subclase de Cliente para instanciar Adultos
 * @author longh
 */
public class Adulto extends Cliente {
    /**
     * Constructor para crear un nuevo adulto.
     * @param type El identificador de dicho adulto.
     */
    public Adulto (String type, String favF,String favD){
        super();
        this.id = type;
        this.comidaFavorita = favF;
        this.bebidaFavorita = favD;
    }
}