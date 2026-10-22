package ud2.appalumnado.videojuegos;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import ADAT.ud2.instituto.ConexionBD;

public class AppVideojuegos {
    public static void main(String[] args) {
        try (Connection con = ConexionBD.conectar()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("-----Elige una opcion-----");
            System.out.println("0. Crear Base de datos (Solo si no existe)");
            System.out.println("1. Añadir un juego");
            System.out.println("2. Listar todos los juegos");
            System.out.println("3. Listar juego por ID");
            System.out.println("4. Actualizar informacion de un juego por ID");
            System.out.println("5. Borrar juego");
            System.out.println("6. Salir del programa");
            int menu = sc.nextInt();

            // Variables
            VideojuegoDAO videojuegoDAO = new VideojuegosDAOImpl(con);
            List<VideojuegoDTO> juegos = null;
            int juego;
            int id;

            // Menu
            switch (menu) {
                case 0:
                    videojuegoDAO.crearBD();
                    break;
                case 1:
                    System.out.println("Nombre del juego");
                    String nombreJuego = sc.nextLine();
                    System.out.println("Fecha de lanzamiento (yyyy-dd-MM)");
                    String fechaJuego = sc.nextLine();
                    System.out.println("Precio del juego");
                    Double precioJuego = sc.nextDouble();
                    videojuegoDAO.insert(new VideojuegoDTO(8, nombreJuego, LocalDate.parse(fechaJuego), precioJuego));
                    juegos = videojuegoDAO.findAll();
                    System.out.println(juegos);

                    break;
                case 2:
                    juegos = videojuegoDAO.findAll();
                    System.out.println(juegos);
                    break;
                case 3:
                    System.out.println("Escribe la id del juego a buscar");
                    id = sc.nextInt();
                    VideojuegoDTO videojuego = videojuegoDAO.findById(id);
                    if (videojuego != null) {
                        System.out.println(videojuego);
                    } else {
                        System.out.println("Error al recuperar el alumno (o no existe)");
                    }
                    break;
                case 4:
                    juegos = videojuegoDAO.findAll();
                    System.out.println(juegos);
                    System.out.println("Escribe el id del juego que quieres modificar");
                    id = sc.nextInt();
                    VideojuegoDTO videojuegoDTO = videojuegoDAO.findById(id);
                    System.out.println("Precio nuevo ?");
                    Double nuevoPrecio = sc.nextDouble();
                    videojuegoDTO.setPrecio(nuevoPrecio);

                    if (videojuegoDAO.update(videojuegoDTO)) {
                        System.out.println("Informacion actualizada correctamente");
                    } else {
                        System.out.println("Error al actualizar la informacion");
                    }
                    break;
                case 5:
                    System.out.println("Escribe el id del juego a borrar");
                    juego = sc.nextInt();
                    videojuegoDAO.delete(new VideojuegoDTO(juego));
                    juegos = videojuegoDAO.findAll();
                    System.out.println(juegos);
                    break;
                case 6:
                    sc.close();
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no válida");
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
