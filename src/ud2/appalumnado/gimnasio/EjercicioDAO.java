package ud2.appalumnado.gimnasio;

import java.util.List;

public interface EjercicioDAO {
    public List<EjercicioDTO> listarTodos();
    public boolean insertar(EjercicioDTO ejercicio);
    public boolean eliminar(int id);
    public EjercicioDTO buscarPorId(int id);
    public boolean actualizar(EjercicioDTO ejercicio);
}
