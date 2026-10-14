package controller.Inicio;

import controller.Venta.MenuVenta;
import view.validarNumeros.Validaciones;
import controller.MenuPrincipal;

// Aca arrancamos la clase MenuInicio, la pantalla principal que le aparece al vendedor despues del login, bien aja pa q amarre :V xd
public class MenuInicio {

    // Aca creamos el validador de numeros pa que el papulin no meta letras locas ni barrabasadas xd
    private final Validaciones valNum = new Validaciones(); 
    // Aca guardamos el ID del vendedor que acaba de iniciar sesion, bien asegurado pa saber quien esta vendiendo
    private final int idVendedorLogueado;
    // Aca declaramos el menu de ventas pasandole de una vez el ID del vendedor, gg ez
    private final MenuVenta menuVenta;
    // Aca declaramos el menu principal para irnos a la parte de gestion, piolin
    private final MenuPrincipal menuPrincipal;

    // Aca armamos el constructor de la clase donde inicializamos los valores y los objetos necesarios, wazaa
    public MenuInicio(int idVendedorLogueado) {
        // Aca guardamos el ID recibido en la variable de la clase, bien aja
        this.idVendedorLogueado = idVendedorLogueado;
        // Aca instanciamos el menu de ventas entregandole el ID del vendedor para que sepa quien factura, pa q amarre bien chido
        this.menuVenta = new MenuVenta(idVendedorLogueado);
        // Aca instanciamos el menu principal de gestion de la tienda, chiclebomba
        this.menuPrincipal = new MenuPrincipal();
    }

    // Aca declaramos el metodo mostrarMenu pa desplegar las opciones principales en la consola, eso tilin :V
    public void mostrarMenu() {
        // Aca guardamos la opcion que escoja el usuario en una variable entera, bien limpio xd
        int opcion;
        
        // Aca armamos el ciclo do-while pa que el menu se repita y no se cierre de una vez, pa q amarre bien chido
        do {
            // Aca mostramos las opciones en la consola y pedimos un numero, si el man escribe babosadas el validador lo frena de una, bien aja
            opcion = valNum.validarEntero("""
                                         
                                         === MENÚ PRINCIPAL TECNOSTORE ===
                                         1. Vender
                                         2. Ir a Gestión (Clientes, Celulares, Marcas, etc.)
                                         3. Cerrar Sesión
                                         Seleccione una opción: """);
                                         
            // Aca evaluamos con un switch que opcion tiro el usuario, asi bien elegante xd
            switch (opcion) {
                // Si marca 1, aqui inserto esto y hago aquello para ello: abrimos el menu de ventas pa empezar a facturar telefonos, piolin pa q amarre
                case 1 -> menuVenta.mostrarMenu();
                // Si marca 2, abrimos el menu principal pa administrar celulares, marcas y demas vainas, chiclebomba xd
                case 2 -> menuPrincipal.mostrarMenuPrincipal();
                // Si marca 3, tiramos el mensaje de que estamos cerrando sesion, gg ez :V
                case 3 -> System.out.println("Cerrando sesión...");
                // Si mete un numero chimbo que no esta en el menu, avisamos sin armar show, xd
                default -> System.out.println("Opción no válida.");
            }
        // Si la opcion es diferente de 3, el menu vuelve a salir bien armado, pa q amarre bien chido :V
        } while (opcion != 3);
    }
}
