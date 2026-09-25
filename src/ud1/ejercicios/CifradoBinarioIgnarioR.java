package ud1.ejercicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Ignacio Rodríguez
 */

public class CifradoBinarioIgnarioR {
    public static void main(String[] args) {

        try (var origen = new FileInputStream("1.png");
                var destino = new FileOutputStream("archivoCifrado.png");) {

            int cifrado;

            while ((cifrado = origen.read()) != -1) {
                destino.write(~cifrado);
            }

            System.out.println("Archivo cifrado correctamente");

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo.");
        } catch (IOException e) {
            System.out.println("Error al leer/escribir el archivo.");
        }
    }
}
