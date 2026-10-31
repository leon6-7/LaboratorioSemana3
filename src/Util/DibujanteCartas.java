/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author USER
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;
import Exceptions.DibujoCartaException;
public class DibujanteCartas {
    
    public static final String[] NOMBRES_SOPORTADOS = {
        "Pikachu", "Charmander", "Bulbasaur", "Squirtle", "Jigglypuff", "Meowth",
        "Psyduck", "Gengar", "Eevee", "Snorlax", "Mewtwo", "Mew",
        "Togepi", "Poliwag", "Magikarp", "Voltorb", "Ditto", "Oddish"
    };
    
    private static final Color NEGRO = new Color(30, 30, 30);
    private static final Color BLANCO = Color.WHITE;
    private static final Color DORADO = new Color(212, 175, 55);
    private static final Color AMARILLO = new Color(250, 215, 60);
    private static final Color CREMA = new Color(245, 230, 190);
    private static final Color ROJO = new Color(220, 50, 50);
    
    private DibujanteCartas() {
    }
    
    public static void dibujarDorso(Graphics2D g, int ancho, int alto) {
        activarSuavizado(g);
        g.setColor(new Color(40, 70, 140));
        g.fillRoundRect(2, 2, ancho - 4, alto - 4, 14, 14);
        g.setColor(new Color(255, 255, 255, 90));
        g.setStroke(new BasicStroke(2f));
        g.drawRoundRect(6, 6, ancho - 12, alto - 12, 10, 10);

       
        int d = (int) (Math.min(ancho, alto) * 0.55);
        int x = (ancho - d) / 2;
        int y = (alto - d) / 2;
        g.setColor(ROJO);
        g.fillArc(x, y, d, d, 0, 180);
        g.setColor(BLANCO);
        g.fillArc(x, y, d, d, 180, 180);
        g.setColor(NEGRO);
        g.setStroke(new BasicStroke(Math.max(2f, d / 14f)));
        g.drawOval(x, y, d, d);
        g.drawLine(x, y + d / 2, x + d, y + d / 2);
        int c = d / 3;
        g.setColor(BLANCO);
        g.fillOval(x + (d - c) / 2, y + (d - c) / 2, c, c);
        g.setColor(NEGRO);
        g.drawOval(x + (d - c) / 2, y + (d - c) / 2, c, c);
    }
    public static void dibujarCara(Graphics2D g, String nombre, boolean especial, int ancho, int alto)
            throws DibujoCartaException {
        if (nombre == null || nombre.isBlank()) {
            throw new DibujoCartaException("La carta no tiene nombre");
        }
        activarSuavizado(g);

        g.setColor(especial ? new Color(255, 248, 220) : new Color(245, 245, 245));
        g.fillRoundRect(2, 2, ancho - 4, alto - 4, 14, 14);
        g.setColor(especial ? DORADO : new Color(160, 160, 160));
        g.setStroke(new BasicStroke(especial ? 5f : 2f));
        g.drawRoundRect(3, 3, ancho - 6, alto - 6, 14, 14);

        
        int altoTexto = Math.max(12, alto / 8);
        g.setFont(new Font("SansSerif", Font.BOLD, Math.max(9, altoTexto - 3)));
        FontMetrics fm = g.getFontMetrics();
        g.setColor(NEGRO);
        g.drawString(nombre, (ancho - fm.stringWidth(nombre)) / 2, alto - 9);

        
        int lado = Math.min(ancho - 14, alto - altoTexto - 16);
        Graphics2D lienzo = (Graphics2D) g.create();
        try {
            lienzo.translate((ancho - lado) / 2.0, 7);
            lienzo.scale(lado / 100.0, lado / 100.0);
            lienzo.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            dibujarPokemon(lienzo, nombre);
        } catch (RuntimeException e) {
            throw new DibujoCartaException("No se pudo dibujar a " + nombre, e);
        } finally {
            lienzo.dispose();
        }

        if (especial) {
            g.setColor(DORADO);
            g.fill(estrella(ancho - 16, 16, 9));
            g.setColor(NEGRO);
            g.setStroke(new BasicStroke(1f));
            g.draw(estrella(ancho - 16, 16, 9));
        }
    }

