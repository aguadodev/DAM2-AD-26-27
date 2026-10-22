package ud2.appalumnado.pokemon;

import java.time.LocalDate;

public class PokemonDTO {

    int npokedex;
    String nombre, tipo, region;
    Double peso;
    LocalDate captura;

    // Constructor con ID
    public PokemonDTO(int id, String nombre, String region, String tipo, Double peso, LocalDate captura) {
        this.npokedex = id;
        this.nombre = nombre;
        this.region = region;
        this.tipo = tipo;
        this.peso = peso;
        this.captura = captura;
    }

    // Constructor sin ID
    public PokemonDTO(String nombre, String region, String tipo, Double peso, LocalDate captura) {
        this.nombre = nombre;
        this.region = region;
        this.tipo = tipo;
        this.peso = peso;
        this.captura = captura;
    }

    public int getNpokedex() {
        return npokedex;
    }

    public void setNpokedex(int id) {
        this.npokedex = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public LocalDate getCaptura() {
        return captura;
    }

    public void setCaptura(LocalDate captura) {
        this.captura = captura;
    }

    @Override
    public String toString() {
        return "\n" + npokedex + " - " + nombre + "\nRegión: " + region + "\nTipo: " + tipo + "\nPeso: "
                + peso + "\nFecha de captura: " + captura + "\n";
    }
}