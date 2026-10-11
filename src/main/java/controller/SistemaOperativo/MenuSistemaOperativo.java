package controller.SistemaOperativo;

import view.validarNumeros.Validaciones;

public class MenuSistemaOperativo {

    private final Validaciones val = new Validaciones();
    private final SistemaOpController sistemaOpController = new SistemaOpController();

    public void mostrarMenu() {
        int opcion;
        do {
            opcion = val.validarEntero("""
                                      
                                      --- GESTIÓN DE SISTEMAS OPERATIVOS ---
                                      1. Crear
                                      2. Actualizar
                                      3. Eliminar
                                      4. Listar
                                      5. Regresar
                                      Seleccione una opción: """);
            switch (opcion) {
                case 1 -> sistemaOpController.crearSistemaOp();
                case 2 -> sistemaOpController.actualizarSistemaOp();
                case 3 -> sistemaOpController.eliminarSistemaOp();
                case 4 -> sistemaOpController.listarSistemasOp();
                case 5 -> System.out.println("Regresando al menú anterior...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}