/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriosem3.Modelo;

/**
 *
 * @author Abril
 */
public class Jugador {

    private final String nombre;
    private int aciertos;
    private int puntaje;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.aciertos = 0;
        this.puntaje = 0;
    }
    public void parejaEncontrada(int puntos){
        aciertos++;
        puntaje+=puntos;
    }
    public void reiniciar(){
        aciertos=0;
        puntaje=0;
    }
    
    public String getNombre() {
        return nombre;
    }
 
    public int getAciertos() {
        return aciertos;
    }
 
    public int getPuntaje() {
        return puntaje;
    }
 
    @Override
    public String toString() {
        return nombre + " - Aciertos: " + aciertos + ", Puntaje: " + puntaje;
    }    
}