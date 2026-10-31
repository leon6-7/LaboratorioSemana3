/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author Abril
 */
public class PosicionInvalidaException extends Exception {
  public PosicionInvalidaException(String mensaje) {
        super(mensaje);
    }
 
    public PosicionInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}    

