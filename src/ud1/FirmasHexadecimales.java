package ud1;

import java.io.FileInputStream;
import java.io.IOException;


public class FirmasHexadecimales {

    // PROBLEMAS

    /* Como obtener la extensión de un archivo (si tiene) */

    /* Formas de almacenar los pares extensión/firma
       1. Matriz de String
       2. Clase Extension {extensión, firma, trailer, etc...}
       3. Map<String, String>
    */

    /* Como convertir dos caracteres hexadecimales en formato String a formato byte/int */       

    
    public static void main(String[] args) {
        firmaVerificada("Programación DAM2-AD-26-27.pdf");
        firmaVerificada("imagen");
        firmaVerificada("foto.jpg");
    }

    public static boolean firmaVerificada(String ruta) {

        if (ruta != null && ruta.contains(".")) {
            String[] partes = ruta.split("\\.");
            String extension = partes[partes.length - 1].toUpperCase();

            try (FileInputStream bf = new FileInputStream(ruta)) {
                int b1 = 0;
                int b2 = 0;
                int b3 = 0;
                int b4 = 0;

                boolean coincide = false;

                switch (extension) {
                    case "PDF":
                        coincide = true;
                        String firma = "25 50 44 46"; // Con espacios o sin espacios?
                        String[] bytesStr = firma.split(" ");
                        for (String byteStr : bytesStr) {
                            // int b = Integer.valueOf(byteStr, 16);
                            if (bf.read() != Integer.valueOf(byteStr, 16))
                                coincide = false;
                        }
                        /*if (b1 == 0x25 && b2 == 0x50 && b3 == 0x44 && b4 == 0x46) {
                            coincide = true;
                        }*/
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
