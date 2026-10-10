package controller.Celular;

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
                   // pa q amarre le pongo la variable, ni idea si sirve con las solid esto, capaz no xdddd
                    menuCatalogoGenerico("MARCA");
                }
                case 3 -> {

                    menuCatalogoGenerico("SISTEMA OPERATIVO");
                }
                case 4 -> {
 
                    menuCatalogoGenerico("GAMA");
                }
                case 5 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    // tabla papulina q sirve pa las 3 tablas bien asi npc q lit son lo mismo waaaaa
    private void menuCatalogoGenerico(String nombreTabla) {
        int opcion;
        do {
            opcion = val.validarEntero("""
                                      
                                      --- GESTIÓN DE %s ---
                                      1. Crear
                                      2. Actualizar
                                      3. Eliminar
                                      4. Listar
                                      5. Regresar
                                      Seleccione una opción: """.formatted(nombreTabla));
            switch (opcion) {
                case 1 -> System.out.println("Crear " + nombreTabla + " en desarrollo...");
                case 2 -> System.out.println("Actualizar " + nombreTabla + " en desarrollo...");
                case 3 -> System.out.println("Eliminar " + nombreTabla + " en desarrollo...");
                case 4 -> System.out.println("Listar " + nombreTabla + " en desarrollo...");
                case 5 -> System.out.println("Regresando al menú anterior...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}