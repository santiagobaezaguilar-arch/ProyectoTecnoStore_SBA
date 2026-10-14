package view;

import model.AccionLog;
import java.util.List;

// Aca arrancamos la clase ReporteView, la encargada de manejar la interfaz visual en consola pa mostrar los reportes y la bitacora de auditoria, bien pro pa q amarre :V xd
public class ReporteView {

    // Aca creamos el metodo mostrarHistorialAuditoria pa recibir la lista de logs leida del archivo binario .dat y tirarla en pantalla, gg ez
    public void mostrarHistorialAuditoria(List<AccionLog> historial) {
        // Aqui inserto esto y hago aquello para ello: imprimimos un encabezado formal pa que la consola muestre el reporte de auditoria con estilo, wazaa
        System.out.println("\n--- REPORTE DE AUDITORÍA HISTORIAL (.DAT) ---");
        
        // Aca validamos si la lista de historial esta vacia o si si hay registros guardados en el sistema, piolin pa q amarre
        if (historial.isEmpty()) {
            // Si no hay ninguna accion registrada en el archivo .dat, soltamos el aviso sin armar show, chiclebomba xd
            System.out.println("No hay registros en el archivo binario.");
        } else {
            // Si si hay movimientos guardados, aqui inserto esto y hago aquello para ello: usamos un metodo de la Stream API con forEach y method reference pa imprimir cada log de forma super limpia, eso tilin
            historial.forEach(System.out::println);
        }
        
        // Aqui inserto esto y hago aquello para ello: cerramos el reporte de auditoria con una linea divisoria bien elegante pa terminar con broche de oro, piolin pa q amarre :V
        System.out.println("---------------------------------------------");
    }
}