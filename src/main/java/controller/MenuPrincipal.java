package controller;

import controller.Persona.MenuPersona;
import view.validarNumeros.Validaciones;

public class MenuPrincipal {

    private final Validaciones val = new Validaciones();

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            opcion = val.validarEntero("""
                                      
                                      ===================================
                                              TIENDA TECNOSTORE
                                      ===================================
                                      1. Usuario
                                      2. Mas Opciones
                                      3. Salir
                                      Seleccione una opcion: """);// q significa el bro respeta
                                     
            switch (opcion) {
                case 1 -> {
                    MenuPersona menuUsuario = new MenuPersona();
                    menuUsuario.mostrarMenu();
                }
                case 2 -> {
                    MenuSecundario menuCelular = new MenuSecundario();
                    menuCelular.mostrarMenuCelularPrincipal();
                }
                case 3 -> System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }
}