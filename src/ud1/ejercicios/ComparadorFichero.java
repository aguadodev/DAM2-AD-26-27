package ud1.ejercicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ComparadorFichero {
    /**
     * 
     * @author Darío Quintillán
     */
    public static void main(String[] args) {
        String ruta = "DATOS/alumnos.txt";
        String ruta2 = "DATOS/alumnos2.txt";
        System.out.println("Leyendo fichero " + ruta);
        try (var in1 = new FileInputStream(ruta); var in2 = new FileInputStream(ruta2);) {
            boolean sonIguales = true;
            int c;
            while ((c = in1.read()) != -1) {
                if (c != in2.read())
                    sonIguales = false;
            }
            if (sonIguales == true) {
                System.out.println("Son iguales");
            } else {
                System.out.println("No son iguales");
            }
        } catch (FileNotFoundException e) {
            // TODO: handle exception
        } catch (IOException e) {
            // TODO: handle exception
        }

    }

}
