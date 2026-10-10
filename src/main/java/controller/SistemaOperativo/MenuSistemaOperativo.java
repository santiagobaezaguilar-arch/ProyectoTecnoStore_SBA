package controller.SistemaOperativo;

import view.validarNumeros.Validaciones;

public class MenuSistemaOperativo {

    private final Validaciones val = new Validaciones();

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
                case 1 -> System.out.println("[Función] Crear Sistema Operativo en desarrollo...");
                case 2 -> System.out.println("[Función] Actualizar Sistema Operativo en desarrollo...");
                case 3 -> System.out.println("[Función] Eliminar Sistema Operativo en desarrollo...");
                case 4 -> System.out.println("[Listado de Sistemas Operativos - Próximamente]");
                case 5 -> System.out.println("Regresando al menú anterior...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}