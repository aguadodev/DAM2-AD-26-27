package ud1.ejemplos;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class EjemploCopiaArchivos {


   public static void main(String[] args) throws IOException {
       FileInputStream in = null;
       FileOutputStream out = null;
       try {
           in = new FileInputStream("README.md");
           out = new FileOutputStream("destino.txt");
           int c;
           while ((c = in.read()) != -1) {
               out.write(c);
           }
       } finally { // Hay que cerrar el flujo en cualquier condición.
           if (in != null) {
               in.close();
           }
           if (out != null) {
               out.close();
           }
       }
   }
}
