package ud2.appalumnado.canciones;

import java.util.List;

public interface CancionDAOInterfaz2 {
    public boolean insertarCancion(CancionDTO3 cancion);

    public List<CancionDTO3> findAll();

    public CancionDTO3 find(int idCancion);

    public boolean actualizar(CancionDTO3 cancion);

    public boolean actualizar(int id, int visualizacionesNvas);

    public boolean borrar(int idCancion);

    public void insertarVariasCanciones(List<CancionDTO3> listaCanciones);
}
