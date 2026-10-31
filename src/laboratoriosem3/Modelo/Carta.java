/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriosem3.Modelo;

/**
 *
 * @author Abril
 */
public abstract class Carta implements Cloneable {
    private final String nombre;  
    private EstadoCarta estado;
    
    protected Carta(String nombre) {
        this.nombre = nombre;
        this.estado = EstadoCarta.BOCA_ABAJO;
    }
    public abstract int getPuntos();
    
    public boolean esEspecial() {
        return false;
    }
    
    public void mostrar(){
    if(estado==EstadoCarta.BOCA_ABAJO){
    estado= EstadoCarta.BOCA_ARRIBA;
    }
    }
    
    public void ocultar(){
    if(estado==EstadoCarta.BOCA_ARRIBA){
    estado= EstadoCarta.BOCA_ABAJO;
    }
    }
    
     public boolean estaEmparejada() {
        return estado == EstadoCarta.EMPAREJADA;
    }
 
    public boolean estaBocaAbajo() {
        return estado == EstadoCarta.BOCA_ABAJO;
    }
 
    public boolean esPareja(Carta otra) {
        return otra != null && this.nombre.equals(otra.nombre);
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public EstadoCarta getEstado() {
        return estado;
    }
 
    @Override
    public Carta clone() throws CloneNotSupportedException {
        Carta copia = (Carta) super.clone();
        copia.estado = EstadoCarta.BOCA_ABAJO;
        return copia;
    }
 
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + nombre + ", " + estado + "]";
    }
}
