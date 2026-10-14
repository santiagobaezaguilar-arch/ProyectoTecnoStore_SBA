package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Aca arrancamos la clase AuditoriaArchivo, la encargada de manejar la persistencia en archivos binarios (.dat) pa guardar el chismografo de todas las acciones del sistema, bien pro pa q amarre :V xd
public class AuditoriaArchivo {

    // Aca inicializamos la lista que va a guardar todos los logs de auditoria en memoria, bien organizada
    private List<AccionLog> acciones = new ArrayList<>();
    // Aca definimos el nombre del archivo binario donde se va a almacenar la info de forma persistente, gg ez
    private final String nombreArchivo = "historial_acciones.dat";

    // Aca creamos el metodo privado obtenerId pa calcular automaticamente el ID del siguiente log, piolin pa q amarre
    private int obtenerId() {
        // Aqui inserto esto y hago aquello para ello: si la lista esta vacia arrancamos en 1; si no, pillamos el ultimo ID y le sumamos 1, eso tilin :V
        return acciones.isEmpty() ? 1 : acciones.get(acciones.size() - 1).getId() + 1;
    }

    // Aca creamos el metodo cargar pa leer el archivo binario y recuperar los registros guardados anteriormente, wazaa
    public void cargar() {
        // Aca creamos un objeto File con el nombre del archivo pa verificar si ya existe en disco, bien seguro xd
        File f = new File(nombreArchivo);
        // Si el archivo si existe, aqui inserto esto y hago aquello para ello: abrimos el ObjectInputStream pa deserializar la lista, chiclebomba
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
                // Leemos los objetos del archivo y los casteamos a una lista de AccionLog, gg ez
                acciones = (List<AccionLog>) ois.readObject();
            } catch (Exception e) {
                // Si hay algun error o cancer leyendo el archivo binario, lo atrapamos aca sin armar show, piolin
                System.out.println("Error al cargar el archivo de auditoría: " + e.getMessage());
            }
        }
    }

    // Aca creamos el metodo reemplazar pa serializar y guardar toda la lista actualizada dentro del archivo binario, bien pro xd
    public void reemplazar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            // Escribimos la lista completa de acciones directamente en el archivo .dat, eso tilin
            oos.writeObject(acciones);
        } catch (Exception e) {
            // Si truena al escribir en el disco, avisamos al papulin sin armar lio, xd
            System.out.println("Error al guardar en el archivo de auditoría: " + e.getMessage());
        }
    }

    // Aca armamos el constructor de la clase AuditoriaArchivo pa que apenas se instancie, cargue los datos existentes de una vez, bien aja
    public AuditoriaArchivo() {
        cargar();
    }

    // Aca creamos el metodo registrarAccion pa anotar un nuevo evento (como una venta o un login) en la bitacora, wazaa
    public void registrarAccion(String usuario, String tipoAccion, String descripcion) {
        // Primero cargamos por si acaso hay datos nuevos de otros lados, piolin pa q amarre
        cargar();
        // Calculamos el ID que le va a tocar al nuevo registro, gg ez
        int id = obtenerId();
        // Creamos el objeto AccionLog con la fecha y hora exacta del momento, eso tilin :V
        AccionLog nuevoLog = new AccionLog(id, usuario, tipoAccion, descripcion, LocalDateTime.now());
        // Añadimos el log recien creado a la lista en memoria, chiclebomba
        acciones.add(nuevoLog);
        // Mandamos a guardar los cambios en el archivo binario con el metodo reemplazar, bien pro xd
        reemplazar();
    }

    // Aca creamos el metodo getObtenerLista pa recargar los datos y devolver la lista completa de auditoria a quien la pida, bien limpio
    public List<AccionLog> getObtenerLista() {
        cargar();
        return acciones;
    }
}