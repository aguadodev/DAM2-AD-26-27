package ud1.json;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;


import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class EjemploGSON {
    public static void main(String[] args) {
        // Creamos un objeto Gson
        Gson gson = new Gson();

        Persona per = new Persona("Pepe", LocalDate.of(1990, 1, 1), 175);

        // Convertimos el objeto Persona a JSON
        String json = gson.toJson(per);
        System.out.println("Objeto Persona en JSON:\n" + json);

        // Convertimos el JSON de nuevo a un objeto Persona
        Persona per2 = gson.fromJson(json, Persona.class);
        System.out.println("\nObjeto Persona generado a partir del JSON:\n" + per2);

        // Array de personas a JSON
        Persona per3 = new Persona("Juan", LocalDate.of(1985, 5, 15), 180);
        Persona[] personas = {per, per3};
        String jsonPersonas = gson.toJson(personas);
        System.out.println("\nArray de personas en JSON:\n" + jsonPersonas);


        // Guardar el array de personas en un archivo JSON
        try (var out = new FileWriter("DATOS/personas.json")){
            out.write(jsonPersonas);
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
