package controller.SistemaOperativo;

import dao.impl.SistemaOpDaoImpl;
import model.SistemOp;
import view.SistemaOpView;
import view.validarNumeros.Validaciones;
import java.util.List;
import java.util.Scanner;

public class SistemaOpController {

    private final Validaciones val = new Validaciones();
    private final SistemaOpDaoImpl sistemaOpDao = new SistemaOpDaoImpl();
    private final SistemaOpView sistemaOpView = new SistemaOpView();
    private final Scanner scanner = new Scanner(System.in);

    public void crearSistemaOp() {
        System.out.println("\n--- CREAR NUEVO SISTEMA OPERATIVO ---");
        System.out.print("Ingrese el nombre del sistema operativo: ");
        String nombre = scanner.nextLine();
        sistemaOpDao.crear(new SistemOp(nombre));
    }

    public void actualizarSistemaOp() {
        System.out.println("\n--- ACTUALIZAR SISTEMA OPERATIVO ---");
        sistemaOpView.mostrarLista(sistemaOpDao.obtenerSistemasOp());
        
        int id = val.validarEntero("Ingrese el ID del sistema operativo a actualizar: ");
        SistemOp so = sistemaOpDao.buscar(id);

        if (so != null) {
            System.out.print("Ingrese el nuevo nombre: ");
            so.setNombre(scanner.nextLine());
            sistemaOpDao.actualizar(so);
        } else {
            System.out.println("Sistema operativo no encontrado.");
        }
    }

    public void eliminarSistemaOp() {
        System.out.println("\n--- ELIMINAR SISTEMA OPERATIVO ---");
        sistemaOpView.mostrarLista(sistemaOpDao.obtenerSistemasOp());
        int id = val.validarEntero("Ingrese el ID del sistema operativo a eliminar: ");
        sistemaOpDao.eliminar(id);
    }

    public void listarSistemasOp() {
        List<SistemOp> lista = sistemaOpDao.obtenerSistemasOp();
        sistemaOpView.mostrarLista(lista);
    }
}
