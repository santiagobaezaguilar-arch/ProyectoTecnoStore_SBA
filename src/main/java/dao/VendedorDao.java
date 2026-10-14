package dao;

import model.Vendedor;
import java.util.List;

public interface VendedorDao {
    void crear(Vendedor vendedor);
    List<Vendedor> obtenerVendedores();
    Vendedor buscar(int id);
    void actualizar(Vendedor vendedor);
    void eliminar(int id);
}
