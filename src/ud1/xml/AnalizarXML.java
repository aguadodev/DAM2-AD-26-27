package ud1.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class AnalizarXML {

    public static void main(String[] args) {

        try {
            // 1. Crear el parser DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            // 2. Leer el archivo XML
            Document documento = builder.parse(new File("DATOS/alumnos.xml"));

            // 3. Obtener el elemento raíz
            Element raiz = documento.getDocumentElement();

            System.out.println("Elemento raíz: " + raiz.getNodeName());

            // 4. Obtener todos los elementos "alumno"
            NodeList listaAlumnos = raiz.getElementsByTagName("alumno");

            // 5. Recorrer los alumnos
            for (int i = 0; i < listaAlumnos.getLength(); i++) {

                Element alumno = (Element) listaAlumnos.item(i);

                // Obtener atributo
                String id = alumno.getAttribute("id");

                // Obtener nombre
                String nombre = alumno
                        .getElementsByTagName("nombre")
                        .item(0)
                        .getTextContent();

                // Obtener curso
                String curso = alumno
                        .getElementsByTagName("curso")
                        .item(0)
                        .getTextContent();

                System.out.println();
                System.out.println("Alumno: " + nombre);
                System.out.println("ID: " + id);
                System.out.println("Curso: " + curso);

                // Obtener módulos
                NodeList modulos = alumno.getElementsByTagName("modulo");

                for (int j = 0; j < modulos.getLength(); j++) {
                    Element modulo = (Element) modulos.item(j);
                    String nombreModulo = modulo.getAttribute("nombre");
                    String nota = modulo.getTextContent();
                    System.out.println("  " + nombreModulo + ": " + nota);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
