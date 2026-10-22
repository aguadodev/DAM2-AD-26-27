package ud2.appalumnado.restaurante;

public class PlatoDTO 
{
    private int id;
    private String nombre;
    private double precio;
    private String categoria;
    private boolean disponible;

    // Constructor vacío (estándar JavaBean)
    public PlatoDTO() {
    }

    // Constructor completo (usado al leer de la BD)
    public PlatoDTO(int id, String nombre, double precio, String categoria, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.disponible = disponible;
    }

    // Constructor sin id (para inserciones donde la BD autogenera el id)
    public PlatoDTO(String nombre, double precio, String categoria, boolean disponible) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.disponible = disponible;
    }

    // Getters y Setters
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "PlatoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}