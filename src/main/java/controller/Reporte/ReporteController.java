package controller.Reporte;

import dao.impl.CelularDaoImpl;
import dao.impl.VentaDaoImpl;
import model.Celular;
import model.DetalleVenta;
import model.Venta;
import model.DatosVentaArchivo;
import model.VentaRegistro;
import model.AccionLog;
import model.AuditoriaArchivo;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Aca arrancamos la clase ReporteController, el mero cerebro analitico pa sacar estadisticas con la Stream API y los archivos .dat, bien aja pa q amarre :V xd
public class ReporteController {

    // Aca instanciamos el DAO de celulares pa buscar modelos y datos rapidito, gg ez
    private final CelularDaoImpl celularDao = new CelularDaoImpl();
    // Aca traemos el DAO de ventas pa consultar la base de datos MySQL, piolin
    private final VentaDaoImpl ventaDao = new VentaDaoImpl();
    // Aca declaramos el manejador del archivo binario .dat de ventas pa cargar la persistencia local, wazaa
    private final DatosVentaArchivo datosVentaArchivo = new DatosVentaArchivo();
    // Aca traemos el manejador de auditoria binaria pa chismosear las acciones del sistema, eso tilin
    private final AuditoriaArchivo auditoriaArchivo = new AuditoriaArchivo();

    // ==========================================
    // 1. REPORTE: CELULARES CON STOCK BAJO
    // ==========================================
    // Aca creamos el metodo pa filtrar y mostrar los telefonos que estan a punto de agotarse (stock menor o igual a 5), bien armado xd
    public void mostrarCelularesStockBajo() {
        System.out.println("\n--- REPORTE: CELULARES CON STOCK BAJO (<= 5) ---");
        // Aca traemos la lista completa de celulares desde la base de datos, bien aja
        List<Celular> celulares = celularDao.obtenerCelulares();

        // Aca usamos el poder de la Stream API pa filtrar unicamente los telefonos con stock menor o igual a 5, gg ez pa q amarre
        List<Celular> stockBajo = celulares.stream()
                .filter(c -> c.getStock() <= 5)
                .collect(Collectors.toList());

        // Si la lista de stock bajo esta vacia (no hay alertas), avisamos; si no, imprimimos cada telefono con un forEach, piolin
        if (stockBajo.isEmpty()) {
            System.out.println("No hay celulares con stock bajo en este momento.");
        } else {
            stockBajo.forEach(System.out::println);
        }
    }

