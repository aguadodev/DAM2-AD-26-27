package ud1.examen.xxx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author oaguado
 * LeerEmpleadosXml
 */
public class LeerEmpleadosXml {

    public static List<Empleado> leerEmpleados(String fichero) {
        List<Empleado> empleados = new ArrayList<>();

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document document = builder.parse(fichero);
            
            Element raiz = document.getDocumentElement();

            NodeList listaEmpleados = raiz.getElementsByTagName("empleado");

            for (int i = 0; i < listaEmpleados.getLength(); i++) {
                Element nodoEmpleado = (Element) listaEmpleados.item(i);
                int id = Integer.parseInt(nodoEmpleado.getAttribute("id"));
                String nombre = nodoEmpleado.getElementsByTagName("nombre").item(0).getTextContent();
                String departamento = nodoEmpleado.getElementsByTagName("departamento").item(0).getTextContent();
                double salario = Double.parseDouble(nodoEmpleado.getElementsByTagName("salario").item(0).getTextContent());

                Empleado e = new Empleado(id, nombre, departamento, salario);
                empleados.add(e);
            }
            


        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        


        return empleados;
    }
    public static void main(String[] args) {
        for (Empleado e : leerEmpleados("DATOS/Empleados.xml")) {
            System.out.println(e);
        }
    }
}
