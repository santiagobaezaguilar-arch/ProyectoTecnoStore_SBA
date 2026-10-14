package view;

import model.AccionLog;
import java.util.List;

public class ReporteView {
    public void mostrarHistorialAuditoria(List<AccionLog> historial) {
        System.out.println("\n--- REPORTE DE AUDITORÍA HISTORIAL (.DAT) ---");
        if (historial.isEmpty()) {
            System.out.println("No hay registros en el archivo binario.");
        } else {
            historial.forEach(System.out::println);
        }
        System.out.println("---------------------------------------------");
    }
}
