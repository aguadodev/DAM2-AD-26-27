package ud2.appalumnado.instituto.dto;

/**
 * AlumnoConCursoDTO
 */
public class AlumnoConCursoDTO {
    private int alumnoId;
    private String nombreAlumno;

    private int cursoId;
    private String nombreCurso;


    public AlumnoConCursoDTO(int alumnoId, String nombreAlumno, int cursoId, String nombreCurso) {
        this.alumnoId = alumnoId;
        this.nombreAlumno = nombreAlumno;
        this.cursoId = cursoId;
        this.nombreCurso = nombreCurso;
    }


    public int getAlumnoId() {
        return alumnoId;
    }


    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }


    public String getNombreAlumno() {
        return nombreAlumno;
    }


    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }


    public int getCursoId() {
        return cursoId;
    }


    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }


    public String getNombreCurso() {
        return nombreCurso;
    }


    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }


    @Override
    public String toString() {
        return "AlumnoConCursoDTO [alumnoId=" + alumnoId + ", nombreAlumno=" + nombreAlumno + ", cursoId=" + cursoId
                + ", nombreCurso=" + nombreCurso + "]";
    }


    
    
}
