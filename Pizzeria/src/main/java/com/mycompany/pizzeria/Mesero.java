package com.mycompany.pizzeria;

import java.util.List;


/**
 * Clase que simula un Mesero. Hereda de Empleado.
 * Se encarga principalmente de tomar pedidos y colocarlos en la cocina
 * @author andres
 */
public class Mesero extends Empleado {
    
    private int totalClientes;
    private int totalOrdenes;
    private List<Comida> listaComidas;
    private List<Bebida> listaBebidas;
    
    /**
     * Constructor para mesero. Inicializa los atributos que requiere Empleado.
     * Setea los atributos propios de cliente en cero.
     * @param id ID del empleado
     */
    public Mesero(String id) {
        super(id, false);
        this.totalClientes = 0;
        this.totalOrdenes = 0;
    }
    

    public List<Mesa> getListaMesas(Pizzeria p){
        return p.getListaMesas();
    }
     
    public int getTotalClientes() {
        return totalClientes;
    }


    public void setTotalClientes(int totalClientes) {
        this.totalClientes = totalClientes;
    }
    
    
    /**
     * Dada una comida, verifica si hay stock para prepararla
     * @param c La comida a verificar
     * @param cocinaPizzeria La cocina de la que queremos analizar el stock
     * @return Un booleano que nos dice si hay (true) o no (false) stock suficiente.
     */
    public boolean verificarInventarioComida(Comida c, Cocina cocinaPizzeria){
    
        this.ocupado = true;
        
        for (String key : c.getIngredientes().keySet()){
            if (c.getIngredientes().get(key) > cocinaPizzeria.getStock().get(key)){
                return false;
            }
        }
        
        return true;
    }
    
    public boolean verificarInventarioBebida(Bebida b, Cocina cocinaPizzeria){
        return (cocinaPizzeria.getStock().get(b.getIngredientes()) > 0);
    }
    
    public Comida getComidaGivenString(String nombre){
        for (Comida c : this.listaComidas){
            if (c.getNombre().compareTo(nombre) == 0)
                return c;
        }
        
        return new Comida();
    }
    
    public Bebida getBebidaGivenString(String nombre){
        for (Bebida b : this.listaBebidas){
            if (b.getNombre().compareTo(nombre) == 0)
                return b;
        }
        
        return new Bebida();
    }
    //El mesero toma una mesa y genera un pedido, preguntando a cada cliente
    //Crea un objeto de clase orden y lo coloca en la cocina
    public void tomarOrden(Mesa m, Cocina cocina){
        this.ocupado = true;
        
        Orden ord = new Orden(m.getNumero());
        
        this.totalOrdenes++;
        
        for (Cliente clim : m.getListaClientes()){
            
            this.totalClientes++;
            
            //Si hay stock, el cliente pide su comida favorita
            Comida comidaPedido = getComidaGivenString(clim.getComidaFavorita());
            
            if (!verificarInventarioComida(comidaPedido, cocina)){
                comidaPedido = new Comida();

            }
            
            ord.addToListaComidas(comidaPedido);
            
            //Se repite lo mismo para la bebida
            Bebida bebidaPedido = getBebidaGivenString(clim.getBebidaFavorita());
            
            if (!verificarInventarioBebida(bebidaPedido, cocina))
            
            ord.addToListaBebidas(bebidaPedido);
            
            //se actualiza el precio total de la orden
            ord.setPrecio(ord.getPrecio() + comidaPedido.getPrecio() + bebidaPedido.getPrecio());
            
            int tiempoActual = Integer.max(bebidaPedido.getTiempoConsumo(), comidaPedido.getTiempoConsumo());
            
            ord.setTiempoConsumo(Integer.max(ord.getTiempoConsumo(), tiempoActual));
        }
        //finalmente se pone la orden al final de la cola en la cocina
        cocina.addLastListaOrdenes(ord);
        
        this.ocupado = false;
    }
        
    
}
