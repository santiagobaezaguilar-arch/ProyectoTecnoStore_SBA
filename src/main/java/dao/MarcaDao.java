package dao;

import model.Marca;
import java.util.List;

public interface MarcaDao {
    void crear(Marca marca);
    void actualizar(Marca marca);
    void eliminar(int id);
    List<Marca> obtenerMarcas();
    Marca buscar(int id);
}