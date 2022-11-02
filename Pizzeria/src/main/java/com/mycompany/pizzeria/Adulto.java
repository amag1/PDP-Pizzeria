/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeria;

/**
 * Subclase de Cliente para instanciar Adultos
 * @author longh
 */
public class Adulto extends Cliente {
    public Adulto (){
        super();
        this.id = "Padre";
        this.comidaFavorita = "Hamburguesa";
        this.bebidaFavorita = "Cerveza";
    }
}