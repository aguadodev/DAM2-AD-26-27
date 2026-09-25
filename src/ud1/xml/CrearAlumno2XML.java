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

public class CrearAlumno2XML {

    public static void main(String[] args) throws Exception {

        // Crear documento
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document documento = builder.newDocument();

        // <alumno>
        Element alumno = documento.createElement("alumno");

        documento.appendChild(alumno);

        // <nombre>
        Element nombre = documento.createElement("nombre");

        nombre.setTextContent("Ana García");

        alumno.appendChild(nombre);

        // <ciclo>
        Element ciclo = documento.createElement("ciclo");
        alumno.appendChild(ciclo);
        Element nombreCiclo = documento.createElement("nombre");
        nombreCiclo.setTextContent("DAM");
        ciclo.appendChild(nombreCiclo);
        Element cursoCiclo = documento.createElement("curso");
        cursoCiclo.setTextContent("DAM");
        ciclo.appendChild(cursoCiclo);

        // <nota>
        Element nota = documento.createElement("nota");

        nota.setTextContent("8.5");

        alumno.appendChild(nota);

        // Guardar
        Transformer transformer =
                TransformerFactory
                        .newInstance()
                        .newTransformer();

        transformer.setOutputProperty("indent", "yes");

        transformer.transform(
                new DOMSource(documento),
                new StreamResult(new File("DATOS/alumno2.xml")));
    }
}