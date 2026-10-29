package ud2.appalumnado.instituto.dao;

import java.util.List;

import ud2.appalumnado.instituto.dto.AlumnoConCursoDTO;
import ud2.appalumnado.instituto.dto.AlumnoDTO;

public interface AlumnoDAO {
    public List<AlumnoDTO> listarTodos();
    public int insertar(AlumnoDTO alumno);
    public boolean eliminar(int id);
    public AlumnoDTO buscarPorId(int id);
    public boolean actualizar(AlumnoDTO alumno);

    // Otros métodos
    public void matricularAlumnos(List<AlumnoDTO> alumnos);
    //public void matricular(int idAlumno, int idCurso);
    public AlumnoConCursoDTO buscarAlumnoConCurso(int AlumnoId);
    public List<AlumnoDTO> buscarPorCurso(int cursoId);    
}
