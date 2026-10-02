package ud1.json;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import tools.jackson.databind.json.JsonMapper;


public class EjemploJACKSON3 {
    public static void main(String[] args) {
        // Creamos un objeto Gson
        JsonMapper mapper = new JsonMapper();

        Persona per = new Persona("Pepe", LocalDate.of(2000, 12, 11), 175);

        // Convertimos el objeto Persona a JSON
        String json = mapper.writeValueAsString(per);
        System.out.println("Objeto Persona en JSON:\n" + json);

        // Convertimos el JSON de nuevo a un objeto Persona
        Persona per2 = mapper.readValue(json, Persona.class);
        System.out.println("\nObjeto Persona generado a partir del JSON:\n" + per2);

        // Array de personas a JSON
        Persona per3 = new Persona("Juan", LocalDate.of(1985, 5, 15), 180);
        Persona[] personas = { per, per3 };
        String jsonPersonas = mapper.writeValueAsString(personas);
        System.out.println("\nArray de personas en JSON:\n" + jsonPersonas);

        // Guardar el array de personas en un archivo JSON
        try (var out = new FileWriter("DATOS/personas3.json")) {
            out.write(jsonPersonas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
