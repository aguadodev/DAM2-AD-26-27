package ud2.appalumnado.pokemon;

import java.util.Scanner;

import ud2.appalumnado.canciones.AppCanciones;
import ud2.appalumnado.gimnasio.AppGimnasio;
import ud2.appalumnado.instituto.AppAlumnos;

public class App {
    public static void main(String[] args) {
        // Programa que accede a varias BDs

        // Menú principal
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\nMENU ALUMNOS");
            System.out.println("============\n");
            System.out.println("0. ----- ");
            System.out.println("1. Instituto");
            System.out.println("2. Canciones");
            System.out.println("3. Gimnasio ");
            System.out.println("4. ----- ");
            System.out.println("5. ----- ");
            System.out.println("6. Salir");
            System.out.print("\nSeleccione una opción: ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 0:
                    break;
                case 1: 
                    AppAlumnos.main();
                    break;
                case 2:
                    AppCanciones.main(null);
                    break;
                case 3:
                    AppGimnasio.main(null);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 9:
                    break;
                case 6:
                    // Salir
                    sc.close();
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        } while (true);
    }
}
