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
        String identificacion = valTexto.validarTextoNoVacio("Ingrese la identificación: ");
        String correo = valTexto.validarTextoNoVacio("Ingrese el correo: ");
        String contraseña = valTexto.validarTextoNoVacio("Ingrese la contraseña: ");
        String telefono = valTexto.validarTextoNoVacio("Ingrese el teléfono: ");

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
            System.out.println("No se encontró ninguna persona con ese ID.");
            return;
        }

        int opcionActualizacion;
        do {
            opcionActualizacion = valNum.validarEntero("""
                                                    
                                                    --- SUBMENÚ: ¿QUÉ DESEA ACTUALIZAR? ---
                                                    1. Nombre
                                                    2. Identificación
                                                    3. Correo
                                                    4. Contraseña
                                                    5. Teléfono
                                                    6. Regresar al menú anterior
                                                    Seleccione una opción: """);
            switch (opcionActualizacion) {
                case 1 -> {
                    persona.setNombre(valTexto.validarTextoNoVacio("Ingrese el nuevo nombre: "));
                    personaDao.actualizar(persona);
                }
                case 2 -> {
                    persona.setIdentificacion(valTexto.validarTextoNoVacio("Ingrese la nueva identificación: "));
                    personaDao.actualizar(persona);
                }
                case 3 -> {
                    persona.setCorreo(valTexto.validarTextoNoVacio("Ingrese el nuevo correo: "));
                    personaDao.actualizar(persona);
                }
                case 4 -> {
                    persona.setContraseña(valTexto.validarTextoNoVacio("Ingrese la nueva contraseña: "));
                    personaDao.actualizar(persona);
                }
                case 5 -> {
                    persona.setTelefono(valTexto.validarTextoNoVacio("Ingrese el nuevo teléfono: "));
                    personaDao.actualizar(persona);
                }
                case 6 -> System.out.println("Regresando al menú de personas...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcionActualizacion != 6);
    }
}