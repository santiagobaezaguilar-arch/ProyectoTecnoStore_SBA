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

public class ReporteController {

    private final CelularDaoImpl celularDao = new CelularDaoImpl();
    private final VentaDaoImpl ventaDao = new VentaDaoImpl();
    private final DatosVentaArchivo datosVentaArchivo = new DatosVentaArchivo();
    private final AuditoriaArchivo auditoriaArchivo = new AuditoriaArchivo();

    // ==========================================
    // 1. REPORTE: CELULARES CON STOCK BAJO
    // ==========================================
    public void mostrarCelularesStockBajo() {
        System.out.println("\n--- REPORTE: CELULARES CON STOCK BAJO (<= 5) ---");
        List<Celular> celulares = celularDao.obtenerCelulares();

        List<Celular> stockBajo = celulares.stream()
                .filter(c -> c.getStock() <= 5)
                .collect(Collectors.toList());

        if (stockBajo.isEmpty()) {
            System.out.println("No hay celulares con stock bajo en este momento.");
        } else {
            stockBajo.forEach(System.out::println);
        }
    }

    // ==========================================
    // 2. REPORTE: TOP 3 MÁS VENDIDOS (DESDE BD)
    // ==========================================
    public void mostrarTop3MasVendidos(List<DetalleVenta> todosLosDetalles) {
        System.out.println("\n--- REPORTE: TOP 3 CELULARES MAS VENDIDOS ---");
        
        Map<Integer, Integer> ventasPorCelular = todosLosDetalles.stream()
                .collect(Collectors.groupingBy(
                        DetalleVenta::getIdCelular,
                        Collectors.summingInt(DetalleVenta::getCantidad)
                ));

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
    public void mostrarVentasPorMes(List<Venta> todasLasVentas) {
        System.out.println("\n--- REPORTE: VENTAS TOTALES POR MES ---");

        DateTimeFormatter formatoMes = DateTimeFormatter.ofPattern("yyyy-MM");

        Map<String, Double> ventasPorMes = todasLasVentas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFecha().format(formatoMes),
                        Collectors.summingDouble(Venta::getTotal)
                ));

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
    public void mostrarVentasArchivoMayoresA(double montoMinimo) {
        datosVentaArchivo.cargar();
        System.out.println("\n--- REPORTE ARCHIVO .DAT: VENTAS MAYORES A $" + montoMinimo + " ---");
        List<VentaRegistro> lista = datosVentaArchivo.getObtenerLista();

        List<VentaRegistro> resultado = lista.stream()
                .filter(v -> v.getTotal() > montoMinimo)
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            System.out.println("No hay ventas en el archivo binario que superen ese monto.");
        } else {
            resultado.forEach(System.out::println);
        }
    }

    public void mostrarTotalRecaudadoArchivo() {
        datosVentaArchivo.cargar();
        List<VentaRegistro> lista = datosVentaArchivo.getObtenerLista();

        double totalGeneral = lista.stream()
                .collect(Collectors.summingDouble(VentaRegistro::getTotal));
        
        System.out.println("\n--- REPORTE FINANCIERO ARCHIVO .DAT ---");
        System.out.println("Recaudación total histórica en archivo binario: $" + String.format("%.2f", totalGeneral));
    }

    // ==========================================
    // 5. REPORTE: ARCHIVO .DAT DE AUDITORÍA
    // ==========================================
    public void mostrarHistorialAuditoriaOrdenado() {
        auditoriaArchivo.cargar();
        List<AccionLog> lista = auditoriaArchivo.getObtenerLista();

        System.out.println("\n--- REPORTE: HISTORIAL DE ACCIONES DE AUDITORÍA (.DAT) ---");
        if (lista.isEmpty()) {
            System.out.println("No hay acciones registradas en el archivo de auditoría.");
            return;
        }

        lista.stream()
                .sorted((a, b) -> a.getFechaHora().compareTo(b.getFechaHora()))
                .forEach(System.out::println);
    }

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