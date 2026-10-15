package ud1.examen.xxx;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

public class ExportarJsonEmpleados {
    public static void main(String[] args) {
        List<Empleado> empleados = List.of(
                new Empleado(1, "Ana García", "Informática", 35000),
                new Empleado(2, "Luis Pérez", "Recursos Humanos", 28000),
                new Empleado(3, "María López", "Informática", 42000));
        
        if (exportarJson(empleados, "DATOS/Empleados.json")) {
            System.out.println("Datos exportados correctamente");
        } else {
            System.out.println("Error al exportar los datos");
        }

    }

    public static boolean exportarJson(List<Empleado> empleados, String fichero) {
        Gson gson = new Gson();
        String json = gson.toJson(empleados);
        try (var out = new BufferedWriter(new FileWriter(fichero))) {
            out.write(json);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
