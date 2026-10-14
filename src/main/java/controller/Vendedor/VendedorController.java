package controller.Vendedor;

import dao.impl.PersonaDaoImpl;
import dao.impl.VendedorDaoImpl;
import model.Persona;
import model.Vendedor;
import view.VendedorView;
import view.validarNumeros.Validaciones;

public class VendedorController {

    private final Validaciones valNum = new Validaciones();
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    private final VendedorDaoImpl vendedorDao = new VendedorDaoImpl();
    private final PersonaDaoImpl personaDao = new PersonaDaoImpl();
    private final VendedorView vendedorView = new VendedorView();

    public void crearVendedor() {
        System.out.println("\n--- REGISTRAR NUEVO VENDEDOR ---");
        System.out.println("Personas disponibles para asignar como vendedor:");
        personaDao.obtenerPersonas().forEach(System.out::println);

        int idPersona = valNum.validarEntero("Ingrese el ID de la persona que sera vendedor: ");
        Persona persona = personaDao.buscar(idPersona);

        if (persona == null) {
            System.out.println("No se encontro ninguna persona con ese ID.");
            return;
        }

        String rol = valTexto.validarTextoNoVacio("Ingrese el rol del vendedor (Ej: Administrador, Cajero, etc.): ");

        vendedorDao.crear(new Vendedor(idPersona, rol));
        System.out.println("Vendedor registrado exitosamente.");
    }

    public void listarVendedores() {
        vendedorView.mostrarLista(vendedorDao.obtenerVendedores());
    }

    public void actualizarVendedor() {
        System.out.println("\n--- ACTUALIZAR VENDEDOR ---");
        listarVendedores();
        int id = valNum.validarEntero("Ingrese el ID del vendedor a actualizar: ");
        
        System.out.println("Personas disponibles:");
        personaDao.obtenerPersonas().forEach(System.out::println);
        
        int nuevoIdPersona = valNum.validarEntero("Ingrese el nuevo ID de persona asociado: ");
        String nuevoRol = valTexto.validarTextoNoVacio("Ingrese el nuevo rol del vendedor: ");
        
        vendedorDao.actualizar(new Vendedor(id, nuevoIdPersona, nuevoRol));
        System.out.println("Vendedor actualizado correctamente.");
    }

    public void eliminarVendedor() {
        System.out.println("\n--- ELIMINAR VENDEDOR ---");
        listarVendedores();
        int id = valNum.validarEntero("Ingrese el ID del vendedor que desea eliminar: ");
        vendedorDao.eliminar(id);
    }
}