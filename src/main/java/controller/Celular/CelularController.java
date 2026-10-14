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

// Aca arrancamos la clase CelularController, el mero jefe pa administrar todos los celulares de la tienda, bien aja pa q amarre :V
public class CelularController {

    // Aca declaramos el validador de numeros pa que el papulin no meta letras locas ni barrabasadas, gg ez pa q amarre
    private final Validaciones valNum = new Validaciones();
    // Aca ponemos el validador de texto pa asegurarnos que los campos obligatorios no queden con babosadas vacías, eso tilin
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    // Aca instanciamos el DAO de celulares pa hacer los INSERT, UPDATE y DELETE directos en MySQL, bien aja
    private final CelularDaoImpl celularDao = new CelularDaoImpl();
    // Aca armamos la vista de celulares pa pintar las listas de productos en la consola, piolin
    private final CelularView celularView = new CelularView();
    
    // Aca traemos los DAOs extras pa consultar las marcas, gamas y sistemas operativos disponibles, wazaa pa q amarre bien chido
    private final MarcaDaoImpl marcaDao = new MarcaDaoImpl();
    private final GamaDaoImpl gamaDao = new GamaDaoImpl();
    private final SistemaOpDaoImpl sistemaOpDao = new SistemaOpDaoImpl();

    // Aca creamos el metodo pa registrar un nuevo celular en el sistema, asi bien armado
    public void crearCelular() {
        System.out.println("\n--- REGISTRAR NUEVO CELULAR ---");
        
        // Aca pedimos el modelo del celular validando que el texto no este vacio con babosadas, chiclebomba
        String modelo = valTexto.validarTextoNoVacio("Ingrese el modelo del celular: ");

        // Aca mostramos en pantalla todas las marcas registradas recorriendo la lista con un for, bien aja
        System.out.println("\n--- MARCAS DISPONIBLES ---");
        for (Marca m : marcaDao.obtenerMarcas()) {
            System.out.println(m);
        }
        // Aca pedimos el ID de la marca usando el validador de enteros, eso tilin
        int idMarca = valNum.validarEntero("Ingrese el ID de la marca: ");

        // Aca mostramos los sistemas operativos disponibles para que el usuario elija bien, piolin
        System.out.println("\n--- SISTEMAS OPERATIVOS DISPONIBLES ---");
        for (SistemOp so : sistemaOpDao.obtenerSistemasOp()) {
            System.out.println(so);
        }
        // Aca pedimos el ID del sistema operativo, gg ez
        int idSo = valNum.validarEntero("Ingrese el ID del sistema operativo: ");

        // Aca mostramos las gamas disponibles en la base de datos, wazaa
        System.out.println("\n--- GAMAS DISPONIBLES ---");
        for (Gama g : gamaDao.obtenerGamas()) {
            System.out.println(g);
        }
        // Aca pedimos el ID de la gama pa q amarre bonito
        int idGama = valNum.validarEntero("Ingrese el ID de la gama: ");

        // Aca pedimos el precio en decimales y el stock en enteros, asegurando que no metan barrabasadas negativas, bien aja
        double precio = valNum.validarDecimal("Ingrese el precio del celular: ");
        int stock = valNum.validarEntero("Ingrese el stock disponible: ");

        // Aca armamos el objeto Celular con todos los datos y lo mandamos a guardar a la base de datos con el DAO, gg ez pa q amarre
        celularDao.crear(new Celular(modelo, idMarca, idSo, idGama, precio, stock));
    }

    // Aca creamos el metodo listarCelulares pa mostrar todos los telefonos guardados usando la vista, piolin :V
    public void listarCelulares() {
        celularView.mostrarLista(celularDao.obtenerCelulares());
    }

    // Aca creamos el metodo eliminarCelular pa borrar un telefono de la base de datos, asi bien limpio
    public void eliminarCelular() {
        System.out.println("\n--- ELIMINAR CELULAR ---");
        // Aca llamamos a la lista primero pa que el usuario vea los IDs antes de cagarla, eso tilin
        listarCelulares();
        // Aca pedimos el ID que quiere borrar el papulin y lo mandamos a eliminar con el DAO, wazaa
        int id = valNum.validarEntero("Ingrese el ID del celular que desea eliminar: ");
        celularDao.eliminar(id);
    }

