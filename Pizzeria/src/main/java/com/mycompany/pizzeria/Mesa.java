package com.mycompany.pizzeria;

import java.util.ArrayList;
import com.mycompany.clientes.Cliente;

/**
 * Clase que contiene la represnetacion de una mesa.
 * Constantes para su numero y capacidad.
 * Atributos de instancia para su status y los clientes que contiene.
 * Implementa la interfaz Comparable para poder ordenar una lista de Mesas.
 * @author andres
 */
public class Mesa implements Comparable<Mesa> {
    
    private final int numero;
    private final int capacidad; //puede ser 2 o 4
    private boolean ocupada;
    private ArrayList<Cliente> listaClientes;
    private boolean atendida;
    private int tiempoEspera;
    private int tiempoConsumo;
    
    /**
     * Construye una mesa vacia dados un numero y su capacidad.
     * @param numero Numero de mesa.
     * @param capacidad Capacidad de la mesa (en este caso puede ser 2 o 4).
     */
    public Mesa(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ocupada = false;
        this.listaClientes = new ArrayList();
        this.tiempoEspera = 0;
        
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    
    public int getNumero() {
        return numero;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public int getTiempoConsumo() {
        return tiempoConsumo;
    }

    public void setTiempoConsumo(int tiempoConsumo) {
        this.tiempoConsumo = tiempoConsumo;
    }
    
    /**
     * Implementacion del metodo abstracto compareTo.
     * @param t La mesa a la que deseamos comparar la actual.
     * @return 0, si ambas mesas tienen la misma capacidad, > 0, si la mesa actual es mas grande que t, y < 0 de otro modo.
     */
    @Override
    public int compareTo(Mesa t) {
        
        return this.getCapacidad() - t.getCapacidad();
    }
    
    
}
