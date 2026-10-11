package controller.Gama;

import dao.impl.GamaDaoImpl;
import model.Gama;
import view.GamaView;
import view.validarNumeros.Validaciones;
import java.util.List;
import java.util.Scanner;

public class GamaController {

    private final Validaciones val = new Validaciones();
    private final GamaDaoImpl gamaDao = new GamaDaoImpl();
    private final GamaView gamaView = new GamaView();
    private final Scanner scanner = new Scanner(System.in);

    public void crearGama() {
        System.out.println("\n--- CREAR NUEVA GAMA ---");
        System.out.print("Ingrese el nombre de la gama: ");
        String nombre = scanner.nextLine();
        gamaDao.crear(new Gama(nombre));
    }

    public void actualizarGama() {
        System.out.println("\n--- ACTUALIZAR GAMA ---");
        gamaView.mostrarLista(gamaDao.obtenerGamas());
        
        int id = val.validarEntero("Ingrese el ID de la gama a actualizar: ");
        Gama gama = gamaDao.buscar(id);

        if (gama != null) {
            System.out.print("Ingrese el nuevo nombre: ");
            gama.setNombre(scanner.nextLine());
            gamaDao.actualizar(gama);
        } else {
            System.out.println("Gama no encontrada.");
        }
    }

    public void eliminarGama() {
        System.out.println("\n--- ELIMINAR GAMA ---");
        gamaView.mostrarLista(gamaDao.obtenerGamas());
        int id = val.validarEntero("Ingrese el ID de la gama a eliminar: ");
        gamaDao.eliminar(id);
    }

    public void listarGamas() {
        List<Gama> lista = gamaDao.obtenerGamas();
        gamaView.mostrarLista(lista);
    }
}