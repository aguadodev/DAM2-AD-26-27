package ud2.appalumnado.instituto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ud2.appalumnado.JdbcUtilProp;

public class AppCursos {
    public static void main() {
        try (Connection con = JdbcUtilProp.getConnection()) {
            CursoDAO dao = new CursoDAOImplTran(con);

            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("\nMENU CURSOS");
                System.out.println("============\n");
                System.out.println("0. Crear BD/Tabla");
                System.out.println("1. Insertar");
                System.out.println("2. Listar");
                System.out.println("3. Buscar por ID");
                System.out.println("4. Actualizar");
                System.out.println("5. Eliminar");
                System.out.println("6. Salir");
                System.out.print("\nSeleccione una opción: ");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 0:
                        // CrearBD/Tabla
                        crearTabla();
                        break;
                    case 1:
                        // Insertar
                        insertar(sc, dao);
                        break;
                    case 2:
                        // Listar
                        System.out.println("\nListado de Cursos");
                        System.out.println("------------------\n");
                        dao.listarTodos().forEach(System.out::println);
                        break;
                    case 3:
                        // Buscar por ID
                        buscarPorId(sc, dao);
                        break;
                    case 4:
                        // Actualizar
                        actualizar(sc, dao);
                        break;
                    case 5:
                        // Eliminar
                        eliminar(sc, dao);
                        break;
                    case 6:
                        // Salir
                        sc.close();
                        System.out.println("Saliendo del programa...");
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            } while (true);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static void crearTabla() throws IOException {
        System.out.println("\nCreando Tabla/BDs");
        System.out.println("-----------------\n");
        try (Connection con = JdbcUtilProp.getConnection(); 
             Statement statement = con.createStatement();) {
            String sql = """
                    CREATE TABLE IF NOT EXISTS cursos (
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      nombre VARCHAR (100),
                      horas INT
                    );
                    """;

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
               
    }

    private static void insertar(Scanner sc, CursoDAO dao) {
        System.out.println("\nDatos del curso a insertar: ");
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Horas: ");
        int horas = sc.nextInt();
        sc.nextLine();
        System.out.println();

        CursoDTO curso = new CursoDTO(nombre, horas);

        if (dao.insertar(curso)) {
            System.out.println("Curso insertado correctamente");
        } else {
            System.out.println("Error al insertar el curso: " + curso);
        }
    }

    private static void eliminar(Scanner sc, CursoDAO dao) {
        System.out.print("\nId de usuario a borrar: ");
        int id = sc.nextInt();
        System.out.println();

        if (dao.eliminar(id)) {
            System.out.println("Curso borrado correctamente");
        } else {
            System.out.println("Error al borrar el curso");
        }
    }

    private static void actualizar(Scanner sc, CursoDAO dao) {
        System.out.println("\nDatos del curso a modificar: ");
        System.out.print("Id: ");
        int id = sc.nextInt();
        sc.nextLine();
        // TODO Obtener el Id y si existe pedir el resto de los datos. Introducir sólo
        // los datos que se deseen modificar
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Horas: ");
        int horas = sc.nextInt();
        sc.nextLine();
        System.out.println();

        CursoDTO curso = new CursoDTO(id, nombre, horas);
        if (dao.actualizar(curso)) {
            System.out.println("Curso actualizado correctamente");
        } else {
            System.out.println("Error al actualizar el curso");
        }
    }

    private static void buscarPorId(Scanner sc, CursoDAO dao) {
        System.out.print("\nId de curso a recuperar: ");
        int id = sc.nextInt();
        System.out.println();

        CursoDTO curso = dao.buscarPorId(id);
        if (curso != null) {
            System.out.println(curso);
        } else {
            System.out.println("Error al recuperar el curso (o no existe)");
        }
    }
}
