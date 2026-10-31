/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriosem3.Modelo;
import java.util.Random;
import Exceptions.PosicionInvalidaException;
import Exceptions.SeleccionInvalidaException;

/**
 *
 * @author Abril
 */

public class Tablero {

    public static final int FILAS = 6;
    public static final int COLUMNAS = 6;
    public static final int TOTAL_CARTAS = FILAS * COLUMNAS;   // 36
    public static final int TOTAL_PAREJAS = TOTAL_CARTAS / 2;  // 18
    public static final int PAREJAS_ESPECIALES = 2;
    public static final String[] NOMBRES_POKEMON = {
        "Pikachu", "Charmander", "Bulbasaur", "Squirtle", "Jigglypuff", "Meowth",
        "Psyduck", "Gengar", "Eevee", "Snorlax", "Mewtwo", "Mew",
        "Togepi", "Poliwag", "Magikarp", "Voltorb", "Ditto", "Oddish",
        "Onix", "Vulpix", "Charizard", "Machop", "Geodude"
    };

    private final Carta[] cartas = new Carta[TOTAL_CARTAS];
    private final Random random = new Random();

    public Tablero() {
        reiniciar();
    }

    public void reiniciar() {
        construirCartas();
        mezclar(TOTAL_CARTAS - 1);
    }

    private void construirCartas() {
        boolean[] especial = new boolean[TOTAL_PAREJAS];
        int marcadas = 0;
        while (marcadas < PAREJAS_ESPECIALES) {
            int k = random.nextInt(TOTAL_PAREJAS);
            if (!especial[k]) {
                especial[k] = true;
                marcadas++;
            }
        }
        for (int i = 0; i < TOTAL_PAREJAS; i++) {
            Carta prototipo = especial[i]
                    ? new CartaEspecial(NOMBRES_POKEMON[i])
                    : new CartaEstandar(NOMBRES_POKEMON[i]);
            cartas[2 * i] = prototipo;
            cartas[2 * i + 1] = clonarCarta(prototipo);
        }
    }

    private Carta clonarCarta(Carta prototipo) {
        try {
            return prototipo.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("Error al clonar " + prototipo.getNombre() + ": " + e.getMessage());
            return prototipo.esEspecial()
                    ? new CartaEspecial(prototipo.getNombre())
                    : new CartaEstandar(prototipo.getNombre());
        }
    }

    private void mezclar(int i) {
        if (i <= 0) {
            return;
        }
        int j = random.nextInt(i + 1);
        Carta temp = cartas[i];
        cartas[i] = cartas[j];
        cartas[j] = temp;
        mezclar(i - 1);
    }

    public int contarEmparejadas(int indice) {
        if (indice >= TOTAL_CARTAS) {
            return 0;
        }
        return (cartas[indice].estaEmparejada() ? 1 : 0) + contarEmparejadas(indice + 1);
    }

    public int contarEmparejadas() {
        return contarEmparejadas(0);
    }

    public boolean todasEmparejadas() {
        return contarEmparejadas() == TOTAL_CARTAS;
    }

    public Carta getCarta(int posicion) throws PosicionInvalidaException {
        try {
            return cartas[posicion];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PosicionInvalidaException(
                    "La posición " + posicion + " no existe. Use valores de 0 a "
                            + (TOTAL_CARTAS - 1) + ".", e);
        }
    }

    public void validarPrimeraSeleccion(int posicion)
            throws PosicionInvalidaException, SeleccionInvalidaException {
        Carta carta = getCarta(posicion);
        if (carta.estaEmparejada()) {
            throw new SeleccionInvalidaException("Esa carta ya fue emparejada.");
        }
        if (!carta.estaBocaAbajo()) {
            throw new SeleccionInvalidaException("Esa carta ya está descubierta.");
        }
    }

    public void validarSegundaSeleccion(int posicion, int primeraPosicion)
            throws PosicionInvalidaException, SeleccionInvalidaException {
        if (posicion == primeraPosicion) {
            throw new SeleccionInvalidaException("No puede seleccionar la misma carta dos veces.");
        }
        validarPrimeraSeleccion(posicion);
    }

    public int getFila(int posicion) {
        return posicion / COLUMNAS;
    }

    public int getColumna(int posicion) {
        return posicion % COLUMNAS;
    }

    public static String[] getNombresPokemon() {
        return NOMBRES_POKEMON.clone();
    }
}
