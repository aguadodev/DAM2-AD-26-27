package ud1.examen.xxx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MeteogaliciaTemperaturaConcello {

    public static void main(String[] args) {
        final String RUTA_FICHERO = "DATOS/observacionConcellos.json";
        final String CONCELLO = "Marín";

        try (var in = new BufferedReader(new FileReader(RUTA_FICHERO))) {
            String json = in.readAllAsString();
            System.out.println(temperaturaConcello(json, CONCELLO));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    public static Double temperaturaConcello(String jsonObservacion, String concello) {
        Double temperatura = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode nodoRaiz = mapper.readTree(jsonObservacion);
            int i = 0;
            boolean encontrado = false;
            JsonNode nodoConcello = nodoRaiz.get("listaObservacionConcellos").get(i);
            while (nodoConcello != null && !encontrado) {
                if (concello.equals(nodoConcello.get("nomeConcello").asText())) {
                    temperatura = nodoConcello.get("temperatura").asDouble();
                    encontrado = true;
                }
                i++;
                nodoConcello = nodoRaiz.get("listaObservacionConcellos").get(i);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return temperatura;
    }


}
