package ud2.appalumnado.instituto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import ud2.appalumnado.JdbcUtilProp;
import ud2.appalumnado.instituto.dao.ModuloDAO;
import ud2.appalumnado.instituto.dao.ModuloDAOImpl;
import ud2.appalumnado.instituto.dto.ModuloDTO;
import ud2.appalumnado.instituto.excepciones.DataAccessException;

public class AppModulos {

    public static void main() {
        try (Connection con = JdbcUtilProp.getConnection()) {
            ModuloDAO dao = new ModuloDAOImpl(con);
            ejecutarMenu(dao);
        } catch (SQLException | IOException e) {
            // Solo errores de apertura/cierre de conexión o de configuración.
            System.err.println("No se pudo establecer la conexión con la base de datos: "
                    + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void ejecutarMenu(ModuloDAO dao) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero(sc, "Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> insertar(sc, dao);
                    case 2 -> listar(dao);
                    case 3 -> buscarPorId(sc, dao);
                    case 4 -> actualizar(sc, dao);
                    case 5 -> eliminar(sc, dao);
                    case 0 -> salir = true;
                    default -> System.out.println("Opción no válida");
                }
            } catch (DataAccessException e) {
                // Fallo técnico de BD: se informa al usuario y el menú continúa.
                // En una app real aquí iría un logger; aquí usamos System.err.
                System.err.println("Error de acceso a datos: " + e.getMessage());
                // Para depurar:
                // e.printStackTrace();
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("""

                MENU MÓDULOS
                ============

                1. Insertar
                2. Listar
                3. Buscar por ID
                4. Actualizar
                5. Eliminar
                0. Salir
                """);
    }

    // ---------- Operaciones ----------

    private static void insertar(Scanner sc, ModuloDAO dao) {
        System.out.println("\nDatos del módulo a insertar: ");
        String nombre = leerTexto(sc, "Nombre: ");
        int horas = leerEntero(sc, "Horas: ");

        ModuloDTO modulo = new ModuloDTO(nombre, horas);

        if (dao.insertar(modulo)) {
            System.out.println("Módulo insertado correctamente");
        } else {
            // Con el DAO corregido, false en INSERT no debería ocurrir;
            // si ocurre, es una anomalía, no un fallo técnico.
            System.out.println("No se pudo insertar el módulo (0 filas afectadas)");
        }
    }

    private static void listar(ModuloDAO dao) {
        System.out.println("\nListado de Módulos");
        System.out.println("------------------\n");
        List<ModuloDTO> modulos = dao.listarTodos();
        if (modulos.isEmpty()) {
            System.out.println("(No hay módulos registrados)");
        } else {
            modulos.forEach(System.out::println);
        }
    }

    private static void buscarPorId(Scanner sc, ModuloDAO dao) {
        int id = leerEntero(sc, "\nId de módulo a recuperar: ");
        ModuloDTO modulo = dao.buscarPorId(id);
        if (modulo != null) {
            System.out.println(modulo);
        } else {
            // null = "no existe", no es un error técnico
            System.out.println("No existe un módulo con id " + id);
        }
    }

    private static void actualizar(Scanner sc, ModuloDAO dao) {
        System.out.println("\nDatos del módulo a modificar: ");
        int id = leerEntero(sc, "Id: ");

        // Comprobamos existencia antes de pedir el resto (buena práctica)
        if (dao.buscarPorId(id) == null) {
            System.out.println("No existe un módulo con id " + id);
            return;
        }

        String nombre = leerTexto(sc, "Nombre: ");
        int horas = leerEntero(sc, "Horas: ");

        ModuloDTO modulo = new ModuloDTO(id, nombre, horas);
        if (dao.actualizar(modulo)) {
            System.out.println("Módulo actualizado correctamente");
        } else {
            // false aquí = "no se actualizó ninguna fila" (p. ej. carrera con otro proceso)
            System.out.println("No se actualizó ningún módulo (0 filas afectadas)");
        }
    }

    private static void eliminar(Scanner sc, ModuloDAO dao) {
        int id = leerEntero(sc, "\nId de módulo a borrar: ");
        if (dao.eliminar(id)) {
            System.out.println("Módulo borrado correctamente");
        } else {
            // false = "no existía esa fila", no es error técnico
            System.out.println("No existe un módulo con id " + id);
        }
    }

    // ---------- Utilidades de entrada ----------

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                int valor = sc.nextInt();
                sc.nextLine(); // consumir salto de línea pendiente
                return valor;
            }
            System.out.println("Entrada no válida, introduce un número entero.");
            sc.nextLine(); // descartar la entrada incorrecta
        }
    }

    private static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

}
