import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class ParticipacionEnClase {
    public static void main(String[] args) {
        System.out.println("PARTICIPACIÓN EN CLASE");
        System.out.println("**********************");
        System.out.println("Leyendo fichero DATOS/alumnos.txt");
        try (var in = new BufferedReader(new FileReader("DATOS/alumnos.txt")); 
             var out = new BufferedWriter(new FileWriter("DATOS/participaciones.txt", true));) {
            // alumnos.txt
            List<String> alumnos = in.readAllLines();
            Random rnd = new Random();
            String alumno = alumnos.get(rnd.nextInt(alumnos.size()));
            System.out.println("El elegido es..." + alumno);

            // participaciones.txt
            System.out.println("Añadiendo alumno a DATOS/participaciones.txt");
            out.write(LocalDateTime.now() + " - " + alumno + "\n");     
        } catch (FileNotFoundException e) {
            // TODO: handle exception
        } catch (IOException e) {
            // TODO: handle exception 
        }
    }
}
