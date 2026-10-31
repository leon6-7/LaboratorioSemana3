/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriosem3.Modelo;

/**
 *
 * @author Abril
 */
public class CartaEspecial extends Carta {
 
    public static final int PUNTOS = 2;
 
    public CartaEspecial(String nombre) {
        super(nombre);
    }
 
    @Override
    public int getPuntos() {
        return PUNTOS;
    }
 
    @Override
    public boolean esEspecial() {
        return true;
    }
}
