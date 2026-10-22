package ud2.appalumnado.restaurante;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Scanner;

public class AppRestaurante {

    public static void main(String[] args) {

        // Conexión abierta

        try (Connection con = JdbcUtil.getConnection()) {

            PlatoDAO dao = new PlatoDAOImpl(con);

            Scanner sc = new Scanner(System.in);

            do {
                System.out.println("\nMENU RESTAURANTE");
                System.out.println("================");
                System.out.println("0. Crear Tabla");
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
                        dao.crearTabla();
                        break;
                    case 1:
                        insertar(sc, dao);
                        break;
                    case 2:
                        System.out.println("\nListado de Platos:");
                        System.out.println("------------------");
                        if (dao.listarTodos() != null) {
                            dao.listarTodos().forEach(System.out::println);
                        }
                        break;
                    case 3:
                        buscarPorId(sc, dao);
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
            e.printStackTrace();
        }
    }

    // Métodos modulares

    private static void insertar(Scanner sc, PlatoDAO dao) {
        System.out.println("\nDatos del plato a insertar: ");
        sc.nextLine(); // Limpiar el buffer

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        sc.nextLine();

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        System.out.print("Disponible (true/false): ");
        boolean disponible = sc.nextBoolean();

        PlatoDTO plato = new PlatoDTO(nombre, precio, categoria, disponible);
        if (dao.insertar(plato)) {
            System.out.println("Plato insertado correctamente.");
        } else {
            System.out.println("Error al insertar el plato.");
        }
    }

    private static void buscarPorId(Scanner sc, PlatoDAO dao) {
        System.out.print("\nIntroduce ID del plato a buscar: ");
        int id = sc.nextInt();

        PlatoDTO plato = dao.buscarPorId(id);
        if (plato != null) {
            System.out.println(plato);
        } else {
            System.out.println("No se encontró ningún plato con ese ID.");
        }
    }

    private static void actualizar(Scanner sc, PlatoDAO dao) {
        System.out.print("\nIntroduce ID del plato a actualizar: ");
        int id = sc.nextInt();
        PlatoDTO plato = dao.buscarPorId(id);

        if (plato != null) {
            sc.nextLine();
            System.out.print("Nuevo nombre (actual: " + plato.getNombre() + "): ");
            plato.setNombre(sc.nextLine());

            System.out.print("Nuevo precio: ");
            plato.setPrecio(sc.nextDouble());
            sc.nextLine();

            System.out.print("Nueva categoria: ");
            plato.setCategoria(sc.nextLine());

            System.out.print("Disponible (true/false): ");
            plato.setDisponible(sc.nextBoolean());

            if (dao.actualizar(plato)) {
                System.out.println("Plato actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar.");
            }
        } else {
            System.out.println("No existe ningún plato con ese ID.");
        }
    }

    private static void eliminar(Scanner sc, PlatoDAO dao) {
        System.out.print("\nIntroduce ID del plato a eliminar: ");
        int id = sc.nextInt();
        if (dao.eliminar(id)) {
            System.out.println("Plato eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar el plato.");
        }
    }
}