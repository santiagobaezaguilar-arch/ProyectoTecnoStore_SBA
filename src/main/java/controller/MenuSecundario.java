package controller;

import controller.celular.MenuCelular;
import controller.Gama.MenuGama;
import controller.Marca.MenuMarca;
import controller.SistemaOperativo.MenuSistemaOperativo;
import controller.Vendedor.MenuVendedor;
import controller.Reporte.ReporteController;
import dao.impl.VentaDaoImpl;
import model.AuditoriaArchivo;
import view.validarNumeros.Validaciones;

public class MenuSecundario {

    private final Validaciones val = new Validaciones();
    private final VentaDaoImpl ventaDao = new VentaDaoImpl();
    private final ReporteController reporteController = new ReporteController();
    private final AuditoriaArchivo auditoriaArchivo = new AuditoriaArchivo();

    public void mostrarMenuCelularPrincipal() {
        int opcion;
        do {
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
            switch (opcion) {
                case 1 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "ACCESO_MENU", "Ingreso a Gestión de Celulares");
                    new MenuCelular().mostrarMenu();
                }
                case 2 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "ACCESO_MENU", "Ingreso a Gestión de Marcas");
                    new MenuMarca().mostrarMenu();
                }
                case 3 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "ACCESO_MENU", "Ingreso a Gestión de Gamas");
                    new MenuGama().mostrarMenu();
                }
                case 4 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "ACCESO_MENU", "Ingreso a Gestión de Sistemas Operativos");
                    new MenuSistemaOperativo().mostrarMenu();
                }
                case 5 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "ACCESO_MENU", "Ingreso a Gestión de Vendedores");
                    new MenuVendedor().mostrarMenu();
                }
                case 6 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "CONSULTA", "Consulta del historial completo de ventas en BD");
                    System.out.println("\n--- HISTORIAL DE VENTAS ---");
                    ventaDao.obtenerTodasLasVentas().forEach(v -> {
                        System.out.println("ID Venta: " + v.getId() + " | Cliente ID: " + v.getIdPersona() + " | Vendedor ID: " + v.getIdVendedor() + " | Fecha: " + v.getFecha() + " | Total: $" + v.getTotal());
                    });
                }
                case 7 -> {
                    auditoriaArchivo.registrarAccion("Sistema", "CONSULTA_REPORTES", "Acceso al submódulo analítico de reportes y auditoría");
                    int opcionReporte;
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
                        switch (opcionReporte) {
                            case 1 -> reporteController.mostrarCelularesStockBajo();
                            case 2 -> reporteController.mostrarTop3MasVendidos(new java.util.ArrayList<>());
                            case 3 -> reporteController.mostrarVentasPorMes(ventaDao.obtenerTodasLasVentas());
                            case 4 -> {
                                double monto = val.validarDecimal("Ingrese el monto mínimo a filtrar en el archivo .dat de ventas: ");
                                reporteController.mostrarVentasArchivoMayoresA(monto);
                            }
                            case 5 -> reporteController.mostrarTotalRecaudadoArchivo();
                            case 6 -> reporteController.mostrarHistorialAuditoriaOrdenado();
                            case 7 -> {
                                System.out.println("Tipos comunes registrados: LOGIN, VENTA, REGISTRO_CLIENTE, ACCESO_MENU");
                                String tipo = new view.validarTexto.Validaciones().validarTextoNoVacio("Ingrese el tipo de acción a buscar: ");
                                reporteController.mostrarAuditoriaPorTipo(tipo);
                            }
                            case 8 -> reporteController.mostrarEstadisticasAuditoriaPorUsuario();
                            case 9 -> System.out.println("Regresando al menú de gestión...");
                            default -> System.out.println("Opción no válida.");
                        }
                    } while (opcionReporte != 9);
                }
                case 8 -> System.out.println("Regresando al menu principal...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 8);
    }
}