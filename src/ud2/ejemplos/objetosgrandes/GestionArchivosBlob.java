package ud2.ejemplos.objetosgrandes;

import java.io.*;
import java.sql.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class GestionArchivosBlob {

    // Configuración de la conexión
    private static final String URL = "jdbc:h2:./BD/blobdb";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File file = chooser.getSelectedFile();

            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                // Crear tabla si no existe
                crearTabla(conn);

                // 1. Guardar un archivo en la base de datos
                guardarArchivo(conn, file);

                listarArchivos(conn);

                // 2. Recuperar un archivo de la base de datos y guardarlo en disco
                int id = Integer.parseInt(JOptionPane.showInputDialog("Indica el id de fichero a guardar en disco: "));

                JFileChooser chooser2 = new JFileChooser(".");
                if (chooser2.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String destino = chooser2.getSelectedFile().getAbsolutePath();
                    recuperarArchivo(conn, id, destino);
                }                

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }

        }

    }

    private static void listarArchivos(Connection conn) {
        String sql = "SELECT id, nombre FROM archivos";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
        }
    }

    // Método para crear la tabla
    private static void crearTabla(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS archivos (id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(255), contenido BLOB)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    // MÉTODO PARA GUARDAR (INSERT)
    private static void guardarArchivo(Connection conn, File archivo)
            throws SQLException, IOException {
        String sql = "INSERT INTO archivos (nombre, contenido) VALUES (?, ?)";

        // Comprueba si el archivo existe
        if (!archivo.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
                FileInputStream fis = new FileInputStream(archivo)) {

            pstmt.setString(1, archivo.getName());

            // setBinaryStream evita cargar todo en memoria
            pstmt.setBinaryStream(2, fis, (int) archivo.length());

            pstmt.executeUpdate();
            System.out.println("Archivo guardado correctamente en la BD.");
        }
    }

    // MÉTODO PARA RECUPERAR (SELECT)
    private static void recuperarArchivo(Connection conn, int id, String rutaDestino) throws SQLException, IOException {
        String sql = "SELECT contenido FROM archivos WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Obtenemos el Blob
                    Blob blob = rs.getBlob("contenido");

                    // getBinaryStream lee el contenido por partes
                    try (InputStream is = blob.getBinaryStream();
                            FileOutputStream fos = new FileOutputStream(rutaDestino)) {

                        byte[] buffer = new byte[1024];
                        int bytesLeidos;
                        while ((bytesLeidos = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesLeidos);
                        }
                        System.out.println("Archivo recuperado y guardado en: " + rutaDestino);
                    }
                } else {
                    System.out.println("No se encontró el archivo con ID: " + id);
                }
            }
        }
    }
}
