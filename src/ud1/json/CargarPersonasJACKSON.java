package ud1.json;

import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CargarPersonasJACKSON {
    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Persona2[] personas = mapper.readValue(new FileReader("DATOS/personas2.json"), Persona2[].class);
        for (Persona2 persona : personas) {
            System.out.println(persona);            
        }
        
    }
}
