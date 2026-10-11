package controller.Gama;

import view.validarNumeros.Validaciones;

public class MenuGama {

    private final Validaciones val = new Validaciones();
    private final GamaController gamaController = new GamaController();

    public void mostrarMenu() {
        int opcion;
        do {
            opcion = val.validarEntero("""
                                      
                                      --- GESTIÓN DE GAMAS ---
                                      1. Crear
                                      2. Actualizar
                                      3. Eliminar
                                      4. Listar
                                      5. Regresar
                                      Seleccione una opción: """);
            switch (opcion) {
                case 1 -> gamaController.crearGama();
                case 2 -> gamaController.actualizarGama();
                case 3 -> gamaController.eliminarGama();
                case 4 -> gamaController.listarGamas();
                case 5 -> System.out.println("Regresando al menú anterior...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}