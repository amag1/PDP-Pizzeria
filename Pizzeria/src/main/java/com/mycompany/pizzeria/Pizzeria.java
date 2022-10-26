package com.mycompany.pizzeria;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

public class Pizzeria {

    private LinkedList<Mesa> listaMesas;
    private ArrayList<Empleado> listaEmpleados;
    private LinkedList<ArrayList<Cliente>> listaEsperando;
    
    //Toma la lista de mesas y la de clientes esperando
    //Encuentra la primera mesa vacia y coloca el primer grupo que entre alli
    //Luego los clientes son eliminados
    public void acomodarClientes(){
        
        //iterador para listaesperando
        Iterator it = listaEsperando.iterator();
        
        for (Mesa m : listaMesas){
            
            if (!m.isOcupada()){
                
                ArrayList<Cliente> i = (ArrayList<Cliente>) it.next();
                
                while (it.hasNext() && i.size() > m.getCapacidad()){
                    i = (ArrayList<Cliente>) it.next();
                }
                
                m.setListaClientes(i);
                m.setOcupada(true);
                listaEsperando.remove(i);
                
            }
        }
    }
}
