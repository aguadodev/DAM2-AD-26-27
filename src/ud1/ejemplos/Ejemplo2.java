package ud1.ejemplos;
import java.io.File;


public class Ejemplo2 {
    public static void main(String[] args) {
        var arquivo = new File("c:\\users");
        System.out.println("Archivo existe: " + arquivo.exists());
        if (arquivo.exists()) {
            System.out.println("Ruta absoluta: " + arquivo.getAbsolutePath());
            System.out.println("Es un directorio: " + arquivo.isDirectory());
            System.out.println("Ruta padre: " + arquivo.getParent());
            if (arquivo.isFile()) {
                System.out.println("Tamaño: " + arquivo.length());
                System.out.println("Última modificación: " + arquivo.lastModified());
            } else {
                for (File subArquivo : arquivo.listFiles()) {
                    System.out.println(" " + subArquivo.getName());
                }
            }
        }
    }
}
