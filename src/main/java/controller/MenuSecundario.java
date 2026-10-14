package controller;

import controller.Celular.MenuCelular;
import controller.Gama.MenuGama;
import controller.Marca.MenuMarca;
import controller.SistemaOperativo.MenuSistemaOperativo;
import controller.Vendedor.MenuVendedor;
import controller.Reporte.ReporteController;
import dao.impl.VentaDaoImpl;
import model.AuditoriaArchivo;
import view.validarNumeros.Validaciones;

// Aca arrancamos la clase MenuSecundario, el mero centro de mando donde el papulin gestiona marcas, celulares, ventas y reportes, bien aja aca :V
public class MenuSecundario {

    // Aca creamos el validador de numeros pa que el usuario no escriba babosadas ni rompa la consola, gg ez pa q amarre
    private final Validaciones val = new Validaciones();
    // Aca instanciamos el DAO de ventas pa consultar el historial directo de la base de datos MySQL, asi bien aja
    private final VentaDaoImpl ventaDao = new VentaDaoImpl();
    // Aca armamos el controlador de reportes pa procesar las consultas y la Stream API, chiclebomba
    private final ReporteController reporteController = new ReporteController();
    // Aca declaramos el archivo de auditoria pa ir chismoseando y guardando cada clic en el .dat, wazaa
    private final AuditoriaArchivo auditoriaArchivo = new AuditoriaArchivo();

