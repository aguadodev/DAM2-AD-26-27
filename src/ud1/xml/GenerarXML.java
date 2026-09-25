package ud1.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class GenerarXML {

    public static void main(String[] args) {

        try {
            // 1. Crear el documento DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.newDocument();

            // 2. Crear elemento raíz
            Element alumnos = documento.createElement("alumnos");
            documento.appendChild(alumnos);

            // 3. Crear primer alumno
            Element alumno1 = documento.createElement("alumno");
            alumno1.setAttribute("id", "1");
            alumnos.appendChild(alumno1);

            // Nombre
            Element nombre1 = documento.createElement("nombre");
            nombre1.setTextContent("Ana García");
            alumno1.appendChild(nombre1);

            // Curso
            Element curso1 = documento.createElement("curso");
            curso1.setTextContent("2 DAM");
            alumno1.appendChild(curso1);

            // Módulos
            Element modulos1 = documento.createElement("modulos");
            alumno1.appendChild(modulos1);

            // Programación
            Element programacion = documento.createElement("modulo");
            programacion.setAttribute("nombre", "Programación");
            programacion.setTextContent("8.5");
            modulos1.appendChild(programacion);

            // Bases de Datos
            Element basesDatos = documento.createElement("modulo");
            basesDatos.setAttribute("nombre", "Bases de Datos");
            basesDatos.setTextContent("7.0");
            modulos1.appendChild(basesDatos);

            // Sistemas Informáticos
            Element sistemas = documento.createElement("modulo");
            sistemas.setAttribute("nombre", "Sistemas Informáticos");
            sistemas.setTextContent("9.0");
            modulos1.appendChild(sistemas);

            // 4. Crear segundo alumno
            Element alumno2 = documento.createElement("alumno");
            alumno2.setAttribute("id", "2");
            alumnos.appendChild(alumno2);

            Element nombre2 = documento.createElement("nombre");
            nombre2.setTextContent("Pedro López");
            alumno2.appendChild(nombre2);

            Element curso2 = documento.createElement("curso");
            curso2.setTextContent("2 DAM");
            alumno2.appendChild(curso2);

            Element modulos2 = documento.createElement("modulos");
            alumno2.appendChild(modulos2);

            Element programacion2 = documento.createElement("modulo");
            programacion2.setAttribute("nombre", "Programación");
            programacion2.setTextContent("6.5");
            modulos2.appendChild(programacion2);

            Element basesDatos2 = documento.createElement("modulo");
            basesDatos2.setAttribute("nombre", "Bases de Datos");
            basesDatos2.setTextContent("8.0");
            modulos2.appendChild(basesDatos2);

            // 5. Guardar el documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(
                    OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(documento);

            StreamResult resultado = new StreamResult(new File("DATOS/alumnos.xml"));

            transformer.transform(source, resultado);

            System.out.println("XML generado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
