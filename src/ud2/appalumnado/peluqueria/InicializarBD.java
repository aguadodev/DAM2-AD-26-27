package ud2.appalumnado.peluqueria;

/** @author ANDRE PAZO */

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializarBD {

    private static final String SQL_DROP = "DROP TABLE IF EXISTS perros";
    private static final String SQL_CREATE = """
            CREATE TABLE perros (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                chip INTEGER NOT NULL UNIQUE,
                nombre VARCHAR(50) NOT NULL,
                raza VARCHAR(50),
                edad SMALLINT,
                desparasitado BOOLEAN NOT NULL DEFAULT FALSE
            )""";
    private static final String SQL_INSERT = """
            INSERT INTO perros (chip, nombre, raza, edad, desparasitado) VALUES
                (111, 'Toby', 'Beagle', 16, TRUE),
                (222, 'Luna', 'Caniche', 90, TRUE),
                (333, 'Rocky', 'Bulldog francés', 6, FALSE),
                (444, 'Kira', 'Golden retriever', 45, TRUE),
                (555, 'Max', 'Pastor alemán', 23, TRUE),
                (666, 'Nala', 'Chihuahua', 65, FALSE),
                (777, 'Thor', 'Husky siberiano', 20, TRUE),
                (577, 'Thor', 'pastor alemán', 20, TRUE),
                (888, 'Lola', 'Shih tzu', 17, FALSE)""";

    public static void inicializar(Connection conn) {
        try (Statement st = conn.createStatement()) {
            st.execute(SQL_DROP);
            st.execute(SQL_CREATE);
            int filas = st.executeUpdate(SQL_INSERT);
            System.out.println("Base de datos creada en ./BD/peluqueria.db con " + filas + " perros.");
        } catch (SQLException e) {
            System.err.println("Error inicializando la BD: " + e.getMessage());
        }
    }
}
