package controller.Gama;

import view.validarNumeros.Validaciones;

// Aca arrancamos la clase MenuGama, la pantalla pa administrar las categorias de celulares, bien aja pa q amarre :V xd
public class MenuGama {

    // Aca creamos el validador de numeros pa que el papulin no meta letras locas ni babosadas xd
    private final Validaciones val = new Validaciones();
    // Aca instanciamos el GamaController pa llamar los metodos de crear, actualizar y borrar gamas, gg ez
    private final GamaController gamaController = new GamaController();

    // Aca declaramos el metodo mostrarMenu pa desplegar las opciones de gamas en pantalla, wazaa
    public void mostrarMenu() {
        // Aca guardamos la opcion que escoja el usuario en una variable entera, bien limpio xd
        int opcion;
        
        // Aca armamos el ciclo do-while pa que el menu se repita y no se cierre de una vez, pa q amarre bien chido
        do {
            // Aca mostramos el menu en la consola y pedimos un numero, si el man escribe barrabasadas el validador lo frena de una, eso tilin :V
            opcion = val.validarEntero("""
                                       
                                       --- GESTIÓN DE GAMAS ---
                                       1. Crear
                                       2. Actualizar
                                       3. Eliminar
                                       4. Listar
                                       5. Regresar
                                       Seleccione una opción: """);
                                       
            // Aca evaluamos con un switch que opcion tiro el usuario, asi bien elegante xd
            switch (opcion) {
                // Si marca 1, aqui inserto esto y hago aquello para ello: mandamos a llamar a crearGama pa registrar una nueva categoria, piolin pa q amarre
                case 1 -> gamaController.crearGama();
                // Si marca 2, ejecutamos actualizarGama pa modificar el nombre, chiclebomba xd
                case 2 -> gamaController.actualizarGama();
                // Si marca 3, llamamos a eliminarGama pa borrar la categoria de la base de datos, gg ez :V
                case 3 -> gamaController.eliminarGama();
                // Si marca 4, tiramos la lista de gamas en pantalla usando la vista, bien aja xd
                case 4 -> gamaController.listarGamas();
                // Si marca 5, imprimimos mensaje y nos salimos de este menu de vuelta al anterior, eso tilin
                case 5 -> System.out.println("Regresando al menú anterior...");
                // Si mete una opcion que no existe o cualquier babosada, avisamos sin armar show, xd
                default -> System.out.println("Opción no válida.");
            }
        // Si la opcion es diferente de 5, el menu vuelve a salir bien armado, pa q amarre bien chido :V
        } while (opcion != 5);
    }
}