package ud1.ejemplos;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class EjemploAccesoAleatorio {
    public static void main(String[] args) {

        try {
            // Generamos un fichero con 10.000 números aleatorios
            final String FICHERO = "DATOS/Nnumeros.dat";
            final int N = 10000;
            generarFicheroDeNumeros(FICHERO, N);

            // Leemos el número que está en la posición 500 y lo mostramos por pantalla
            int pos = 500;
            System.out.println(getInt(FICHERO, pos));

            // Modificamos el número que está en la posición 500 y lo ponemos a 1
            setInt(FICHERO, pos, 1);

            // Leemos de nuevo el número que está en la posición 500 y lo mostramos por pantalla
            System.out.println(getInt(FICHERO, pos)); // 1

        } catch (FileNotFoundException e) {
            System.out.println("Error: No se puede crear el fichero");
        } catch (IOException e) {
            System.out.println("Error E/S");

        }

    }

    private static void setInt(String fichero, int pos, int valor) throws FileNotFoundException, IOException {
        try (var in = new RandomAccessFile(fichero, "rw")) {
            int posByte = (pos - 1) * 4;
            in.seek(posByte);
            in.writeInt(valor);
        }
    }

    private static int getInt(String fichero, int pos) throws FileNotFoundException, IOException {
        int valor = 0;
        try (var in = new RandomAccessFile(fichero, "r")) {
            int posByte = (pos - 1) * 4;
            in.seek(posByte);
            valor = in.readInt();
        }
        return valor;
    }

    private static void generarFicheroDeNumeros(final String fichero, final int N) throws IOException {
        try (var out = new DataOutputStream(new FileOutputStream(fichero))) {
            Random rnd = new Random();
            for (int i = 0; i < N; i++) {
                out.writeInt(rnd.nextInt());
            }
        }
    }
}
