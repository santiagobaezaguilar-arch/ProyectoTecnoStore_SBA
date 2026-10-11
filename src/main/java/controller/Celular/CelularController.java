package controller.Celular;

import dao.impl.CelularDaoImpl;
import dao.impl.GamaDaoImpl;
import dao.impl.MarcaDaoImpl;
import dao.impl.SistemaOpDaoImpl;
import model.Celular;
import model.Gama;
import model.Marca;
import model.SistemOp;
import view.CelularView;
import view.validarNumeros.Validaciones;

public class CelularController {

    private final Validaciones valNum = new Validaciones();
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    private final CelularDaoImpl celularDao = new CelularDaoImpl();
    private final CelularView celularView = new CelularView();
    
    private final MarcaDaoImpl marcaDao = new MarcaDaoImpl();
    private final GamaDaoImpl gamaDao = new GamaDaoImpl();
    private final SistemaOpDaoImpl sistemaOpDao = new SistemaOpDaoImpl();

    public void crearCelular() {
        System.out.println("\n--- REGISTRAR NUEVO CELULAR ---");
        
        String modelo = valTexto.validarTextoNoVacio("Ingrese el modelo del celular: ");

        System.out.println("\n--- MARCAS DISPONIBLES ---");
        for (Marca m : marcaDao.obtenerMarcas()) {
            System.out.println(m);
        }
        int idMarca = valNum.validarEntero("Ingrese el ID de la marca: ");

        System.out.println("\n--- SISTEMAS OPERATIVOS DISPONIBLES ---");
        for (SistemOp so : sistemaOpDao.obtenerSistemasOp()) {
            System.out.println(so);
        }
        int idSo = valNum.validarEntero("Ingrese el ID del sistema operativo: ");

        System.out.println("\n--- GAMAS DISPONIBLES ---");
        for (Gama g : gamaDao.obtenerGamas()) {
            System.out.println(g);
        }
        int idGama = valNum.validarEntero("Ingrese el ID de la gama: ");

        double precio = valNum.validarDecimal("Ingrese el precio del celular: ");
        int stock = valNum.validarEntero("Ingrese el stock disponible: ");

        celularDao.crear(new Celular(modelo, idMarca, idSo, idGama, precio, stock));
    }

    public void listarCelulares() {
        celularView.mostrarLista(celularDao.obtenerCelulares());
    }

    public void eliminarCelular() {
        System.out.println("\n--- ELIMINAR CELULAR ---");
        listarCelulares();
        int id = valNum.validarEntero("Ingrese el ID del celular que desea eliminar: ");
        celularDao.eliminar(id);
    }

    public void actualizarCelular() {
        System.out.println("\n--- ACTUALIZAR CELULAR ---");
        listarCelulares();
        
        int id = valNum.validarEntero("Ingrese el ID del celular que desea actualizar: ");
        Celular celular = celularDao.buscar(id);

        if (celular == null) {
            System.out.println("No se encontró ningún celular con ese ID.");
            return;
        }

        int opcionActualizacion;
        do {
            opcionActualizacion = valNum.validarEntero("""
                                                    
                                                    --- SUBMENÚ: ¿QUÉ DESEA ACTUALIZAR? ---
                                                    1. Modelo
                                                    2. Marca (por ID)
                                                    3. Sistema Operativo (por ID)
                                                    4. Gama (por ID)
                                                    5. Precio
                                                    6. Stock
                                                    7. Regresar al menú anterior
                                                    Seleccione una opción: """);
            switch (opcionActualizacion) {
                case 1 -> {
                    String nuevoModelo = valTexto.validarTextoNoVacio("Ingrese el nuevo modelo: ");
                    celular.setModelo(nuevoModelo);
                    celularDao.actualizar(celular);
                }
                case 2 -> {
                    System.out.println("\n--- MARCAS DISPONIBLES ---");
                    for (Marca m : marcaDao.obtenerMarcas()) {
                        System.out.println(m);
                    }
                    celular.setIdMarca(valNum.validarEntero("Ingrese el nuevo ID de la marca: "));
                    celularDao.actualizar(celular);
                }
                case 3 -> {
                    System.out.println("\n--- SISTEMAS OPERATIVOS DISPONIBLES ---");
                    for (SistemOp so : sistemaOpDao.obtenerSistemasOp()) {
                        System.out.println(so);
                    }
                    celular.setIdSistemaOperativo(valNum.validarEntero("Ingrese el nuevo ID del sistema operativo: "));
                    celularDao.actualizar(celular);
                }
                case 4 -> {
                    System.out.println("\n--- GAMAS DISPONIBLES ---");
                    for (Gama g : gamaDao.obtenerGamas()) {
                        System.out.println(g);
                    }
                    celular.setIdGama(valNum.validarEntero("Ingrese el nuevo ID de la gama: "));
                    celularDao.actualizar(celular);
                }
                case 5 -> {
                    celular.setPrecio(valNum.validarDecimal("Ingrese el nuevo precio: "));
                    celularDao.actualizar(celular);
                }
                case 6 -> {
                    celular.setStock(valNum.validarEntero("Ingrese el nuevo stock: "));
                    celularDao.actualizar(celular);
                }
                case 7 -> System.out.println("Regresando al menú de celulares...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcionActualizacion != 7);
    }
}