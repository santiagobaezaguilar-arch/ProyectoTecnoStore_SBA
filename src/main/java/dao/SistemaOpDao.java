package dao;

import model.SistemOp;
import java.util.List;

public interface SistemaOpDao {
    void crear(SistemOp sistemaOp);
    void actualizar(SistemOp sistemaOp);
    void eliminar(int id);
    List<SistemOp> obtenerSistemasOp();
    SistemOp buscar(int id);
}