    // Aca declaramos el metodo principal que despliega todas las opciones de gestion y reportes de la tienda
    public void mostrarMenuCelularPrincipal() {
        // Aca guardamos la opcion que escoja el usuario en una variable entera, asi bien limpio
        int opcion;
        
        // Aca armamos el ciclo do-while pa que el menu no se cierre y vuelva a salir hasta que el man quiera salir, pa q amarre bien chido
        do {
            // Aca mostramos las opciones en pantalla y pedimos un numero, y si el usuario mete letras o barrabasadas, el validador lo frena de una, eso tilin :V
            opcion = val.validarEntero("""
                                      
                                      === MENU DE GESTION Y REPORTES ===
                                      1. Gestionar Celulares
                                      2. Gestionar Marcas
                                      3. Gestionar Gamas
                                      4. Gestionar Sistemas Operativos
                                      5. Gestionar Vendedores
                                      6. Listar Historial de Ventas y Detalles
                                      7. Ver Reportes, Archivos .DAT y Auditoría (Stream API)
                                      8. Regresar al Menu Principal
                                      Seleccione una opcion: """);
                                      
            // Aca evaluamos con un switch que opcion tiro el usuario, gg ez bien elegante
            switch (opcion) {
                // Si elije 1, aqui inserto esto y hago aquello para ello: registramos en el .dat que entro a celulares y abrimos el menu de celulares, asi bien aja aca pa q amarre
                case 1 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "ACCESO_MENU", "Ingreso a Gestión de Celulares");
                    new MenuCelular().mostrarMenu();
                }
                // Si elije 2, guardamos la accion en la auditoria y abrimos el menu de marcas, piolin pa q amarre bien chido
                case 2 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "ACCESO_MENU", "Ingreso a Gestión de Marcas");
                    new MenuMarca().mostrarMenu();
                }
                // Si elije 3, guardamos el registro en el .dat y abrimos el menu de gamas, asi bien armado
                case 3 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "ACCESO_MENU", "Ingreso a Gestión de Gamas");
                    new MenuGama().mostrarMenu();
                }
                // Si elije 4, dejamos rastro en la auditoria y abrimos el menu de sistemas operativos, gg ez
                case 4 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "ACCESO_MENU", "Ingreso a Gestión de Sistemas Operativos");
                    new MenuSistemaOperativo().mostrarMenu();
                }
                // Si elije 5, apuntamos el acceso en el archivo binario y abrimos el menu de vendedores, wazaa
                case 5 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "ACCESO_MENU", "Ingreso a Gestión de Vendedores");
                    new MenuVendedor().mostrarMenu();
                }
                // Si elije 6, registramos la consulta en el .dat y sacamos todo el historial de ventas de la base de datos en la consola, bien aja
                case 6 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "CONSULTA", "Consulta del historial completo de ventas en BD");
                    System.out.println("\n--- HISTORIAL DE VENTAS ---");
                    ventaDao.obtenerTodasLasVentas().forEach(v -> {
                        System.out.println("ID Venta: " + v.getId() + " | Cliente ID: " + v.getIdPersona() + " | Vendedor ID: " + v.getIdVendedor() + " | Fecha: " + v.getFecha() + " | Total: $" + v.getTotal());
                    });
                }
                // Si elije 7, entramos al mundo poderoso de los reportes y la Stream API, pa q amarre bonito
                case 7 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "CONSULTA_REPORTES", "Acceso al submódulo analítico de reportes y auditoría");
                    int opcionReporte;
                    
                    // Aca armamos otro ciclo do-while pa el submenu de reportes, asi bien organizado aca pa que no se salga de una
                    do {
                        opcionReporte = val.validarEntero("""
                                                          
                                                          === SUBMENÚ: REPORTES Y ANALÍTICA (STREAM API & .DAT) ===
                                                          1. Celulares con stock bajo (<= 5)
                                                          2. Top 3 celulares más vendidos
                                                          3. Ventas totales por mes
                                                          4. Filtrar ventas en archivo .dat (Mayores a monto)
                                                          5. Recaudación total histórica en archivo .dat
                                                          6. Ver historial completo de auditoría de acciones (.dat)
                                                          7. Filtrar acciones de auditoría por tipo (Ej: LOGIN, VENTA, etc.)
                                                          8. Ver estadísticas de acciones por usuario (.dat)
                                                          9. Regresar al menú anterior
                                                          Seleccione una opción: """);
                                                          
                        // Aca evaluamos que reporte quiere ver el papulin con otro switch bien armado, chiclebomba
                        switch (opcionReporte) {
                            // Opcion 1: Mostramos celulares con stock menor o igual a 5 usando Stream API, gg ez
                            case 1 -> reporteController.mostrarCelularesStockBajo();
                            // Opcion 2: Sacamos el top 3 de los mas vendidos desde la BD con agrupaciones, asi bien pro
                            case 2 -> reporteController.mostrarTop3MasVendidos(new java.util.ArrayList<>());
                            // Opcion 3: Agrupamos las ventas totales por mes con la Stream API, pa q amarre re bien
                            case 3 -> reporteController.mostrarVentasPorMes(ventaDao.obtenerTodasLasVentas());
                            // Opcion 4: Pedimos un monto minimo y filtramos el archivo .dat de ventas, wazaa
                            case 4 -> {
                                double monto = val.validarDecimal("Ingrese el monto mínimo a filtrar en el archivo .dat de ventas: ");
                                reporteController.mostrarVentasArchivoMayoresA(monto);
                            }
                            // Opcion 5: Mostramos la plata total acumulada en el archivo binario .dat, eso tilin
                            case 5 -> reporteController.mostrarTotalRecaudadoArchivo();
                            // Opcion 6: Imprimimos completico el historial de acciones guardadas en el .dat de auditoria, bien aja
                            case 6 -> reporteController.mostrarHistorialAuditoriaOrdenado();
                            // Opcion 7: Pedimos un tipo de accion (como LOGIN o VENTA) y filtramos la auditoria, piolin
                            case 7 -> {
                                System.out.println("Tipos comunes registrados: LOGIN, VENTA, REGISTRO_CLIENTE, ACCESO_MENU");
                                String tipo = new view.validarTexto.Validaciones().validarTextoNoVacio("Ingrese el tipo de acción a buscar: ");
                                reporteController.mostrarAuditoriaPorTipo(tipo);
                            }
                            // Opcion 8: Sacamos cuantas veces participo cada usuario usando groupingBy y counting de la Stream API, gg ez
                            case 8 -> reporteController.mostrarEstadisticasAuditoriaPorUsuario();
                            // Opcion 9: Nos salimos del subsubmenu de reportes de vuelta al menu anterior, asi bien limpio
                            case 9 -> System.out.println("Regresando al menú de gestión...");
                            // Si mete una opcion loca que no esta, le avisamos sin armar lio, bien aja
                            default -> System.out.println("Opción no válida.");
                        }
                    } while (opcionReporte != 9);
                }
                // Si elije 8, mandamos mensaje y nos salimos al menu principal, papulin
                case 8 -> System.out.println("Regresando al menu principal...");
                // Si marca cualquier otra babosada en el menu principal, soltamos el aviso de error, asi bien tranquilo :V
                default -> System.out.println("Opcion no valida.");
            }
        // Si la opcion es 8, cerramos este menu y volvemos al inicio, pa q amarre bien chido
        } while (opcion != 8);
    }
}