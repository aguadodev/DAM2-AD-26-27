package ud2.instituto;

import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

public class AppInstituto {
    public static void main(String[] args) {

        Connection con = ConexionBD.conectar();

        try (Statement statement = con.createStatement();) {

            /*String sql = """
                    CREATE TABLE alumnos (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      nombre VARCHAR (50),
                      apellidos VARCHAR (100),
                      fecha_nacimiento DATE,
                      curso VARCHAR (100),
                      nota_media DECIMAL (4,2)
                    );
                    """;

            System.out.println(statement.executeUpdate(sql));

            sql = "INSERT INTO alumnos VALUES (1, 'Pepe', 'García', '2000-12-12', 'DAM2', NULL)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO alumnos VALUES (2, 'Marta', 'Castro', '2001-2-12', 'ASIR1', NULL)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO alumnos VALUES (3, 'Lolo', 'Martínez', '2002-1-12', 'DAM2', NULL)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO alumnos VALUES (4, 'Lola', 'López', '2003-4-20', 'DAM2', NULL)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO alumnos VALUES (5, 'Juan', 'Vázquez', '2004-5-10', 'ASIR1', NULL)";
            statement.executeUpdate(sql);       */    

            // INSERTAR (Create)
            AlumnoDTO alumno = new AlumnoDTO(7, "Manuel", "López", LocalDate.of(2000, 1, 1), "COM1", 7.);
            if (AlumnoDAO.insertar(alumno)) {
                System.out.println("Alumno insertado correctamente");
            } else {
                System.out.println("Error al insertar el alumno: " + alumno);
            }
            

            // LISTAR ALUMNADO (Read)
            System.out.println("\nALUMNOS");
            System.out.println("=======\n");
            List<AlumnoDTO> alumnos = AlumnoDAO.findAll();
            alumnos.forEach(System.out::println);



            // RECUPERAR (Read)
            Scanner sc = new Scanner(System.in);
            System.out.print("Id de usuario a recuperar: ");
            int id = sc.nextInt();
            AlumnoDTO alumno2 = AlumnoDAO.find(new AlumnoDTO(id));
            if (alumno2 != null) {
                System.out.println(alumno2);
            } else {
                System.out.println("Error al recuperar el alumno (o no existe)");
            }


            // ACTUALIZAR (Update)
            System.out.println("Vamos a ponerle una nota media de 10!!");
            alumno2.setNotaMedia(10.);
            if (AlumnoDAO.actualizar(alumno2)) {
                System.out.println("Alumno actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el alumno");
            }            
            




            // BORRAR (Delete)
            System.out.print("Id de usuario a borrar: ");
            id = sc.nextInt();
            if (AlumnoDAO.borrar(new AlumnoDTO(id))) {
                System.out.println("Alumno borrado correctamente");
            } else {
                System.out.println("Error al borrar el alumno");
            }



            // LISTAR ALUMNADO
            System.out.println("\nALUMNOS");
            System.out.println("=======\n");
            alumnos = AlumnoDAO.findAll();
            alumnos.forEach(System.out::println);            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