    // Aca creamos el metodo actualizarCelular pa modificar cualquier dato de un telefono existente, bien armado
    public void actualizarCelular() {
        System.out.println("\n--- ACTUALIZAR CELULAR ---");
        // Mostramos la lista pa que el usuario se ubique con los IDs, chiclebomba
        listarCelulares();
        
        // Pedimos el ID del celular a modificar y lo buscamos en la base de datos con el DAO, bien aja
        int id = valNum.validarEntero("Ingrese el ID del celular que desea actualizar: ");
        Celular celular = celularDao.buscar(id);

        // Si el celular no existe (es null), soltamos el aviso y nos salimos con return pa que no explote con ningun error, gg ez
        if (celular == null) {
            System.out.println("No se encontró ningún celular con ese ID.");
            return;
        }

        // Aca declaramos la variable pa el submenu de actualizacion y armamos un do-while, pa q amarre chido
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
            // Aca evaluamos con un switch que campo quiere cambiar el papulin, asi bien elegante
            switch (opcionActualizacion) {
                // Si marca 1, aqui inserto esto y hago aquello para ello: pedimos el nuevo modelo, lo actualizamos en el objeto y mandamos el UPDATE al DAO, piolin
                case 1 -> {
                    String nuevoModelo = valTexto.validarTextoNoVacio("Ingrese el nuevo modelo: ");
                    celular.setModelo(nuevoModelo);
                    celularDao.actualizar(celular);
                }
                // Si marca 2, mostramos las marcas, pedimos el nuevo ID y actualizamos la marca del celular, eso tilin
                case 2 -> {
                    System.out.println("\n--- MARCAS DISPONIBLES ---");
                    for (Marca m : marcaDao.obtenerMarcas()) {
                        System.out.println(m);
                    }
                    celular.setIdMarca(valNum.validarEntero("Ingrese el nuevo ID de la marca: "));
                    celularDao.actualizar(celular);
                }
                // Si marca 3, mostramos los sistemas operativos, pedimos el nuevo ID y actualizamos, wazaa
                case 3 -> {
                    System.out.println("\n--- SISTEMAS OPERATIVOS DISPONIBLES ---");
                    for (SistemOp so : sistemaOpDao.obtenerSistemasOp()) {
                        System.out.println(so);
                    }
                    celular.setIdSistemaOperativo(valNum.validarEntero("Ingrese el nuevo ID del sistema operativo: "));
                    celularDao.actualizar(celular);
                }
                // Si marca 4, mostramos las gamas, pedimos el nuevo ID y actualizamos, chiclebomba
                case 4 -> {
                    System.out.println("\n--- GAMAS DISPONIBLES ---");
                    for (Gama g : gamaDao.obtenerGamas()) {
                        System.out.println(g);
                    }
                    celular.setIdGama(valNum.validarEntero("Ingrese el nuevo ID de la gama: "));
                    celularDao.actualizar(celular);
                }
                // Si marca 5, pedimos el nuevo precio con decimales y actualizamos en la BD, bien aja
                case 5 -> {
                    celular.setPrecio(valNum.validarDecimal("Ingrese el nuevo precio: "));
                    celularDao.actualizar(celular);
                }
                // Si marca 6, pedimos el nuevo stock entero y actualizamos, gg ez
                case 6 -> {
                    celular.setStock(valNum.validarEntero("Ingrese el nuevo stock: "));
                    celularDao.actualizar(celular);
                }
                // Si marca 7, tiramos mensaje y nos salimos del submenú de actualización, pa q amarre
                case 7 -> System.out.println("Regresando al menú de celulares...");
                // Si mete barrabasadas que no estan en el menú, avisamos sin armar show, bien tranquilo :V
                default -> System.out.println("Opción no válida.");
            }
        // Si la opcion es diferente de 7, el submenú se repite bien aja aca
        } while (opcionActualizacion != 7);
    }
}