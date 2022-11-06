package com.mycompany.pizzeria;

import java.util.List;
import com.mycompany.clientes.Cliente;
import java.util.HashMap;

/**
 * Clase que simula un Mesero. Hereda de Empleado.
 * Se encarga principalmente de tomar pedidos y colocarlos en la cocina
 * @author andres
 */
public class Mesero extends Empleado {
    
    private static int totalClientes;
    private int totalOrdenes;
    private static HashMap<String,Integer> tiposClientes;

    
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

    /**
     * Dada una comida, verifica si hay stock para prepararla.
     * @param c La comida a verificar.
     * @param cocinaPizzeria La cocina de la que queremos analizar el stock.
     * @return Un booleano que nos dice si hay (true) o no (false) stock suficiente.
     */
    public boolean verificarInventarioComida(Comida c, Cocina cocinaPizzeria){
    
        this.ocupado = true;
        
        for (String key : c.getIngredientes().keySet()){
            if (c.getIngredientes().get(key) + 3 > cocinaPizzeria.getStock().get(key)){
                return false;
            }
        }
        
        return true;
    }
    /**
     * Dada una bebida, verifica si hay stock de ellaa.
     * @param b La bebidaa verificar.
     * @param cocinaPizzeria La cocina de la que queremos analizar el stock.
     * @return Un booleano que nos dice si hay (true) o no (false) stock suficiente.
     */
    public boolean verificarInventarioBebida(Bebida b, Cocina cocinaPizzeria){
        return (cocinaPizzeria.getStock().get(b.getNombre()) > 0);
    }
    
    /**
     * Dado un String, busca en la lista de comidas si existe dicha comida.
     * Si no existe, devuelve una comida por default.
     * @param nombre El nombre que queremos buscar.
     * @param listaComidas La lista donde debemos buscar la comida.
     * @return La comida asociada a ese nombre, o una comida default si no lo hallamos (Pizza).
     */
    public Comida getComidaGivenString(String nombre, List<Comida> listaComidas){
        for (Comida c : listaComidas){
            if (c.getNombre().compareTo(nombre) == 0)
                return c;
        }
        
        HashMap<String,Integer> ingPizza = new HashMap<String,Integer>();
        ingPizza.put("Prepizza",1);
        ingPizza.put("Cebolla",1);
        ingPizza.put("Queso",1);
        ingPizza.put("Condimentos",1);
        ingPizza.put("Tomate",1);
        ingPizza.put("Huevo",1);
        return new Comida("Pizza",ingPizza,20,30,5);

        
    }
    
    /**
     * Se encarga de buscar una bebida en el menu dado su nombre.
     * @param nombre El nombre de la bebida (String).
     * @param listaBebidas El menu de bebidas (List<Bebida>).
     * @return La bebida a la que hace referencia el nombre, o una gaseosa si no lo hallamos.
     */
    public Bebida getBebidaGivenString(String nombre, List<Bebida> listaBebidas){
        for (Bebida b : listaBebidas){
            if (b.getNombre().compareTo(nombre) == 0)
                return b;
        }
        return new Bebida("Gaseosa",1);
    }
    
    /**
     * Metodo que se encarga de tomar los pedidos en una mesa.
     * @param tiposClientes HashMap con informacion sobre los clientes que ya han venido.
     * @param tiempoActual Momento actual en el tiempo.
     * @param m La mesa a la que queremos tomarle el pedido.
     * @param cocina La cocina en la que debemos verificar el stock.
     * @param menuComida Lista de comidas posibles.
     * @param menuBebida Lista de bebidas posibles.
     */
    public void tomarOrden(HashMap<String,Integer> tiposClientes, int tiempoActual,Mesa m, Cocina cocina, List<Comida> menuComida, List<Bebida> menuBebida){
        totalOrdenes++;
        this.ocupado = true;
        
        Orden ord = new Orden(m.getNumero());
        ord.setHoraPedido(tiempoActual);
        
        this.totalOrdenes++;
        
        for (Cliente clim : m.getListaClientes()){
            
                    String tipo = clim.getClass().getSimpleName();
                    if (!tiposClientes.containsKey(tipo))
                        tiposClientes.put(tipo,1);
                    
                    else
                        tiposClientes.put(tipo, tiposClientes.get(tipo) + 1);
                
            this.totalClientes++;
            
            //Si hay stock, el cliente pide su comida favorita
            
            Comida comidaPedido = getComidaGivenString(clim.getComidaFavorita(),menuComida);
            
            if (!verificarInventarioComida(comidaPedido, cocina)){
                comidaPedido = new Comida();

            }
            
            ord.addToListaComidas(comidaPedido);
            
            //Se repite lo mismo para la bebida
            Bebida bebidaPedido = getBebidaGivenString(clim.getBebidaFavorita(), menuBebida);
            
            if (!verificarInventarioBebida(bebidaPedido, cocina)){
                bebidaPedido = new Bebida();
            }
            
            ord.addToListaBebidas(bebidaPedido);
            cocina.actualizarInventario(ord);
            //se actualiza el precio total de la orden
            ord.setPrecio(ord.getPrecio() + comidaPedido.getPrecio() + bebidaPedido.getPrecio());
            
            int tiempoFinal = comidaPedido.getTiempoConsumo();
            
            ord.setTiempoConsumo(Integer.max(ord.getTiempoConsumo(), tiempoFinal));
            ord.setTiempoPreparacion(ord.getTiempoPreparacion() + comidaPedido.getTiempoPreparacion());
        }
        //finalmente se pone la orden al final de la cola en la cocina
        cocina.addLastListaOrdenes(ord);
    }
    
    /**
     * Toma una orden y la entrega a la mesa especificada.
     * Actualiza el tiempo de espera y consumo de la mesa.
     * @param ord La ordfen a entregar.
     * @param m La mesa a la que debemos entregarla.
     */
    public void entregarOrden(Orden ord, Mesa m){
        
        this.ocupado = true;
        int totalTime = ord.getHoraEntrega() - ord.getHoraPedido();
        m.setTiempoEspera(totalTime);
        m.setTiempoConsumo(ord.getTiempoConsumo());
    }
        

}
