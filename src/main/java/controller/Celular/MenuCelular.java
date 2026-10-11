package controller.celular;

import controller.Celular.CelularController;
import view.validarNumeros.Validaciones;

public class MenuCelular {

    private final Validaciones val = new Validaciones();
    private final CelularController celularController = new CelularController();

    public void mostrarMenu() {
        int opcion;
        do {
            opcion = val.validarEntero("""
                                      
                                      --- GESTIÓN DE CELULARES ---
                                      1. Crear
                                      2. Actualizar
                                      3. Eliminar
                                      4. Listar
                                      5. Regresar
                                      Seleccione una opción: """);
            switch (opcion) {
                case 1 -> celularController.crearCelular();
                case 2 -> celularController.actualizarCelular();
                case 3 -> celularController.eliminarCelular();
                case 4 -> celularController.listarCelulares();
                case 5 -> System.out.println("Regresando al menú anterior...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}
