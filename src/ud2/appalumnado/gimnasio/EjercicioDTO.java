package ud2.appalumnado.gimnasio;

public class EjercicioDTO {
    private int id;
    private String nombre;
    private String musculosInvolucrados;
    private int series;
    private int repeticiones;

    public EjercicioDTO() {
    }

    public EjercicioDTO(String nombre, String musculosInvolucrados, int series, int repeticiones) {
        this.nombre = nombre;
        this.musculosInvolucrados = musculosInvolucrados;
        this.series = series;
        this.repeticiones = repeticiones;
    }

    public EjercicioDTO(int id, String nombre, String musculosInvolucrados, int series, int repeticiones) {
        this.id = id;
        this.nombre = nombre;
        this.musculosInvolucrados = musculosInvolucrados;
        this.series = series;
        this.repeticiones = repeticiones;
    }

    public EjercicioDTO(int id) {
        this.id = id;
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

    public String getMusculosInvolucrados() {
        return musculosInvolucrados;
    }

    public void setMusculosInvolucrados(String musculosInvolucrados) {
        this.musculosInvolucrados = musculosInvolucrados;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + musculosInvolucrados + "): " + series + "x" + repeticiones;
    }
}