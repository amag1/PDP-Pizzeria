package com.mycompany.pizzeria;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import com.mycompany.clientes.Cliente;

/**
 * Clase muy general que representa a la pizzeria.
 * La pizzeria esta formada por listas de Mesas, Clientes, y Empleados.
 * @author andres
 */
public class Pizzeria implements PartePizzeria{

    private LinkedList<Mesa> listaMesas;
    private LinkedList<ArrayList<Cliente>> listaEsperando;
    private ArrayList<Mesero> listaMeseros;
    private ArrayList<CocineroAyudante> listaCocineros;
    private ArrayList<Comida> menuComida;
    private ArrayList<Bebida> menuBebida;
    private Cocina cocinaPizzeria;
    private int totalDemora;
    private int totalMesas;
    private int totalacomodados;
    

    public Pizzeria(LinkedList<Mesa> listaMesas, ArrayList<Mesero> listaMeseros, ArrayList<CocineroAyudante> listaCocineros, ArrayList<Comida> menuComida, ArrayList<Bebida> menuBebida, Cocina c) {
        this.listaMesas = listaMesas;
        this.listaMeseros = listaMeseros;
        this.listaCocineros = listaCocineros;
        this.menuComida = menuComida;
        this.menuBebida = menuBebida;
        this.listaEsperando = new LinkedList();
        this.cocinaPizzeria = c;
        this.totalacomodados = 0;
    }

    public int getTotalacomodados() {
        return totalacomodados;
    }
    
    
    /**
     * Metodo para acomodar clientes adentro del local
     * Escanea la lista de mesas y cuando halla una vacia, inserta a los primeros clientes de la cola alli
     * 
     */
    public void acomodarClientes() {
        totalacomodados++;
        //iterador para listaesperando
        Iterator<ArrayList<Cliente>> it = listaEsperando.iterator();
        
        Collections.sort(listaMesas);
        
        for (Mesa m : listaMesas){
            
            if (!m.isOcupada()){
                
                boolean flag = true; 
                
                
                while (it.hasNext() && flag){
                    ArrayList<Cliente> i = it.next();
                    if (i.size() <= m.getCapacidad()){
                        it.remove();
                        //finalmente actualiza el status de la mesa y remueve a los clientes de la cola
                        m.setListaClientes(i);
                        m.setOcupada(true);
                        m.setAtendida(false);
                    }
                }
                
                
                
                
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
        m.setAtendida(false);
        m.setListaClientes(new ArrayList());
        m.setTiempoConsumo(0);
        System.out.println(m.getTiempoEspera());
        this.totalDemora += m.getTiempoEspera();
        m.setTiempoEspera(0);
        
        this.totalMesas++;
        
    }
    
    /**
     * Agrega un nuevo grupo de clientes a la cola
     * @param l El grupo de clientes que debemos agregar
     */
    public void addToListaClientes(ArrayList<Cliente> l){
        this.listaEsperando.add(l);
    }
    
    public boolean thereAreClients(){
        return (!this.listaEsperando.isEmpty());
    }
    
   
    
    public void tomarPedidos(int tiempoActual){
        for (Mesero m : listaMeseros){
            
          if (!m.isOcupado()){
              
          Iterator<Mesa>  iter = listaMesas.iterator();
          
          boolean flag = true; 
          
          while (iter.hasNext() & flag){
              
            Mesa i = iter.next();
            
            if (i.isOcupada() && !i.isAtendida()){
                i.setAtendida(true);
                m.tomarOrden(tiempoActual,i, cocinaPizzeria,menuComida,menuBebida);
                flag = false;
            }
            
                
        }  
        }
        }
        
    }
    
    public void entregarPedidos(int tiempoActual){
        
        for (Mesero mesero : listaMeseros){
            
            if (!mesero.isOcupado()){
                
                if (cocinaPizzeria.getListaEntregas().size() > 0){
                    
                    Orden ord = cocinaPizzeria.getFirstListaEntregas();
                    Mesa m = hallarMesa(ord);
                    cocinaPizzeria.removeFirstListaEntregas();
                    ord.setHoraEntrega(tiempoActual);
                    mesero.entregarOrden(ord, m);
                }
            }
        }
    }
    
    public Mesa hallarMesa(Orden ord){
        
        for (Mesa m : listaMesas){
            if (m.getNumero() == ord.getNumMesa())
                return m;
        }
        
        return null;
    }
    
    public CocineroJefe getCocineroLibre(){
        
        for (CocineroJefe cocinero : this.cocinaPizzeria.getCocinerosJ()){
            if (!cocinero.isOcupado())
                return cocinero;
        }
        
        return null;
    }
    
    public void updateMesas(){
        for (Mesa mesa : listaMesas){
            if (mesa.isOcupada() && mesa.getTiempoEspera() != 0){
            if (mesa.getTiempoConsumo() > 0)
                mesa.setTiempoConsumo(mesa.getTiempoConsumo() - 1);
            else{
                System.out.println("mesavacia");
                vaciarMesa(mesa);}
            
            }
            
        }
    }
    
    public void resetMeseros(){
        for (Mesero m : listaMeseros){
            m.setOcupado(false);
        }
    }
    
    public void cocinar(){
        this.cocinaPizzeria.hacerPreparaciones();
        this.cocinaPizzeria.cocinarTurnoActual();
    }

    public int getTotalDemora() {
        return totalDemora;
    }

    public void setTotalDemora(int totalDemora) {
        this.totalDemora = totalDemora;
    }

    public int getTotalMesas() {
        return totalMesas;
    }

    public void setTotalMesas(int totalMesas) {
        this.totalMesas = totalMesas;
    }

    @Override
    public void mostrarEstado() {
        int desocupadas = 0;
        for (Mesa m : this.listaMesas){
            if (m.isOcupada() == false){
                desocupadas++;
            }
        }
        
        System.out.println("Actualmente hay " + desocupadas + " mesas desocupadas");
    }

    @Override
    public void mostrarTotales() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
