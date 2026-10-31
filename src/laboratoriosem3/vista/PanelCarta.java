/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriosem3.vista;

/**
 *
 * @author USER
 */


import Exceptions.DibujoCartaException;
import Util.DibujanteCartas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import laboratoriosem3.Modelo.Carta;
import laboratoriosem3.Modelo.EstadoCarta;


public class PanelCarta extends JButton {

    private final int posicion;
    private Carta carta;

    public PanelCarta(int posicion) {
        this.posicion = posicion;
        setPreferredSize(new Dimension(95, 112));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setRolloverEnabled(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public int getPosicion() {
        return posicion;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int ancho = getWidth();
        int alto = getHeight();
        try {
            if (carta == null || carta.getEstado() == EstadoCarta.BOCA_ABAJO) {
                DibujanteCartas.dibujarDorso(g2, ancho, alto);
                if (getModel().isRollover() && isEnabled()) {
                    g2.setColor(new Color(250, 215, 60));
                    g2.setStroke(new BasicStroke(3f));
                    g2.drawRoundRect(3, 3, ancho - 6, alto - 6, 14, 14);
                }
            } else {
                DibujanteCartas.dibujarCara(g2, carta.getNombre(), carta.esEspecial(), ancho, alto);
                if (carta.getEstado() == EstadoCarta.EMPAREJADA) {
                    g2.setColor(new Color(60, 170, 90, 60));
                    g2.fillRoundRect(2, 2, ancho - 4, alto - 4, 14, 14);
                }
            }
        } catch (DibujoCartaException ex) {
            dibujarAlternativa(g2, ancho, alto);
        } finally {
            g2.dispose();
        }
    }

    
    private void dibujarAlternativa(Graphics2D g, int ancho, int alto) {
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(new Color(235, 235, 235));
        g.fillRoundRect(2, 2, ancho - 4, alto - 4, 14, 14);
        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(2f));
        g.drawRoundRect(3, 3, ancho - 6, alto - 6, 14, 14);
        String texto = carta.getNombre() != null ? carta.getNombre() : "?";
        g.setFont(new Font("SansSerif", Font.BOLD, 13));
        FontMetrics fm = g.getFontMetrics();
        g.drawString(texto, (ancho - fm.stringWidth(texto)) / 2, (alto + fm.getAscent()) / 2 - 2);
    }
}
