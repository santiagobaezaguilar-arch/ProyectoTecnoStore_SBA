package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatosVentaArchivo {

    private List<VentaRegistro> ventas = new ArrayList<>();
    private final String nombreArchivo = "ventas_tecnostore.dat";

    private int obtenerId() {
        return ventas.isEmpty() ? 1 : ventas.get(ventas.size() - 1).getId() + 1;
    }

    // Administrar archivo .dat (idéntico a la estructura que mostraste)
    public void cargar() {
        File f = new File(nombreArchivo);
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
                ventas = (List<VentaRegistro>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error al cargar el archivo .dat: " + e.getMessage());
            }
        }
    }

    public void reemplazar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(ventas);
        } catch (Exception e) {
            System.out.println("Error al guardar en el archivo .dat: " + e.getMessage());
        }
    }

    public DatosVentaArchivo() {
        cargar();
    }

    public void guardar(VentaRegistro venta) {
        venta.setId(obtenerId());
        ventas.add(venta);
        reemplazar();
    }

    public List<VentaRegistro> getObtenerLista() {
        cargar();
        return ventas;
    }

    // ==========================================
    // REPORTES UTILIZANDO STREAM API
    // ==========================================

    // Reporte 1: Filtrar ventas mayores a un valor específico usando Stream API
    public void filtrarVentasMayoresA(double montoMinimo) {
        cargar();
        System.out.println("\n--- REPORTE: VENTAS MAYORES A $" + montoMinimo + " ---");
        List<VentaRegistro> resultado = ventas.stream()
                .filter(v -> v.getTotal() > montoMinimo)
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            System.out.println("No hay ventas que superen ese monto.");
        } else {
            resultado.forEach(System.out::println);
        }
    }

    // Reporte 2: Calcular el total general recaudado usando Stream API (summingDouble)
    public void mostrarTotalRecaudado() {
        cargar();
        double totalGeneral = ventas.stream()
                .collect(Collectors.summingDouble(VentaRegistro::getTotal));
        
        System.out.println("\n--- REPORTE FINANCIERO ---");
        System.out.println("Recaudacion total historica en archivo: $" + String.format("%.2f", totalGeneral));
    }
}