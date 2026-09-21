package ud1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class EjemploFlujoSerializado {
    public static void main(String[] args) {
        Persona persona1 = new Persona("Pepe", LocalDate.of(2000, 1, 1), "abc123.,");
        Persona persona2 = new Persona("Marta", LocalDate.of(2001, 2, 2), "12345");

        // Escribir objetos serializados
        try (var out = new ObjectOutputStream(new FileOutputStream("personasSerie.dat"))) {
            out.writeObject(persona1);
            out.writeObject(persona2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Leer Objetos serializados
        try (var in = new ObjectInputStream(new FileInputStream("personasSerie.dat"))) {
            Persona persona3 = (Persona) in.readObject();
            Persona persona4 = (Persona) in.readObject();
            System.out.println(persona3);
            System.out.println(persona4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
