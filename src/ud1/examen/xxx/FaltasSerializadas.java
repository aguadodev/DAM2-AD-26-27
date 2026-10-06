package ud1.examen.xxx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author oaguado
 * FaltasSerializadas
 */
public class FaltasSerializadas {
    public static void main(String[] args) {
        try (var in = new BufferedReader(new FileReader("DATOS/Faltas.csv"));
             var out = new ObjectOutputStream(new FileOutputStream("DATOS/FaltasAsistencia.dat"))) {
                // Leer cabecera
                String linea = in.readLine();
                int numFaltasSerializadas = 0;

                // Lectura anticipada
                linea = in.readLine();
                while (linea != null) {
                    linea = linea.replace("\"", "");
                    String[] campos = linea.split(";");
                    String tipoFalta = campos[4];
                    String xustificada = campos[7];
                    
                    if (tipoFalta.equals("Asistencia") && xustificada.equals("Non")) {
                        numFaltasSerializadas++;
                        // Instancio y serializo el objeto FaltaAsistencia
                        String alumno = campos[1];
                        String curso = campos[2];
                        String modulo = campos[5];
                        LocalDate data = LocalDate.parse(campos[3],DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        int sesion = Integer.parseInt(campos[6]);
                        FaltaAsistencia falta = new FaltaAsistencia(data, sesion, modulo, curso, alumno);
                        out.writeObject(falta);
                    }
                    // Volver a leer
                    linea = in.readLine();
                }
            System.out.println("Registros serializados: " + numFaltasSerializadas);
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error E/S");
            e.printStackTrace();
        } 
    }
}

class FaltaAsistencia implements Serializable{
    LocalDate data;
    int sesion;
    String modulo;
    String curso;
    String Alumno;

    public FaltaAsistencia(LocalDate data, int sesion, String modulo, String curso, String alumno) {
        this.data = data;
        this.sesion = sesion;
        this.modulo = modulo;
        this.curso = curso;
        Alumno = alumno;
    }
   
}