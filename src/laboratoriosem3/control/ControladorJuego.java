/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriosem3.control;

/**
 *
 * @author sergio
 */
public class ControladorJuego extends ControlPartida{
    private Jugador[]jugadores;
    private Tablero tablero;
    private int turno;
    private int primera;
    private int segunda;
    private boolean bloqueado;
    private boolean terminada;
    
    public ControladorJuego(){
        jugadores=new Jugador[2];
        tablero=new Tablero();
        turno=0;
        primera=-1;
        segunda=-1;
        bloqueado=false;
        terminada=false;
    }
    
    @Override
    public void iniciarJuego(String nombre1,String nombre2){
        if (nombre1==null||nombre2==null||nombre1.trim().isEmpty()||nombre2.trim().isEmpty()) {
            throw new IllegalArgumentException("Los nombres son obligatorios");
        }
        if (nombre1.trim().equalsIgnoreCase(nombre2.trim())) {
            throw new IllegalArgumentException("Los jugadores deben tener nombres diferentes");
        }
        jugadores[0]=new Jugador(nombre1.trim());
        jugadores[1]=new Jugador(nombre2.trim());
        
        tablero.prepararTablero();
        
        turno=0;
        primera=-1;
        segunda=-1;
        bloqueado=false;
        terminada=false;
    }
    
    @Override
    public Jugador getJugadorActual(){
        return jugadores[turno];
    }
    public Jugador[]getJugadores(){
        return jugadores;
    }
    public Tablero getTablero(){
        return tablero;
    }
    
    @Override
    public void cambiarTurno(){
        if (!terminada) {
            turno=1-turno;
        }
    }
    
    @Override
    public boolean verificarPareja(Carta c1, Carta c2){
        return c1.getNombre().equals(c2.getNombre());
    }
    
    public int seleccionarCarta(int posicion){
        if (terminada || bloqueado){
            return 0;
        } 
        if (posicion < 0 || posicion >= 36){
            throw new IllegalArgumentException("Posicion fuera del tablero");
        }
        Carta carta = tablero.getCarta(posicion);
        if (carta.estaDescubierta() || carta.estaEmparejada()){
            throw new IllegalArgumentException("Esta carta no se puede seleccionar");
        }
        carta.mostrar();
         if (primera==-1){
             primera=posicion;
             return 1;
         }
         segunda=posicion;
         Carta c1=tablero.getCarta(primera);
         Carta c2=tablero.getCarta(segunda);
          if (verificarPareja(c1, c2)){
              c1.emparejar();
              c2.emparejar();
              getJugadorActual().sumarAcierto(c1.getPuntos());
              primera=-1;
              segunda=-1;
               if (finalizarPartida()) {
                   terminada=true;
                   return 4;
               }
               return 2;
          }
          bloqueado=true;
          return 3;
    }
    
    public void resolverFallo(){
         if (!bloqueado||primera==-1||segunda==-1){
             return;
         }
         tablero.getCarta(primera).ocultar();
         tablero.getCarta(segunda).ocultar();
         primera=-1;
         segunda=-1;
         bloqueado=false;
         cambiarTurno();
    }
    
    @Override
    public boolean finalizarPartida(){
        return tablero.contarEmparejadas()==36;
    }
    
    public String obtenerResultado(){
        if (!terminada) {
            return "La partida todavia no ha terminado";
        }
        Jugador j1=jugadores[0];
        Jugador j2=jugadores[1];
        if (j1.getPuntaje() > j2.getPuntaje()) {
            return "Ganador: " + j1.getNombre()+ "Puntos: "+j1.getPuntaje();
        }else if(j2.getPuntaje() > j1.getPuntaje()){
            return "Ganador: " + j2.getNombre()+ "Puntos: "+j2.getPuntaje();
        }else{
            return "Empate entre " + j1.getNombre()+ " y "+j2.getNombre()+ "Puntos: "+j1.getPuntaje();
        }
    }
    
    public boolean estaBloqueado(){
        return bloqueado;
    }
    public boolean estaTerminada(){
        return terminada;
    }
    
}
