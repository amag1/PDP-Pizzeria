/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeria;

import java.util.List;

/**
 *
 * @author andres
 */
public class Orden {
    
    private Mesa mesa;
    private List<Comida> listaComidas;
    private List<Bebida> listaBebidas;
    private int precio;
    
    public Orden(Mesa mesa, List<Comida> listaComidas, List<Bebida> listaBebidas, int precio) {
        this.mesa = mesa;
        this.listaComidas = listaComidas;
        this.listaBebidas = listaBebidas;
        this.precio = precio;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<Comida> getListaComidas() {
        return listaComidas;
    }

    public void setListaComidas(List<Comida> listaComidas) {
        this.listaComidas = listaComidas;
    }

    public List<Bebida> getListaBebidas() {
        return listaBebidas;
    }

    public void setListaBebidas(List<Bebida> listaBebidas) {
        this.listaBebidas = listaBebidas;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
}