    private static void dibujarPokemon(Graphics2D g, String nombre) throws DibujoCartaException {
        switch (nombre) {
            case "Pikachu" -> pikachu(g);
            case "Charmander" -> charmander(g);
            case "Bulbasaur" -> bulbasaur(g);
            case "Squirtle" -> squirtle(g);
            case "Jigglypuff" -> jigglypuff(g);
            case "Meowth" -> meowth(g);
            case "Psyduck" -> psyduck(g);
            case "Gengar" -> gengar(g);
            case "Eevee" -> eevee(g);
            case "Snorlax" -> snorlax(g);
            case "Mewtwo" -> mewtwo(g);
            case "Mew" -> mew(g);
            case "Togepi" -> togepi(g);
            case "Poliwag" -> poliwag(g);
            case "Magikarp" -> magikarp(g);
            case "Voltorb" -> voltorb(g);
            case "Ditto" -> ditto(g);
            case "Oddish" -> oddish(g);
            default -> throw new DibujoCartaException("No hay dibujo para " + nombre);
        }
    }



    private static void pikachu(Graphics2D g) {
        rellenar(g, AMARILLO, poligono(32, 40, 14, 4, 44, 30));
        pintar(g, NEGRO, poligono(14, 4, 19, 16, 25, 11));
        rellenar(g, AMARILLO, poligono(68, 40, 86, 4, 56, 30));
        pintar(g, NEGRO, poligono(86, 4, 81, 16, 75, 11));
        rellenar(g, AMARILLO, elipse(20, 28, 60, 56));
        pintar(g, ROJO, elipse(23, 62, 13, 11));
        pintar(g, ROJO, elipse(64, 62, 13, 11));
        ojos(g, 37, 63, 52, 5);
        pintar(g, NEGRO, elipse(48, 59, 4, 3));
        sonrisa(g, 50, 66, 12);
    }

