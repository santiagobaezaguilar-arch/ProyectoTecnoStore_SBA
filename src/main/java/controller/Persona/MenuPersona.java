package controller.Persona;

import view.validarNumeros.Validaciones;

public class MenuPersona {

    private final Validaciones val = new Validaciones();
    private final PersonaController personaController = new PersonaController();

    public void mostrarMenu() {
        int opcion;
        do {
            opcion = val.validarEntero("""
                                      
                                      --- GESTIÓN DE PERSONAS ---
                                      1. Crear
                                      2. Actualizar
                                      3. Eliminar
                                      4. Listar
                                      5. Regresar
                                      Seleccione una opción: """);
            switch (opcion) {
                case 1 -> personaController.crearPersona();
                case 2 -> personaController.actualizarPersona();
                case 3 -> personaController.eliminarPersona();
                case 4 -> personaController.listarPersonas();
                case 5 -> System.out.println("Regresando al menú anterior...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}