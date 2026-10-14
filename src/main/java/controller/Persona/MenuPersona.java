package controller.Persona;

import view.validarNumeros.Validaciones;

// Aca arrancamos la clase MenuPersona, la pantalla pa administrar todo el show de las personas y clientes, bien aja pa q amarre :V xd
public class MenuPersona {

    // Aca creamos el objeto validador de numeros pa evitar barrabasadas si el papulin mete letras locas xd
    private final Validaciones val = new Validaciones();
    // Aca instanciamos el PersonaController pa llamar los metodos de crear, actualizar y borrar personas, piolin
    private final PersonaController personaController = new PersonaController();

    // Aca declaramos el metodo mostrarMenu pa desplegar las opciones de personas en la consola, wazaa
    public void mostrarMenu() {
        // Aca declaramos la variable opcion pa guardar lo que el papulin teclee, bien limpio xd
        int opcion;
        
        // Aca armamos el ciclo do-while pa que el menu se repita y no se cierre de una sola vez, pa q amarre bien chido
        do {
            // Aca mostramos el menu en la consola y pedimos un numero, si el man escribe babosadas el validador lo frena de una, eso tilin :V
            opcion = val.validarEntero("""
                                       
                                       --- GESTIÓN DE PERSONAS ---
                                       1. Crear
                                       2. Actualizar
                                       3. Eliminar
                                       4. Listar
                                       5. Regresar
                                       Seleccione una opción: """);
                                       
            // Aca evaluamos con un switch que opcion tiro el usuario, asi bien elegante xd
            switch (opcion) {
                // Si marca 1, aqui inserto esto y hago aquello para ello: mandamos a llamar a crearPersona pa registrar un nuevo usuario, piolin pa q amarre
                case 1 -> personaController.crearPersona();
                // Si marca 2, ejecutamos actualizarPersona pa modificar los datos de la persona, chiclebomba xd
                case 2 -> personaController.actualizarPersona();
                // Si marca 3, llamamos a eliminarPersona pa borrar a alguien de la base de datos, gg ez :V
                case 3 -> personaController.eliminarPersona();
                // Si marca 4, tiramos la lista de personas en pantalla usando la vista, bien aja xd
                case 4 -> personaController.listarPersonas();
                // Si marca 5, imprimimos mensaje y nos salimos de este menu de vuelta al anterior, eso tilin
                case 5 -> System.out.println("Regresando al menú anterior...");
                // Si mete una opcion que no existe o cualquier babosada, avisamos sin armar show, xd
                default -> System.out.println("Opción no válida.");
            }
        // Si la opcion es diferente de 5, el menu vuelve a salir bien armado, pa q amarre bien chido :V
        } while (opcion != 5);
    }
}