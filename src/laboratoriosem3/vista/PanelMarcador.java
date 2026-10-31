/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriosem3.vista;

/**
 *
 * @author USER
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import laboratoriosem3.Modelo.Jugador;


public class PanelMarcador extends JPanel {

    private static final Color FONDO = new Color(40, 70, 140);
    private static final Color TURNO = new Color(250, 215, 60);
    private static final Color SIN_TURNO = new Color(70, 100, 170);

    private final JLabel lblTurno = new JLabel(" ", SwingConstants.CENTER);
    private final JPanel[] tarjetas = new JPanel[2];
    private final JLabel[] lblNombres = new JLabel[2];
    private final JLabel[] lblDatos = new JLabel[2];

    public PanelMarcador() {
        super(new BorderLayout(0, 8));
        setBackground(FONDO);
        setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        lblTurno.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTurno.setForeground(Color.WHITE);
        add(lblTurno, BorderLayout.NORTH);

        JPanel jugadores = new JPanel(new GridLayout(1, 2, 12, 0));
        jugadores.setOpaque(false);
        for (int i = 0; i < 2; i++) {
            tarjetas[i] = new JPanel(new GridLayout(2, 1));
            tarjetas[i].setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
            lblNombres[i] = new JLabel(" ", SwingConstants.CENTER);
            lblNombres[i].setFont(new Font("SansSerif", Font.BOLD, 16));
            lblDatos[i] = new JLabel(" ", SwingConstants.CENTER);
            lblDatos[i].setFont(new Font("SansSerif", Font.PLAIN, 14));
            tarjetas[i].add(lblNombres[i]);
            tarjetas[i].add(lblDatos[i]);
            jugadores.add(tarjetas[i]);
        }
        add(jugadores, BorderLayout.CENTER);
    }

    public void actualizar(Jugador[] jugadores, int turnoActual) {
        for (int i = 0; i < jugadores.length && i < 2; i++) {
            boolean enTurno = i == turnoActual;
            Color texto = enTurno ? Color.BLACK : Color.WHITE;
            tarjetas[i].setBackground(enTurno ? TURNO : SIN_TURNO);
            lblNombres[i].setForeground(texto);
            lblDatos[i].setForeground(texto);
            lblNombres[i].setText((enTurno ? "▶ " : "") + jugadores[i].getNombre());
            lblDatos[i].setText("Aciertos: " + jugadores[i].getAciertos()
                    + "    Puntos: " + jugadores[i].getPuntaje());
        }
        lblTurno.setText("Turno de " + jugadores[turnoActual].getNombre());
    }
}
