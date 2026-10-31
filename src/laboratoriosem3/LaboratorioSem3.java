
package laboratoriosem3;

import laboratoriosem3.control.ControladorJuego;
import laboratoriosem3.vista.VentanaInicio;
import javax.swing.SwingUtilities;

public class LaboratorioSem3 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            VentanaInicio inicio = new VentanaInicio((nombre1, nombre2) -> {
                ControladorJuego control = new ControladorJuego();
                control.iniciarJuego(nombre1, nombre2);
                return control;
            });

            inicio.setVisible(true);
        });
    }
}
