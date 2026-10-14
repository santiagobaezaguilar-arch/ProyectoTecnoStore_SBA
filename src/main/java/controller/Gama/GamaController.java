package controller.Gama;

import dao.impl.GamaDaoImpl;
import model.Gama;
import view.GamaView;
import view.validarNumeros.Validaciones;

// Aca arrancamos la clase GamaController, la encargada de manejar todo el show de las gamas de celulares, bien aja pa q amarre :V xd
public class GamaController {

    // Aca declaramos el validador de numeros pa que el papulin no meta letras locas ni barrabasadas xd
    private final Validaciones valNum = new Validaciones();
    // Aca ponemos el validador de texto pa asegurar que no dejen campos vacios con babosadas, eso tilin
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    // Aca instanciamos el DAO de gamas pa hacer los INSERT, UPDATE y DELETE directos en la base de datos MySQL, gg ez
    private final GamaDaoImpl gamaDao = new GamaDaoImpl();
    // Aca armamos la vista de gamas pa pintar las listas de categorias en la consola, piolin
    private final GamaView gamaView = new GamaView();

    // Aca creamos el metodo pa registrar una nueva gama en el sistema, wazaa
    public void crearGama() {
        System.out.println("\n--- CREAR NUEVA GAMA ---");
        // Aca pedimos el nombre validando que no este vacio con babosadas, bien aja
        String nombre = valTexto.validarTextoNoVacio("Ingrese el nombre de la gama: ");
        // Aca guardamos la nueva gama en la base de datos usando el DAO, pa q amarre bien chido :V
        gamaDao.crear(new Gama(nombre));
    }

    // Aca creamos el metodo pa actualizar el nombre de una gama existente, chiclebomba
    public void actualizarGama() {
        System.out.println("\n--- ACTUALIZAR GAMA ---");
        // Aca mostramos la lista actual pa que el usuario vea los IDs antes de cagarla, eso tilin
        gamaView.mostrarLista(gamaDao.obtenerGamas());
        
        // Aca pedimos el ID de la gama que quiere modificar el papulin, gg ez
        int id = valNum.validarEntero("Ingrese el ID de la gama a actualizar: ");
        // Aca buscamos la gama en la base de datos con ese ID, bien aja
        Gama gama = gamaDao.buscar(id);

        // Si la gama si existe (no es null), aqui inserto esto y hago aquello para ello: pedimos el nuevo nombre, lo actualizamos y mandamos el UPDATE al DAO, piolin
        if (gama != null) {
            String nuevoNombre = valTexto.validarTextoNoVacio("Ingrese el nuevo nombre: ");
            gama.setNombre(nuevoNombre);
            gamaDao.actualizar(gama);
        } else {
            // Si no se encontro ninguna gama con ese ID, soltamos el aviso sin armar lio, xd
            System.out.println("Gama no encontrada.");
        }
    }

    // Aca creamos el metodo eliminarGama pa borrar una categoria de la base de datos, asi bien armado
    public void eliminarGama() {
        System.out.println("\n--- ELIMINAR GAMA ---");
        // Mostramos la lista pa que el usuario se ubique bien con los IDs, wazaa
        gamaView.mostrarLista(gamaDao.obtenerGamas());
        // Pedimos el ID que quiere borrar el papulin, eso tilin
        int id = valNum.validarEntero("Ingrese el ID de la gama a eliminar: ");
        // Mandamos a borrar la gama con el DAO, gg ez pa q amarre
        gamaDao.eliminar(id);
    }

    // Aca creamos el metodo listarGamas pa mostrar todas las gamas registradas usando la vista, bien limpio xd
    public void listarGamas() {
        gamaView.mostrarLista(gamaDao.obtenerGamas());
    }
}