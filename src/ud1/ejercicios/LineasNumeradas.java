package ud1.ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
    /**
     * @author HugoDíazRuadas
     */
public class LineasNumeradas {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("fichero2.txt"));
            BufferedWriter out = new BufferedWriter(new FileWriter("fichero2COPIA.txt"));) {
            List<String> lineas = in.readAllLines();
            for (int i = 0; i < lineas.size(); i++) {
                out.write((i+1) + ": " + lineas.get(i) + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e1) {
            System.out.println("Error entrada salida");
        }
    }
}
