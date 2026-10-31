/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriosem3.vista;

import laboratoriosem3.control.ControlPartida;
import Exceptions.SeleccionInvalidaException;
import Exceptions.PosicionInvalidaException;
import laboratoriosem3.Modelo.Jugador;
import laboratoriosem3.Modelo.Tablero;

import javax.swing.*;
import java.awt.*;
import laboratoriosem3.vista.PanelCarta;
import laboratoriosem3.vista.PanelMarcador;

public class VentanaTablero extends JFrame {
 
    // Códigos que devuelve ControlPartida.seleccionarCarta()
    private static final int IGNORADA = 0;
    private static final int PRIMERA_CARTA = 1;
    private static final int PAREJA = 2;
    private static final int FALLO = 3;
    private static final int PAREJA_FINAL = 4;
 
    private static final int FILAS = 6;
    private static final int COLUMNAS = 6;
    private static final int TOTAL_CARTAS = FILAS * COLUMNAS;
    private static final int PAUSA_MS = 1000;
 
    private static final Color FONDO = new Color(40, 10, 80);
    private static final Color[] COLORES = {new Color(90, 200, 255), new Color(255, 120, 180)};
 
    private final ControlPartida control;
    private final PanelCarta[] paneles = new PanelCarta[TOTAL_CARTAS];
    private final JPanel[] panelesJugador = new JPanel[2];
    private final JLabel[] etiquetasJugador = new JLabel[2];
    private final JLabel etiquetaTurno = new JLabel("", SwingConstants.CENTER);
    private final String[] nombres = new String[2];   // para reiniciar con los mismos nombres
 
    public VentanaTablero(ControlPartida control) {
        super("Memorama Pokémon");
        this.control = control;
 
        Jugador[] jugadores = control.getJugadores();
        nombres[0] = jugadores[0].getNombre();
        nombres[1] = jugadores[1].getNombre();
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(FONDO);
 
        add(construirEncabezado(), BorderLayout.NORTH);
        add(construirCuadricula(), BorderLayout.CENTER);
 
        refrescar();
        setSize(780, 860);
        setMinimumSize(new Dimension(600, 700));
        setLocationRelativeTo(null);
    }
 
 
    private JPanel construirEncabezado() {
        JPanel encabezado = new JPanel(new GridLayout(1, 3, 10, 0));
        encabezado.setBackground(FONDO);
        encabezado.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
 
        etiquetaTurno.setFont(new Font("SansSerif", Font.BOLD, 20));
 
        encabezado.add(crearPanelJugador(0));
        encabezado.add(etiquetaTurno);
        encabezado.add(crearPanelJugador(1));
        return encabezado;
    }
 
    private JPanel crearPanelJugador(int indice) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(FONDO);
 
        JLabel etiqueta = new JLabel("", SwingConstants.CENTER);
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(etiqueta, BorderLayout.CENTER);
 
        panelesJugador[indice] = panel;
        etiquetasJugador[indice] = etiqueta;
        return panel;
    }
 
    private JPanel construirCuadricula() {
        JPanel cuadricula = new JPanel(new GridLayout(FILAS, COLUMNAS, 6, 6));
        cuadricula.setBackground(FONDO);
        cuadricula.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
 
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            final int posicion = i;
            paneles[i] = new PanelCarta(posicion);
            paneles[i].addActionListener(e -> alSeleccionarCarta(posicion));
            cuadricula.add(paneles[i]);
        }
        return cuadricula;
    }
 
 
    private void alSeleccionarCarta(int posicion) {
        if (control.estaBloqueado() || control.estaTerminada()) {
            return;
        }
        try {
            int resultado = control.seleccionarCarta(posicion);
            refrescar();
 
            if (resultado == FALLO) {
                iniciarPausa();
            } else if (resultado == PAREJA_FINAL) {
                mostrarResultadoFinal();
            }
        } catch (PosicionInvalidaException | SeleccionInvalidaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Selección inválida", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error inesperado: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    private void iniciarPausa() {
        Timer temporizador = new Timer(PAUSA_MS, e -> {
            try {
                control.resolverFallo();
            } catch (PosicionInvalidaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            refrescar();
        });
        temporizador.setRepeats(false);
        temporizador.start();
    }
 
    private void mostrarResultadoFinal() {
        Jugador[] j = control.getJugadores();
 
        String resumen = j[0].getNombre() + ": " + j[0].getPuntaje() + " puntos ("
                + j[0].getAciertos() + " aciertos)\n"
                + j[1].getNombre() + ": " + j[1].getPuntaje() + " puntos ("
                + j[1].getAciertos() + " aciertos)";
 
        String mensaje;
        if (j[0].getPuntaje() == j[1].getPuntaje()) {
            mensaje = "¡Empate!\n\n" + resumen;
        } else {
            Jugador ganador = j[0].getPuntaje() > j[1].getPuntaje() ? j[0] : j[1];
            mensaje = "¡El ganador es " + ganador.getNombre() + " con "
                    + ganador.getPuntaje() + " puntos!\n\n" + resumen;
        }
 
        Object[] opciones = {"Nueva partida", "Salir"};
        int eleccion = JOptionPane.showOptionDialog(this, mensaje, "Fin de la partida",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, opciones, opciones[0]);
 
        if (eleccion == 0) {
            // iniciarJuego reinicia marcadores (nuevos Jugador) y vuelve a mezclar el tablero
            control.iniciarJuego(nombres[0], nombres[1]);
            refrescar();
        } else {
            dispose();
            System.exit(0);
        }
    }

    private void refrescar() {
        Tablero tablero = control.getTablero();
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            try {
                paneles[i].actualizar(tablero.getCarta(i));
            } catch (PosicionInvalidaException e) {
                System.err.println("Posición inválida al refrescar: " + e.getMessage());
            }
        }
 
        Jugador[] jugadores = control.getJugadores();
        Jugador actual = control.getJugadorActual();
        int turno = (actual == jugadores[0]) ? 0 : 1;
 
        for (int i = 0; i < 2; i++) {
            etiquetasJugador[i].setText("<html><center><b>" + jugadores[i].getNombre() + "</b><br>"
                    + "Aciertos: " + jugadores[i].getAciertos() + "<br>"
                    + "Puntaje: " + jugadores[i].getPuntaje() + "</center></html>");
            panelesJugador[i].setBorder(i == turno
                    ? BorderFactory.createLineBorder(COLORES[i], 3)
                    : BorderFactory.createLineBorder(Color.GRAY, 1));
        }
 
        etiquetaTurno.setText("Turno de: " + actual.getNombre());
        etiquetaTurno.setForeground(COLORES[turno]);
        repaint();
    }
}
 