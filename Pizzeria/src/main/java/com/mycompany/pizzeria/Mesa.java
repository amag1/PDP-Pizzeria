/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeria;

import java.util.ArrayList;

/**
 *
 * @author andres
 */
public class Mesa {
    
    private int numero;
    private int capacidad; //puede ser 2 o 4
    private boolean ocupada;
    private ArrayList<Cliente> listaClientes;

    public Mesa(int numero, int capacidad, boolean ocupada, ArrayList<Cliente> listaClientes) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ocupada = ocupada;
        this.listaClientes = listaClientes;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
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
    
    
    
}
