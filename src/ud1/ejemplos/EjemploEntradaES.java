package ud1.ejemplos;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * EjEntradaES
 */
public class EjemploEntradaES {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            char letra = (char) System.in.read();
            int disponibles = System.in.available();
            System.out.println("Disponibles: " + disponibles);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Escribe una frase:");
        String str = sc.nextLine();
        System.err.println("ERR: " + str);
    }
}