package com.mycompany.pizzeria;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Clase que representa la cocina de la pizzeria.
 * Contiene colas FIFO para ordenes comlpetas y sin completar.
 * El stock de ingredientes se representa como un hashmap.
 * @author andres
 */
public class Cocina implements PartePizzeria{
    
    private LinkedList<Orden> listaEntregas;
    private LinkedList<Orden> listaOrdenes;
    private ArrayList<CocineroAyudante> cocinerosA;
    private ArrayList<CocineroJefe> cocinerosJ;
    private HashMap<String,Integer> stock;
    private int totalPreparadas;
    private HashMap<String,Integer> totalesComidas;
    
    /**
     * Constructor para una nueva cocina.
     * @param stock Un hashmap con la lista inicial de ingredientes
     * @param listaCocineros Los cocineros que forman parte de la cocina
     */
    public Cocina(HashMap<String, Integer> stock,ArrayList<CocineroAyudante> listaCocineros) {
        this.listaEntregas = new LinkedList();
        this.listaOrdenes = new LinkedList();
        this.stock = stock;
        this.totalPreparadas = 0;
        this.totalesComidas = new HashMap();
        this.cocinerosA = listaCocineros;
        this.cocinerosJ = new ArrayList<CocineroJefe>();
        for (CocineroAyudante c : listaCocineros){
            if(CocineroJefe.class.isInstance(c)){
                this.cocinerosJ.add((CocineroJefe) c);
            }
        }
    }

    public LinkedList<Orden> getListaEntregas() {
        return listaEntregas;
    }
    
    public ArrayList<CocineroAyudante> getCocinerosA() {
        return cocinerosA;
    }

    public ArrayList<CocineroJefe> getCocinerosJ() {
        return cocinerosJ;
    }
    
    public int getTotalPreparadas() {
        return totalPreparadas;
    }


    public LinkedList<Orden> getListaOrdenes() {
        return listaOrdenes;
    }
    
    /**
     * Obtiene el primer elemento de la lista de ordenes.
     * @return La proxima orden que debe realizarse
     */
    public Orden getFirstListaOrdenes(){
        return listaOrdenes.getFirst();
    }

    /**
     * Elimina el primer elemento de la lista de ordenes.
     * @see removeFirstListaEntregas
     */
    public void removeFirstListaOrdenes(){
        this.listaOrdenes.removeFirst();
    }
    
    /**
     * Aniade una nueva orden al final de la cola.
     * @param ord La orden que queremos agregar
     */
    public void addLastListaOrdenes(Orden ord){
        this.listaOrdenes.addLast(ord);
    }

    public HashMap<String, Integer> getStock() {
        return stock;
    }

    public void setStock(HashMap<String, Integer> stock) {
        this.stock = stock;
    }
    
    public void set0totalesComida(){
        this.totalesComidas.clear();
    }
    
    /**
     * Le pide a cada cocinero ayudante que haga preparaciones
     */
    public void hacerPreparaciones(){
        
        for (CocineroAyudante cocinero : cocinerosA){
            
            Iterator<Orden> iter = listaOrdenes.iterator();
            boolean flag = true;
            
            while (iter.hasNext() && flag){
                
                Orden ord = iter.next();
                if (!ord.isPreparada()){
                    cocinero.hacerPreparacion(ord);
                    flag = false;
                }
            }
        }
    }
    
    /**
     * Aniade una orden al final de la lista de entregas.
     * @param ord La orden a agregar.
     */
    public void addLastListaEntregas(Orden ord){
        listaEntregas.add(ord);
    }
    
    public Orden getFirstListaEntregas(){
        return this.listaEntregas.getFirst();
    }
    
    /**
     * Elimina la primer orden de la lista de entregas
     * Debemos verificar que no este vacia
     */
    public void removeFirstListaEntregas(){
        this.listaEntregas.remove(0);
    }
    
    /**
     * Metodo para cocinar en cada turno.
     * Recorre la lista de cocineros y a cada uno lo manda a cocinar una orden.
     * Se encarga de quitar las ordenes de la cola.
     */
    public void cocinarTurnoActual(){
        
        for (CocineroJefe cocinero : cocinerosJ){
            
            if (listaOrdenes.size() > 0){
                if (cocinero.cocinar(getFirstListaOrdenes()) == 0){
                    
                    Orden ultimaOrden = getFirstListaOrdenes();
                    addLastListaEntregas(ultimaOrden);



                    for (Comida com : ultimaOrden.getListaComidas()){

                        String nombre = com.getNombre();
                        
                        if (!totalesComidas.containsKey(nombre))
                            totalesComidas.put(nombre,1);

                        else
                            totalesComidas.put(nombre, totalesComidas.get(nombre) + 1);
                    }

                    for (Bebida beb : ultimaOrden.getListaBebidas()){
                        String nombre = beb.getNombre();
                        if (!totalesComidas.containsKey(nombre))
                            totalesComidas.put(nombre,1);

                        else
                            totalesComidas.put(nombre, totalesComidas.get(nombre) + 1);
                    }

                    removeFirstListaOrdenes();
                    totalPreparadas++;
            }
            
        }
        }
    
    }
    
    /**
     * Dada una orden, decrementa las cantidades de los ingredientes.
     * @param ord La orden que se desea analizar.
     */
    public void actualizarInventario(Orden ord){
        for (Comida c : ord.getListaComidas()){
            if (!c.getNombre().equals("Nada")){
            for (HashMap.Entry<String,Integer> entry : c.getIngredientes().entrySet()){
                String ingrediente = entry.getKey();
                int cantidad = entry.getValue();
                
                stock.put(ingrediente, stock.get(ingrediente) - cantidad);
            }
        }
        }
        
        for (Bebida b : ord.getListaBebidas()){
            if (stock.containsKey(b.getNombre()))
                stock.put(b.getNombre(), stock.get(b.getNombre()) - 1);
        }
    }

    /**
     * Metodo de la interfaz partePizzeria.
     * Muestra el estado actual.
     */
    @Override
    public void mostrarEstado() {
        for (String ingr : stock.keySet()){
            String amt = stock.get(ingr).toString();
            System.out.println(ingr + ": " + amt);
        }
    }
    
    /**
     * Metodo de la interfaz partePizzeria
     * Muestra el estado final
     */
    @Override
    public void mostrarTotales() {
        for (String comida : totalesComidas.keySet()){
            int amt = totalesComidas.get(comida);
            System.out.println(comida + ": " + amt);
        }
    }

}
