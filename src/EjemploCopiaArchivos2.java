import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class EjemploCopiaArchivos2 {


   public static void main(String[] args) throws IOException {
       try (var in = new BufferedInputStream(new FileInputStream("README.md")); 
            var out = new BufferedOutputStream(new FileOutputStream("destino.txt"));) {
           int c;
           while ((c = in.read()) != -1) {
               out.write(c);
           }
       } 
   }
}
