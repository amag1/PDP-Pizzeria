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
import java.util.Scanner;


/**
 * Clase principal: contiene al metodo main. Desde aqui debe inicializarse la simulacion
 * @author andres
 */
public class Simulacion {
    private boolean findeSemana;
    private Pizzeria laTana;
            
    public static void main(String[] args) {
        
        Simulacion control = new Simulacion();
        Random r = new Random();
        
        //Inicializa las partes de una pizzeria random
        //Podria mejorarse para que el usuario elija la forma
        LinkedList<Mesa> listaMesas = control.innitMesas();       
        HashMap<String, Integer> ingredientes = control.innitIngredientes();
        ArrayList<Mesero> listaMeseros = control.innitMeseros();
        ArrayList<CocineroAyudante> listaCocineros = control.innitCocineros();
        ArrayList<Comida> menuComida = control.innitMenuComida();
        ArrayList<Bebida> menuBebida = control.innitMenuBebida();
        
        Cocina cocina = new Cocina(ingredientes,listaCocineros);
        
        
        //Se crea una pizzeria estandar
        control.laTana = new Pizzeria(listaMesas,listaMeseros,listaCocineros,menuComida,menuBebida,cocina);
        
        
        //TODO: menu
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Bienvenido a la pizzeria \"La Tana\"");
        System.out.println("Seleccione la opcion que desea visualizar:");
        System.out.println("1: Generar 30 movimientos aleatorios para un dia de semana");
        System.out.println("2: Generar 30 movimientos aleatorios para un fin de semana");
        System.out.println("3: Generar 30 movimientos un dia de semana, 50 un fin de semana e imprimir la comparacion");
        
        System.out.print("A continuacion, seleccione la opcion deseada: ");
        
        boolean flag = true;
        int opcion = 0;
        
        while(flag)
        try{
            opcion = scan.nextInt();
            if (opcion < 1 || opcion > 3)
                throw new Exception();
            flag = false;
        }
        catch (Exception e){
            System.out.println("--------------------------------------");
            System.out.print("Por favor, ingrese una opcion valida: ");
            scan.next();
        }
        
        
        int nextGroupTimeStamp=0;
        
        int cantMov=0;
        
        switch(opcion){
            case 1:
                control.setFindeSemana(true);
                
        }
        int count = 0;
        for (int i=0; i<1000;i++){
            //genera tantos movimientos de clientes como le hayamos pedido
            if (i>=nextGroupTimeStamp && cantMov<30){
                nextGroupTimeStamp=r.nextInt(8)+nextGroupTimeStamp;
                cantMov++;
                count++;
                control.laTana.addToListaClientes(control.generateClientes());    
            }
            
            //a cada hora, imprime el estado de los ingredientes
            if (i % 600 == 0){
                System.out.println("------------------------------");
                //cocina.mostrarEstado();
            }
            
            //en cada iteracion, verifica si pueden agregarse clientes
            if (control.laTana.thereAreClients())
                control.laTana.acomodarClientes();
            
            
            control.laTana.tomarPedidos(i);
            control.laTana.cocinar();
            
            control.laTana.entregarPedidos(i);
            control.laTana.updateMesas();
            control.laTana.resetMeseros();

        }
        
        cocina.mostrarEstado();
        System.out.println("Total de mesas que consumieron: " +control.laTana.getTotalMesas());
        System.out.println("Total de demora para dicahs mesas: " +control.laTana.getTotalDemora());
        System.out.println("Total de ordenes preparadas:  " + cocina.getTotalPreparadas());
        System.out.println("Total de grupos acomodados: " + control.laTana.getTotalacomodados());
        System.out.println("cantidad de grupos creados: " + count);
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
        ingredientes.put("Gaseosa", 100);
        ingredientes.put("Cerveza", 100);
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

    public void setFindeSemana(boolean findeSemana) {
        this.findeSemana = findeSemana;
    }
    
    
}
    