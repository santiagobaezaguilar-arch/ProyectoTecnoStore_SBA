package dao;

import model.Gama;
import java.util.List;

public interface GamaDao {
    void crear(Gama gama);
    void actualizar(Gama gama);
    void eliminar(int id);
    List<Gama> obtenerGamas();
    Gama buscar(int id);
}
