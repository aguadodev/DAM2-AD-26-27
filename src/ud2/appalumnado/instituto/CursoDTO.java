package ud2.appalumnado.instituto;

public class CursoDTO {

    private int id;
    private String nombre;
    private int horas;

    // Constructores

    public CursoDTO() {
    }

    public CursoDTO(String nombre, int horas) {
        this.nombre = nombre;
        this.horas = horas;
    }

    public CursoDTO(int id, String nombre, int horas) {
        this.id = id;
        this.nombre = nombre;
        this.horas = horas;
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

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    // toString()
    @Override
    public String toString() {
        return "CursoDTO [id=" + id + ", nombre=" + nombre + ", horas=" + horas + "]";
    }

}
