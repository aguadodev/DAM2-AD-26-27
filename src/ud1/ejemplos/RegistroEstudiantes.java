package ud1.ejemplos;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class RegistroEstudiantes {

    public static void main(String[] args) throws IOException { // En realidad es mala opción lanzar la excepción, pero es para simplificar el ejemplo

        try (RandomAccessFile file = new RandomAccessFile("DATOS\\estudiantes.txt", "rw")) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Introduce el número de estudiantes: ");
            int numEstudiantes = scanner.nextInt();
            file.writeInt(numEstudiantes);

            for (int i = 0; i < numEstudiantes; i++) {
                System.out.println("Introduce el nombre del estudiante " + (i + 1) + ": ");
                String nombre = scanner.next();
                file.writeUTF(nombre);
            }

            System.out.println("Introduce el número del estudiante a leer: ");
            int numEstudiante = scanner.nextInt();
            scanner.close();

            file.seek(0);
            int numEstudiantesGuardados = file.readInt();

            if (numEstudiante > numEstudiantesGuardados) {
                System.out.println("No hay tantos estudiantes guardados.");
            } else {
                file.seek(4); // Saltamos el número de estudiantes
                for (int i = 0; i < numEstudiante - 1; i++) {
                    file.readUTF();
                }
                System.out.println("El estudiante " + numEstudiante + " es: " + file.readUTF());
            }
        }
    }
}