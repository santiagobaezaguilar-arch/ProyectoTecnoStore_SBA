package controller.Vendedor;

import view.validarNumeros.Validaciones;

public class MenuVendedor {

    private final Validaciones val = new Validaciones();
    private final VendedorController controller = new VendedorController();

    public void mostrarMenu() {
        int opcion;
        do {
            opcion = val.validarEntero("""
                                      
                                      === GESTION DE VENDEDORES ===
                                      1. Registrar Vendedor
                                      2. Listar Vendedores
                                      3. Actualizar Vendedor
                                      4. Eliminar Vendedor
                                      5. Regresar al menu anterior
                                      Seleccione una opcion: """);
            switch (opcion) {
                case 1 -> controller.crearVendedor();
                case 2 -> controller.listarVendedores();
                case 3 -> controller.actualizarVendedor();
                case 4 -> controller.eliminarVendedor();
                case 5 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 5);
    }
}
