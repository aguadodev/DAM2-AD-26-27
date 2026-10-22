package ud2.appalumnado.restaurante;

import java.util.List;

public interface PlatoDAO {
    void crearTabla();
    boolean insertar(PlatoDTO plato);
    List<PlatoDTO> listarTodos();
    PlatoDTO buscarPorId(int id);
    boolean actualizar(PlatoDTO plato);
    boolean eliminar(int id);
}