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
                                          1. Iniciar Sesion
                                          2. Salir
                                          Seleccione una opcion: """);
            switch (opcion) {
                case 1 -> {
                    int idVendedor = logueoController.iniciarSesion();
                    if (idVendedor != -1) {
                        MenuInicio menuInicio = new MenuInicio(idVendedor);
                        menuInicio.mostrarMenu();
                    }
                }
                case 2 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 2);
    }
}
