package com.mycompany.pizzeria;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import com.mycompany.clientes.Cliente;
import java.util.HashMap;

/**
 * Clase muy general que representa a la pizzeria.
 * La pizzeria esta formada por listas de Mesas, Clientes, y Empleados.
 * Tambien posee el menu y diversos atributos para guardar el estado actual
 * @author andres
 */
public class Pizzeria implements PartePizzeria{

    private LinkedList<Mesa> listaMesas;
    private LinkedList<ArrayList<Cliente>> listaEsperando;
    private ArrayList<Mesero> listaMeseros;
    private ArrayList<CocineroAyudante> listaCocineros;
    private ArrayList<Comida> menuComida;
    private ArrayList<Bebida> menuBebida;
    private HashMap<String,Integer> tiposClientes;
    private Cajero cajero;
    private static Cocina cocinaPizzeria;
    private int totalDemora;
    private int totalMesas;


    
    /**
     * Constructor para pizzeria
     * @param cajero El cajero de la empresa.
     * @param listaMesas Lista de objetos tipo mesa.
     * @param listaMeseros Lista de objetos tipo Mesero.
     * @param listaCocineros Lista de objetos tipo Cocinero.
     * @param menuComida Lista de comidas que simulan el menu.
     * @param menuBebida Lista de bebidas que simulan el menu.
     * @param c Cocina asociada a la pizzeria.
     */
    public Pizzeria(Cajero cajero,LinkedList<Mesa> listaMesas, ArrayList<Mesero> listaMeseros, ArrayList<CocineroAyudante> listaCocineros, ArrayList<Comida> menuComida, ArrayList<Bebida> menuBebida, Cocina c) {
        this.listaMesas = listaMesas;
        this.listaMeseros = listaMeseros;
        this.listaCocineros = listaCocineros;
        this.menuComida = menuComida;
        this.menuBebida = menuBebida;
        this.listaEsperando = new LinkedList();
        this.cocinaPizzeria = c;
        this.cajero = cajero;
        this.tiposClientes = new HashMap<>();

    }

    public LinkedList<ArrayList<Cliente>> getListaEsperando() {
        return listaEsperando;
    }
    
    /**
     * Metodo para acomodar clientes adentro del local.
     * Escanea la lista de mesas y cuando halla una vacia, inserta a los primeros clientes de la cola alli.
    
     */
    public void acomodarClientes() {
        //iterador para listaesperando
        Iterator<ArrayList<Cliente>> it = listaEsperando.iterator();
        
        Collections.sort(listaMesas);
        
        for (Mesa m : listaMesas){
            
            if (!m.isOcupada() && !m.isAtendida()){
                
                boolean flag = true; 
                
                
                while (it.hasNext() && flag){
                    ArrayList<Cliente> i = it.next();
                    if (i.size() <= m.getCapacidad()){
                        it.remove();
                        //finalmente actualiza el status de la mesa y remueve a los clientes de la cola
                        m.setListaClientes(i);
                        m.setOcupada(true);
                        
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
    
    /**
     * Verifica si hay clientes esperando.
     * @return true, si los hay, false, si no los hay.
     */
    public boolean thereAreClients(){
        return (!this.listaEsperando.isEmpty());
    }
    
   
    /**
     * Le pide a los meseros que tomen ordenes.
     * Las ordenes son agregadas a la cocina.
     * @param tiempoActual El momento actual en el que tomamos las ordenes.
     */
    public void tomarPedidos(int tiempoActual){
        for (Mesero m : listaMeseros){
            
          if (!m.isOcupado()){
   
          Iterator<Mesa>  iter = listaMesas.iterator();
          
          boolean flag = true; 
          
          while (iter.hasNext() & flag){
              
            Mesa i = iter.next();
            
            if (i.isOcupada() && !i.isAtendida()){
                i.setAtendida(true);
                m.tomarOrden(tiposClientes, tiempoActual,i, cocinaPizzeria,menuComida,menuBebida);
                
                flag = false;
            }
            
                
        }  
        }
        }
        
    }
    
    /**
     * Le pide a los meseros que entreguen ordenes que ya estan preparados.
     * @param tiempoActual El momento del tiempo en el que estamos haciendo la entrega.
     */
    public void entregarPedidos(int tiempoActual){
        
        for (Mesero mesero : listaMeseros){
            
            if (!mesero.isOcupado()){
                
                if (!cocinaPizzeria.getListaEntregas().isEmpty()){
                    
                    Orden ord = cocinaPizzeria.getFirstListaEntregas();
                    Mesa m = hallarMesa(ord);
                    cocinaPizzeria.removeFirstListaEntregas();
                    ord.setHoraEntrega(tiempoActual);
                    mesero.entregarOrden(ord, m);
                    cajero.incrGanancias(ord.getPrecio());
                }
            }
        }
    }
    
    /**
     * Dada una orden, busca la mesa a la que esta asociada.
     * @param ord La orden a analizar.
     * @return La mesa que pidio dicha orden.
     */
    public Mesa hallarMesa(Orden ord){
        
        for (Mesa m : listaMesas){
            if (m.getNumero() == ord.getNumMesa())
                return m;
        }
        
        return null;
    }
    
    /**
     * Actualiza el estado de las mesas.
     * Si aun no han terminado, les descuenta el tiempo de consumo.
     * De otro modo, la vacia.
     */
    public void updateMesas(){
        for (Mesa mesa : listaMesas){
            if (mesa.isOcupada() && mesa.getTiempoEspera() != 0){
            if (mesa.getTiempoConsumo() > 0)
                mesa.setTiempoConsumo(mesa.getTiempoConsumo() - 1);
            else{
                vaciarMesa(mesa);}
            
            }
            
        }
    }
    /**
     * Reestablece todos los meseros para que esten desocupados
     */
    public void resetMeseros(){
        for (Mesero m : listaMeseros){
            m.setOcupado(false);
        }
    }
    
    /**
     * Se encarga de avisarle a la cocina que se debe cocinar.
     */
    public void cocinar(){
        Pizzeria.cocinaPizzeria.hacerPreparaciones();
        Pizzeria.cocinaPizzeria.cocinarTurnoActual();
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
    
    /**
     * Metodo de la interfaz partePizzeria
     * Muestra el estado actual
     */
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
    
    /**
     * Metodo de la interfaz partePizzeria
     * Muestra el estado final
     */
    @Override
    public void mostrarTotales() {
        System.out.println("Los tipos de clientes que asistieron fueron los siguientes: ");
        for (String key : tiposClientes.keySet()){
            int amt = tiposClientes.get(key);
            System.out.println(key + ": " + amt);
        }
        System.out.println("La demora promedio para cada mesa fue de " + totalDemora/totalMesas + " minutos");
        System.out.println("El gasto promedio de cada mesa fue de " + cajero.getTotalGanado()/totalMesas + " Euros");
    }
    
}
