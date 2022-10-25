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
public class Menu {
    
    private List<Comida> listaComidas;
    private List<Bebida> listaBebidas;

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

    public Menu(List<Comida> listaComidas, List<Bebida> listaBebidas) {
        this.listaComidas = listaComidas;
        this.listaBebidas = listaBebidas;
    }
    
    
}
