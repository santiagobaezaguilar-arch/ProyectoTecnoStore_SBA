package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Aca arrancamos la clase DatosVentaArchivo, la encargada de manejar el archivo binario .dat pa guardar los registros de venta y sacar reportes con la Stream API de Java, bien pro pa q amarre :V xd
public class DatosVentaArchivo {

    // Aca inicializamos la lista en memoria donde guardaremos todos los registros de ventas del sistema, bien organizada
    private List<VentaRegistro> ventas = new ArrayList<>();
    // Aca definimos el nombre del archivo binario local donde se almacena toda la guaca y los datos de las ventas, gg ez
    private final String nombreArchivo = "ventas_tecnostore.dat";

    // Aca creamos el metodo privado obtenerId pa calcular automaticamente el ID del proximo registro de venta, piolin pa q amarre
    private int obtenerId() {
        // Aqui inserto esto y hago aquello para ello: si la lista esta vacia arrancamos en 1; si no, pillamos el ID del ultimo registro y le sumamos 1, eso tilin :V
        return ventas.isEmpty() ? 1 : ventas.get(ventas.size() - 1).getId() + 1;
    }

    // Aca creamos el metodo cargar pa leer el archivo binario .dat y recuperar las ventas guardadas anteriormente, wazaa
    public void cargar() {
        // Aca creamos un objeto File pa verificar si el archivo ya existe en el disco, bien seguro xd
        File f = new File(nombreArchivo);
        // Si el archivo si existe, aqui inserto esto y hago aquello para ello: abrimos el ObjectInputStream pa deserializar la lista de ventas, chiclebomba
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
                // Leemos los objetos del archivo y los casteamos a una lista de VentaRegistro, gg ez
                ventas = (List<VentaRegistro>) ois.readObject();
            } catch (Exception e) {
                // Si hay algun error o cancer leyendo el archivo binario, lo atrapamos aca sin armar show, piolin
                System.out.println("Error al cargar el archivo .dat: " + e.getMessage());
            }
        }
    }

    // Aca creamos el metodo reemplazar pa serializar y escribir toda la lista actualizada dentro del archivo .dat, bien pro xd
    public void reemplazar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            // Escribimos la lista completa de ventas directamente en el archivo en disco, eso tilin
            oos.writeObject(ventas);
        } catch (Exception e) {
            // Si truena al guardar en el disco, avisamos al papulin sin armar lio, xd
            System.out.println("Error al guardar en el archivo .dat: " + e.getMessage());
        }
    }

    // Aca armamos el constructor de la clase pa que apenas nazca el objeto, cargue las ventas existentes de una vez, bien aja
    public DatosVentaArchivo() {
        cargar();
    }

    // Aca creamos el metodo guardar pa registrar una nueva venta, asignarle su ID automaticamente y actualizar el archivo, gg ez
    public void guardar(VentaRegistro venta) {
        // Asignamos el ID calculado al registro de venta, piolin
        venta.setId(obtenerId());
        // Añadimos la venta a la lista en memoria, eso tilin
        ventas.add(venta);
        // Mandamos a sobreescribir el archivo binario con la nueva venta incluida, chiclebomba pa q amarre
        reemplazar();
    }

    // Aca creamos el metodo getObtenerLista pa recargar los datos y devolver la lista completa de ventas a quien la pida, bien limpio xd
    public List<VentaRegistro> getObtenerLista() {
        cargar();
        return ventas;
    }

    // ==========================================
    // REPORTES UTILIZANDO STREAM API
    // ==========================================

    // Aca creamos el reporte 1 pa filtrar las ventas que superen un monto especifico usando la Stream API de Java, wazaa
    public void filtrarVentasMayoresA(double montoMinimo) {
        // Recargamos los datos del archivo por si hubo ventas nuevas, eso tilin
        cargar();
        System.out.println("\n--- REPORTE: VENTAS MAYORES A $" + montoMinimo + " ---");
        // Aqui inserto esto y hago aquello para ello: convertimos la lista en stream, filtramos con una expresion lambda las que superen el minimo y las juntamos en una nueva lista, piolin pa q amarre
        List<VentaRegistro> resultado = ventas.stream()
                .filter(v -> v.getTotal() > montoMinimo)
                .collect(Collectors.toList());

        // Si la lista de resultados esta vacia, avisamos que no hay nada; si no, las imprimimos todas con un method reference, gg ez :V
        if (resultado.isEmpty()) {
            System.out.println("No hay ventas que superen ese monto.");
        } else {
            resultado.forEach(System.out::println);
        }
    }

    // Aca creamos el reporte 2 pa calcular el total general recaudado historicamente usando Streams (summingDouble), bien financiero xd
    public void mostrarTotalRecaudado() {
        // Recargamos el archivo pa tener la info fresquita, chiclebomba
        cargar();
        // Aqui inserto esto y hago aquello para ello: pasamos la lista a stream y usamos el colector summingDouble pa sumar rapidito los totales de cada venta, eso tilin
        double totalGeneral = ventas.stream()
                .collect(Collectors.summingDouble(VentaRegistro::getTotal));
        
        System.out.println("\n--- REPORTE FINANCIERO ---");
        // Imprimimos la recaudacion total formateada a dos decimales, bien pro pa q amarre :V
        System.out.println("Recaudacion total historica en archivo: $" + String.format("%.2f", totalGeneral));
    }
}