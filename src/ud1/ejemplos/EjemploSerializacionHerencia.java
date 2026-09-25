package ud1.ejemplos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EjemploSerializacionHerencia {

    public static void main(String[] args) {

        try (var in = new ObjectInputStream(new FileInputStream("DATOS/perros.dat"))) {
            Perro p = (Perro) in.readObject();
            System.out.println(p);

        } catch (Exception e) {
            System.out.println("Nada que leer/mostrar");
        }

        Perro perro = new Perro("Max", "Labrador");
        try {
            // Crear un flujo de salida para escribir el objeto en un archivo binario
            ObjectOutputStream salida = new ObjectOutputStream(
                    new FileOutputStream("DATOS/perros.dat"));

            // Serializar los objetos
            salida.writeObject(perro);
            salida.close();
            System.out.println("Objeto serializado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Animal implements Serializable {
    private static final long serialVersionUID = 1L; // Asegura la compatibilidad de versiones
    String nombre;

    // Constructor de la clase Animal
    public Animal(String nombre) {
        this.nombre = nombre;
    }

    public void hacerSonido() {
        System.out.println("El animal hace un sonido");
    }
}

class Perro extends Animal {
    // private static final long serialVersionUID = 1L; // Asegura la compatibilidad de versiones
    String raza;

    // Constructor de la clase Perro
    public Perro(String nombre, String raza) {
        super(nombre); // Llamada al constructor de la superclase
        this.raza = raza;
    }

    @Override
    public void hacerSonido() {
        System.out.println("El perro hace guau");
    }

    public void mostrarRaza() {
        System.out.println("Raza del perro: " + raza);
    }

    @Override
    public String toString() {
        return "Perro [nombre=" + nombre + ", raza=" + raza + "]";
    }

}