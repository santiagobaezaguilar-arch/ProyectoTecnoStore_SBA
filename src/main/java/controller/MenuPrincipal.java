package controller;

import controller.Persona.MenuPersona;
import view.validarNumeros.Validaciones;

// Aca la clase MenuPrincipal, el mero jefe de la pantalla de opciones de la tienda, bien aja aca bien armado :V
public class MenuPrincipal {

    // Aca creamos un objeto validaciones (val) pa q el usuario no meta letras locas ni haga babosadas al elegir, gg ez pa q amarre bien
    private final Validaciones val = new Validaciones();

    // Aca metemos el metodo principal pa mostrar el menu en la consola, wazaa
    public void mostrarMenuPrincipal() {
        // Aca declaramos la variable opcion pa guardar lo que el papulin teclee
        int opcion;
        
        // Aca armamos un ciclo do-while pa que el menu se repita y no se cierre de una vez, pa q amarre chido
        do {
            // Aca mostramos el menu principal bien elegante de TecnoStore y le pedimos al usuario que elija, asi bien aja aca si bien bien aca
            opcion = val.validarEntero("""
                                       
                                       ===================================
                                            TIENDA TECNOSTORE
                                       ===================================
                                       1. Usuario
                                       2. Mas Opciones
                                       3. Salir
                                       Seleccione una opcion: """); // q significa el bro respeta, puro estilo la grasa
                                       
            // Aca evaluamos que opcion eligio el man con un switch-case bien limpio, gg ez
            switch (opcion) {
                // Si marca 1, aqui inserto esto y hago aquello para ello: abrimos el menu de personas pa gestionar perfiles, asi bien aja aca pa q amarre
                case 1 -> {
                    MenuPersona menuUsuario = new MenuPersona();
                    menuUsuario.mostrarMenu();
                }
                // Si marca 2, aqui abrimos el MenuSecundario pa ver celulares, marcas y reportes, asi bien aja aca si bien aca pa q amarre bien chido
                case 2 -> {
                    MenuSecundario menuCelular = new MenuSecundario();
                    menuCelular.mostrarMenuCelularPrincipal();
                }
                // Si marca 3, tiramos el mensaje de despedida pa salir del sistema, gg ez :V
                case 3 -> System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                
                // Si el man mete un numero chimbo que no existe, saltamos el aviso de opcion no valida, asi bien aja
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
            
        // Si la opcion es diferente de 3, el ciclo se repite bien aja aca pa que la app siga viva y no se muera de la nada
        } while (opcion != 3);
    }
}