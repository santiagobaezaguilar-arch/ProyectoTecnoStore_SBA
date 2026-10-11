package controller.Persona;

import dao.impl.PersonaDaoImpl;
import model.Persona;
import view.PersonaView;
import view.validarNumeros.Validaciones;
import java.util.List;
import java.util.Scanner;

public class PersonaController {

    private final Validaciones val = new Validaciones();
    private final PersonaDaoImpl personaDao = new PersonaDaoImpl();
    private final PersonaView personaView = new PersonaView();
    private final Scanner scanner = new Scanner(System.in);

    public void crearPersona() {
        System.out.println("\n--- REGISTRAR NUEVA PERSONA ---");
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la identificación: ");
        String identificacion = scanner.nextLine();
        System.out.print("Ingrese el correo: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contraseña = scanner.nextLine();
        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine();

        Persona nuevaPersona = new Persona(nombre, identificacion, correo, contraseña, telefono);
        personaDao.crear(nuevaPersona);
    }

    public void listarPersonas() {
        List<Persona> lista = personaDao.obtenerPersonas();
        personaView.mostrarLista(lista);
    }

    public void eliminarPersona() {
        System.out.println("\n--- ELIMINAR PERSONA ---");
        listarPersonas();
        int id = val.validarEntero("Ingrese el ID de la persona que desea eliminar: ");
        personaDao.eliminar(id);
    }

    public void actualizarPersona() {
        System.out.println("\n--- ACTUALIZAR PERSONA ---");
        listarPersonas();
        
        int id = val.validarEntero("Ingrese el ID de la persona que desea actualizar: ");
        Persona persona = personaDao.buscar(id);

        if (persona == null) {
            System.out.println("No se encontró ninguna persona con ese ID.");
            return;
        }

        int opcionActualizacion;
        do {
            opcionActualizacion = val.validarEntero("""
                                                    
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
                    System.out.print("Ingrese el nuevo nombre: ");
                    persona.setNombre(scanner.nextLine());
                    personaDao.actualizar(persona);
                }
                case 2 -> {
                    System.out.print("Ingrese la nueva identificación: ");
                    persona.setIdentificacion(scanner.nextLine());
                    personaDao.actualizar(persona);
                }
                case 3 -> {
                    System.out.print("Ingrese el nuevo correo: ");
                    persona.setCorreo(scanner.nextLine());
                    personaDao.actualizar(persona);
                }
                case 4 -> {
                    System.out.print("Ingrese la nueva contraseña: ");
                    persona.setContraseña(scanner.nextLine());
                    personaDao.actualizar(persona);
                }
                case 5 -> {
                    System.out.print("Ingrese el nuevo teléfono: ");
                    persona.setTelefono(scanner.nextLine());
                    personaDao.actualizar(persona);
                }
                case 6 -> System.out.println("Regresando al menú de personas...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcionActualizacion != 6);
    }
}