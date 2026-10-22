package ud2.appalumnado.canciones;

import java.time.LocalDate;

public class CancionDTO3 {
    private int id;
    private String nombre;
    private String genero;
    private LocalDate fPublicacion;
    private int visualizaciones;
    private double duracion;

    public CancionDTO3(int id, String nombre, String genero, LocalDate fPublicacion, int visualizaciones,
            double ingresosGenerados) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.fPublicacion = fPublicacion;
        this.visualizaciones = visualizaciones;
        this.duracion = ingresosGenerados;
    }

    public CancionDTO3(String nombre, String genero, LocalDate fPublicacion, int visualizaciones, double duracion) {
        this.nombre = nombre;
        this.genero = genero;
        this.fPublicacion = fPublicacion;
        this.visualizaciones = visualizaciones;
        this.duracion = duracion;
    }

    public CancionDTO3(int id) {
        this.id = id;
    }

    public CancionDTO3() {
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getfPublicacion() {
        return fPublicacion;
    }

    public void setfPublicacion(LocalDate fPublicacion) {
        this.fPublicacion = fPublicacion;
    }

    public int getVisualizaciones() {
        return visualizaciones;
    }

    public void setVisualizaciones(int visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double ingresosGenerados) {
        this.duracion = ingresosGenerados;
    }

    @Override
    public String toString() {
        return "Musica [id=" + id + ", nombre=" + nombre + ", genero=" + genero + ", fPublicacion=" + fPublicacion
                + ", visualizaciones=" + visualizaciones + ", duracion=" + duracion + "]";
    }
}
