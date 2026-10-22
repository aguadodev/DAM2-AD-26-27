package ud2.appalumnado.canciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppCanciones {
    public static void main(String[] args) {

        try (Connection conn = ConexionBD3.conectar();
                Statement statement = conn.createStatement();) {

            CancionDAOInterfaz2 cancionDAOImpl = new CancionDAO2(conn);

            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("MENU ALUMNOS");
                System.out.println("0. Crear Tabla/BD (por defecto 5 auto)");
                System.out.println("1. Insertar");
                System.out.println("2. Listar");
                System.out.println("3. Buscar por ID");
                System.out.println("4. Actualizar");
                System.out.println("5. Eliminar");
                System.out.println("6. Salir");
                System.out.println("7. Insertar lista canciones (transaccion)");
                System.out.print("Seleccione una opción: ");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 0:
                        // Crear Tabla/BD
                        String sql = """
                                    CREATE TABLE canciones (
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        nombre VARCHAR(50) UNIQUE,
                                        genero VARCHAR(30),
                                        fPublicacion DATE,
                                        visualizaciones INT,
                                        duracion DECIMAL(3,2)
                                    );
                                """;
                        statement.execute(sql);

                        sql = "INSERT INTO canciones (nombre,genero,fPublicacion,visualizaciones,duracion) VALUES('cancion1', 'pop', '2013-04-09', '12000000', '3.42')";
                        statement.executeUpdate(sql);
                        sql = "INSERT INTO canciones (nombre,genero,fPublicacion,visualizaciones,duracion) VALUES('cancion2', 'rock', '2011-01-01', '4000000', '3.42')";
                        statement.executeUpdate(sql);
                        sql = "INSERT INTO canciones (nombre,genero,fPublicacion,visualizaciones,duracion) VALUES('cancion3', 'trap', '2019-06-29', '54000000', '3.42')";
                        statement.executeUpdate(sql);
                        sql = "INSERT INTO canciones (nombre,genero,fPublicacion,visualizaciones,duracion) VALUES('cancion4', 'trap', '2018-07-12', '52000000', '3.42')";
                        statement.executeUpdate(sql);
                        sql = "INSERT INTO canciones (nombre,genero,fPublicacion,visualizaciones,duracion) VALUES('cancion5', 'pop', '2016-06-06', '19000000', '3.42')";
                        statement.executeUpdate(sql);
                        break;
                    case 1:
                        // Insertar
                        sc.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Genero: ");
                        String genero = sc.nextLine();
                        System.out.print("Fecha de Publicacion(AAAA-MM-dd): ");
                        String fPublicacion = sc.nextLine();
                        System.out.print("Visualizaciones: ");
                        int visualizaciones = sc.nextInt();
                        System.out.print("Duracion(en mins): ");
                        double duracion = sc.nextDouble();
                        CancionDTO3 c = new CancionDTO3(nombre, genero, LocalDate.parse(fPublicacion), visualizaciones,duracion);
                        if (cancionDAOImpl.insertarCancion(c)) {
                            System.out.println("Cancion nueva insertada corectamente");
                        } else {
                            System.out.println("Error - cancion no insertada");
                        }
                        break;

                    case 2:
                        // Listar
                        System.out.println("\nALUMNOS");
                        System.out.println("================================");
                        List<CancionDTO3> canciones = cancionDAOImpl.findAll();
                        canciones.forEach(System.out::println);
                        System.out.println("================================\n");
                        break;

                    case 3:
                        // Buscar por ID
                        System.out.print("\nID de la cancion a buscar: ");
                        int idCancion = sc.nextInt();
                        CancionDTO3 cancion = cancionDAOImpl.find(idCancion);
                        System.out.println(cancion + "\n");

                        break;

                    case 4:
                        // Actualizar
                        sc.nextLine();
                        System.out.print("ID de la cancion: ");
                        int id = sc.nextInt();
                        System.out.print("Visualizaciones a anadir: ");
                        int visualizacionesNvas = sc.nextInt();
                        System.out.println("\nVamos a ponerla las visitas!!");
                        if (cancionDAOImpl.actualizar(id,visualizacionesNvas)) {
                            System.out.println("Cancion actualizada correctamente");
                        } else {
                            System.out.println("Error - cancion no actualizada");
                        }
                        break;
                    case 5:
                        // Eliminar
                        System.out.print("\nId de la cancion a borrar: ");
                        idCancion = sc.nextInt();
                        if (cancionDAOImpl.borrar(idCancion)) {
                            System.out.println("Cancion borrada correctamente");
                        } else {
                            System.out.println("Error - cancion no borrada");
                        }

                        break;

                    case 6:
                        // Salir
                        sc.close();
                        System.out.println("Saliendo del programa...");
                        return;
                    
                    case 7:
                        // Insertar lista de canciones
                        ArrayList<CancionDTO3> listaCanciones = new ArrayList<>();
                        listaCanciones.add(new CancionDTO3("cancionX1", "pop", LocalDate.of(2000, 3, 4), 2444,4.21));
                        listaCanciones.add(new CancionDTO3("cancionX2", "rock", LocalDate.of(2011, 3, 6), 2444,4.21));
                        //listaCanciones.add(new CancionDTO3("cancionX1", "pop", LocalDate.of(2000, 9, 4), 2444,4.21)); da error pq el campo nombre es UNIQUE entonces al ser una transaccion no se inserta ninguna cancion
                        cancionDAOImpl.insertarVariasCanciones(listaCanciones); 
                        break;

                    default:
                        System.out.println("Opción no válida");
                }
            } while (true);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
