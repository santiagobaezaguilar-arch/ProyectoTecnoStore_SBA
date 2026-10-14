package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditoriaArchivo {

    private List<AccionLog> acciones = new ArrayList<>();
    private final String nombreArchivo = "historial_acciones.dat";

    private int obtenerId() {
        return acciones.isEmpty() ? 1 : acciones.get(acciones.size() - 1).getId() + 1;
    }

    public void cargar() {
        File f = new File(nombreArchivo);
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
                acciones = (List<AccionLog>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error al cargar el archivo de auditoría: " + e.getMessage());
            }
        }
    }

    public void reemplazar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(acciones);
        } catch (Exception e) {
            System.out.println("Error al guardar en el archivo de auditoría: " + e.getMessage());
        }
    }

    public AuditoriaArchivo() {
        cargar();
    }

    public void registrarAccion(String usuario, String tipoAccion, String descripcion) {
        cargar();
        int id = obtenerId();
        AccionLog nuevoLog = new AccionLog(id, usuario, tipoAccion, descripcion, LocalDateTime.now());
        acciones.add(nuevoLog);
        reemplazar();
    }

    public List<AccionLog> getObtenerLista() {
        cargar();
        return acciones;
    }
}