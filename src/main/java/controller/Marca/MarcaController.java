package controller.Marca;

import dao.impl.MarcaDaoImpl;
import model.Marca;
import view.MarcaView;
import view.validarNumeros.Validaciones;
import java.util.List;
import java.util.Scanner;

public class MarcaController {

    private final Validaciones val = new Validaciones();
    private final MarcaDaoImpl marcaDao = new MarcaDaoImpl();
    private final MarcaView marcaView = new MarcaView();
    private final Scanner scanner = new Scanner(System.in);

    public void crearMarca() {
        System.out.println("\n--- CREAR NUEVA MARCA ---");
        System.out.print("Ingrese el nombre de la marca: ");
        String nombre = scanner.nextLine();
        marcaDao.crear(new Marca(nombre));
    }

    public void actualizarMarca() {
        System.out.println("\n--- ACTUALIZAR MARCA ---");
        marcaView.mostrarLista(marcaDao.obtenerMarcas());
        
        int id = val.validarEntero("Ingrese el ID de la marca a actualizar: ");
        Marca marca = marcaDao.buscar(id);

        if (marca != null) {
            System.out.print("Ingrese el nuevo nombre: ");
            marca.setNombre(scanner.nextLine());
            marcaDao.actualizar(marca);
        } else {
            System.out.println("Marca no encontrada.");
        }
    }

    public void eliminarMarca() {
        System.out.println("\n--- ELIMINAR MARCA ---");
        marcaView.mostrarLista(marcaDao.obtenerMarcas());
        int id = val.validarEntero("Ingrese el ID de la marca a eliminar: ");
        marcaDao.eliminar(id);
    }

    public void listarMarcas() {
        List<Marca> lista = marcaDao.obtenerMarcas();
        marcaView.mostrarLista(lista);
    }
}