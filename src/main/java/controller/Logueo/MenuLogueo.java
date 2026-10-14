package controller.Logueo;

import controller.Inicio.MenuInicio;
import view.validarNumeros.Validaciones;

// Aca arrancamos la clase MenuLogueo, la primera pantalla que ve el usuario al abrir la aplicacion, bien aja pa q amarre :V xd
public class MenuLogueo {

    // Aca creamos el validador de numeros pa frenar barrabasadas si el papulin mete letras locas xd
    private final Validaciones valNum = new Validaciones();
    // Aca instanciamos el LogueoController pa verificar las credenciales del vendedor en la base de datos, piolin pa q amarre
    private final LogueoController logueoController = new LogueoController();

    // Aca declaramos el metodo mostrarMenu pa desplegar la pantalla de inicio de sesion en la consola, wazaa
    public void mostrarMenu() {
        // Aca guardamos la opcion que escoja el usuario en una variable entera, bien limpio xd
        int opcion;
        
        // Aca armamos el ciclo do-while pa que el menu se mantenga activo y no se cierre de una sola vez, pa q amarre bien chido
        do {
            // Aca mostramos el menu de TecnoStore y pedimos un numero, y si escriben babosadas el validador lo frena de una, eso tilin :V
            opcion = valNum.validarEntero("""
                                         
                                         === SISTEMA TECNOSTORE ===
                                         1. Iniciar Sesion
                                         2. Salir
                                         Seleccione una opcion: """);
                                         
            // Aca evaluamos con un switch que opcion tiro el usuario, asi bien elegante xd
            switch (opcion) {
                // Si marca 1, aqui inserto esto y hago aquello para ello: mandamos a iniciar sesion llamando al controlador, bien aja
                case 1 -> {
                    // Aca guardamos el ID que nos devuelva el controlador tras validar el correo y la clave, chiclebomba
                    int idVendedor = logueoController.iniciarSesion();
                    
                    // Si el ID es diferente de -1 (o sea, el login salio exitoso y no fue un fracaso), piolin pa q amarre
                    if (idVendedor != -1) {
                        // Aca instanciamos el MenuInicio pasandole de una vez el ID del vendedor que se acaba de loguear, gg ez
                        MenuInicio menuInicio = new MenuInicio(idVendedor);
                        // Aca abrimos el menu principal del sistema para que empiece a operar, wazaa :V
                        menuInicio.mostrarMenu();
                    }
                }
                // Si marca 2, imprimimos el mensaje de salida y nos despedimos del sistema, eso tilin xd
                case 2 -> System.out.println("Saliendo del sistema...");
                
                // Si el man mete un numero chimbo que no esta en el menu, tiramos el aviso sin armar show, bien aja
                default -> System.out.println("Opcion no valida.");
            }
        // Si la opcion es diferente de 2, el menu vuelve a salir bien armado, pa q amarre bien chido :V
        } while (opcion != 2);
    }
}
