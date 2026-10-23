package ud2.appalumnado.clientes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
/**
 * @author Adrián Gómez
 */
public class AppClientes {

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAOImpl();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("MENU CLIENTES");
            System.out.println("0. Crear Tabla/BD");
            System.out.println("1. Insertar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 0:
                    crearTabla();
                    break;
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = sc.nextLine();
                    System.out.print("Fecha de alta (dd/mm/yyyy): ");
                    LocalDate fecha = LocalDate.parse(sc.nextLine().trim(), formato);
                    System.out.print("Vip? (S/N): ");
                    String vip = sc.nextLine().trim();
                    Boolean esVip = false;
                    if (vip.equalsIgnoreCase("s")) {
                        esVip = true;
                    } else if (vip.equalsIgnoreCase("n")) {
                        esVip = false;
                    } else {
                        System.out.println("Escribe S o N.");
                    }

                    ClienteDTO c = new ClienteDTO(nombre, apellidos, fecha, esVip);
                    System.out.println(clienteDAO.insertar(c)
                            ? "Insertado correctamente"
                            : "No se ha podido insertar");
                    break;
                case 2:
                    List<ClienteDTO> lista = clienteDAO.listarTodos();
                    if (lista == null || lista.isEmpty()) {
                        System.out.println("No hay clientes");
                    } else {
                        System.out.println("Lista de clientes:");
                        lista.forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.print("Id del cliente a buscar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    ClienteDTO cd = clienteDAO.buscarPorId(id);
                    System.out.println(cd != null ? cd : "Cliente no encontrado");
                    break;
                case 4:
                    System.out.print("Actualizar el cliente con id: ");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    if (clienteDAO.buscarPorId(id2) != null) {
                        System.out.print("Nombre: ");
                        String nombreActualizado = sc.nextLine();
                        System.out.print("Apellidos: ");
                        String apellidosActualizado = sc.nextLine();
                        System.out.print("Fecha de alta (dd/mm/yyyy): ");
                        String fechaAltaActualizado = sc.nextLine();
                        System.out.print("Vip? (S/N)");
                        String vipActualizado = sc.nextLine();
                        boolean esVipActualizado = false;
                        if (vipActualizado.equalsIgnoreCase("s")) {
                            esVipActualizado = true;
                        } else if (vipActualizado.equalsIgnoreCase("n")) {
                            esVipActualizado = false;
                        }
                        ClienteDTO cActualizado = new ClienteDTO(nombreActualizado, apellidosActualizado,
                                LocalDate.parse(fechaAltaActualizado, formato),
                                esVipActualizado);
                        System.out.println(clienteDAO.actualizar(cActualizado)
                                ? "Actualizado correctamente"
                                : "No se ha podido actualizar");
                    } else {
                        System.out.println("No existe un cliente con ese id");
                    }
                    break;
                case 5:
                    System.out.print("Inserta el id del cliente a borrar: ");
                    int id3 = sc.nextInt();
                    sc.nextLine();
                    if (clienteDAO.eliminar(id3)) {
                        System.out.println("Eliminado correctamente");
                    } else {
                        System.out.println("No se ha podido eliminar el cliente con id: " + id3);
                    }
                    break;
                case 6:
                    sc.close();
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        } while (true);
    }

    private static void crearTabla() {
        try (Connection conn = JbdcUtil.getConnection();
                Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS clientes(id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(50), apellidos VARCHAR(100), fecha_alta DATE, vip BOOLEAN)");
        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
            return;
        }
    }
}
