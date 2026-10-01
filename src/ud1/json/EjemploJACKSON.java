package ud1.json;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EjemploJACKSON {
    public static void main(String[] args) throws JsonProcessingException {
        // Creamos un objeto Gson
        ObjectMapper mapper = new ObjectMapper();

        Persona2 per = new Persona2("Pepe", Fecha.of(2000, 12, 11), 175);

        // Convertimos el objeto Persona a JSON
        String json = mapper.writeValueAsString(per);
        System.out.println("Objeto Persona en JSON:\n" + json);

        // Convertimos el JSON de nuevo a un objeto Persona
        Persona2 per2 = mapper.readValue(json, Persona2.class);
        System.out.println("\nObjeto Persona generado a partir del JSON:\n" + per2);

        // Array de personas a JSON
        Persona2 per3 = new Persona2("Juan", Fecha.of(1985, 5, 15), 180);
        Persona2[] personas = { per, per3 };
        String jsonPersonas = mapper.writeValueAsString(personas);
        System.out.println("\nArray de personas en JSON:\n" + jsonPersonas);

        // Guardar el array de personas en un archivo JSON
        try (var out = new FileWriter("DATOS/personas2.json")) {
            out.write(jsonPersonas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
