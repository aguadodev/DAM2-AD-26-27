package ud1.ejercicios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
    @author Anxo Quintana
*/
public class EstadisticasAlumnado {
    public static void main(String[] args) {
        String fichero = "DATOS/alumnos.txt";

        int totalAlumnos = 0;
        int aprobados = 0;
        int sumaNotas = 0;
        int notaMaxima = Integer.MIN_VALUE;
        int notaMinima = Integer.MAX_VALUE;
        String mejorAlumno = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                // Omitir líneas vacías
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(";");

                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    String notaTexto = partes[1].trim(); 

                    if (notaTexto.contains("_")) {
                        notaTexto = notaTexto.split("_")[0];
                    }

                    int nota = Integer.parseInt(notaTexto);

                    totalAlumnos++;
                    sumaNotas += nota;

                    if (nota >= 5) {
                        aprobados++;
                    }

                    if (nota > notaMaxima) {
                        notaMaxima = nota;
                        mejorAlumno = nombre;
                    }

                    if (nota < notaMinima) {
                        notaMinima = nota;
                    }
                }
            }

            if (totalAlumnos > 0) {
                double media = (double) sumaNotas / totalAlumnos;

                System.out.println("=== ESTADÍSTICAS DEL ALUMNADO ===");
                System.out.println("Total de alumnos procesados: " + totalAlumnos);
                System.out.printf("Nota media: %.2f\n", media);
                System.out.println("Nota máxima: " + notaMaxima + " (" + mejorAlumno + ")");
                System.out.println("Nota mínima: " + notaMinima);
                System.out.println("Número de aprobados: " + aprobados);
            } else {
                System.out.println("El fichero está vacío.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir la nota a número entero: " + e.getMessage());
        }
    }
}
