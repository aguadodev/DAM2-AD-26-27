package ud2.appalumnado.instituto;

import java.util.List;

public interface AlumnoDAO {
    public List<AlumnoDTO> listarTodos();
    public boolean insertar(AlumnoDTO alumno);
    public boolean eliminar(int id);
    public AlumnoDTO buscarPorId(int id);
    public boolean actualizar(AlumnoDTO alumno);

    // Otros métodos
    public void matricularAlumnos(List<AlumnoDTO> alumnos);
    //public void matricular(int idAlumno, int idCurso);
}
