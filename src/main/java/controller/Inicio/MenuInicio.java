package controller.Inicio;

import controller.Venta.MenuVenta;
import view.validarNumeros.Validaciones;
import controller.MenuPrincipal;

public class MenuInicio {

    private final Validaciones valNum = new Validaciones(); 
    private final int idVendedorLogueado;
    private final MenuVenta menuVenta;
    private final MenuPrincipal menuPrincipal;

    public MenuInicio(int idVendedorLogueado) {
        this.idVendedorLogueado = idVendedorLogueado;
        this.menuVenta = new MenuVenta(idVendedorLogueado);
        this.menuPrincipal = new MenuPrincipal();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            opcion = valNum.validarEntero("""
                                          
                                          === MENÚ PRINCIPAL TECNOSTORE ===
                                          1. Vender
                                          2. Ir a Gestión (Clientes, Celulares, Marcas, etc.)
                                          3. Cerrar Sesión
                                          Seleccione una opción: """);
            switch (opcion) {
                case 1 -> menuVenta.mostrarMenu();
                case 2 -> menuPrincipal.mostrarMenuPrincipal();
                case 3 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }
}
