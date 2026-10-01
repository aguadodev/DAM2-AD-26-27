package ud1.json;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class CargarPersonasGSON {
    public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        Gson gson = new Gson();
        Persona[] personas = gson.fromJson(new FileReader("DATOS/personas.json"), Persona[].class);
        for (Persona persona : personas) {
            System.out.println(persona);            
        }
        
    }
}
