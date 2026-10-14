package view;

import model.Marca;
import java.util.List;

// Aca arrancamos la clase MarcaView, la encargada de manejar la interfaz visual en consola pa pintar las marcas de los telefonos, bien pro pa q amarre :V xd
public class MarcaView {

    // Aca creamos el metodo mostrarLista pa recibir una lista de marcas y tirarlas en pantalla de forma ordenada, gg ez
    public void mostrarLista(List<Marca> marcas) {
        // Aqui inserto esto y hago aquello para ello: imprimimos un encabezado formal pa que la consola se vea bien chula, wazaa
        System.out.println("\n--- LISTADO DE MARCAS ---");
        
        // Aca validamos si la lista esta vacia o si si hay marcas registradas, piolin pa q amarre
        if (marcas.isEmpty()) {
            // Si no hay ninguna marca guardada en la base de datos, soltamos el aviso sin armar show, chiclebomba xd
            System.out.println("No hay marcas registradas.");
        } else {
            // Si si hay marcas en la bodega, aqui inserto esto y hago aquello para ello: recorremos la lista con un ciclo for-each, eso tilin
            for (Marca m : marcas) {
                // Imprimimos el ID y el nombre de cada marca formateados con barritas bien limpias, gg ez :V
                System.out.println("ID: " + m.getId() + " | Nombre: " + m.getNombre());
            }
        }
        // Aqui inserto esto y hago aquello para ello: cerramos el listado con una linea divisoria bien elegante pa cerrar con broche de oro, piolin pa q amarre
        System.out.println("-------------------------");
    }
}