package ud1.ejercicios;

import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Darío Quintillán
 */

public class ComparadorFichero {
    public static void main(String[] args) {

        String ruta = "DATOS/alumnos.txt";
        String ruta2 = "DATOS/alumnos2.txt";

        try (var in1 = new FileReader(ruta);
                var in2 = new FileReader(ruta2)) {
            int c1;
            int c2;
            int linea = 1;
            int columna = 1;
            boolean fin = false;

            while (!fin) {
                c1 = in1.read();
                c2 = in2.read();

                if (c1 != c2) {
                    System.out.println("Los ficheros son distintos");
                    System.out.println("Linea: " + linea);
                    System.out.println("Columna: " + columna);
                    fin = true;
                } else if (c1 == -1) {
                    System.out.println("los ficheros son iguales");
                    fin = true;
                } else if (c1 == '\n') {
                    linea++;
                    columna = 1;
                } else {
                    columna++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer los ficheros");
        }
    }

}
