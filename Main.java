package registroacademico;

import javax.swing.SwingUtilities;

/**
 * Clase principal del sistema de registro académico.
 *
 * @author Estudiante
 */
public class Main {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de ejecución.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            RegistroAcademicoGUI ventana = new RegistroAcademicoGUI();

            ventana.setVisible(true);

        });
    }
}