    private static void charmander(Graphics2D g) {
        Color naranja = new Color(245, 140, 60);
        g.setColor(naranja);
        g.setStroke(new BasicStroke(8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.draw(new QuadCurve2D.Double(65, 82, 90, 78, 87, 52));
        g.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        rellenar(g, new Color(240, 70, 40), elipse(79, 26, 16, 28));
        pintar(g, AMARILLO, elipse(83, 37, 8, 15));
        rellenar(g, naranja, elipse(22, 55, 50, 40));
        pintar(g, new Color(250, 225, 150), elipse(34, 66, 26, 25));
        rellenar(g, naranja, elipse(20, 12, 56, 52));
        ojos(g, 37, 59, 36, 6);
        sonrisa(g, 48, 50, 16);
    }

    private static void bulbasaur(Graphics2D g) {
        Color verdeAgua = new Color(120, 200, 170);
        rellenar(g, verdeAgua, poligono(20, 55, 16, 36, 34, 48));
        rellenar(g, verdeAgua, poligono(80, 55, 84, 36, 66, 48));
        rellenar(g, new Color(70, 160, 80), elipse(28, 6, 44, 46));
        g.setColor(new Color(40, 110, 50));
        g.draw(new Arc2D.Double(36, 10, 28, 40, 60, 60, Arc2D.OPEN));
        g.draw(new QuadCurve2D.Double(50, 8, 46, 28, 50, 46));
        rellenar(g, verdeAgua, elipse(10, 42, 80, 48));
        Color mancha = new Color(80, 150, 130);
        pintar(g, mancha, elipse(16, 66, 9, 7));
        pintar(g, mancha, elipse(76, 70, 8, 6));
        pintar(g, mancha, elipse(47, 46, 8, 5));
        ojoColor(g, 34, 64, 6, new Color(200, 50, 60));
        ojoColor(g, 66, 64, 6, new Color(200, 50, 60));
        sonrisa(g, 50, 76, 20);
    }

    private static void squirtle(Graphics2D g) {
        Color azul = new Color(130, 200, 235);
        rellenar(g, azul, elipse(68, 60, 26, 20));
        g.setColor(NEGRO);
        g.draw(new Arc2D.Double(76, 64, 10, 10, 90, 270, Arc2D.OPEN));
        rellenar(g, new Color(170, 110, 60), elipse(20, 52, 60, 44));
        rellenar(g, new Color(245, 220, 150), elipse(31, 58, 38, 34));
        g.drawLine(34, 70, 66, 70);
        g.drawLine(33, 81, 67, 81);
        rellenar(g, azul, elipse(25, 6, 50, 48));
        ojos(g, 39, 61, 28, 6);
        sonrisa(g, 50, 40, 16);
    }

    private static void jigglypuff(Graphics2D g) {
        Color rosa = new Color(250, 185, 205);
        rellenar(g, rosa, poligono(22, 38, 16, 8, 42, 22));
        pintar(g, NEGRO, poligono(19, 18, 16, 8, 27, 14));
        rellenar(g, rosa, poligono(78, 38, 84, 8, 58, 22));
        pintar(g, NEGRO, poligono(81, 18, 84, 8, 73, 14));
        rellenar(g, rosa, elipse(42, 6, 18, 18));
        rellenar(g, rosa, elipse(15, 15, 70, 70));
        g.setColor(NEGRO);
        g.draw(new Arc2D.Double(44, 18, 12, 12, 90, 270, Arc2D.OPEN));
        ojoGrande(g, 28, 40, new Color(60, 150, 160));
        ojoGrande(g, 54, 40, new Color(60, 150, 160));
        sonrisa(g, 50, 70, 10);
    }

    private static void meowth(Graphics2D g) {
        Color cafe = new Color(140, 90, 50);
        rellenar(g, CREMA, poligono(24, 42, 18, 8, 44, 28));
        pintar(g, cafe, poligono(24, 34, 21, 16, 34, 28));
        rellenar(g, CREMA, poligono(76, 42, 82, 8, 56, 28));
        pintar(g, cafe, poligono(76, 34, 79, 16, 66, 28));
        rellenar(g, CREMA, elipse(18, 24, 64, 60));
        rellenar(g, DORADO, elipse(42, 27, 16, 16));
        pintar(g, new Color(180, 140, 30), elipse(46, 31, 8, 8));
        ojos(g, 37, 63, 54, 5);
        sonrisa(g, 50, 68, 14);
        g.setColor(NEGRO);
        g.drawLine(22, 62, 4, 57);
        g.drawLine(22, 68, 4, 71);
        g.drawLine(78, 62, 96, 57);
        g.drawLine(78, 68, 96, 71);
    }

    private static void psyduck(Graphics2D g) {
        Color amarillo = new Color(250, 220, 90);
        g.setColor(NEGRO);
        g.drawLine(50, 22, 43, 5);
        g.drawLine(50, 22, 50, 3);
        g.drawLine(50, 22, 57, 5);
        rellenar(g, amarillo, elipse(20, 18, 60, 58));
        rellenar(g, new Color(250, 240, 190), elipse(31, 56, 38, 18));
        g.setColor(NEGRO);
        g.drawLine(34, 65, 66, 65);
        rellenar(g, BLANCO, elipse(31, 34, 15, 15));
        rellenar(g, BLANCO, elipse(54, 34, 15, 15));
        pintar(g, NEGRO, elipse(36, 40, 4, 4));
        pintar(g, NEGRO, elipse(60, 40, 4, 4));
    }

    private static void gengar(Graphics2D g) {
        rellenar(g, new Color(110, 70, 150), poligono(
                15, 88, 8, 42, 22, 32, 18, 10, 35, 24, 50, 6, 65, 24, 82, 10, 78, 32, 92, 42, 85, 88));
        rellenar(g, ROJO, poligono(26, 40, 44, 47, 30, 53));
        rellenar(g, ROJO, poligono(74, 40, 56, 47, 70, 53));
        rellenar(g, BLANCO, new Arc2D.Double(24, 48, 52, 32, 180, 180, Arc2D.CHORD));
        g.setColor(NEGRO);
        for (int x = 34; x <= 66; x += 8) {
            g.drawLine(x, 64, x, 77);
        }
    }

    private static void eevee(Graphics2D g) {
        Color cafe = new Color(185, 125, 70);
        Color cafeOscuro = new Color(120, 75, 40);
        rellenar(g, cafe, poligono(32, 40, 10, 2, 46, 28));
        pintar(g, cafeOscuro, poligono(30, 32, 16, 10, 40, 28));
        rellenar(g, cafe, poligono(68, 40, 90, 2, 54, 28));
        pintar(g, cafeOscuro, poligono(70, 32, 84, 10, 60, 28));
        rellenar(g, CREMA, poligono(18, 68, 28, 96, 39, 82, 50, 98, 61, 82, 72, 96, 82, 68));
        rellenar(g, cafe, elipse(24, 24, 52, 50));
        ojos(g, 39, 61, 50, 6);
        pintar(g, NEGRO, elipse(48, 57, 4, 3));
        sonrisa(g, 50, 63, 10);
    }

    private static void snorlax(Graphics2D g) {
        Color verdeOscuro = new Color(60, 100, 115);
        rellenar(g, verdeOscuro, poligono(24, 32, 26, 12, 40, 24));
        rellenar(g, verdeOscuro, poligono(76, 32, 74, 12, 60, 24));
        rellenar(g, verdeOscuro, elipse(10, 18, 80, 80));
        pintar(g, CREMA, elipse(28, 26, 44, 34));
        pintar(g, CREMA, elipse(22, 60, 56, 36));
        g.setColor(NEGRO);
        g.drawLine(36, 40, 45, 40);
        g.drawLine(55, 40, 64, 40);
        g.drawLine(43, 51, 57, 51);
        pintar(g, BLANCO, poligono(45, 51, 47, 47, 49, 51));
        pintar(g, BLANCO, poligono(51, 51, 53, 47, 55, 51));
    }

    private static void mewtwo(Graphics2D g) {
        Color lila = new Color(215, 200, 225);
        Color morado = new Color(140, 80, 170);
        g.setColor(morado);
        g.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.draw(new QuadCurve2D.Double(64, 40, 95, 50, 78, 92));
        g.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        rellenar(g, lila, poligono(33, 26, 28, 4, 44, 18));
        rellenar(g, lila, poligono(67, 26, 72, 4, 56, 18));
        rellenar(g, lila, elipse(28, 14, 44, 54));
        pintar(g, morado, elipse(35, 38, 11, 7));
        pintar(g, morado, elipse(54, 38, 11, 7));
        g.setColor(NEGRO);
        g.drawLine(45, 56, 55, 56);
    }

    private static void mew(Graphics2D g) {
        Color rosa = new Color(250, 190, 210);
        g.setColor(rosa);
        g.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.draw(new QuadCurve2D.Double(58, 70, 100, 85, 92, 24));
        g.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        rellenar(g, rosa, elipse(86, 10, 11, 16));
        rellenar(g, rosa, poligono(30, 38, 22, 12, 45, 28));
        rellenar(g, rosa, poligono(70, 38, 78, 12, 55, 28));
        rellenar(g, rosa, elipse(22, 24, 56, 52));
        ojoGrande(g, 32, 40, new Color(70, 130, 220));
        ojoGrande(g, 52, 40, new Color(70, 130, 220));
        sonrisa(g, 50, 66, 10);
    }

    private static void togepi(Graphics2D g) {
        rellenar(g, new Color(250, 240, 200), poligono(28, 34, 33, 10, 41, 26, 50, 4, 59, 26, 67, 10, 72, 34));
        rellenar(g, new Color(250, 240, 200), elipse(26, 20, 48, 42));
        ojos(g, 41, 59, 38, 4);
        sonrisa(g, 50, 46, 10);
        rellenar(g, BLANCO, poligono(18, 60, 30, 52, 40, 62, 50, 52, 60, 62, 70, 52, 82, 60, 80, 84, 68, 96, 32, 96, 20, 84));
        rellenar(g, ROJO, poligono(28, 72, 38, 66, 40, 80));
        rellenar(g, new Color(60, 110, 220), poligono(56, 70, 68, 68, 62, 82));
    }

    private static void poliwag(Graphics2D g) {
        Color azul = new Color(80, 130, 220);
        rellenar(g, new Color(230, 240, 250), elipse(70, 55, 28, 16));
        rellenar(g, azul, elipse(14, 18, 70, 72));
        rellenar(g, BLANCO, elipse(30, 42, 40, 38));
        g.setColor(NEGRO);
        g.draw(new Arc2D.Double(36, 47, 28, 28, 0, 180, Arc2D.OPEN));
        g.draw(new Arc2D.Double(36, 50, 22, 22, 180, 180, Arc2D.OPEN));
        g.draw(new Arc2D.Double(42, 53, 16, 16, 0, 180, Arc2D.OPEN));
        g.draw(new Arc2D.Double(42, 56, 10, 10, 180, 180, Arc2D.OPEN));
        rellenar(g, BLANCO, elipse(26, 14, 18, 18));
        rellenar(g, BLANCO, elipse(56, 14, 18, 18));
        pintar(g, NEGRO, elipse(32, 20, 7, 7));
        pintar(g, NEGRO, elipse(62, 20, 7, 7));
        rellenar(g, new Color(240, 150, 170), elipse(44, 34, 12, 6));
    }

    private static void magikarp(Graphics2D g) {
        Color naranja = new Color(240, 110, 60);
        Color aleta = new Color(250, 225, 150);
        rellenar(g, aleta, poligono(78, 50, 98, 28, 98, 72));
        rellenar(g, aleta, poligono(34, 34, 50, 12, 66, 34));
        rellenar(g, naranja, elipse(8, 28, 76, 46));
        g.setColor(new Color(200, 80, 40));
        for (int x = 40; x <= 64; x += 12) {
            g.draw(new Arc2D.Double(x, 42, 12, 16, 90, 180, Arc2D.OPEN));
        }
        rellenar(g, BLANCO, elipse(18, 36, 17, 17));
        pintar(g, NEGRO, elipse(23, 41, 7, 7));
        rellenar(g, new Color(250, 170, 170), elipse(6, 54, 11, 8));
        g.setColor(AMARILLO);
        g.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.draw(new QuadCurve2D.Double(16, 60, 8, 74, 22, 86));
    }

    private static void voltorb(Graphics2D g) {
        pintar(g, ROJO, new Arc2D.Double(14, 14, 72, 72, 0, 180, Arc2D.PIE));
        pintar(g, BLANCO, new Arc2D.Double(14, 14, 72, 72, 180, 180, Arc2D.PIE));
        g.setColor(NEGRO);
        g.draw(elipse(14, 14, 72, 72));
        g.drawLine(14, 50, 86, 50);
        rellenar(g, BLANCO, elipse(30, 30, 14, 12));
        rellenar(g, BLANCO, elipse(56, 30, 14, 12));
        pintar(g, NEGRO, elipse(35, 34, 5, 5));
        pintar(g, NEGRO, elipse(60, 34, 5, 5));
        g.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.drawLine(29, 24, 45, 30);
        g.drawLine(71, 24, 55, 30);
    }

    private static void ditto(Graphics2D g) {
        Path2D blob = new Path2D.Double();
        blob.moveTo(14, 86);
        blob.quadTo(4, 56, 24, 40);
        blob.quadTo(28, 14, 50, 26);
        blob.quadTo(72, 12, 78, 38);
        blob.quadTo(98, 56, 86, 86);
        blob.closePath();
        rellenar(g, new Color(195, 155, 225), blob);
        pintar(g, NEGRO, elipse(37, 50, 4, 4));
        pintar(g, NEGRO, elipse(59, 50, 4, 4));
        sonrisa(g, 50, 62, 26);
    }

    private static void oddish(Graphics2D g) {
        Color verde = new Color(70, 165, 70);
        hoja(g, verde, 50, 42, -40);
        hoja(g, verde, 50, 42, 0);
        hoja(g, verde, 50, 42, 40);
        Color azul = new Color(70, 90, 170);
        rellenar(g, azul, elipse(30, 84, 14, 10));
        rellenar(g, azul, elipse(56, 84, 14, 10));
        rellenar(g, azul, elipse(22, 38, 56, 52));
        pintar(g, new Color(210, 50, 50), elipse(36, 56, 8, 9));
        pintar(g, new Color(210, 50, 50), elipse(56, 56, 8, 9));
        sonrisa(g, 50, 74, 12);
    }


    private static void activarSuavizado(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

 
    private static void rellenar(Graphics2D g, Color color, Shape figura) {
        g.setColor(color);
        g.fill(figura);
        g.setColor(NEGRO);
        g.draw(figura);
    }

    private static void pintar(Graphics2D g, Color color, Shape figura) {
        g.setColor(color);
        g.fill(figura);
    }

    private static Ellipse2D elipse(double x, double y, double ancho, double alto) {
        return new Ellipse2D.Double(x, y, ancho, alto);
    }

    private static Path2D poligono(double... puntos) {
        Path2D camino = new Path2D.Double();
        camino.moveTo(puntos[0], puntos[1]);
        for (int i = 2; i < puntos.length; i += 2) {
            camino.lineTo(puntos[i], puntos[i + 1]);
        }
        camino.closePath();
        return camino;
    }

    private static void ojos(Graphics2D g, double xIzq, double xDer, double y, double radio) {
        ojoColor(g, xIzq, y, radio, NEGRO);
        ojoColor(g, xDer, y, radio, NEGRO);
    }

 
    private static void ojoColor(Graphics2D g, double x, double y, double radio, Color color) {
        pintar(g, color, elipse(x - radio, y - radio, radio * 2, radio * 2));
        pintar(g, BLANCO, elipse(x - radio * 0.6, y - radio * 0.7, radio * 0.7, radio * 0.7));
    }

 
    private static void ojoGrande(Graphics2D g, double x, double y, Color iris) {
        rellenar(g, BLANCO, elipse(x, y, 16, 18));
        pintar(g, iris, elipse(x + 3, y + 4, 11, 13));
        pintar(g, NEGRO, elipse(x + 6, y + 7, 6, 7));
        pintar(g, BLANCO, elipse(x + 5, y + 5, 4, 4));
    }

    private static void sonrisa(Graphics2D g, double x, double y, double ancho) {
        g.setColor(NEGRO);
        g.draw(new Arc2D.Double(x - ancho / 2, y - ancho / 4, ancho, ancho / 2, 200, 140, Arc2D.OPEN));
    }

    private static void hoja(Graphics2D g, Color color, double x, double y, double grados) {
        AffineTransform original = g.getTransform();
        g.rotate(Math.toRadians(grados), x, y);
        rellenar(g, color, elipse(x - 8, y - 40, 16, 42));
        g.setColor(new Color(40, 110, 40));
        g.drawLine((int) x, (int) y - 4, (int) x, (int) y - 34);
        g.setTransform(original);
    }

    private static Path2D estrella(double cx, double cy, double radio) {
        Path2D camino = new Path2D.Double();
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? radio : radio * 0.45;
            double angulo = Math.toRadians(-90 + i * 36);
            double px = cx + r * Math.cos(angulo);
            double py = cy + r * Math.sin(angulo);
            if (i == 0) {
                camino.moveTo(px, py);
            } else {
                camino.lineTo(px, py);
            }
        }
        camino.closePath();
        return camino;
    }
    
}
