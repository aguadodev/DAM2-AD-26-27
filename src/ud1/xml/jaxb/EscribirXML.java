package ud1.xml.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class EscribirXML {

    public static void main(String[] args) throws Exception {

        Alumno alumno =  new Alumno("Ana García", "2 DAM", 8.5);

        // Crear contexto JAXB para la clase Alumno
        JAXBContext contexto = JAXBContext.newInstance(Alumno.class);

        // Crear objeto que convierte Java → XML
        Marshaller marshaller = contexto.createMarshaller();

        // XML con formato legible
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Escribir en fichero
        marshaller.marshal(alumno, new File("DATOS/alumnoJAXB.xml"));

        System.out.println("XML creado.");
    }
}