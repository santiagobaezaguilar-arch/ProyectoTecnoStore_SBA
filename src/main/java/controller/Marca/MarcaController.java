package controller.Marca;

import dao.impl.MarcaDaoImpl;
import model.Marca;
import view.MarcaView;
import view.validarNumeros.Validaciones; 

public class MarcaController {

    private final Validaciones valNum = new Validaciones();
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    private final MarcaDaoImpl marcaDao = new MarcaDaoImpl();
    private final MarcaView marcaView = new MarcaView();

    public void crearMarca() {
        System.out.println("\n--- CREAR NUEVA MARCA ---");
        String nombre = valTexto.validarTextoNoVacio("Ingrese el nombre de la marca: ");
        marcaDao.crear(new Marca(nombre));
    }

    public void actualizarMarca() {
        System.out.println("\n--- ACTUALIZAR MARCA ---");
        marcaView.mostrarLista(marcaDao.obtenerMarcas());
        
        int id = valNum.validarEntero("Ingrese el ID de la marca a actualizar: ");
        Marca marca = marcaDao.buscar(id);

        if (marca != null) {
            String nuevoNombre = valTexto.validarTextoNoVacio("Ingrese el nuevo nombre: ");
            marca.setNombre(nuevoNombre);
            marcaDao.actualizar(marca);
        } else {
            System.out.println("Marca no encontrada.");
        }
    }

    public void eliminarMarca() {
        System.out.println("\n--- ELIMINAR MARCA ---");
        marcaView.mostrarLista(marcaDao.obtenerMarcas());
        int id = valNum.validarEntero("Ingrese el ID de la marca a eliminar: ");
        marcaDao.eliminar(id);
    }

    public void listarMarcas() {
        marcaView.mostrarLista(marcaDao.obtenerMarcas());
    }
}