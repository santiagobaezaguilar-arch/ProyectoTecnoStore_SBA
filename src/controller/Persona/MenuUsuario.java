package controller.Persona; // o view según prefieras organizarlo

import view.validarNumeros.Validaciones;

public class MenuUsuario {

    private final Validaciones val = new Validaciones();

    public void mostrarMenuUsuario() {
        int opcion;
        do {
            opcion = val.validarEntero("""
                                      
                                      --- GESTIÓN DE USUARIOS ---
                                      1. Crear usuario
                                      2. Actualizar usuario
                                      3. Eliminar usuario
                                      4. Listar usuarios
                                      5. Validar usuario (Buscar por cédula)
                                      6. Regresar al menú principal
                                      Seleccione una opción: """);
            switch (opcion) {
                case 1 -> System.out.println("[Función] Crear usuario en desarrollo...");
                case 2 -> System.out.println("[Función] Actualizar usuario en desarrollo...");
                case 3 -> System.out.println("[Función] Eliminar usuario en desarrollo...");
                case 4 -> System.out.println("[Función] Listar usuarios en desarrollo...");
                case 5 -> System.out.println("[Función] Validar existencia por cédula en desarrollo...");
                case 6 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }
}
