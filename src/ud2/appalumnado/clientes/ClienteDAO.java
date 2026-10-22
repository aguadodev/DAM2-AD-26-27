package ud2.appalumnado.clientes;

import java.util.List;
/**
 * @author Adrián Gómez
 */
public interface ClienteDAO {
    public List<ClienteDTO> listarTodos();

    public boolean insertar(ClienteDTO cliente);

    public boolean eliminar(int id);

    public boolean eliminar(ClienteDTO cliente);

    public ClienteDTO buscarPorId(int id);

    public boolean actualizar(ClienteDTO cliente);

    public void insertarClientes(List<ClienteDTO> clientes);
}
