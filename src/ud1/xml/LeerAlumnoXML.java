package ud1.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class LeerAlumnoXML {

    public static void main(String[] args) throws Exception {

        // Crear parser
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder =
                factory.newDocumentBuilder();

        // Leer XML
        Document documento =
                builder.parse(new File("DATOS/alumno.xml"));

        // Obtener raíz
        Element alumno =
                documento.getDocumentElement();

        // Leer datos
        String nombre =
                alumno
                    .getElementsByTagName("nombre")
                    .item(0)
                    .getTextContent();

        String curso =
                alumno
                    .getElementsByTagName("curso")
                    .item(0)
                    .getTextContent();

        String nota =
                alumno
                    .getElementsByTagName("nota")
                    .item(0)
                    .getTextContent();

        // Mostrar
        System.out.println("Nombre: " + nombre);
        System.out.println("Curso: " + curso);
        System.out.println("Nota: " + nota);
    }
}
