
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andres
 */
public class Comida {
    
    public class ParComida  {
    
    private String nombre;
    private int cantidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ParComida(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    
    
    
}

    private String nombre;
    private ArrayList<ParComida> ingredientes;
    private int tiempoPreparacion;
    private int tiempoConsumo;
    private int precio;

    public Comida(String nombre, ArrayList<ParComida> ingredientes, int tiempoPreparacion, int tiempoConsumo, int precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoConsumo = tiempoConsumo;
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIngredientes(ArrayList<ParComida> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public void setTiempoConsumo(int tiempoConsumo) {
        this.tiempoConsumo = tiempoConsumo;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<ParComida> getIngredientes() {
        return ingredientes;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getTiempoConsumo() {
        return tiempoConsumo;
    }

    public int getPrecio() {
        return precio;
    }
    
    
}
