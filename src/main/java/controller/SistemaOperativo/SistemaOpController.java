package controller.SistemaOperativo;

import dao.impl.SistemaOpDaoImpl;
import model.SistemOp;
import view.SistemaOpView;
import view.validarNumeros.Validaciones;

// Aca arrancamos la clase SistemaOpController, la encargada de manejar todo el show de los sistemas operativos de los telefonos, bien armado pa q amarre :V xd
public class SistemaOpController {

    // Aca creamos el validador de numeros pa que el papulin no meta letras locas ni barrabasadas xd
    private final Validaciones valNum = new Validaciones();
    // Aca ponemos el validador de texto pa asegurar que no dejen campos vacios con babosadas, eso tilin
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    // Aca traemos el DAO de sistemas operativos pa hacer los INSERT, UPDATE y DELETE directos en MySQL, gg ez
    private final SistemaOpDaoImpl sistemaOpDao = new SistemaOpDaoImpl();
    // Aca armamos la vista de sistemas operativos pa pintar las listas en la consola, piolin
    private final SistemaOpView sistemaOpView = new SistemaOpView();

    // Aca creamos el metodo crearSistemaOp pa registrar un nuevo SO en el sistema, wazaa
    public void crearSistemaOp() {
        System.out.println("\n--- CREAR NUEVO SISTEMA OPERATIVO ---");
        // Aca pedimos el nombre validando que no este vacio con babosadas, chiclebomba
        String nombre = valTexto.validarTextoNoVacio("Ingrese el nombre del sistema operativo: ");
        // Aqui inserto esto y hago aquello para ello: creamos el objeto SistemOp y lo mandamos a guardar con el DAO, pa q amarre bien chido :V
        sistemaOpDao.crear(new SistemOp(nombre));
    }

    // Aca creamos el metodo actualizarSistemaOp pa modificar el nombre de un SO existente, bien pro xd
    public void actualizarSistemaOp() {
        System.out.println("\n--- ACTUALIZAR SISTEMA OPERATIVO ---");
        // Mostramos la lista actual pa que el usuario vea los IDs antes de cagarla, eso tilin
        sistemaOpView.mostrarLista(sistemaOpDao.obtenerSistemasOp());
        
        // Pedimos el ID del sistema operativo que quiere modificar el papulin, bien aja
        int id = valNum.validarEntero("Ingrese el ID del sistema operativo a actualizar: ");
        // Buscamos el sistema operativo en la base de datos con ese ID, piolin
        SistemOp so = sistemaOpDao.buscar(id);

        // Si el SO si existe (no es null), aqui inserto esto y hago aquello para ello: pedimos el nuevo nombre y actualizamos, wazaa
        if (so != null) {
            String nuevoNombre = valTexto.validarTextoNoVacio("Ingrese el nuevo nombre: ");
            so.setNombre(nuevoNombre);
            // Mandamos el UPDATE al DAO pa guardar los cambios en la base de datos, gg ez :V
            sistemaOpDao.actualizar(so);
        } else {
            // Si no se encontro ningun SO con ese ID, soltamos el aviso de que no existe sin armar lio, xd
            System.out.println("Sistema operativo no encontrado.");
        }
    }

    // Aca creamos el metodo eliminarSistemaOp pa borrar un sistema operativo de la base de datos, bien armado
    public void eliminarSistemaOp() {
        System.out.println("\n--- ELIMINAR SISTEMA OPERATIVO ---");
        // Mostramos la lista pa que el usuario ubique bien el ID que va a borrar, wazaa
        sistemaOpView.mostrarLista(sistemaOpDao.obtenerSistemasOp());
        // Pedimos el ID que desea eliminar el papulin, eso tilin
        int id = valNum.validarEntero("Ingrese el ID del sistema operativo a eliminar: ");
        // Mandamos a borrar el registro con el DAO, gg ez pa q amarre
        sistemaOpDao.eliminar(id);
    }

    // Aca creamos el metodo listarSistemasOp pa mostrar todos los SO registrados usando la vista, bien limpio xd
    public void listarSistemasOp() {
        // Llamamos a la vista pa pintar la lista completa en la consola, bien aja
        sistemaOpView.mostrarLista(sistemaOpDao.obtenerSistemasOp());
    }
}