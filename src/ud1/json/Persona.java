package ud1.json;
import java.io.Serializable;
import java.time.LocalDate;

public class Persona implements Serializable{
    private String nombre;
    private LocalDate fechaNacimiento;
    private int altura;

    public Persona(String nombre, LocalDate fechaNacimiento, int altura) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", altura=" + altura + "]";
    }

}
