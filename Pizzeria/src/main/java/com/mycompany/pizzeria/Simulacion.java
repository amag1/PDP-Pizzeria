package com.mycompany.pizzeria;

import com.mycompany.clientes.Cliente;
import com.mycompany.clientes.Ninio;
import com.mycompany.clientes.Adulto;
import com.mycompany.clientes.Estudiante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal: contiene al metodo main. 
 * Inicializa la simulacion y contiene el bucle principal del programa
 */
public class Simulacion {
    private boolean findeSemana;
    private static Pizzeria laTana;
    
    /**
     * Metodo principal del programa.
     * Presenta un menu al usuario donde puede elegir que desea simular.
     */
    public static void main(String[] args) {
        
        Simulacion control = new Simulacion();

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
        control.laTana = new Pizzeria(new Cajero("Marco"),listaMesas,listaMeseros,listaCocineros,menuComida,menuBebida,cocina);

        Scanner scan = new Scanner(System.in);
        
        //Menu principal
        System.out.println("Bienvenido a la pizzeria \"La Tana\"");
        System.out.println("Seleccione la opcion que desea visualizar:");
        System.out.println("1: Generar 30 movimientos de clientes aleatorios para un dia de semana");
        System.out.println("2: Generar 30 movimientos de clientes aleatorios para un fin de semana");
        System.out.println("3: Generar 30 movimientos de clientes un dia de semana, 50 un fin de semana e imprimir la comparacion");
        
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
        
        switch(opcion){
            //Opcion1
            case 1:
                control.setFindeSemana(false);
                control.runPizzeria(cocina, 30);
                control.showTotal(cocina);
                break;
            //opcion2
            case 2:
                control.setFindeSemana(true);
                control.runPizzeria(cocina, 30);
                control.showTotal(cocina);
                break;
            //opcion3
            case 3:
                Simulacion other = new Simulacion();
                other.laTana=new Pizzeria(new Cajero("Marco"),listaMesas,listaMeseros,listaCocineros,menuComida,menuBebida,cocina);
                
                control.setFindeSemana(false);
                control.runPizzeria(cocina, 30);
                other.setFindeSemana(true);
                other.runPizzeria(cocina, 50);
                
                control.showTotal(cocina);
                other.showTotal(cocina);
                break;
                
                
        }

    }
    
    /**
     * Metodo que genera un grupo random de clientes
     * Requiere saber si es fin de semana o no
     * @return Un arraylist de clientes de un tipo aleatorio
     */
    public ArrayList<Cliente> generateClientes(){
        ArrayList<Cliente> grupo = new ArrayList<Cliente>();
        Random r = new Random();
        
        int seed= r.nextInt(6)+1;
        if (findeSemana){
            switch(seed){
                case 1:
                    for (int i=0;i<=r.nextInt(2);i++)
                        
                        grupo.add(new Ninio());
                    for (int i=0;i<=r.nextInt(2);i++)
                        grupo.add(new Adulto("Padre","Hamburguesa","Cerveza"));
                    break;
                case 2:
                    for (int i=0;i<=r.nextInt(3);i++)
                        grupo.add(new Adulto("Adulto","Pasta","Cerveza"));
                    break;
                case 3:
                    for (int i=0;i<=r.nextInt(4);i++)
                        grupo.add(new Estudiante());
                    break;
                default:
                    for (int i=0;i<=r.nextInt(4);i++)
                        grupo.add(new Adulto("Turista","Pizza","Cerveza"));
                    break;
            }
        }
        else
            switch(seed){
                case 1:
                    for (int i=0;i<=r.nextInt(1);i++)
                        grupo.add(new Ninio());
                    for (int i=0;i<=r.nextInt(1);i++)
                        grupo.add(new Adulto("Padre","Hamburguesa","Cerveza"));
                    break;
                case 2:
                    for (int i=0;i<=r.nextInt(4);i++)
                        grupo.add(new Adulto("Adulto","Pasta","Cerveza"));
                    break;
                case 3:
                    for (int i=0;i<=r.nextInt(4);i++)
                        grupo.add(new Adulto("Adulto","Pizza","Cerveza"));
                    break;
                case 4:
                    for (int i=0;i<=r.nextInt(4);i++)
                        grupo.add(new Estudiante());
                    break;
                case 5:
                    for (int i=0;i<=r.nextInt(4);i++)
                        grupo.add(new Estudiante());
                    break;
                default:
                    for (int i=0;i<=r.nextInt(3);i++)
                        grupo.add(new Adulto("Trabajador","Pasta","Gaseosa"));
                    break;
            }
        return grupo;
    }
    
    /**
     * Crea las 24 mesas especificadas para el problema, 8 de 2 y 16 de 4.
     * @return una linkedlist de mesas.
     */
    public LinkedList<Mesa> innitMesas(){
        LinkedList<Mesa> listaMesas = new LinkedList<Mesa>();
        for (int i=1; i<=8;i++)
            listaMesas.add(new Mesa(i, 2));
        for (int i=9; i<=24;i++)
            listaMesas.add(new Mesa(i, 4));
        return listaMesas;
    }
    
