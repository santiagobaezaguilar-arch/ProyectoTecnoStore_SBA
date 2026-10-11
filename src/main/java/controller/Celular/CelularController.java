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
import java.util.Scanner;

public class CelularController {

    private final Validaciones val = new Validaciones();
    private final CelularDaoImpl celularDao = new CelularDaoImpl();
    private final CelularView celularView = new CelularView();
    
    // Instanciamos los DAOs de los catálogos para mostrar los IDs disponibles
    private final MarcaDaoImpl marcaDao = new MarcaDaoImpl();
    private final GamaDaoImpl gamaDao = new GamaDaoImpl();
    private final SistemaOpDaoImpl sistemaOpDao = new SistemaOpDaoImpl();
    
    private final Scanner scanner = new Scanner(System.in);

    public void crearCelular() {
        System.out.println("\n--- REGISTRAR NUEVO CELULAR ---");
        
        System.out.print("Ingrese el modelo del celular: ");
        String modelo = scanner.nextLine();

        // 1. Mostrar marcas y pedir ID
        System.out.println("\n--- MARCAS DISPONIBLES ---");
        for (Marca m : marcaDao.obtenerMarcas()) {
            System.out.println(m);
        }
        int idMarca = val.validarEntero("Ingrese el ID de la marca: ");

        // 2. Mostrar sistemas operativos y pedir ID
        System.out.println("\n--- SISTEMAS OPERATIVOS DISPONIBLES ---");
        for (SistemOp so : sistemaOpDao.obtenerSistemasOp()) {
            System.out.println(so);
        }
        int idSo = val.validarEntero("Ingrese el ID del sistema operativo: ");

        // 3. Mostrar gamas y pedir ID
        System.out.println("\n--- GAMAS DISPONIBLES ---");
        for (Gama g : gamaDao.obtenerGamas()) {
            System.out.println(g);
        }
        int idGama = val.validarEntero("Ingrese el ID de la gama: ");

        // 4. Pedir precio y stock usando validaciones de números
        double precio = val.validarDecimal("Ingrese el precio del celular: ");
        int stock = val.validarEntero("Ingrese el stock disponible: ");

        Celular nuevoCelular = new Celular(modelo, idMarca, idSo, idGama, precio, stock);
        celularDao.crear(nuevoCelular);
    }

    public void listarCelulares() {
        celularView.mostrarLista(celularDao.obtenerCelulares());
    }
    
    public void eliminarCelular() {
        System.out.println("\n--- ELIMINAR CELULAR ---");
        listarCelulares();
        
        int id = val.validarEntero("Ingrese el ID del celular que desea eliminar: ");
        celularDao.eliminar(id);
    }
    
    public void actualizarCelular() {
        System.out.println("\n--- ACTUALIZAR CELULAR ---");
        celularView.mostrarLista(celularDao.obtenerCelulares());
        
        int id = val.validarEntero("Ingrese el ID del celular que desea actualizar: ");
        Celular celular = celularDao.buscar(id);

        if (celular == null) {
            System.out.println("No se encontró ningún celular con ese ID.");
            return;
        }

        int opcionActualizacion;
        do {
            opcionActualizacion = val.validarEntero("""
                                                    
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
                    System.out.print("Ingrese el nuevo modelo: ");
                    celular.setModelo(scanner.nextLine());
                    celularDao.actualizar(celular);
                }
                case 2 -> {
                    System.out.println("\n--- MARCAS DISPONIBLES ---");
                    for (Marca m : marcaDao.obtenerMarcas()) {
                        System.out.println(m);
                    }
                    int nuevoIdMarca = val.validarEntero("Ingrese el nuevo ID de la marca: ");
                    celular.setIdMarca(nuevoIdMarca);
                    celularDao.actualizar(celular);
                }
                case 3 -> {
                    System.out.println("\n--- SISTEMAS OPERATIVOS DISPONIBLES ---");
                    for (SistemOp so : sistemaOpDao.obtenerSistemasOp()) {
                        System.out.println(so);
                    }
                    int nuevoIdSo = val.validarEntero("Ingrese el nuevo ID del sistema operativo: ");
                    celular.setIdSistemaOperativo(nuevoIdSo);
                    celularDao.actualizar(celular);
                }
                case 4 -> {
                    System.out.println("\n--- GAMAS DISPONIBLES ---");
                    for (Gama g : gamaDao.obtenerGamas()) {
                        System.out.println(g);
                    }
                    int nuevoIdGama = val.validarEntero("Ingrese el nuevo ID de la gama: ");
                    celular.setIdGama(nuevoIdGama);
                    celularDao.actualizar(celular);
                }
                case 5 -> {
                    double nuevoPrecio = val.validarDecimal("Ingrese el nuevo precio: ");
                    celular.setPrecio(nuevoPrecio);
                    celularDao.actualizar(celular);
                }
                case 6 -> {
                    int nuevoStock = val.validarEntero("Ingrese el nuevo stock: ");
                    celular.setStock(nuevoStock);
                    celularDao.actualizar(celular);
                }
                case 7 -> System.out.println("Regresando al menú de celulares...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcionActualizacion != 7);
    }
    
}