package ud2.appalumnado.instituto.dao;

import java.util.List;

import ud2.appalumnado.instituto.dto.CursoDTO;

public interface CursoDAO {
    public List<CursoDTO> listarTodos();
    public boolean insertar(CursoDTO curso);
    public boolean eliminar(int id);
    public CursoDTO buscarPorId(int id);
    public boolean actualizar(CursoDTO curso);
}
