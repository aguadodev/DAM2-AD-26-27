package ud2.appalumnado.instituto.dto;

public class ModuloDTO {

    private int id;
    private String nombre;
    private int horas;

    public ModuloDTO(int id, String nombre, int horas) {
        this.id = id;
        this.nombre = nombre;
        this.horas = horas;
    }

    public ModuloDTO(String nombre, int horas) {
        this.nombre = nombre;
        this.horas = horas;
    }

    public ModuloDTO() {
    }

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

    @Override
    public String toString() {
        return "ModuloDTO [id=" + id + ", nombre=" + nombre + ", horas=" + horas + "]";
    }

}
