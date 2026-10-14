package view;

import model.SistemOp;
import java.util.List;

// Aca arrancamos la clase SistemaOpView, la encargada de manejar la interfaz visual en consola pa pintar los sistemas operativos de los telefonos, bien pro pa q amarre :V xd
public class SistemaOpView {

    // Aca creamos el metodo mostrarLista pa recibir una lista de sistemas operativos y tirarlos en pantalla de forma ordenada, gg ez
    public void mostrarLista(List<SistemOp> sistemas) {
        // Aqui inserto esto y hago aquello para ello: imprimimos un encabezado formal pa que la consola se vea bien chula, wazaa
        System.out.println("\n--- LISTADO DE SISTEMAS OPERATIVOS ---");
        
        // Aca validamos si la lista esta vacia o si si hay sistemas operativos registrados, piolin pa q amarre
        if (sistemas.isEmpty()) {
            // Si no hay ningun SO guardado en la base de datos, soltamos el aviso sin armar show, chiclebomba xd
            System.out.println("No hay sistemas operativos registrados.");
        } else {
            // Si si hay sistemas operativos en la bodega, aqui inserto esto y hago aquello para ello: recorremos la lista con un ciclo for-each, eso tilin
            for (SistemOp so : sistemas) {
                // Imprimimos el ID y el nombre de cada sistema operativo formateados con barritas bien limpias, gg ez :V
                System.out.println("ID: " + so.getId() + " | Nombre: " + so.getNombre());
            }
        }
        // Aqui inserto esto y hago aquello para ello: cerramos el listado con una linea divisoria bien elegante pa cerrar con broche de oro, piolin pa q amarre
        System.out.println("--------------------------------------");
    }
}