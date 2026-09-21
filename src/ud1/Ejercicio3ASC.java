package ud1;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Ejercicio3ASC {

    // Herramienta para abrir la ventana de selección de archivos/carpetas
    static JFileChooser selector = new JFileChooser();
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println("\n===== GESTOR DE ARCHIVOS =====");
            System.out.println("1. Crear un directorio");
            System.out.println("2. Listar directorio (recursivo)");
            System.out.println("3. Eliminar archivo o directorio (recursivo)");
            System.out.println("4. Mover o renombrar");
            System.out.println("5. Salir");
            System.out.print("Elige una opcion: ");

            opcion = teclado.nextInt();
            teclado.nextLine(); // Limpiar el intro del teclado

            switch (opcion) {
                case 1:
                    crearDirectorio();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    eliminar();
                    break;
                case 4:
                    moverRenombrar();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 5);
    }

    // 1. CREAR DIRECTORIO
    public static void crearDirectorio() {
        System.out.println("\nSelecciona en la ventana donde quieres crear la carpeta...");
        selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int respuesta = selector.showOpenDialog(null);
        File carpetaPadre;
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            carpetaPadre = selector.getSelectedFile();
        } else {
            System.out.println("Directorio no valido");
            return;
        }

        System.out.print("Introduce el nombre de la nueva carpeta: ");
        String nuevoNombre = teclado.nextLine();

        File nuevaCarpeta = new File(carpetaPadre, nuevoNombre);
        if (nuevaCarpeta.exists()) {
            System.out.println("El directorio ya existe");
        } else {
            if (nuevaCarpeta.mkdir()) {
                System.out.println("Directorio creado con exito en: " + nuevaCarpeta.getAbsolutePath());
            } else {
                System.out.println("No se pudo crear.");
            }
        }

    }

    // 2. LISTADO RECURSIVO
    public static void listar() {
        System.out.println("\nSelecciona la carpeta a inspeccionar...");
        selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int respuesta = selector.showOpenDialog(null);

        File raiz = selector.getSelectedFile();
        System.out.println("\n--- CONTENIDO DE " + raiz.getName() + " ---");
        listarRecursivo(raiz, "");

    }

    // El metodo recursivo que se llama a si mismo
    private static void listarRecursivo(File carpeta, String sangria) {
        File[] elementos = carpeta.listFiles();

        if (elementos == null) {
            return;
        }

        for (File f : elementos) {
            if (f.isDirectory()) {
                System.out.println(sangria + "[CARPETA] " + f.getName());
                // Si encontramos una carpeta, volvemos a llamar a este metodo metiendo mas
                // sangria
                listarRecursivo(f, sangria + "   ");
            } else {
                System.out.println(sangria + "[ARCHIVO] " + f.getName() + " (" + f.length() + " bytes)");
            }
        }

    }

    // 3. ELIMINAR RECURSIVO
    public static void eliminar() {
        System.out.println("\nSelecciona el archivo o carpeta que quieres BORRAR...");
        selector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int respuesta = selector.showOpenDialog(null);

        File objetivo = selector.getSelectedFile();
        eliminarRecursivo(objetivo);
        System.out.println("Elemento eliminado.");

    }

    // Para borrar una carpeta en Java, tiene que estar 100% vacia primero
    private static void eliminarRecursivo(File elemento) {
        if (elemento.isDirectory()) {
            File[] hijos = elemento.listFiles();
            if (hijos != null) {
                for (File h : hijos) {
                    eliminarRecursivo(h); // Vaciamos primero lo de dentro
                }
            }
        }
        // Una vez vacia (o si era un archivo simple), la borramos del disco
        elemento.delete();
    }

    // 4. MOVER O RENOMBRAR
    public static void moverRenombrar() {
        System.out.println("\nSelecciona el archivo o carpeta que quieres renombrar/mover...");
        selector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int respuesta = selector.showOpenDialog(null);

        File origen = selector.getSelectedFile();
        System.out.print("Introduce el nuevo nombre o ruta completa de destino: ");
        String nuevoNombre = teclado.nextLine();

        // Creamos el destino en la misma carpeta cambiando solo el nombre
        File destino = new File(origen.getParentFile(), nuevoNombre);

        if (origen.renameTo(destino)) {
            System.out.println("Operacion realizada con exito.");
        } else {
            System.out.println("Error al renombrar/mover.");
        }

    }
}
