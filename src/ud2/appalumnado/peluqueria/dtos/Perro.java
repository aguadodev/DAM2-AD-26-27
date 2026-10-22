package ud2.appalumnado.peluqueria.dtos;

/** @author ANDRE PAZO */

public class Perro {
    private int id;
    private int chip;
    private String nombre;
    private String raza;
    private int edad;
    private boolean desparasitado;

    public Perro(int id, int chip, String nombre, String raza, int edad, boolean desparasitado) {
        this.id = id;
        this.chip = chip;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.desparasitado = desparasitado;
    }

    public Perro(int chip, String nombre, String raza, int edad, boolean desparasitado) {
        this.chip = chip;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.desparasitado = desparasitado;
    }

    @Override
    public String toString() {
        return nombre + " [" + chip + ", " + raza
                + ", " + edad + " meses, " + (desparasitado ? "Desparasitado" : "Sin desparasitar") + "] (autoincrental: "
                + id + ")";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChip() {
        return chip;
    }

    public void setChip(int chip) {
        this.chip = chip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isDesparasitado() {
        return desparasitado;
    }

    public void setDesparasitado(boolean desparasitado) {
        this.desparasitado = desparasitado;
    }

}
