package view;

import model.Gama;
import java.util.List;

// Aca arrancamos la clase GamaView, la encargada de manejar la interfaz visual en consola pa pintar las categorias y gamas de los telefonos, bien pro pa q amarre :V xd
public class GamaView {

    // Aca creamos el metodo mostrarLista pa recibir una lista de gamas y tirarlas en pantalla de forma ordenada, gg ez
    public void mostrarLista(List<Gama> gamas) {
        // Aqui inserto esto y hago aquello para ello: imprimimos un encabezado formal pa que la consola se vea bien chula, wazaa
        System.out.println("\n--- LISTADO DE GAMAS ---");
        
        // Aca validamos si la lista esta vacia o si si hay categorias guardadas, piolin pa q amarre
        if (gamas.isEmpty()) {
            // Si no hay ninguna gama registrada en la base de datos, soltamos el aviso sin armar show, chiclebomba xd
            System.out.println("No hay gamas registradas.");
        } else {
            // Si si hay gamas en la bodega, aqui inserto esto y hago aquello para ello: recorremos la lista con un ciclo for-each, eso tilin
            for (Gama g : gamas) {
                // Imprimimos el ID y el nombre de cada gama formateados con barritas bien limpias, gg ez :V
                System.out.println("ID: " + g.getId() + " | Nombre: " + g.getNombre());
            }
        }
        // Aqui inserto esto y hago aquello para ello: cerramos el listado con una linea divisoria bien elegante pa cerrar con broche de oro, piolin pa q amarre
        System.out.println("------------------------");
    }
}