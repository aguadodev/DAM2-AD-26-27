package ud1.ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LecturaPorLineas {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce una palabra clave: ");
        String palabra = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader("src\\ud1\\prueba.txt"))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.contains(palabra)) {
                    System.out.println(linea);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }

        sc.close();
    }
}