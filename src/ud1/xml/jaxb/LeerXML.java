package ud1.xml.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class LeerXML {

    public static void main(String[] args) throws Exception {

        // Crear contexto
        JAXBContext contexto = JAXBContext.newInstance(Alumno.class);

        // Crear objeto que convierte XML → Java
        Unmarshaller unmarshaller = contexto.createUnmarshaller();

        // Leer fichero
        Alumno alumno = (Alumno) unmarshaller.unmarshal(new File("DATOS/alumnoJAXB.xml"));

        // Mostrar información
        System.out.println("Nombre: " + alumno.getNombre());
        System.out.println("Curso: " + alumno.getCurso());
        System.out.println("Nota: " + alumno.getNota());
    }
}