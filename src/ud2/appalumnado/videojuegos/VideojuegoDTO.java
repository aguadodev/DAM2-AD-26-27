package ud2.appalumnado.videojuegos;

import java.time.LocalDate;

public class VideojuegoDTO {
    int id;
    String nombre;
    LocalDate fechaLanzamiento;
    Double precio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VideojuegoDTO(int id, String nombre, LocalDate fechaLanzamiento, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precio = precio;
    }

    public VideojuegoDTO(String nombre, LocalDate fechaLanzamiento, Double precio) {
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precio = precio;
    }

    public VideojuegoDTO(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Nombre: " + nombre + " Fecha de Lanzamiento: " + fechaLanzamiento + " Precio: " + precio;
    }

}
