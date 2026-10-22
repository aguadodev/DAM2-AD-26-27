package ud2.appalumnado.peluqueria.daos;

/** @author ANDRE PAZO */

import java.util.List;

import ud2.peluqueria.dtos.Perro;

public interface PerroDAO {
    void insert(Perro perro);

    void update(Perro perro);

    void delete(int chip);

    Perro find(int chip);

    List<Perro> findAll();

}
