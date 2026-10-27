package ud2.appalumnado.instituto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ud2.appalumnado.JdbcUtilProp;
import ud2.appalumnado.instituto.dao.AlumnoDAO;
import ud2.appalumnado.instituto.dao.AlumnoDAOImpl;
import ud2.appalumnado.instituto.dto.AlumnoDTO;

public class AppAlumnos {
    public static void main() {
        try (Connection con = JdbcUtilProp.getConnection()) {
            AlumnoDAO dao = new AlumnoDAOImpl(con);

            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("\nMENU ALUMNOS");
                System.out.println("============\n");
                System.out.println("1. Insertar");
                System.out.println("2. Listar");
                System.out.println("3. Buscar por ID");
                System.out.println("4. Actualizar");
                System.out.println("5. Eliminar");
                System.out.println("9. TRANSACCION PRUEBA: Insertar Lista de alumnos");
                System.out.println("0. Salir");
                System.out.print("\nSeleccione una opción: ");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        // Insertar
                        insertar(sc, dao);
                        break;
                    case 2:
                        // Listar
                        System.out.println("\nListado de Alumnos");
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
                    case 9:
                        List<AlumnoDTO> alumnos = new ArrayList<>();
                        alumnos.add(new AlumnoDTO("Ana", "López Ruiz", LocalDate.parse("2000-05-10"), "ana@mail.com", 5.));
                        alumnos.add(new AlumnoDTO("Luis", "Pérez Gil", LocalDate.parse("1999-11-23"),"luis@mail.com",  6.));
                        alumnos.add(new AlumnoDTO("Lola", "López Ruiz", LocalDate.parse("2000-05-10"), "lola@mail.com",  7.));
                        dao.matricularAlumnos(alumnos);
                        break;
                    case 0:
                        // Salir
                        //System.out.println("Saliendo del programa...");
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

    private static void insertar(Scanner sc, AlumnoDAO dao) {
        System.out.println("\nDatos del alumno a insertar: ");
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
        String fechaNacimiento = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Nota Media: ");
        Double notaMedia = sc.nextDouble();
        System.out.print("Curso: ");
        Integer curso = sc.nextInt();
        sc.nextLine();
        System.out.println();

        AlumnoDTO alumno = new AlumnoDTO(nombre, apellidos, LocalDate.parse(fechaNacimiento), email, notaMedia, curso);

        if (dao.insertar(alumno)) {
            System.out.println("Alumno insertado correctamente");
        } else {
            System.out.println("Error al insertar el alumno: " + alumno);
        }
    }

    private static void eliminar(Scanner sc, AlumnoDAO dao) {
        System.out.print("\nId de usuario a borrar: ");
        int id = sc.nextInt();
        System.out.println();

        if (dao.eliminar(id)) {
            System.out.println("Alumno borrado correctamente");
        } else {
            System.out.println("Error al borrar el alumno");
        }
    }

    private static void actualizar(Scanner sc, AlumnoDAO dao) {
        System.out.println("\nDatos del alumno a modificar: ");
        System.out.print("Id: ");
        int id = sc.nextInt();
        sc.nextLine();
        // TODO Obtener el Id y si existe pedir el resto de los datos. Introducir sólo
        // los datos que se deseen modificar
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
        String fechaNacimiento = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Nota Media: ");
        Double notaMedia = sc.nextDouble();
        System.out.print("Curso: ");
        Integer curso = sc.nextInt();        
        sc.nextLine();
        System.out.println();

        AlumnoDTO alumno = new AlumnoDTO(id, nombre, apellidos, LocalDate.parse(fechaNacimiento), email, notaMedia, curso);
        if (dao.actualizar(alumno)) {
            System.out.println("Alumno actualizado correctamente");
        } else {
            System.out.println("Error al actualizar el alumno");
        }
    }

    private static void buscarPorId(Scanner sc, AlumnoDAO dao) {
        System.out.print("\nId de alumno a recuperar: ");
        int id = sc.nextInt();
        System.out.println();

        AlumnoDTO alumno = dao.buscarPorId(id);
        if (alumno != null) {
            System.out.println(alumno);
        } else {
            System.out.println("Error al recuperar el alumno (o no existe)");
        }
    }
}
