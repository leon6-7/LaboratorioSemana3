/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author USER
 */
public class DibujoCartaException extends Exception {

    public DibujoCartaException(String mensaje) {
        super(mensaje);
    }

    public DibujoCartaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
