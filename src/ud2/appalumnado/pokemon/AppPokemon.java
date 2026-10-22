package ud2.appalumnado.pokemon;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AppPokemon {

    public static void main(String[] args) {

        String createTable = """
                    CREATE TABLE POKEMON (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    nombre varchar(20),
                    region varchar(15),
                    tipo varchar(15),
                    peso decimal (5,2),
                    captura DATE
                    );
                """;

        try (Connection con = ConexionBD.getConnection();
                Statement stm = con.createStatement()) {

            Scanner sc = new Scanner(System.in);
            PokemonDAO pokemonDAO = new PokemonDAOImp(con);
            do {
                System.out.println("MENU POKEMON");
                System.out.println("0. Crear Tabla/BD");
                System.out.println("1. Insertar");
                System.out.println("2. Listar");
                System.out.println("3. Buscar por ID");
                System.out.println("4. Actualizar");
                System.out.println("5. Eliminar");
                System.out.println("6. Salir");
                System.out.println("Seleccione una opción:");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 0:
                        stm.executeUpdate(createTable);
                        break;
                    case 1:
                        sc.nextLine();
                        System.out.println("Escribe los datos del pokémon a registrar\n" + "Nombre:");
                        String nombre = sc.nextLine();

                        System.out.println("Escribe la región:");
                        String region = sc.nextLine();
                        System.out.println("Escribe el tipo:");
                        String tipo = sc.nextLine();

                        System.out.println("Escribe el peso:");
                        Double peso = sc.nextDouble();
                        System.out.println("Escribe la fecha de captura (AAAA-MM-DD):");
                        sc.nextLine();
                        LocalDate captura = LocalDate.parse(sc.nextLine());

                        PokemonDTO p = new PokemonDTO(nombre, region, tipo, peso, captura);
                        pokemonDAO.insertar(p);
                        break;
                    case 2:
                        List<PokemonDTO> pokemones = pokemonDAO.getAllPokemon();
                        System.out.println(pokemones.toString());
                        break;
                    case 3:
                        System.out.println("Inserta el id del pokémon que quieras mostrar: ");
                        int id = sc.nextInt();
                        PokemonDTO pokemon = pokemonDAO.getPokemonById(id);
                        System.out.println(pokemon);
                        break;
                    case 4:
                        System.out.println("Inserta el id del pokémon que quieres actualizar: ");
                        id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Escribe los datos del pokémon a actualizar\n" + "Nombre:");
                        nombre = sc.nextLine();

                        System.out.println("Escribe la región:");
                        region = sc.nextLine();
                        System.out.println("Escribe el tipo:");
                        tipo = sc.nextLine();

                        System.out.println("Escribe el peso:");
                        peso = sc.nextDouble();
                        System.out.println("Escribe la fecha de captura (AAAA-MM-DD):");
                        sc.nextLine();
                        captura = LocalDate.parse(sc.nextLine());

                        p = new PokemonDTO(id, nombre, region, tipo, peso, captura);
                        pokemonDAO.actualizar(p);
                        break;
                    case 5:
                        System.out.println("Inserta el id del pokémon que quieres eliminar: ");
                        id = sc.nextInt();
                        pokemonDAO.eliminar(pokemonDAO.getPokemonById(id));
                        break;
                    case 6:
                        sc.close();
                        System.out.println("Saliendo del programa...");
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            } while (true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}