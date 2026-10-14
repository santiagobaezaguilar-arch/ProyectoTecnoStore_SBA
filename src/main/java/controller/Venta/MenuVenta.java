package controller.Venta;

import view.validarNumeros.Validaciones;

// Aca arrancamos la clase MenuVenta, la pantalla principal pa manejar todo el show de la facturación y las ventas de la tienda, bien aja pa q amarre :V xd
public class MenuVenta {

    // Aca creamos el validador de numeros pa que el papulin no meta letras locas ni barrabasadas xd
    private final Validaciones valNum = new Validaciones();
    // Aca declaramos el controlador de ventas que se va a encargar de todo el proceso pesado de facturar, gg ez
    private final VentaController ventaController;

    // Aca armamos el constructor de la clase recibiendo el ID del vendedor que se logueó, bien asegurado pa saber quien factura
    public MenuVenta(int idVendedor) {
        // Aqui inserto esto y hago aquello para ello: instanciamos el VentaController pasándole de una vez el ID del vendedor pa que no se pierda la pista, piolin pa q amarre
        this.ventaController = new VentaController(idVendedor);
    }

    // Aca declaramos el metodo mostrarMenu pa desplegar las opciones de venta en la consola, wazaa
    public void mostrarMenu() {
        // Aca guardamos la opcion que escoja el usuario en una variable entera, bien limpio xd
        int opcion;
        
        // Aca armamos el ciclo do-while pa que el menu se repita y no se cierre de una sola vez, pa q amarre bien chido
        do {
            // Aca mostramos el menu en la consola y pedimos un numero, si el man escribe babosadas el validador lo frena de una, eso tilin :V
            opcion = valNum.validarEntero("""
                                         
                                         --- MENÚ DE VENTA ---
                                         1. Realizar Venta / Facturación
                                         2. Regresar al Menú Principal
                                         Seleccione una opción: """);
                                         
            // Aca evaluamos con un switch que opcion tiro el usuario, asi bien elegante xd
            switch (opcion) {
                // Si marca 1, aqui inserto esto y hago aquello para ello: mandamos a llamar a iniciarProcesoVenta pa arrancar el show de la factura, piolin pa q amarre
                case 1 -> ventaController.iniciarProcesoVenta();
                // Si marca 2, imprimimos el mensaje de retorno y nos salimos de este menú de vuelta al anterior, chiclebomba xd
                case 2 -> System.out.println("Regresando al menú principal...");
                // Si mete una opcion que no existe o cualquier babosada, avisamos sin armar show, gg ez :V
                default -> System.out.println("Opción no válida.");
            }
        // Si la opcion es diferente de 2, el menu vuelve a salir bien armado, pa q amarre bien chido :V
        } while (opcion != 2);
    }
}