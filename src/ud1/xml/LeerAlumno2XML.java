package ud1.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class LeerAlumno2XML {

    public static void main(String[] args) throws Exception {

        // Crear parser
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder =
                factory.newDocumentBuilder();

        // Leer XML
        Document documento =
                builder.parse(new File("DATOS/alumno2.xml"));

        // Obtener raíz
        Element alumno =
                documento.getDocumentElement();

        // Leer datos
        String nombre =
                alumno
                    .getElementsByTagName("nombre")
                    .item(0)
                    .getTextContent();

        Element ciclo = (Element) alumno.getElementsByTagName("ciclo").item(0);

        String nombreCiclo = ciclo.getElementsByTagName("nombre").item(0).getTextContent();
        String cursoCiclo = ciclo.getElementsByTagName("curso").item(0).getTextContent();
        String curso = nombreCiclo + "(" + cursoCiclo + ")";

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
