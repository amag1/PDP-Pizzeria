package com.mycompany.pizzeria;

import com.mycompany.clientes.Cliente;
import com.mycompany.clientes.Ninio;
import com.mycompany.clientes.Adulto;
import com.mycompany.clientes.Estudiante;
import com.mycompany.clientes.Turista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;


/**
 * Clase principal: contiene al metodo main. Desde aqui debe inicializarse la simulacion
 * @author andres
 */
public class Simulacion {
    private boolean findeSemana;
    private Pizzeria laTana;
            
    public static void main(String[] args) {
        Simulacion Control = new Simulacion();
        Random r = new Random();
        
        LinkedList<Mesa> listaMesas = Control.innitMesas();       
        HashMap<String, Integer> ingredientes = Control.innitIngredientes();
        ArrayList<Mesero> listaMeseros = Control.innitMeseros();
        ArrayList<CocineroAyudante> listaCocineros = Control.innitCocineros();
        ArrayList<Comida> menuComida = Control.innitMenuComida();
        ArrayList<Bebida> menuBebida = Control.innitMenuBebida();
        
        Cocina cocina = new Cocina(ingredientes);
        int nextGroupTimeStamp=0;
        int cantMov=0;
        Control.laTana = new Pizzeria(listaMesas,listaMeseros,listaCocineros,menuComida,menuBebida);
        
        for (int i=0; i<240;i++){
            if (i>=nextGroupTimeStamp && cantMov<30){
                nextGroupTimeStamp=r.nextInt(8)+nextGroupTimeStamp;
                cantMov++;
                Control.laTana.addToListaClientes(Control.generateClientes());    
            }
            if (Control.laTana.thereAreClients())
                Control.laTana.acomodarClientes();
            
        }  
    }
    
    public ArrayList<Cliente> generateClientes(){
        ArrayList<Cliente> grupo = new ArrayList<Cliente>();
        Random r = new Random();
        
        int seed= r.nextInt(6)+1;
        if (findeSemana){
            switch(seed){
                case 1:
                    for (int i=0;i<=r.nextInt(1);i++)
                        
                        grupo.add(new Ninio());
                    for (int i=0;i<=r.nextInt(1);i++)
                        grupo.add(new Adulto("Padre"));
                    break;
                case 2:
                    for (int i=0;i<=r.nextInt(1);i++)
                        grupo.add(new Adulto("Adulto"));
                    break;
                case 3:
                    for (int i=0;i<=r.nextInt(3);i++)
                        grupo.add(new Estudiante());
                    break;
                default:
                    for (int i=0;i<=r.nextInt(3);i++)
                        grupo.add(new Turista());
                    break;
            }
        }
        else
            switch(seed){
                case 1:
                    for (int i=0;i<=r.nextInt(1);i++)
                        grupo.add(new Ninio());
                    for (int i=0;i<=r.nextInt(1);i++)
                        grupo.add(new Adulto("Padre"));
                    break;
                case 2:
                    for (int i=0;i<=r.nextInt(3);i++)
                        grupo.add(new Adulto("Adulto"));
                    break;
                case 3:
                    for (int i=0;i<=r.nextInt(3);i++)
                        grupo.add(new Adulto("Adulto"));
                    break;
                case 4:
                    for (int i=0;i<=r.nextInt(3);i++)
                        grupo.add(new Estudiante());
                    break;
                case 5:
                    for (int i=0;i<=r.nextInt(3);i++)
                        grupo.add(new Estudiante());
                    break;
                default:
                    for (int i=0;i<=r.nextInt(2);i++)
                        grupo.add(new Turista());
                    break;
            }
        return grupo;
    }
    
    public LinkedList<Mesa> innitMesas(){
        LinkedList<Mesa> listaMesas = new LinkedList<Mesa>();
        for (int i=1; i<=8;i++)
            listaMesas.add(new Mesa(i, 2));
        for (int i=9; i<=24;i++)
            listaMesas.add(new Mesa(i, 4));
        return listaMesas;
    }    
    public HashMap<String, Integer> innitIngredientes(){
        HashMap<String, Integer> ingredientes = new HashMap<String,Integer>();
        ingredientes.put("Prepizza",50);
        ingredientes.put("Cebolla",50);
        ingredientes.put("Queso",50);
        ingredientes.put("Pan",50);
        ingredientes.put("Carne molida",50);
        ingredientes.put("Condimentos",50);
        ingredientes.put("Tomate",50);
        ingredientes.put("Lechuga",50);
        ingredientes.put("Huevo",50);
        return ingredientes;
    }    
    public ArrayList<Mesero> innitMeseros(){
        ArrayList<Mesero> listaMeseros = new ArrayList<Mesero>();
        listaMeseros.add(new Mesero("Tonio"));
        listaMeseros.add(new Mesero("Stella"));
        listaMeseros.add(new Mesero("Maria"));
        return listaMeseros;
    }
    public ArrayList<CocineroAyudante> innitCocineros(){
        ArrayList<CocineroAyudante> listaCocineros = new ArrayList<CocineroAyudante>();
        listaCocineros.add(new CocineroJefe("Pietro"));
        listaCocineros.add(new CocineroAyudante("Anna"));
        listaCocineros.add(new CocineroAyudante("Giulia"));
        listaCocineros.add(new CocineroAyudante("Stefano"));
        return listaCocineros;
    }    
    public ArrayList<Comida> innitMenuComida(){
        ArrayList<Comida> menuComida = new ArrayList<Comida>();
        HashMap<String,Integer> ingPizza = new HashMap<String,Integer>();
        ingPizza.put("Prepizza",1);
        ingPizza.put("Cebolla",1);
        ingPizza.put("Queso",1);
        ingPizza.put("Condimentos",1);
        ingPizza.put("Tomate",1);
        ingPizza.put("Huevo",1);
        HashMap<String,Integer> ingBurger = new HashMap<String,Integer>();
        ingBurger.put("Cebolla",1);
        ingBurger.put("Queso",1);
        ingBurger.put("Pan",2);
        ingBurger.put("Carne molida",1);
        ingBurger.put("Condimentos",1);
        ingBurger.put("Tomate",1);
        ingBurger.put("Lechuga",1);
        ingBurger.put("Huevo",1);
        menuComida.add(new Comida("Pizza",ingPizza,20,30,5));
        menuComida.add(new Comida("Hamburguesa",ingBurger,15,15,3));
        return menuComida;
    }
    public ArrayList<Bebida> innitMenuBebida(){
        ArrayList<Bebida> menuBebida = new ArrayList<Bebida>();
        menuBebida.add(new Bebida("Gaseosa",15,1));
        menuBebida.add(new Bebida("Cerveza",20,2));
        return menuBebida;
    }
}
    
