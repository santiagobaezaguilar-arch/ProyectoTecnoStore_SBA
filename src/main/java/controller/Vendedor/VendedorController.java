package controller.Vendedor;

import dao.impl.ERROR;
import dao.impl.VendedorDaoImpl;
import model.Persona;
import model.Vendedor;
import view.VendedorView;
import view.validarNumeros.Validaciones;

// Aca arrancamos la clase VendedorController, la encargada de manejar todo el show de los vendedores de la tienda, bien aja pa q amarre :V xd
public class VendedorController {

    // Aca creamos el validador de numeros pa que el papulin no meta letras locas ni barrabasadas xd
    private final Validaciones valNum = new Validaciones();
    // Aca ponemos el validador de texto pa asegurar que los roles no queden con babosadas vacias, eso tilin
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    // Aca instanciamos el DAO de vendedores pa hacer los INSERT, UPDATE y DELETE directos en MySQL, gg ez
    private final VendedorDaoImpl vendedorDao = new VendedorDaoImpl();
    // Aca traemos el DAO de personas pa consultar los usuarios registrados que pueden ser vendedores, wazaa
    private final ERROR personaDao = new ERROR();
    // Aca armamos la vista de vendedores pa pintar las listas en la consola, piolin
    private final VendedorView vendedorView = new VendedorView();

    // Aca creamos el metodo pa registrar un nuevo vendedor en el sistema, bien armado
    public void crearVendedor() {
        System.out.println("\n--- REGISTRAR NUEVO VENDEDOR ---");
        System.out.println("Personas disponibles para asignar como vendedor:");
        // Aca recorremos la lista de personas con un forEach para mostrarlas en pantalla, bien aja
        personaDao.obtenerPersonas().forEach(System.out::println);

        // Aca pedimos el ID de la persona usando el validador de enteros, chiclebomba
        int idPersona = valNum.validarEntero("Ingrese el ID de la persona que sera vendedor: ");
        // Aca buscamos a la persona en la base de datos con ese ID, gg ez
        Persona persona = personaDao.buscar(idPersona);

        // Si la persona no existe (es null), aqui inserto esto y hago aquello para ello: avisamos y nos salimos con return pa que no explote, piolin
        if (persona == null) {
            System.out.println("No se encontro ninguna persona con ese ID.");
            return;
        }

        // Aca pedimos el rol del vendedor validando que no dejen el texto vacio con babosadas, wazaa
        String rol = valTexto.validarTextoNoVacio("Ingrese el rol del vendedor (Ej: Administrador, Cajero, etc.): ");

        // Aca guardamos el nuevo vendedor en la base de datos usando el DAO, pa q amarre bien chido :V
        vendedorDao.crear(new Vendedor(idPersona, rol));
        System.out.println("Vendedor registrado exitosamente.");
    }

    // Aca creamos el metodo listarVendedores pa mostrar todos los cajeros o admins usando la vista, bien limpio xd
    public void listarVendedores() {
        vendedorView.mostrarLista(vendedorDao.obtenerVendedores());
    }

    // Aca creamos el metodo actualizarVendedor pa modificar los datos de un vendedor existente, eso tilin
    public void actualizarVendedor() {
        System.out.println("\n--- ACTUALIZAR VENDEDOR ---");
        // Mostramos la lista actual pa que el usuario vea los IDs antes de cagarla, gg ez
        listarVendedores();
        // Pedimos el ID del vendedor que quiere modificar el papulin, bien aja
        int id = valNum.validarEntero("Ingrese el ID del vendedor a actualizar: ");
        
        System.out.println("Personas disponibles:");
        // Mostramos de nuevo las personas pa que elija bien el nuevo ID asociado, piolin
        personaDao.obtenerPersonas().forEach(System.out::println);
        
        // Pedimos el nuevo ID de persona y el nuevo rol con validaciones, chiclebomba xd
        int nuevoIdPersona = valNum.validarEntero("Ingrese el nuevo ID de persona asociado: ");
        String nuevoRol = valTexto.validarTextoNoVacio("Ingrese el nuevo rol del vendedor: ");
        
        // Mandamos el objeto actualizado al DAO pa que haga el UPDATE en la base de datos, wazaa pa q amarre
        vendedorDao.actualizar(new Vendedor(id, nuevoIdPersona, nuevoRol));
        System.out.println("Vendedor actualizado correctamente.");
    }

    // Aca creamos el metodo eliminarVendedor pa borrar un vendedor de la base de datos, asi bien armado :V
    public void eliminarVendedor() {
        System.out.println("\n--- ELIMINAR VENDEDOR ---");
        // Mostramos la lista pa que el usuario ubique al empleado que quiere echar, eso tilin
        listarVendedores();
        // Pedimos el ID del vendedor que se va a eliminar, gg ez
        int id = valNum.validarEntero("Ingrese el ID del vendedor que desea eliminar: ");
        // Mandamos a borrar el registro con el DAO, xd
        vendedorDao.eliminar(id);
    }
}