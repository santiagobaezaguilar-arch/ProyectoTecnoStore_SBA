package controller.SistemaOperativo;

import dao.impl.SistemaOpDaoImpl;
import model.SistemOp;
import view.SistemaOpView;
import view.validarNumeros.Validaciones;

public class SistemaOpController {

    private final Validaciones valNum = new Validaciones();
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    private final SistemaOpDaoImpl sistemaOpDao = new SistemaOpDaoImpl();
    private final SistemaOpView sistemaOpView = new SistemaOpView();

    public void crearSistemaOp() {
        System.out.println("\n--- CREAR NUEVO SISTEMA OPERATIVO ---");
        String nombre = valTexto.validarTextoNoVacio("Ingrese el nombre del sistema operativo: ");
        sistemaOpDao.crear(new SistemOp(nombre));
    }

    public void actualizarSistemaOp() {
        System.out.println("\n--- ACTUALIZAR SISTEMA OPERATIVO ---");
        sistemaOpView.mostrarLista(sistemaOpDao.obtenerSistemasOp());
        
        int id = valNum.validarEntero("Ingrese el ID del sistema operativo a actualizar: ");
        SistemOp so = sistemaOpDao.buscar(id);

        if (so != null) {
            String nuevoNombre = valTexto.validarTextoNoVacio("Ingrese el nuevo nombre: ");
            so.setNombre(nuevoNombre);
            sistemaOpDao.actualizar(so);
        } else {
            System.out.println("Sistema operativo no encontrado.");
        }
    }

    public void eliminarSistemaOp() {
        System.out.println("\n--- ELIMINAR SISTEMA OPERATIVO ---");
        sistemaOpView.mostrarLista(sistemaOpDao.obtenerSistemasOp());
        int id = valNum.validarEntero("Ingrese el ID del sistema operativo a eliminar: ");
        sistemaOpDao.eliminar(id);
    }

    public void listarSistemasOp() {
        sistemaOpView.mostrarLista(sistemaOpDao.obtenerSistemasOp());
    }
}
