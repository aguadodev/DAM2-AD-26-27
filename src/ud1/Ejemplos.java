package ud1;
import java.io.File;

public class Ejemplos {
    public static void main(String[] args) {
        // Separadores de directorios según el sistema
        System.out.println(System.getProperty("file.separator"));

        System.out.println(java.io.File.separator);


        // Crear objeto File y comprobar si existe un fichero

        File javaFile = new File("prueba.txt");

        System.out.println(javaFile.exists()); // true, si el archivo existe

        
        // Constructores de objetos File

        File fichero1 = new File("DATOS", "ud1/prueba.txt");

        File directorioPadre = new File("DATOS");

        File fichero2 = new File(directorioPadre, "ud1/prueba.txt");

    }
}
