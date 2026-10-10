package controller.Marca;

import dao.impl.MarcaDaoImpl;
import model.Marca;
import view.validarNumeros.Validaciones;
import java.util.List;

public class MenuMarca {

    private final Validaciones val = new Validaciones();
    private final MarcaDaoImpl marcaDao = new MarcaDaoImpl();

    public void mostrarMenu() {
        int opcion;
        do {
            opcion = val.validarEntero("""
                                      
                                      --- GESTIÓN DE MARCAS ---
                                      1. Crear
                                      2. Actualizar
                                      3. Eliminar
                                      4. Listar
                                      5. Regresar
                                      Seleccione una opción: """);
            switch (opcion) {
                case 1 -> System.out.println("[Función] Crear marca en desarrollo...");
                case 2 -> System.out.println("[Función] Actualizar marca en desarrollo...");
                case 3 -> System.out.println("[Función] Eliminar marca en desarrollo...");
                case 4 -> {
                    List<Marca> lista = marcaDao.obtenerMarcas();
                    System.out.println("\n==================================");
                    System.out.println("       LISTADO DE MARCAS          ");
                    System.out.println("==================================");
                    if (lista.isEmpty()) {
                        System.out.println("No hay marcas registradas en la BD.");
                    } else {
                        for (Marca m : lista) {
                            System.out.println(m);
                        }
                    }
                }
                case 5 -> System.out.println("Regresando al menú anterior...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}