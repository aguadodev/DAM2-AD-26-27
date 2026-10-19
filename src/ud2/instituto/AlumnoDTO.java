package ud2.instituto;

import java.time.LocalDate;

public class AlumnoDTO {
    private int id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String curso;
    private Double notaMedia;
    
    // Constructores
    public AlumnoDTO(int id, String nombre, String apellidos, LocalDate fechaNacimiento, String curso,
            Double notaMedia) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.curso = curso;
        this.notaMedia = notaMedia;
    }

    public AlumnoDTO(String nombre, String apellidos, LocalDate fechaNacimiento, String curso, Double notaMedia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.curso = curso;
        this.notaMedia = notaMedia;
    }

    public AlumnoDTO(int id) {
        this.id = id;
    }

    public AlumnoDTO() {
    }

    // Setters y Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    // toString()
    @Override
    public String toString() {
        return "AlumnoDTO [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
                + fechaNacimiento + ", curso=" + curso + ", notaMedia=" + notaMedia + "]";
    }

}
