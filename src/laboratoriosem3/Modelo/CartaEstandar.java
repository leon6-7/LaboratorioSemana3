/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriosem3.Modelo;

/**
 *
 * @author Abril
 */
public class CartaEstandar extends Carta{
    
    public static final int PUNTOS = 1;
 
    public CartaEstandar(String nombre){
    super(nombre);
    }
    
    @Override
    public int getPuntos() {
        return PUNTOS;
    }
}
