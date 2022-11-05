package com.mycompany.pizzeria;

import java.util.List;
import java.util.LinkedList;

/**
 * Clase que representa una orden dentro de la pizzeria.
 * Contiene informacion sobre comidas y bebidas pedidas, la mesa asociada, el precio
 * y enteros con la hora de pedido, cocina y entrega.
 * @author andres
 */
public class Orden {
    
    private int numMesa;
    private LinkedList<Comida> listaComidas;
    private LinkedList<Bebida> listaBebidas;
    private int precio;
    private int horaPedido;
    private int horaEntrega;
    private int tiempoConsumo;
    private boolean preparada;
    private int tiempoPreparacion;

    /**
     * Dada una mesa, inicializa una orden vacia.
     * @param numMesa El numero de la mesa que hizo la orden.
     */
    public Orden(int numMesa) {
        this.numMesa = numMesa;
        this.listaComidas = new LinkedList();
        this.listaBebidas = new LinkedList();
        this.precio = 0;
        this.horaPedido = 0;
        this.horaEntrega = 0;
        this.tiempoConsumo = 0;
    }
    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public boolean isPreparada() {
        return preparada;
    }

    public void setPreparada(boolean preparada) {
        this.preparada = preparada;
    }
    
    
    public int getTiempoConsumo() {
        return tiempoConsumo;
    }

    public void setTiempoConsumo(int tiempoConsumo) {
        this.tiempoConsumo = tiempoConsumo;
    }
    
    

    public List<Comida> getListaComidas() {
        return listaComidas;
    }

    public void setListaComidas(LinkedList<Comida> listaComidas) {
        this.listaComidas = listaComidas;
    }
    
    public void addToListaComidas(Comida c){
        this.listaComidas.add(c);
    }
    
    public List<Bebida> getListaBebidas() {
        return listaBebidas;
    }

    public void setListaBebidas(LinkedList<Bebida> listaBebidas) {
        this.listaBebidas = listaBebidas;
    }
    
    public void addToListaBebidas(Bebida b){
        this.listaBebidas.add(b);
    }
    

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(int horaPedido) {
        this.horaPedido = horaPedido;
    }

    public int getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(int horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }
    
    
    
    
}
