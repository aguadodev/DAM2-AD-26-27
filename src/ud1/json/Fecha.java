package ud1.json;

import java.io.Serializable;

public class Fecha implements Serializable {
    int dia;
    int mes;
    int anho;

    public Fecha() {
    }

    public static Fecha of(int anho, int mes, int dia) {
        Fecha fecha = new Fecha();
        fecha.dia = dia;
        fecha.mes = mes;
        fecha.anho = anho;
        return fecha;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anho;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

}
