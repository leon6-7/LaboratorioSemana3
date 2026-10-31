package laboratoriosem3.vista;
import laboratoriosem3.control.ControlPartida;
import laboratoriosem3.control.ControladorJuego;
import Exceptions.NombreInvalidoException;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiFunction;


public class VentanaInicio extends JFrame {

    private static final Color FONDO = new Color(40, 10, 80);
    private static final Color DORADO = new Color(255, 200, 40);

    private final JTextField campoJugador1 = new JTextField(18);
    private final JTextField campoJugador2 = new JTextField(18);
    private final BiFunction<String, String, ControlPartida> fabricaControl;

    public VentanaInicio(BiFunction<String, String, ControlPartida> fabricaControl) {
        super("Memorama Pokémon");
        this.fabricaControl = fabricaControl;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(construirPanel());
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private JPanel construirPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(6, 0, 6, 0);

        JLabel titulo = new JLabel("MEMORAMA POKÉMON", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(DORADO);
        g.gridy = 0;
        g.insets = new Insets(0, 0, 4, 0);
        panel.add(titulo, g);

        JLabel subtitulo = new JLabel("Ingrese los nombres de los jugadores", SwingConstants.CENTER);
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitulo.setForeground(Color.WHITE);
        g.gridy = 1;
        g.insets = new Insets(0, 0, 20, 0);
        panel.add(subtitulo, g);

        g.insets = new Insets(6, 0, 2, 0);
        g.gridy = 2;
        panel.add(crearEtiqueta("Jugador 1:"), g);
        g.gridy = 3;
        g.insets = new Insets(0, 0, 10, 0);
        panel.add(campoJugador1, g);

        g.insets = new Insets(6, 0, 2, 0);
        g.gridy = 4;
        panel.add(crearEtiqueta("Jugador 2:"), g);
        g.gridy = 5;
        g.insets = new Insets(0, 0, 20, 0);
        panel.add(campoJugador2, g);

        JButton botonIniciar = new JButton("Iniciar juego");
        botonIniciar.setFont(new Font("SansSerif", Font.BOLD, 16));
        botonIniciar.setBackground(DORADO);
        botonIniciar.setForeground(Color.BLACK);
        botonIniciar.setFocusPainted(false);
        botonIniciar.addActionListener(e -> iniciarJuego());
        g.gridy = 6;
        g.insets = new Insets(0, 0, 0, 0);
        panel.add(botonIniciar, g);

        // Enter en cualquiera de los campos también inicia el juego
        campoJugador1.addActionListener(e -> campoJugador2.requestFocusInWindow());
        campoJugador2.addActionListener(e -> iniciarJuego());
        getRootPane().setDefaultButton(botonIniciar);

        return panel;
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(new Font("SansSerif", Font.BOLD, 14));
        return etiqueta;
    }

    private void iniciarJuego() {
        String nombre1 = campoJugador1.getText().trim();
        String nombre2 = campoJugador2.getText().trim();

        try {
            ControlPartida.validarNombres(nombre1, nombre2);
        } catch (NombreInvalidoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Datos inválidos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ControlPartida control = fabricaControl.apply(nombre1, nombre2);
            ControladorJuego n = new ControladorJuego();
            n.iniciarJuego(nombre1, nombre2);
            new VentanaTablero(control).setVisible(true);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo iniciar la partida: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}