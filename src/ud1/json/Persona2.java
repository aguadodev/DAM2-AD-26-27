package ud1.json;

import java.io.Serializable;

public class Persona2 implements Serializable {
    private String nombre;
    private Fecha fechaNacimiento;
    private int altura;

    public Persona2() {
    }

    public Persona2(String nombre, Fecha fechaNacimiento, int altura) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", altura=" + altura + "]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

}
