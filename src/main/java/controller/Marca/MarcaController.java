package controller.Marca;

import dao.impl.MarcaDaoImpl;
import model.Marca;
import view.MarcaView;
import view.validarNumeros.Validaciones; 

// Aca arrancamos la clase MarcaController, la encargada de manejar todo el show de las marcas de telefonos en la tienda, bien aja pa q amarre :V xd
public class MarcaController {

    // Aca declaramos el validador de numeros pa que el papulin no meta letras locas ni barrabasadas xd
    private final Validaciones valNum = new Validaciones();
    // Aca ponemos el validador de texto pa asegurar que no dejen campos vacios con babosadas, eso tilin
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    // Aca instanciamos el DAO de marcas pa hacer los INSERT, UPDATE y DELETE directos en MySQL, gg ez
    private final MarcaDaoImpl marcaDao = new MarcaDaoImpl();
    // Aca armamos la vista de marcas pa pintar las listas de fabricantes en la consola, piolin
    private final MarcaView marcaView = new MarcaView();

    // Aca creamos el metodo pa registrar una nueva marca en el sistema, wazaa
    public void crearMarca() {
        System.out.println("\n--- CREAR NUEVA MARCA ---");
        // Aca pedimos el nombre de la marca validando que no este vacio con babosadas, chiclebomba
        String nombre = valTexto.validarTextoNoVacio("Ingrese el nombre de la marca: ");
        // Aca guardamos la nueva marca en la base de datos usando el DAO, pa q amarre bien chido :V
        marcaDao.crear(new Marca(nombre));
    }

    // Aca creamos el metodo pa actualizar el nombre de una marca existente, bien armado
    public void actualizarMarca() {
        System.out.println("\n--- ACTUALIZAR MARCA ---");
        // Aca mostramos la lista actual pa que el usuario vea los IDs antes de cagarla, eso tilin
        marcaView.mostrarLista(marcaDao.obtenerMarcas());
        
        // Aca pedimos el ID de la marca que quiere modificar el papulin, gg ez
        int id = valNum.validarEntero("Ingrese el ID de la marca a actualizar: ");
        // Aca buscamos la marca en la base de datos con ese ID, bien aja
        Marca marca = marcaDao.buscar(id);

        // Si la marca si existe (no es null), aqui inserto esto y hago aquello para ello: pedimos el nuevo nombre, lo actualizamos y mandamos el UPDATE al DAO, wazaa
        if (marca != null) {
            String nuevoNombre = valTexto.validarTextoNoVacio("Ingrese el nuevo nombre: ");
            marca.setNombre(nuevoNombre);
            marcaDao.actualizar(marca);
        } else {
            // Si no se encontro ninguna marca con ese ID, soltamos el aviso sin armar lio, xd
            System.out.println("Marca no encontrada.");
        }
    }

    // Aca creamos el metodo eliminarMarca pa borrar un fabricante de la base de datos, asi bien limpio
    public void eliminarMarca() {
        System.out.println("\n--- ELIMINAR MARCA ---");
        // Mostramos la lista pa que el usuario se ubique bien con los IDs, wazaa
        marcaView.mostrarLista(marcaDao.obtenerMarcas());
        // Pedimos el ID que quiere borrar el papulin, eso tilin
        int id = valNum.validarEntero("Ingrese el ID de la marca a eliminar: ");
        // Mandamos a borrar la marca con el DAO, gg ez pa q amarre
        marcaDao.eliminar(id);
    }

    // Aca creamos el metodo listarMarcas pa mostrar todas las marcas registradas usando la vista, bien ordenado xd
    public void listarMarcas() {
        marcaView.mostrarLista(marcaDao.obtenerMarcas());
    }
}