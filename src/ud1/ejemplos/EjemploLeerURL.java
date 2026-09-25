package ud1.ejemplos;
import java.io.*;
import java.net.URI;
import java.net.URL;

public class EjemploLeerURL {
    public static void main(String[] args) throws Exception {
        URI uri = new URI("https://data.iana.org/TLD/tlds-alpha-by-domain.txt");
        URL url = uri.toURL();
        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is);
        // es un puente de bytes a caracteres.
        int c;
        while ((c = isr.read()) != -1) {
            System.out.print((char) c);
        }
     }
//        // Código equivalente con buffer:
//        try (InputStream is = url.openStream();
//             InputStreamReader isr = new InputStreamReader(is);
//             BufferedReader br = new BufferedReader(isr)) { // Lo veremos en el siguiente apartado.
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        }
    
}