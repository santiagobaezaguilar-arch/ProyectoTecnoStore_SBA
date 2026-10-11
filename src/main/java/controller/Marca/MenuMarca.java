package controller.Marca;

import view.validarNumeros.Validaciones;

public class MenuMarca {

    private final Validaciones val = new Validaciones();
    private final MarcaController marcaController = new MarcaController();

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
                case 1 -> marcaController.crearMarca();
                case 2 -> marcaController.actualizarMarca();
                case 3 -> marcaController.eliminarMarca();
                case 4 -> marcaController.listarMarcas();
                case 5 -> System.out.println("Regresando al menú anterior...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}