    /**
     * Inicializa el hashmap con ingredientes que se mantiene en la cocina.
     * @return HashMap de la forma Ingrediente-Cantidad.
     */
    public HashMap<String, Integer> innitIngredientes(){
        HashMap<String, Integer> ingredientes = new HashMap<String,Integer>();
        ingredientes.put("Prepizza",400);
        ingredientes.put("Cebolla",400);
        ingredientes.put("Queso",400);
        ingredientes.put("Pan",400);
        ingredientes.put("Carne molida",400);
        ingredientes.put("Condimentos",400);
        ingredientes.put("Tomate",400);
        ingredientes.put("Lechuga",400);
        ingredientes.put("Huevo",400);
        ingredientes.put("Gaseosa", 400);
        ingredientes.put("Cerveza", 400);
        ingredientes.put("Harina", 400);
        return ingredientes;
    }
    
    /**
     * Inicializa los meseros que tendra la pizzeria.
     * @return Un ArrayList de meseros genericos
     */
    public ArrayList<Mesero> innitMeseros(){
        ArrayList<Mesero> listaMeseros = new ArrayList<Mesero>();
        listaMeseros.add(new Mesero("Tonio"));
        listaMeseros.add(new Mesero("Stella"));
        listaMeseros.add(new Mesero("Maria"));
        return listaMeseros;
    }
    
    /**
     * Inicializa los cocineros
     * @return Un ArrayList con cocineros, tanto jefes como ayudantes
     */
    public ArrayList<CocineroAyudante> innitCocineros(){
        ArrayList<CocineroAyudante> listaCocineros = new ArrayList<CocineroAyudante>();
        listaCocineros.add(new CocineroJefe("Pietro"));
        listaCocineros.add(new CocineroJefe("Giorgio"));
        listaCocineros.add(new CocineroJefe("Luigi"));
        listaCocineros.add(new CocineroAyudante("Anna"));
        listaCocineros.add(new CocineroAyudante("Giulia"));
        listaCocineros.add(new CocineroAyudante("Stefano"));
        return listaCocineros;
    }    
    /**
     * Inicializa cada comida del menu con sus ingredientes
     * @return Un ArrayList de comidas
     */
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
        HashMap<String,Integer> ingPasta = new HashMap<String,Integer>();
        ingPasta.put("Huevo", 2);
        ingPasta.put("Harina", 1);
        
        menuComida.add(new Comida("Pizza",ingPizza,20,30,6));
        menuComida.add(new Comida("Hamburguesa",ingBurger,15,15,3));
        menuComida.add(new Comida("Pasta",ingPasta,23, 20, 4));
        
        return menuComida;
    }
    /**
     * Inicializa cada bebida del menu con sus ingredientes
     * @return Un ArrayList de bebida
     */
    public ArrayList<Bebida> innitMenuBebida(){
        ArrayList<Bebida> menuBebida = new ArrayList<Bebida>();
        menuBebida.add(new Bebida("Gaseosa",1));
        menuBebida.add(new Bebida("Cerveza",2));
        return menuBebida;
    }
    
    public void setFindeSemana(boolean findeSemana) {
        this.findeSemana = findeSemana;
    }
    
    /**
     * Para llamar al final de la ejecucion.
     * Muestra los totales obtenidos luego de la ejecucion.
     * @param cocina La cocina de la pizzeria.
     */
    public void showTotal(Cocina cocina){
        System.out.println("Estado final de los ingredientes: ");
        cocina.mostrarEstado();
        System.out.println("-----------------");
        System.out.println("Los productos mas pedidos fueron los siguientes: ");
        cocina.mostrarTotales();
        System.out.println("-----------------");
        this.laTana.mostrarTotales();
    }
    
    /**
     * Ejecuta el programa principal.
     * Aniade clientes, toma pedidos, los cocina y los entrega.
     * @param cocina La cocina de la pizzeroa.
     * @param maxMov La cantidad de movimientos que se desea simular.
     */
    
    public int[] runPizzeria(Cocina cocina, int maxMov){
        int i = 0;
        int cantMov = 0;
        int nextGroupTimeStamp = 0;
        boolean flag = true;
        Random r = new Random();
            System.out.println("----------------");
        System.out.println("Estado inicial de los ingredientes: ");
        cocina.mostrarEstado();
        System.out.println("----------------");
        while (flag){
            //genera tantos movimientos de clientes como le hayamos pedido
            if (i>=nextGroupTimeStamp && cantMov < maxMov){
                nextGroupTimeStamp=r.nextInt(8)+nextGroupTimeStamp;
                cantMov++;
                
                ArrayList<Cliente> newLista = this.generateClientes();
                this.laTana.addToListaClientes(newLista);    
            }
            
            
           

            //en cada iteracion, verifica si pueden agregarse clientes
            if (this.laTana.thereAreClients())
                this.laTana.acomodarClientes();

            this.laTana.tomarPedidos(i);
            this.laTana.cocinar();

            this.laTana.entregarPedidos(i);
            this.laTana.updateMesas();
            this.laTana.resetMeseros();
           
            if (this.laTana.getTotalMesas() == maxMov){
                flag = false;
            }
            i++;
        }
        
       return new int[] {this.laTana.getTotalDemora(),this.laTana.getTotalMesas(),this.laTana.getCajero().getTotalGanado()};
}
}

    