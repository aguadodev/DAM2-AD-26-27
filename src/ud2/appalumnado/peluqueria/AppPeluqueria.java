package ud2.appalumnado.peluqueria;

/** @author ANDRE PAZO */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import ud2.peluqueria.daos.PerroDAO;
import ud2.peluqueria.daos.PerroDAOImp;
import ud2.peluqueria.dtos.Perro;

public class AppPeluqueria {
    private static final String URL = "jdbc:sqlite:BD/peluqueria.db";;

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            PerroDAO dao = new PerroDAOImp(conn);
            Scanner sc = new Scanner(System.in);
            do {
                System.out.println();
                System.out.println("MENU PERROS");
                System.out.println("0. Crear Tabla/BD (Limpiar BD)");
                System.out.println("1. Insertar");
                System.out.println("2. Listar");
                System.out.println("3. Buscar por chip");
                System.out.println("4. Actualizar");
                System.out.println("5. Eliminar");
                System.out.println("6. Salir");
                int opcion = leerEntero(sc, "0 una opción: ");
                
                switch (opcion) {
                    case 0:
                        InicializarBD.inicializar(conn);
                        break;
                    case 1:
                        insertar(sc, dao);
                        break;
                    case 2:
                        dao.findAll().forEach(System.out::println);
                        break;
                    case 3:
                        buscar(sc, dao);
                        break;
                    case 4:
                        actualizar(sc, dao);
                        break;
                    case 5:
                        eliminar(sc, dao);
                        break;
                    case 6:
                        sc.close();
                        System.out.println("Saliendo del programa...");
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            } while (true);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    private static void insertar(Scanner sc, PerroDAO dao) {
        int chip = leerEntero(sc, "Chip: ");
        String nombre = leerTexto(sc, "Nombre: ");
        String raza = leerTexto(sc, "Raza: ");
        int edad = leerEntero(sc, "Edad (meses): ");
        boolean desparasitado = leerSiNo(sc, "¿Desparasitado? (s/n): ");
        dao.insert(new Perro(chip, nombre, raza, edad, desparasitado));
    }

    private static void buscar(Scanner sc, PerroDAO dao) {
        int chip = leerEntero(sc, "Chip: ");
        Perro p = dao.find(chip);
        System.out.println(p != null ? p : "No existe ningún perro con chip " + chip);
    }

    private static void actualizar(Scanner sc, PerroDAO dao) {
        int chip = leerEntero(sc, "Chip del perro a actualizar: ");
        Perro p = dao.find(chip);
        if (p == null) {
            System.out.println("No existe ningún perro con chip " + chip);
            return;
        }
        System.out.println(p);
        p.setNombre(leerTexto(sc, "Nombre: "));
        p.setRaza(leerTexto(sc, "Raza: "));
        p.setEdad(leerEntero(sc, "Edad (meses): "));
        p.setDesparasitado(leerSiNo(sc, "¿Desparasitado? (s/n): "));
        dao.update(p);
    }

    private static void eliminar(Scanner sc, PerroDAO dao) {
        int chip = leerEntero(sc, "Chip del perro a eliminar: ");
        dao.delete(chip);
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Debe ser un número entero.");
            }
        }
    }

    private static String leerTexto(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            if (!linea.isEmpty()) {
                return linea;
            }
            System.out.println("No puede estar vacío.");
        }
    }

    private static boolean leerSiNo(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            if (linea.equalsIgnoreCase("s")) {
                return true;
            }
            if (linea.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Responde s o n.");
        }
    }
}
