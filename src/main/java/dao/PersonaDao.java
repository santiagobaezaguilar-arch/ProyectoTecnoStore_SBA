package dao;

import model.Persona;
import java.util.List;

public interface PersonaDao {
    void crear(Persona persona);
    void actualizar(Persona persona);
    void eliminar(int id);
    List<Persona> obtenerPersonas();
    Persona buscar(int id);
}
