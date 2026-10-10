package controller.Celular;

import controller.Gama.MenuGama;
import controller.Marca.MenuMarca;
import controller.SistemaOperativo.MenuSistemaOperativo;
import view.validarNumeros.Validaciones;

public class MenuCelularPrincipal {

    private final Validaciones val = new Validaciones();

    public void mostrarMenuCelularPrincipal() {
        int opcion;
        do {
            opcion = val.validarEntero("""
                                      
                                      --- GESTIÓN DE CATÁLOGOS Y CELULARES ---
                                      1. Celular
                                      2. Marca
                                      3. Sistema Operativo
                                      4. Gama
                                      5. Regresar al menú principal
                                      Seleccione una opción: """);
            switch (opcion) {
                case 1 -> System.out.println("[Módulo] Gestión de Celulares (Próximamente)");
                case 2 -> {
                    MenuMarca menuMarca = new MenuMarca();
                    menuMarca.mostrarMenu();
                }
                case 3 -> {
                    MenuSistemaOperativo menuSo = new MenuSistemaOperativo();
                    menuSo.mostrarMenu();
                }
                case 4 -> {
                    MenuGama menuGama = new MenuGama();
                    menuGama.mostrarMenu();
                }
                case 5 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}