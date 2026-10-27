package ud2.appalumnado.instituto;

import java.util.Scanner;

public class AppInstituto {
    public static void main() {

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\nMENU INSTITUTO");
            System.out.println("============\n");
            System.out.println("1. Gestionar Alumnado");
            System.out.println("2. Gestionar Cursos");
            System.out.println("3. Gestionar Módulos");
            System.out.println("4. Buscar Alumno por Curso");
            System.out.println("5. Alumnos por Curso");
            System.out.println("6. Alumnos por Curso");
            System.out.println("0. Salir");
            System.out.print("\nSeleccione una opción: ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    AppAlumnos.main();
                    break;
                case 2:
                    AppCursos.main();
                    break;
                case 3:
                    AppModulos.main();
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 0:
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
