package ud1.json;

import java.io.FileReader;
import java.io.IOException;

import tools.jackson.databind.json.JsonMapper;

public class CargarPersonasJACKSON3 {
    public static void main(String[] args) throws IOException {
        JsonMapper mapper = new JsonMapper();
        Persona[] personas = mapper.readValue(new FileReader("DATOS/personas.json"), Persona[].class);
        for (Persona persona : personas) {
            System.out.println(persona);            
        }
        
    }
}
