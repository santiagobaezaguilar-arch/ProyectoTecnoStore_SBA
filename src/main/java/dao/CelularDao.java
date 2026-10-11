package dao;

import model.Celular;
import java.util.List;

public interface CelularDao {
    void crear(Celular celular);
    void actualizar(Celular celular);
    void eliminar(int id);
    List<Celular> obtenerCelulares();
    Celular buscar(int id);
}
