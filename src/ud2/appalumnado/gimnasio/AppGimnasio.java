package ud2.appalumnado.gimnasio;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class AppGimnasio {
    public static void main(String[] args) {

        try (Connection con = JbcUtil.getConnection();
                Statement st = con.createStatement();
                Scanner sc = new Scanner(System.in);) {

            EjercicioDAO ejercicioDAO = new EjercicioDAOImpl(con);

            do {
                System.out.println("\nMENU EJERCICIO");
                System.out.println("0. Crear Tabla/BD");
                System.out.println("1. Insertar");
                System.out.println("2. Listar");
                System.out.println("3. Buscar por ID");
                System.out.println("4. Actualizar");
                System.out.println("5. Eliminar");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 0:
                        crearTabla(st);
                        break;
                    case 1:
                        insertar(sc, ejercicioDAO);
                        break;
                    case 2:
                        System.out.println("\nEJERCICIOS");
                        System.out.println("=============\n");
                        List<EjercicioDTO> ejercicios = ejercicioDAO.listarTodos();
                        ejercicios.forEach(System.out::println);
                        break;
                    case 3:
                        buscarPorId(sc, ejercicioDAO);
                        break;
                    case 4:
                        actualizar(sc, ejercicioDAO);
                        break;
                    case 5:
                        eliminar(sc, ejercicioDAO);
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
            System.out.println("La BD EJERCICIO ya existe\n" + e.getMessage());
        }

    }

    private static void crearTabla(Statement st) {
        System.out.println("Creando BD EJERCICIO...");
        String sqlCreate = """
                    CREATE TABLE IF NOT EXISTS ejercicio (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        nombre VARCHAR(100),
                        musculosInvolucrados VARCHAR(50),
                        series INT,
                        repeticiones INT
                    );
                """;

        try {
            System.out.println(st.executeUpdate(sqlCreate) == 0 ? "Creado correctamente"
                    : "No se ha creado o ya existe");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void eliminar(Scanner sc, EjercicioDAO dao) {
        System.out.println("\n--- Eliminando un ejercicio ---");
        System.out.print("ID del ejercicio a eliminar: ");
        int id = sc.nextInt();

        if (dao.eliminar(id)) {
            System.out.println("Se ha eliminado correctamente");
        } else {
            System.out.println("Hubo un problema al eliminar");
        }
    }

    private static void actualizar(Scanner sc, EjercicioDAO dao) {
        System.out.println("\n--- Actualizando el ejercicio ---");
        System.out.print("ID del ejercicio a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Grupo muscular que involucra: ");
        String musculosInvolucrados = sc.nextLine();
        System.out.print("Series: ");
        int series = sc.nextInt();
        System.out.print("Repeticiones: ");
        int repeticiones = sc.nextInt();
        System.out.println();

        EjercicioDTO ejercicio = new EjercicioDTO(id, nombre, musculosInvolucrados, series, repeticiones);

        if (dao.actualizar(ejercicio)) {
            System.out.println("Ejercicio actualizado correctamente: " + ejercicio);
        } else {
            System.out.println("Error al actualizar el ejercicio");
        }
    }

    private static void buscarPorId(Scanner sc, EjercicioDAO dao) {
        System.out.println("\n--- Buscando el ejercicio por id ---");
        System.out.print("ID del ejercicio a buscar: ");
        int id = sc.nextInt();

        EjercicioDTO ejercicio = dao.buscarPorId(id);

        if (ejercicio != null) {
            System.out.println("\nEl ejercicio buscado es " + ejercicio);        
        } else {
            System.out.println("Error al recuperar el ejercicio");
        }
    }

    private static void insertar(Scanner sc, EjercicioDAO dao) {
        System.out.println("\n--- Datos del ejercicio a insertar ---");
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Grupo muscular que involucra: ");
        String musculosInvolucrados = sc.nextLine();
        System.out.print("Series: ");
        int series = sc.nextInt();
        System.out.print("Repeticiones: ");
        int repeticiones = sc.nextInt();
        System.out.println();

        EjercicioDTO ejercicio = new EjercicioDTO(nombre, musculosInvolucrados, series, repeticiones);

        if (dao.insertar(ejercicio)) {
            System.out.println("Ejercicio insertado correctamente");
        } else {
            System.out.println("Error al insertar el ejercicio: " + ejercicio);
        }
    }
}