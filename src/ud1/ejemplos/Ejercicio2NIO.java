package ud1.ejemplos;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.Stream;

import javax.swing.JFileChooser;

public class Ejercicio2NIO {

    static long totalSize = 0;

    public static void imprime(Path p) {
        System.out.print("Nombre: " + p.getFileName());
        try {
            long size = Files.size(p);
            totalSize += size;
            System.out.print(" (" + size + ")");
            System.out.println(Files.isDirectory(p) ? " DIRECTORIO" : "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            Path path = chooser.getSelectedFile().toPath();
            // Try-catch con recursos (cierra automáticamente el flujo)
            try (Stream<Path> stream = Files.list(path)) {
                stream.forEach(Ejercicio2NIO::imprime);

                System.out.println("Tamaño Total: " + totalSize + " bytes");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
