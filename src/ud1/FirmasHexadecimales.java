package ud1;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Eusebio Moreira Dominguez
 */
public class FirmasHexadecimales {
    public static void main(String[] args) {
        verificarFirma("Programación DAM2-AD-26-27.pdf");
        verificarFirma("imagen");
        verificarFirma("foto.jpg");
    }

    public static void verificarFirma(String ruta) {

        if (ruta.contains(".")) {
            String[] partes = ruta.split(".");
            String extension = partes[partes.length - 1].toUpperCase();

            try (FileInputStream bf = new FileInputStream(ruta)) {
                int b1 = bf.read();
                int b2 = bf.read();
                int b3 = bf.read();
                int b4 = bf.read();

                boolean coincide = false;

                switch (extension) {
                    case "PDF":
                        if (b1 == 0x25 && b2 == 0x50 && b3 == 0x44 && b4 == 0x46) {
                            coincide = true;
                        }
                        break;
                    case "PNG":
                        if (b1 == 0x89 && b2 == 0x50 && b3 == 0x4E && b4 == 0x47) {
                            coincide = true;
                        }
                        break;
                    case "JPG":
                        if (b1 == 0xFF && b2 == 0xD8 && b3 == 0xFF) {
                            coincide = true;
                        }
                        break;
                    default:
                        coincide = false;
                        break;
                }

                System.out.println("El archivo: " + ruta + " coincide?. Respuesta: " + coincide);

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("El nombre de fichero no tiene extensión válida");
        }
    }
}
