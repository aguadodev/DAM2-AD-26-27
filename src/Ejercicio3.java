import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {

        int opcion = opcionMenu();

        while (opcion >= 1 && opcion <= 4) {
            switch (opcion) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;

                default:
                    break;
            }
            opcion = opcionMenu();
        }
    }

    private static int opcionMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("GESTOR DE ARCHIVOS Y DIRECTORIOS");
        System.out.println("1. Crear un directorio");
        System.out.println("2. Listar directorio");
        System.out.println("3. Eliminar un archivo o directorio");
        System.out.println("4. Mover o renombrar archivos y directorios");
        System.out.println("SALIR (Cualquier otra tecla)");
        System.out.print("Opción:");
        int opcion = sc.nextInt();
        return opcion;
    }
}
