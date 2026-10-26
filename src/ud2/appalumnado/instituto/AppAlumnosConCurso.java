package ud2.appalumnado.instituto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import ud2.appalumnado.JdbcUtilProp;
import ud2.appalumnado.instituto.dao.AlumnoDAO;
import ud2.appalumnado.instituto.dao.AlumnoDAOImplTran;

public class AppAlumnosConCurso {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el id del alumno: ");
        int alumnoId = sc.nextInt();

        AlumnoDAO dao;
        try {
            dao = new AlumnoDAOImplTran(JdbcUtilProp.getConnection());
            System.out.println(dao.buscarAlumnoConCurso(alumnoId));

            System.out.print("Introduce el id del curso para mostrar su alumnado: ");
            int cursoId = sc.nextInt();
            dao.buscarPorCurso(cursoId).forEach(System.out::println);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }     

    }
}
