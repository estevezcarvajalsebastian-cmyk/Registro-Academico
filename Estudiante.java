package registroacademico;

/**
 * Representa a un estudiante y sus calificaciones académicas.
 *
 * @author Estudiante
 */
public class Estudiante {

    // Atributos personales
    private String nombreCompleto;
    private String matricula;
    private String carrera;

    // Atributos de calificaciones
    private double practicas;
    private double parciales;
    private double asignaciones;
    private double examenFinal;

    /**
     * Constructor de la clase Estudiante.
     */
    public Estudiante(String nombreCompleto, String matricula, String carrera,
            double practicas, double parciales,
            double asignaciones, double examenFinal) {

        setNombreCompleto(nombreCompleto);
        setMatricula(matricula);
        setCarrera(carrera);
        setPracticas(practicas);
        setParciales(parciales);
        setAsignaciones(asignaciones);
        setExamenFinal(examenFinal);
    }

    // Getters y setters de datos personales

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    // Getters y setters de calificaciones

    public double getPracticas() {
        return practicas;
    }

    public void setPracticas(double practicas) {
        validarNota(practicas, 40, "Prácticas");
        this.practicas = practicas;
    }

    public double getParciales() {
        return parciales;
    }

    public void setParciales(double parciales) {
        validarNota(parciales, 20, "Parciales");
        this.parciales = parciales;
    }

    public double getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(double asignaciones) {
        validarNota(asignaciones, 20, "Asignaciones");
        this.asignaciones = asignaciones;
    }

    public double getExamenFinal() {
        return examenFinal;
    }

    public void setExamenFinal(double examenFinal) {
        validarNota(examenFinal, 20, "Examen Final");
        this.examenFinal = examenFinal;
    }

    /**
     * Valida que una calificación esté dentro de su rango permitido.
     */
    private void validarNota(double nota, double maximo, String concepto) {
        if (nota < 0 || nota > maximo) {
            throw new IllegalArgumentException(
                    concepto + " debe estar entre 0 y " + maximo);
        }
    }

    /**
     * Calcula la suma total de las cuatro calificaciones.
     *
     * @return nota total del estudiante.
     */
    public double calcularNotaFinal() {
        return practicas + parciales + asignaciones + examenFinal;
    }

    /**
     * Determina si el estudiante aprobó la materia.
     *
     * @return true si obtuvo 60 puntos o más.
     */
    public boolean estaAprobado() {
        return calcularNotaFinal() >= 60;
    }
}