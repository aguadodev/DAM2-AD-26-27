package ud2.appalumnado.instituto.dao;

import java.util.List;

import ud2.appalumnado.instituto.dto.ModuloDTO;

public interface ModuloDAO {
    public List<ModuloDTO> listarTodos();
    public boolean insertar(ModuloDTO modulo);
    public boolean eliminar(int id);
    public ModuloDTO buscarPorId(int id);
    public boolean actualizar(ModuloDTO modulo);
}
