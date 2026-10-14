package controller.Persona;

import dao.impl.PersonaDaoImpl;
import model.Persona;
import view.PersonaView;
import view.validarNumeros.Validaciones;

public class PersonaController {

    private final Validaciones valNum = new Validaciones();
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    private final PersonaDaoImpl personaDao = new PersonaDaoImpl();
    private final PersonaView personaView = new PersonaView();

    public void crearPersona() {
        System.out.println("\n--- REGISTRAR NUEVA PERSONA ---");
        String nombre = valTexto.validarTextoNoVacio("Ingrese el nombre: ");
        String identificacion = String.valueOf(valNum.validarEnteroGrande("Ingrese la identificacion: "));
        String correo = valTexto.validarCorreo("Ingrese el correo electronico: ");
        String contraseña = valTexto.validarTextoNoVacio("Ingrese la contraseña: ");
        String telefono = String.valueOf(valNum.validarEnteroGrande("Ingrese el telefono: "));

        personaDao.crear(new Persona(nombre, identificacion, correo, contraseña, telefono));
    }

    public void listarPersonas() {
        personaView.mostrarLista(personaDao.obtenerPersonas());
    }

    public void eliminarPersona() {
        System.out.println("\n--- ELIMINAR PERSONA ---");
        listarPersonas();
        int id = valNum.validarEntero("Ingrese el ID de la persona que desea eliminar: ");
        personaDao.eliminar(id);
    }

    public void actualizarPersona() {
        System.out.println("\n--- ACTUALIZAR PERSONA ---");
        listarPersonas();
        
        int id = valNum.validarEntero("Ingrese el ID de la persona que desea actualizar: ");
        Persona persona = personaDao.buscar(id);

        if (persona == null) {
            System.out.println("No se encontro ninguna persona con ese ID.");
            return;
        }

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
            switch (opcionActualizacion) {
                case 1 -> {
                    persona.setNombre(valTexto.validarTextoNoVacio("Ingrese el nuevo nombre: "));
                    personaDao.actualizar(persona);
                }
                case 2 -> {
                    persona.setIdentificacion(String.valueOf(valNum.validarEnteroGrande("Ingrese la nueva identificacion: ")));
                    personaDao.actualizar(persona);
                }
                case 3 -> {
                    persona.setCorreo(valTexto.validarCorreo("Ingrese el nuevo correo electronico: "));
                    personaDao.actualizar(persona);
                }
                case 4 -> {
                    persona.setContraseña(valTexto.validarTextoNoVacio("Ingrese la nueva contraseña: "));
                    personaDao.actualizar(persona);
                }
                case 5 -> {
                    persona.setTelefono(String.valueOf(valNum.validarEnteroGrande("Ingrese el nuevo telefono: ")));
                    personaDao.actualizar(persona);
                }
                case 6 -> System.out.println("Regresando al menu de personas...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcionActualizacion != 6);
    }
}