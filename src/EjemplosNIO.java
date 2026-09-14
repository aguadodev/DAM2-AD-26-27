import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EjemplosNIO {
    public static void main(String[] args) {

        // Conversiones entre File y Path
        File file = new File("prueba.txt");

        Path path = file.toPath(); // De File a Path

        File file2 = path.toFile(); // De Path a File

        // Funcionalidades java.io y java.nio
        file.delete(); // Borrar con java.io

        try {
            Files.delete(path); // Borrar con java.nio (Posibles excepciones)
        } catch (IOException e) {
        }

        // Creación de Paths
        // Path.of()
        Path path1 = Path.of("fotos/batman.png");
        Path path2 = Path.of("c:\\users\\usuarioa\\notas.txt");
        Path path3 = Path.of("/home/usuarioa");

        // Path.of(varags)
        Path path1b = Path.of("fotos", "batman.png");
        Path path2b = Path.of("c:", "users", "usuarioa", "notas.txt");
        Path path3b = Path.of("/", "home", "usuarioa");

        // Paths.get()
        Path path1c = Paths.get("fotos/batman.png");
        Path path2c = Paths.get("c:\\users\\usuarioa\\notas.txt");
        Path path3c = Paths.get("/", "home", "usuarioa");

        // Paths y URIs
        try {
            URI uri = new URI("file:///home/usuarioa/Prueba.txt");
            Path pathUri = Path.of(uri); // Creación de Path a partir de URI.
            Path pathUri2 = Paths.get(uri); // Creación de Path a partir de URI.
            URI uri2 = pathUri.toUri(); // Conversión de un Path en URI.
        } catch (URISyntaxException e) { // Posible Excepción!!
        }

        // FileSystem.getPath()
        FileSystem fileSystem = FileSystems.getDefault();
        Path path1d = fileSystem.getPath("fotos/batman.png");
        Path path2d = fileSystem.getPath("c:\\users\\usuarioa\\notas.txt");
        Path path3d = fileSystem.getPath("/home/usuarioa");

        /*try {
            fileSystem = FileSystems.getFileSystem(new URI("https://www.imdb.com"));
            Path pathImdb = fileSystem.getPath("top250.txt");
            System.out.println(Files.exists(pathImdb));

        } catch (URISyntaxException e) {

        }*/

        Path p = Path.of("ballena");
        p = p.resolve("krill"); // Se pierde, debería guardarse en otro Path.
        System.out.println(p); // ballena

        p = Path.of("/zoo/../home");
        p = p.getParent();
        p = p.normalize();
        p = p.toAbsolutePath();
        System.out.println(p);

    }
}
