package ud2.appalumnado.videojuegos;

import java.util.List;

public interface VideojuegoDAO {
    public void crearBD();
    public List<VideojuegoDTO> findAll();
    public VideojuegoDTO findById(int id);
    public boolean update(VideojuegoDTO v);
    public boolean delete(VideojuegoDTO v);
    public boolean insert(VideojuegoDTO v);
}
