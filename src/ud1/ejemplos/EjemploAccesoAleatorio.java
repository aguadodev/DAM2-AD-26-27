package ud1.ejemplos;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class EjemploAccesoAleatorio {
    public static void main(String[] args) {
        final int N = 10000;

        // generarFicheroDeNumeros(N);

        int pos = 500;
        try (var in = new RandomAccessFile("DATOS/Nnumeros.dat", "r")) {
            int posByte = (pos - 1) * 4;
            in.seek(posByte);
            System.out.println(in.readInt());
        } catch (Exception e) {
            // TODO: handle exception
        }

        try (var in = new RandomAccessFile("DATOS/Nnumeros.dat", "rw")) {
            int posByte = (pos - 1) * 4;
            in.seek(posByte);
            in.writeInt(1);
        } catch (Exception e) {
            // TODO: handle exception
        }        

                
        
    }

    private static void generarFicheroDeNumeros(final int N) {
        try (var out = new DataOutputStream(new FileOutputStream("DATOS/Nnumeros.dat"))) {
            Random rnd = new Random();
            for (int i = 0; i < N; i++) {
                out.writeInt(rnd.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se puede crear el fichero");
        } catch (IOException e) {
            System.out.println("Error E/S");            
        }
    }
}
