package com.mycompany.pizzeria;


public class Mesero extends Empleado {
    
    private int totalClientes;
    private int totalOrdenes;
    private Cocina cocinaPizzeria;
    
    //Constructor para mesero (con ambos argumentos)
    public Mesero(int totalClientes, Cocina cocinaPizzeria) {
        this.totalClientes = totalClientes;
        this.cocinaPizzeria = cocinaPizzeria;
    }

    public int getTotalOrdenes() {
        return totalOrdenes;
    }

    public void setTotalOrdenes(int totalOrdenes) {
        this.totalOrdenes = totalOrdenes;
    }
    
    
    public int getTotalClientes() {
        return totalClientes;
    }

    public Cocina getCocinaPizzeria() {
        return cocinaPizzeria;
    }

    public void setTotalClientes(int totalClientes) {
        this.totalClientes = totalClientes;
    }
    
    //Dada una comida, analiza la lista de ingredientes y determina si hay suficiente stock
    public boolean verificarInventarioComida(Comida c){
        
        for (String key : c.getIngredientes().keySet()){
            if (c.getIngredientes().get(key) > this.cocinaPizzeria.getStock().get(key)){
                return false;
            }
        }
        
        return true;
    }
    
    public boolean verificarInventarioBebida(Bebida b){
        return (this.cocinaPizzeria.getStock().get(b.getIngredientes()) > 0);
    }
    
    //El mesero toma una mesa y genera un pedido, preguntando a cada cliente
    //Crea un objeto de clase orden y lo coloca en la cocina
    public void tomarOrden(Mesa m){
        Orden ord = new Orden(m);
        
        this.totalOrdenes++;
        for (Cliente clim : m.getListaClientes()){
            
            this.totalClientes++;
            
            //Si hay stock, el cliente pide su comida favorita
            Comida comidaPedido = clim.getComidaFavorita();
            
            //De otro modo, el cliente hace un pedido random con un metodo propio
            while (!this.verificarInventarioComida(comidaPedido)){
                comidaPedido = clim.pedirComida();
            }
            
            ord.addToListaComidas(comidaPedido);
            
            //Se repite lo mismo para la bebida
            Bebida bebidaPedido = clim.getBebidaFavorita();
            
            while (!this.verificarInventarioBebida(bebidaPedido)){
                
             bebidaPedido = clim.pedirBebida();
             
            }
            
            ord.addToListaBebidas(bebidaPedido);
            
            //se actualiza el precio total de la orden
            ord.setPrecio(ord.getPrecio() + comidaPedido.getPrecio() + bebidaPedido.getPrecio());
            
        }
        //finalmente se pone la orden al final de la cola en la cocina
        this.cocinaPizzeria.addLastListaOrdenes(ord);
    }
        
    
}
