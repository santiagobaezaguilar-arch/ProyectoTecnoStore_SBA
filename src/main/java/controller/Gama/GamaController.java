package controller.Gama;

import dao.impl.GamaDaoImpl;
import model.Gama;
import view.GamaView;
import view.validarNumeros.Validaciones;

public class GamaController {

    private final Validaciones valNum = new Validaciones();
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    private final GamaDaoImpl gamaDao = new GamaDaoImpl();
    private final GamaView gamaView = new GamaView();

    public void crearGama() {
        System.out.println("\n--- CREAR NUEVA GAMA ---");
        String nombre = valTexto.validarTextoNoVacio("Ingrese el nombre de la gama: ");
        gamaDao.crear(new Gama(nombre));
    }

    public void actualizarGama() {
        System.out.println("\n--- ACTUALIZAR GAMA ---");
        gamaView.mostrarLista(gamaDao.obtenerGamas());
        
        int id = valNum.validarEntero("Ingrese el ID de la gama a actualizar: ");
        Gama gama = gamaDao.buscar(id);

        if (gama != null) {
            String nuevoNombre = valTexto.validarTextoNoVacio("Ingrese el nuevo nombre: ");
            gama.setNombre(nuevoNombre);
            gamaDao.actualizar(gama);
        } else {
            System.out.println("Gama no encontrada.");
        }
    }

    public void eliminarGama() {
        System.out.println("\n--- ELIMINAR GAMA ---");
        gamaView.mostrarLista(gamaDao.obtenerGamas());
        int id = valNum.validarEntero("Ingrese el ID de la gama a eliminar: ");
        gamaDao.eliminar(id);
    }

    public void listarGamas() {
        gamaView.mostrarLista(gamaDao.obtenerGamas());
    }
}