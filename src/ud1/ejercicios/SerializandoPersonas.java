package ud1.ejercicios;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Samuel
 */

public class SerializandoPersonas {
    public static void main(String[] args) {
        boolean continuar = true;
        List<Persona> lista = new ArrayList<>();
        File archivo = new File("persona.dat");

        if (archivo.exists()) {
            importarExportar(lista, true, archivo);
        }

        while (continuar) {

            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("===============");
                System.out.println("1. Añadir persona");
                System.out.println("2. Mostrar personas");
                System.out.println("3. Buscar persona ");
                System.out.println("4. Salir \n");
                System.out.print("Que desea hacer?: ");
                int eleccion = sc.nextInt();

                sc.nextLine();

                continuar = ejecutarOpciones(continuar, lista, sc, eleccion, archivo);

            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un dígito");
            }

        }
    }

    private static boolean ejecutarOpciones(boolean continuar, List<Persona> lista, Scanner sc, int eleccion,
            File archivo) {

        switch (eleccion) {
            case 1:
                System.out.println();
                System.out.print("Introduce su nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Introduce su edad: ");
                int edad = sc.nextInt();

                lista.add(new Persona(nombre, edad));

                importarExportar(lista, false, archivo);

                break;
            case 2:
                System.out.println();
                for (Persona persona : lista) {
                    System.out.println(persona.toString());
                }
                break;

            case 3:
                System.out.println();
                System.out.println("1. Filtrar por nombre");
                System.out.println("2. Filtrar por edad");
                System.out.print("Que desea hacer?: ");
                eleccion = sc.nextInt();
                sc.nextLine();

                switch (eleccion) {
                    case 1:
                        System.out.print("Introduce el nombre: ");
                        nombre = sc.nextLine();

                        for (Persona persona : lista) {
                            if (persona.nombre.equals(nombre)) {
                                System.out.println(persona.toString());
                            }
                        }
                        break;

                    case 2:
                        System.out.print("Introduce la edad: ");
                        edad = sc.nextInt();

                        for (Persona persona : lista) {
                            if (persona.edad == edad) {
                                System.out.println(persona.toString());
                            }
                        }

                        break;
                    default:
                        System.out.println("Esa selección no es válida");
                        break;
                }
                break;

            case 4:
                System.out.println("Saliendo del programa...");
                continuar = false;
                sc.close();
                break;

            default:
                System.out.println("Esa selección no es válida");
                break;
        }
        return continuar;
    }

    private static void importarExportar(List<Persona> lista, boolean isImportar, File archivo) {
        if (isImportar) {

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {

                while (true) {
                    lista.add((Persona) in.readObject());
                }

            } catch (FileNotFoundException e1) {
                System.out.println("Error buscando archivo " + e1.getMessage());
                e1.printStackTrace();
            } catch (EOFException e1) {
                System.out.println("Fin de lectura de programa ");
            } catch (ClassNotFoundException e) {
                System.out.println("Se ha producido un error " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Se ha producido un error al leer el archivo" + e.getMessage());
            }

        } else {

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
                for (Persona persona : lista) {
                    out.writeObject(persona);
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error buscando archivo " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Fin de lectura de programa " + e.getMessage());
            }

        }
    }
}

class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    String nombre;
    int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
    }

}
