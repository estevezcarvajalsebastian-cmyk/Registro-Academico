package registroacademico;

import javax.swing.*;
import java.awt.*;

/**
 * Interfaz gráfica para registrar estudiantes
 * y calcular sus calificaciones académicas.
 *
 * @author Estudiante
 */
public class RegistroAcademicoGUI extends JFrame {

    // Campos de información personal
    private JTextField txtNombre;
    private JTextField txtMatricula;
    private JTextField txtCarrera;

    // Controles numéricos de calificaciones
    private JSpinner spPracticas;
    private JSpinner spParciales;
    private JSpinner spAsignaciones;
    private JSpinner spExamenFinal;

    // Etiquetas para mostrar resultados
    private JLabel lblNotaTotal;
    private JLabel lblResultado;

    /**
     * Constructor de la interfaz.
     */
    public RegistroAcademicoGUI() {

        setTitle("Sistema de Registro Académico");
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        crearInterfaz();
    }

    /**
     * Construye y organiza los componentes gráficos.
     */
    private void crearInterfaz() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 7, 7, 7);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int fila = 0;

        // Título principal
        JLabel titulo = new JLabel("REGISTRO ACADÉMICO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 23));
        titulo.setForeground(new Color(30, 60, 100));

        gbc.gridx = 0;
        gbc.gridy = fila++;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        // Subtítulo
        JLabel subtitulo = new JLabel("Información del estudiante",
                SwingConstants.CENTER);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridy = fila++;
        panel.add(subtitulo, gbc);

        gbc.gridwidth = 1;

        // Datos personales
        txtNombre = new JTextField(20);
        txtMatricula = new JTextField(20);
        txtCarrera = new JTextField(20);

        agregarCampo(panel, gbc, fila++, "Nombre completo:", txtNombre);
        agregarCampo(panel, gbc, fila++, "Matrícula:", txtMatricula);
        agregarCampo(panel, gbc, fila++, "Carrera:", txtCarrera);

        // Separador
        JSeparator separador = new JSeparator();

        gbc.gridx = 0;
        gbc.gridy = fila++;
        gbc.gridwidth = 2;
        panel.add(separador, gbc);

        // Título de calificaciones
        JLabel tituloNotas = new JLabel("CALIFICACIONES");
        tituloNotas.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridy = fila++;
        panel.add(tituloNotas, gbc);

        gbc.gridwidth = 1;

        // Spinners con sus respectivos rangos
        spPracticas = crearSpinner(40);
        spParciales = crearSpinner(20);
        spAsignaciones = crearSpinner(20);
        spExamenFinal = crearSpinner(20);

        agregarCampo(panel, gbc, fila++, "Prácticas (0-40):", spPracticas);
        agregarCampo(panel, gbc, fila++, "Parciales (0-20):", spParciales);
        agregarCampo(panel, gbc, fila++, "Asignaciones (0-20):", spAsignaciones);
        agregarCampo(panel, gbc, fila++, "Examen Final (0-20):", spExamenFinal);

        // Botón de cálculo
        JButton btnCalcular = new JButton("Calcular Resultado");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 15));
        btnCalcular.setBackground(new Color(30, 100, 170));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = fila++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 7, 15, 7);

        panel.add(btnCalcular, gbc);

        // Etiquetas de resultados
        lblNotaTotal = new JLabel("Nota total: --", SwingConstants.CENTER);
        lblNotaTotal.setFont(new Font("Arial", Font.BOLD, 18));

        gbc.gridy = fila++;
        panel.add(lblNotaTotal, gbc);

        lblResultado = new JLabel("Resultado: Pendiente",
                SwingConstants.CENTER);
        lblResultado.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridy = fila++;
        panel.add(lblResultado, gbc);

        // Evento del botón
        btnCalcular.addActionListener(e -> calcularResultado());

        add(panel);
    }

    /**
     * Crea un control numérico con mínimo cero y máximo indicado.
     */
    private JSpinner crearSpinner(int maximo) {

        return new JSpinner(
                new SpinnerNumberModel(0.0, 0.0, (double) maximo, 0.5)
        );
    }

    /**
     * Agrega una etiqueta y un componente a una fila del formulario.
     */
    private void agregarCampo(JPanel panel, GridBagConstraints gbc,
            int fila, String texto, JComponent componente) {

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.weightx = 0.4;

        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 13));
        panel.add(etiqueta, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.6;

        panel.add(componente, gbc);
    }

    /**
     * Captura los datos, valida la información y calcula el resultado.
     */
    private void calcularResultado() {

        try {

            // Validación de campos personales
            String nombre = txtNombre.getText().trim();
            String matricula = txtMatricula.getText().trim();
            String carrera = txtCarrera.getText().trim();

            if (nombre.isEmpty() || matricula.isEmpty()
                    || carrera.isEmpty()) {

                throw new IllegalArgumentException(
                        "Debe completar todos los datos personales.");
            }

            // Obtener las calificaciones
            double practicas = ((Number) spPracticas.getValue()).doubleValue();

            double parciales = ((Number) spParciales.getValue()).doubleValue();

            double asignaciones =
                    ((Number) spAsignaciones.getValue()).doubleValue();

            double examenFinal =
                    ((Number) spExamenFinal.getValue()).doubleValue();

            // Crear el objeto estudiante
            Estudiante estudiante = new Estudiante(
                    nombre,
                    matricula,
                    carrera,
                    practicas,
                    parciales,
                    asignaciones,
                    examenFinal
            );

            // Calcular la nota final
            double nota = estudiante.calcularNotaFinal();

            lblNotaTotal.setText(
                    String.format("Nota total: %.2f / 100", nota)
            );

            // Determinar si aprobó o reprobó
            if (estudiante.estaAprobado()) {

                lblResultado.setText("RESULTADO: APROBADO");
                lblResultado.setForeground(new Color(0, 140, 60));

            } else {

                lblResultado.setText("RESULTADO: REPROBADO");
                lblResultado.setForeground(Color.RED);
            }

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error al procesar los datos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}