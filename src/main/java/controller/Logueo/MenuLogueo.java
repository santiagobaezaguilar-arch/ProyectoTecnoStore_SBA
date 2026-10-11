package controller.Logueo;

import controller.Inicio.MenuInicio;
import view.validarNumeros.Validaciones;

public class MenuLogueo {

    private final Validaciones valNum = new Validaciones();
    private final LogueoController logueoController = new LogueoController();

    public void mostrarMenu() {
        int opcion;
        do {
            opcion = valNum.validarEntero("""
                                          
                                          === SISTEMA TECNOSTORE ===
                                          1. Iniciar Sesión (Vendedor)
                                          2. Salir
                                          Seleccione una opción: """);
            switch (opcion) {
                case 1 -> {
                    int idVendedor = logueoController.iniciarSesion();
                    if (idVendedor != -1) {
                        // Pasar al menú de inicio con el ID del vendedor autenticado
                        MenuInicio menuInicio = new MenuInicio(idVendedor);
                        menuInicio.mostrarMenu();
                    }
                }
                case 2 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 2);
    }
}
