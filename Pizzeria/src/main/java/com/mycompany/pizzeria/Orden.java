package com.mycompany.pizzeria;

import java.util.List;

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

    public Orden(Mesa mesa) {
        this.mesa = mesa;
        this.precio = 0;
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
    
    public void addToListaComidas(Comida com){
        this.listaComidas.add(com);
    }

    public List<Bebida> getListaBebidas() {
        return listaBebidas;
    }

    public void setListaBebidas(List<Bebida> listaBebidas) {
        this.listaBebidas = listaBebidas;
    }
    
    public void addToListaBebidas(Bebida beb){
        this.listaBebidas.add(beb);
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
}
