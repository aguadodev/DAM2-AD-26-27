package ud1.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CrearAlumnoXML {

    public static void main(String[] args) throws Exception {

        // Crear documento
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder =
                factory.newDocumentBuilder();

        Document documento =
                builder.newDocument();

        // <alumno>
        Element alumno =
                documento.createElement("alumno");

        documento.appendChild(alumno);

        // <nombre>
        Element nombre =
                documento.createElement("nombre");

        nombre.setTextContent("Ana García");

        alumno.appendChild(nombre);

        // <curso>
        Element curso =
                documento.createElement("curso");

        curso.setTextContent("2 DAM");

        alumno.appendChild(curso);

        // <nota>
        Element nota =
                documento.createElement("nota");

        nota.setTextContent("8.5");

        alumno.appendChild(nota);

        // Guardar
        Transformer transformer =
                TransformerFactory
                        .newInstance()
                        .newTransformer();

        transformer.setOutputProperty(
                "indent", "yes");

        transformer.transform(
                new DOMSource(documento),
                new StreamResult(new File("DATOS/alumno.xml")));
    }
}