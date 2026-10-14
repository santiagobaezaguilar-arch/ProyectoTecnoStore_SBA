package controller.Marca;

import view.validarNumeros.Validaciones;

// Aca arrancamos la clase MenuMarca, la pantalla pa administrar los fabricantes y marcas de celulares, bien aja pa q amarre :V xd
public class MenuMarca {

    // Aca creamos el validador de numeros pa que el papulin no meta letras locas ni babosadas xd
    private final Validaciones val = new Validaciones();
    // Aca instanciamos el MarcaController pa llamar los metodos de crear, actualizar y borrar marcas, gg ez
    private final MarcaController marcaController = new MarcaController();

    // Aca declaramos el metodo mostrarMenu pa desplegar las opciones de marcas en pantalla, wazaa
    public void mostrarMenu() {
        // Aca guardamos la opcion que escoja el usuario en una variable entera, bien limpio xd
        int opcion;
        
        // Aca armamos el ciclo do-while pa que el menu se repita y no se cierre de una vez, pa q amarre bien chido
        do {
            // Aca mostramos el menu en la consola y pedimos un numero, si el man escribe barrabasadas el validador lo frena de una, eso tilin :V
            opcion = val.validarEntero("""
                                       
                                       --- GESTIÓN DE MARCAS ---
                                       1. Crear
                                       2. Actualizar
                                       3. Eliminar
                                       4. Listar
                                       5. Regresar
                                       Seleccione una opción: """);
                                       
            // Aca evaluamos con un switch que opcion tiro el usuario, asi bien elegante xd
            switch (opcion) {
                // Si marca 1, aqui inserto esto y hago aquello para ello: mandamos a llamar a crearMarca pa registrar una nueva marca, piolin pa q amarre
                case 1 -> marcaController.crearMarca();
                // Si marca 2, ejecutamos actualizarMarca pa modificar el nombre del fabricante, chiclebomba xd
                case 2 -> marcaController.actualizarMarca();
                // Si marca 3, llamamos a eliminarMarca pa borrar la marca de la base de datos, gg ez :V
                case 3 -> marcaController.eliminarMarca();
                // Si marca 4, tiramos la lista de marcas en pantalla usando la vista, bien aja xd
                case 4 -> marcaController.listarMarcas();
                // Si marca 5, imprimimos mensaje y nos salimos de este menu de vuelta al anterior, eso tilin
                case 5 -> System.out.println("Regresando al menú anterior...");
                // Si mete una opcion que no existe o cualquier babosadas, avisamos sin armar show, xd
                default -> System.out.println("Opción no válida.");
            }
        // Si la opcion es diferente de 5, el menu vuelve a salir bien armado, pa q amarre bien chido :V
        } while (opcion != 5);
    }
}