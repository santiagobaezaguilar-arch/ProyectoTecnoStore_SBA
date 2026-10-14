package controller.Celular;


import controller.Celular.CelularController;
import view.validarNumeros.Validaciones;

// Aca arrancamos la clase MenuCelular, el menu especifico pa manejar todo el cuento de los telefonos, bien aja pa q amarre :V xd
public class MenuCelular {

    // Aca creamos el validador de numeros pa que el papulin no meta letras ni babosadas locas xd
    private final Validaciones val = new Validaciones();
    // Aca instanciamos el CelularController pa llamar a los metodos de crear, actualizar y borrar, gg ez
    private final CelularController celularController = new CelularController();

    // Aca declaramos el metodo principal pa mostrar las opciones de celulares en pantalla, wazaa
    public void mostrarMenu() {
        // Aca guardamos la opcion que escoja el usuario en una variable entera, bien limpio xd
        int opcion;
        
        // Aca armamos el ciclo do-while pa que este menu no se cierre y siga vivo, pa q amarre bien chido
        do {
            // Aca mostramos el menu en la consola y pedimos un numero, si el man escribe barrabasadas el validador lo frena de una, eso tilin :V
            opcion = val.validarEntero("""
                                       
                                       --- GESTIÓN DE CELULARES ---
                                       1. Crear
                                       2. Actualizar
                                       3. Eliminar
                                       4. Listar
                                       5. Regresar
                                       Seleccione una opción: """);
                                       
            // Aca evaluamos con un switch que opcion tiro el usuario, asi bien elegante xd
            switch (opcion) {
                // Si marca 1, aqui inserto esto y hago aquello para ello: mandamos a llamar a crearCelular pa registrar uno nuevo, piolin pa q amarre
                case 1 -> celularController.crearCelular();
                // Si marca 2, ejecutamos el metodo actualizarCelular pa modificar algun dato, chiclebomba xd
                case 2 -> celularController.actualizarCelular();
                // Si marca 3, llamamos a eliminarCelular pa borrar un telefono de la base de datos, gg ez :V
                case 3 -> celularController.eliminarCelular();
                // Si marca 4, tiramos la lista de celulares en pantalla usando la vista, bien aja xd
                case 4 -> celularController.listarCelulares();
                // Si marca 5, imprimimos mensaje y nos salimos de este menu de vuelta al anterior, eso tilin
                case 5 -> System.out.println("Regresando al menú anterior...");
                // Si mete una opcion que no existe o cualquier babosada, avisamos sin armar show, xd
                default -> System.out.println("Opción no válida.");
            }
        // Si la opcion es diferente de 5, el menu vuelve a salir bien armado, pa q amarre bien chido :V
        } while (opcion != 5);
    }
}
