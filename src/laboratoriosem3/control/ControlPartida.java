/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriosem3.control;
import Exceptions.NombreInvalidoException;
import Exceptions.PosicionInvalidaException;
import Exceptions.SeleccionInvalidaException;
/**
 *
 * @author sergio
 */
import laboratoriosem3.Modelo.Carta;
import laboratoriosem3.Modelo.Jugador;
import laboratoriosem3.Modelo.Tablero;
public abstract class ControlPartida {
    
    public abstract void iniciarJuego(String nombre,String nombre2);
    
    public abstract Jugador getJugadorActual();
    
    public abstract Jugador[] getJugadores();

    public abstract void cambiarTurno();
    
    public abstract boolean verificarPareja(Carta c1, Carta c2);
    
    public abstract boolean finalizarPartida();
    
    public abstract String obtenerResultado();
    
    public abstract boolean estaBloqueado();
    
    public abstract boolean estaTerminada();
    
    public abstract void resolverFallo() throws PosicionInvalidaException;
    public abstract Tablero getTablero();
    public abstract int seleccionarCarta(int posicion) throws PosicionInvalidaException, SeleccionInvalidaException;
    
    public static boolean validarNombres(String nombre1, String nombre2)throws NombreInvalidoException{
        if(nombre1.equals(nombre2)||nombre1.isBlank()||nombre2.isBlank()){
            throw new NombreInvalidoException("Nombres Invalidos");
        }
        else{
        return true;
        }
    }
    
}
