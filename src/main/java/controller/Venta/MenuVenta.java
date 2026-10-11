package controller.Venta;

import view.validarNumeros.Validaciones;

public class MenuVenta {

    private final Validaciones valNum = new Validaciones();
    private final VentaController ventaController;

    public MenuVenta(int idVendedor) {
        this.ventaController = new VentaController(idVendedor);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            opcion = valNum.validarEntero("""
                                      
                                      --- MENÚ DE VENTA ---
                                      1. Realizar Venta / Facturación
                                      2. Regresar al Menú Principal
                                      Seleccione una opción: """);
            switch (opcion) {
                case 1 -> ventaController.iniciarProcesoVenta();
                case 2 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 2);
    }
}
