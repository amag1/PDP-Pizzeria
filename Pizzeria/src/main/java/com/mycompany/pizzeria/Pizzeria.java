package com.mycompany.pizzeria;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * Clase muy general que representa a la pizzeria.
 * La pizzeria esta formada por listas de Mesas, Clientes, y Empleados.
 * @author andres
 */
public class Pizzeria {

    private LinkedList<Mesa> listaMesas;
    private LinkedList<ArrayList<Cliente>> listaEsperando;
    private ArrayList<Mesero> listaMeseros;
    private ArrayList<Comida> menuComida;
    private ArrayList<Bebida> menuBebida;
    private Cocina cocinaPizzeria;
    

    public Pizzeria(LinkedList<Mesa> listaMesas, ArrayList<Mesero> listaEmpleados, ArrayList<Comida> menuComida, ArrayList<Bebida> menuBebida) {
        this.listaMesas = listaMesas;
        this.listaMeseros = listaEmpleados;
        this.menuComida = menuComida;
        this.menuBebida = menuBebida;
        this.listaEsperando = new LinkedList();
    }
    
    /**
     * Metodo para acomodar clientes adentro del local
     * Escanea la lista de mesas y cuando halla una vacia, inserta a los primeros clientes de la cola alli
     * 
     */
    public void acomodarClientes() {
        //iterador para listaesperando
        Iterator it = listaEsperando.iterator();
        
        Collections.sort(listaMesas);
        
        for (Mesa m : listaMesas){
            
            if (!m.isOcupada()){
                
                ArrayList<Cliente> i = (ArrayList<Cliente>) it.next();
                
                while (it.hasNext() && i.size() > m.getCapacidad()){
                    i = (ArrayList<Cliente>) it.next();
                }
                
                //finalmente actualiza el status de la mesa y remueve a los clientes de la cola
                m.setListaClientes(i);
                m.setOcupada(true);
                listaEsperando.remove(i);
                
            }
        }
    }

    public LinkedList<Mesa> getListaMesas() {
        return listaMesas;
    }
    
    /**
     * Dada una mesa, la "reinicia" marcando que esta desocupada y eliminando los clientes
     * @param m La mesa a desocupar
     */
    public void vaciarMesa(Mesa m){
        m.setOcupada(false);
        m.setListaClientes(new ArrayList());
    }
    
    /**
     * Agrega un nuevo grupo de clientes a la cola
     * @param l El grupo de clientes que debemos agregar
     */
    public void addToListaClientes(ArrayList<Cliente> l){
        this.listaEsperando.add(l);
    }
    
    
    public Mesero getMeseroLibre(){
        for (Mesero m : listaMeseros){
            if (!m.ocupado)
                m.setOcupado(true);
                return m;
        }
        
        return null;
    }
    
    public void tomarPedidos(Mesero m){
        
        for (Mesa mesa : listaMesas){
            if (!mesa.isOcupada())
                m.tomarOrden(mesa, cocinaPizzeria);
        }
    }
    
    public CocineroJefe getCocineroLibre(){
        
        for (CocineroJefe cocinero : this.cocinaPizzeria.getCocinerosJ()){
            if (!cocinero.isOcupado())
                return cocinero;
        }
        
        return null;
    }
    
    //public void updateCocina()
}