    // ==========================================
    // 2. REPORTE: TOP 3 MÁS VENDIDOS (DESDE BD)
    // ==========================================
    // Aca armamos el metodo pa sacar el podio de los 3 telefonos mas vendidos usando agrupaciones de la Stream API, wazaa
    public void mostrarTop3MasVendidos(List<DetalleVenta> todosLosDetalles) {
        System.out.println("\n--- REPORTE: TOP 3 CELULARES MAS VENDIDOS ---");
        
        // Aca agrupamos por ID de celular y sumamos todas las cantidades vendidas usando groupingBy y summingInt, bien pro xd
        Map<Integer, Integer> ventasPorCelular = todosLosDetalles.stream()
                .collect(Collectors.groupingBy(
                        DetalleVenta::getIdCelular,
                        Collectors.summingInt(DetalleVenta::getCantidad)
                ));

        // Aca ordenamos el mapa de mayor a menor cantidad, limitamos a los primeros 3 (el top 3) y los imprimimos con elegancia, gg ez
        ventasPorCelular.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> {
                    Celular cel = celularDao.buscar(entry.getKey());
                    String modelo = (cel != null) ? cel.getModelo() : "Desconocido";
                    System.out.println("Modelo: " + modelo + " | Unidades vendidas: " + entry.getValue());
                });
    }

    // ==========================================
    // 3. REPORTE: VENTAS POR MES (DESDE BD)
    // ==========================================
    // Aca creamos el metodo pa agrupar y sumar las ventas totales mes a mes con la Stream API, chiclebomba
    public void mostrarVentasPorMes(List<Venta> todasLasVentas) {
        System.out.println("\n--- REPORTE: VENTAS TOTALES POR MES ---");

        // Aca definimos el formato de fecha para extraer el año y el mes (yyyy-MM), eso tilin
        DateTimeFormatter formatoMes = DateTimeFormatter.ofPattern("yyyy-MM");

        // Aca agrupamos las ventas por mes formateado y sumamos la plata recaudada con summingDouble, bien aja
        Map<String, Double> ventasPorMes = todasLasVentas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFecha().format(formatoMes),
                        Collectors.summingDouble(Venta::getTotal)
                ));

        // Si no hay ventas pa agrupar, avisamos; si hay datos, los recorremos y mostramos el total de cada mes, piolin pa q amarre
        if (ventasPorMes.isEmpty()) {
            System.out.println("No hay ventas registradas para agrupar.");
        } else {
            ventasPorMes.forEach((mes, total) -> {
                System.out.println("Mes: " + mes + " | Total Recaudado: $" + String.format("%.2f", total));
            });
        }
    }

    // ==========================================
    // 4. REPORTE: ARCHIVO .DAT DE VENTAS
    // ==========================================
    // Aca creamos el metodo pa cargar el archivo .dat de ventas y filtrar las que superen un monto minimo digitado, wazaa
    public void mostrarVentasArchivoMayoresA(double montoMinimo) {
        // Aca cargamos los datos binarios desde el archivo .dat, gg ez
        datosVentaArchivo.cargar();
        System.out.println("\n--- REPORTE ARCHIVO .DAT: VENTAS MAYORES A $" + montoMinimo + " ---");
        List<VentaRegistro> lista = datosVentaArchivo.getObtenerLista();

        // Aca filtramos con la Stream API las ventas que superen el monto ingresado, bien armado xd
        List<VentaRegistro> resultado = lista.stream()
                .filter(v -> v.getTotal() > montoMinimo)
                .collect(Collectors.toList());

        // Si no hay resultados que cumplan la condicion, avisamos; si no, los imprimimos, bien aja
        if (resultado.isEmpty()) {
            System.out.println("No hay ventas en el archivo binario que superen ese monto.");
        } else {
            resultado.forEach(System.out::println);
        }
    }

    // Aca creamos el metodo pa sumar toda la recaudacion historica guardada en el archivo binario .dat, chiclebomba
    public void mostrarTotalRecaudadoArchivo() {
        // Cargamos el archivo .dat de ventas, eso tilin
        datosVentaArchivo.cargar();
        List<VentaRegistro> lista = datosVentaArchivo.getObtenerLista();

        // Usamos summingDouble de la Stream API pa acumular todo el dinero historico, gg ez
        double totalGeneral = lista.stream()
                .collect(Collectors.summingDouble(VentaRegistro::getTotal));
        
        System.out.println("\n--- REPORTE FINANCIERO ARCHIVO .DAT ---");
        System.out.println("Recaudación total histórica en archivo binario: $" + String.format("%.2f", totalGeneral));
    }

    // ==========================================
    // 5. REPORTE: ARCHIVO .DAT DE AUDITORÍA
    // ==========================================
    // Aca creamos el metodo pa cargar la auditoria, ordenarla cronologicamente por fecha y hora, y mostrarla, piolin
    public void mostrarHistorialAuditoriaOrdenado() {
        auditoriaArchivo.cargar();
        List<AccionLog> lista = auditoriaArchivo.getObtenerLista();

        System.out.println("\n--- REPORTE: HISTORIAL DE ACCIONES DE AUDITORÍA (.DAT) ---");
        if (lista.isEmpty()) {
            System.out.println("No hay acciones registradas en el archivo de auditoría.");
            return;
        }

        // Ordenamos los logs por fecha y hora con un sorted bien limpio y los imprimimos, wazaa pa q amarre
        lista.stream()
                .sorted((a, b) -> a.getFechaHora().compareTo(b.getFechaHora()))
                .forEach(System.out::println);
    }

    // Aca creamos el metodo pa filtrar las acciones de auditoria por un tipo especifico (ej: LOGIN, VENTA), bien pro xd
    public void mostrarAuditoriaPorTipo(String tipoAccionBuscado) {
        auditoriaArchivo.cargar();
        List<AccionLog> lista = auditoriaArchivo.getObtenerLista();

        System.out.println("\n--- REPORTE: ACCIONES FILTRADAS POR [" + tipoAccionBuscado + "] ---");
        List<AccionLog> filtrados = lista.stream()
                .filter(log -> log.getTipoAccion().equalsIgnoreCase(tipoAccionBuscado))
                .collect(Collectors.toList());

        if (filtrados.isEmpty()) {
            System.out.println("No se encontraron registros para ese tipo de acción.");
        } else {
            filtrados.forEach(System.out::println);
        }
    }

    // Aca creamos el metodo pa contar cuantas acciones realizo cada usuario usando groupingBy y counting de la Stream API, gg ez :V
    public void mostrarEstadisticasAuditoriaPorUsuario() {
        auditoriaArchivo.cargar();
        List<AccionLog> lista = auditoriaArchivo.getObtenerLista();

        System.out.println("\n--- REPORTE: ESTADÍSTICAS DE ACCIONES POR USUARIO ---");
        Map<String, Long> conteo = lista.stream()
                .collect(Collectors.groupingBy(
                        AccionLog::getUsuario,
                        Collectors.counting()
                ));

        if (conteo.isEmpty()) {
            System.out.println("No hay datos de auditoría suficientes para generar estadísticas.");
        } else {
            conteo.forEach((usuario, total) -> {
                System.out.println("Usuario: " + usuario + " | Total de acciones ejecutadas: " + total);
            });
        }
    }
}