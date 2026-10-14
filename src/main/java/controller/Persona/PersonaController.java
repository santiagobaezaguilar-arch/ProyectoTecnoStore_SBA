package controller.Persona;

import dao.impl.PersonaDaoImpl;
import model.Persona;
import view.PersonaView;
import view.validarNumeros.Validaciones;

// Aca arrancamos la clase PersonaController, la encargada de manejar todo el show de los clientes y personas en la tienda, bien aja pa q amarre :V xd
public class PersonaController {

    // Aca creamos el validador de numeros pa que el papulin no meta letras locas ni barrabasadas xd
    private final Validaciones valNum = new Validaciones();
    // Aca ponemos el validador de texto pa asegurar que los campos no queden con babosadas vacias, eso tilin
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    // Aca instanciamos el DAO de personas pa hacer los INSERT, UPDATE y DELETE directos en MySQL, gg ez
    private final PersonaDaoImpl personaDao = new PersonaDaoImpl();
    // Aca armamos la vista de personas pa pintar las listas de usuarios en la consola, piolin
    private final PersonaView personaView = new PersonaView();

    // Aca creamos el metodo crearPersona pa registrar un nuevo usuario en el sistema, bien armado
    public void crearPersona() {
        System.out.println("\n--- REGISTRAR NUEVA PERSONA ---");
        // Aca pedimos el nombre validando que no este vacio con babosadas, chiclebomba
        String nombre = valTexto.validarTextoNoVacio("Ingrese el nombre: ");
        // Aca pedimos la identificacion usando el validador de enteros grandes y la pasamos a texto, eso tilin
        String identificacion = String.valueOf(valNum.validarEnteroGrande("Ingrese la identificacion: "));
        // Aca pedimos el correo validando que tenga el formato correcto con arroba y todo, gg ez
        String correo = valTexto.validarCorreo("Ingrese el correo electronico: ");
        // Aca pedimos la contraseña de forma segura, wazaa
        String contraseña = valTexto.validarTextoNoVacio("Ingrese la contraseña: ");
        // Aca pedimos el telefono usando enteros grandes pa que no falle, bien aja
        String telefono = String.valueOf(valNum.validarEnteroGrande("Ingrese el telefono: "));

        // Aca armamos el objeto Persona con todos los datos y lo mandamos a guardar a la base de datos con el DAO, pa q amarre bien chido :V
        personaDao.crear(new Persona(nombre, identificacion, correo, contraseña, telefono));
    }

    // Aca creamos el metodo listarPersonas pa mostrar todos los registros guardados usando la vista, bien limpio xd
    public void listarPersonas() {
        personaView.mostrarLista(personaDao.obtenerPersonas());
    }

    // Aca creamos el metodo eliminarPersona pa borrar a alguien de la base de datos, asi bien pro
    public void eliminarPersona() {
        System.out.println("\n--- ELIMINAR PERSONA ---");
        // Mostramos la lista primero pa que el usuario vea los IDs antes de cagarla, eso tilin
        listarPersonas();
        // Pedimos el ID de la persona que quiere borrar el papulin, gg ez
        int id = valNum.validarEntero("Ingrese el ID de la persona que desea eliminar: ");
        // Mandamos a borrar el registro con el DAO, xd
        personaDao.eliminar(id);
    }

    // Aca creamos el metodo actualizarPersona pa modificar cualquier dato de una persona existente, bien armado
    public void actualizarPersona() {
        System.out.println("\n--- ACTUALIZAR PERSONA ---");
        // Mostramos la lista actual pa que el usuario se ubique bien con los IDs, bien aja
        listarPersonas();
        
        // Pedimos el ID de la persona a modificar y la buscamos en la base de datos con el DAO, chiclebomba
        int id = valNum.validarEntero("Ingrese el ID de la persona que desea actualizar: ");
        Persona persona = personaDao.buscar(id);

        // Si la persona no existe (es null), aqui inserto esto y hago aquello para ello: avisamos y nos salimos con return pa que no explote, piolin
        if (persona == null) {
            System.out.println("No se encontro ninguna persona con ese ID.");
            return;
        }

        // Aca declaramos la variable pa el submenu de actualizacion y armamos un do-while, pa q amarre chido
        int opcionActualizacion;
        do {
            opcionActualizacion = valNum.validarEntero("""
                                                      
                                                      --- SUBMENU: QUE DESEA ACTUALIZAR? ---
                                                      1. Nombre
                                                      2. Identificacion
                                                      3. Correo
                                                      4. Contraseña
                                                      5. Telefono
                                                      6. Regresar al menu anterior
                                                      Seleccione una opcion: """);
            // Aca evaluamos con un switch que campo quiere cambiar el papulin, asi bien elegante xd
            switch (opcionActualizacion) {
                // Si marca 1, actualizamos el nombre y mandamos el UPDATE al DAO, eso tilin
                case 1 -> {
                    persona.setNombre(valTexto.validarTextoNoVacio("Ingrese el nuevo nombre: "));
                    personaDao.actualizar(persona);
                }
                // Si marca 2, pedimos la nueva identificacion en entero grande, la convertimos a texto y actualizamos, gg ez
                case 2 -> {
                    persona.setIdentificacion(String.valueOf(valNum.validarEnteroGrande("Ingrese la nueva identificacion: ")));
                    personaDao.actualizar(persona);
                }
                // Si marca 3, validamos el nuevo correo y actualizamos en la BD, wazaa
                case 3 -> {
                    persona.setCorreo(valTexto.validarCorreo("Ingrese el nuevo correo electronico: "));
                    personaDao.actualizar(persona);
                }
                // Si marca 4, pedimos la nueva contraseña y actualizamos, bien aja
                case 4 -> {
                    persona.setContraseña(valTexto.validarTextoNoVacio("Ingrese la nueva contraseña: "));
                    personaDao.actualizar(persona);
                }
                // Si marca 5, pedimos el nuevo telefono y actualizamos, piolin
                case 5 -> {
                    persona.setTelefono(String.valueOf(valNum.validarEnteroGrande("Ingrese el nuevo telefono: ")));
                    personaDao.actualizar(persona);
                }
                // Si marca 6, tiramos mensaje y nos salimos del submenú de actualización, xd
                case 6 -> System.out.println("Regresando al menu de personas...");
                // Si mete barrabasadas que no estan en el menú, avisamos sin armar show, bien tranquilo :V
                default -> System.out.println("Opcion no valida.");
            }
        // Si la opcion es diferente de 6, el submenú se repite bien aja aca, pa q amarre
        } while (opcionActualizacion != 6);
    }
}