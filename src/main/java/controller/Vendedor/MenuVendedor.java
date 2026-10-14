package controller.Vendedor;

import view.validarNumeros.Validaciones;

// Aca arrancamos la clase MenuVendedor, la pantalla pa administrar todo el cuento de los cajeros y admins, bien aja pa q amarre :V xd
public class MenuVendedor {

    // Aca creamos el validador de numeros pa que el papulin no meta letras locas ni barrabasadas xd
    private final Validaciones val = new Validaciones();
    // Aca instanciamos el VendedorController pa llamar a los metodos de registrar, actualizar y borrar empleados, gg ez
    private final VendedorController controller = new VendedorController();

    // Aca declaramos el metodo mostrarMenu pa desplegar las opciones de vendedores en la consola, wazaa
    public void mostrarMenu() {
        // Aca guardamos la opcion que escoja el usuario en una variable entera, bien limpio xd
        int opcion;
        
        // Aca armamos el ciclo do-while pa que el menu se repita y no se cierre de una vez, pa q amarre bien chido
        do {
            // Aca mostramos el menu en la consola y pedimos un numero, si el man escribe babosadas el validador lo frena de una, eso tilin :V
            opcion = val.validarEntero("""
                                       
                                       === GESTION DE VENDEDORES ===
                                       1. Registrar Vendedor
                                       2. Listar Vendedores
                                       3. Actualizar Vendedor
                                       4. Eliminar Vendedor
                                       5. Regresar al menu anterior
                                       Seleccione una opcion: """);
                                       
            // Aca evaluamos con un switch que opcion tiro el usuario, asi bien elegante xd
            switch (opcion) {
                // Si marca 1, aqui inserto esto y hago aquello para ello: mandamos a llamar a crearVendedor pa registrar un nuevo empleado, piolin pa q amarre
                case 1 -> controller.crearVendedor();
                // Si marca 2, ejecutamos listarVendedores pa mostrar la lista completa de personal, chiclebomba xd
                case 2 -> controller.listarVendedores();
                // Si marca 3, llamamos a actualizarVendedor pa modificar los datos de un empleado, gg ez :V
                case 3 -> controller.actualizarVendedor();
                // Si marca 4, tiramos el metodo eliminarVendedor pa echar al cajero de la base de datos, bien aja xd
                case 4 -> controller.eliminarVendedor();
                // Si marca 5, imprimimos mensaje y nos salimos de este menu de vuelta al anterior, eso tilin
                case 5 -> System.out.println("Regresando...");
                // Si mete una opcion que no existe o cualquier babosada, avisamos sin armar show, xd
                default -> System.out.println("Opcion no valida.");
            }
        // Si la opcion es diferente de 5, el menu vuelve a salir bien armado, pa q amarre bien chido :V
        } while (opcion != 5);
    }
}