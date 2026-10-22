package ud2.appalumnado.instituto;

import java.time.LocalDate;

public class AlumnoDTO {
    private int id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String email;
    private Double notaMedia;
    
    // Constructores
    public AlumnoDTO(int id, String nombre, String apellidos, LocalDate fechaNacimiento, String email,
            Double notaMedia) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.notaMedia = notaMedia;
    }

    public AlumnoDTO(String nombre, String apellidos, LocalDate fechaNacimiento, String email, Double notaMedia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String curso) {
        this.email = curso;
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
                + fechaNacimiento + ", email=" + email + ", notaMedia=" + notaMedia + "]";
    }

}
