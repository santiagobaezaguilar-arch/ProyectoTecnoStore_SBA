package view;

import model.Celular;
import java.util.List;

// Aca arrancamos la clase CelularView, la encargada de manejar todo el show visual y pintar los telefonos en la consola, bien pro pa q amarre :V xd
public class CelularView {

    // Aca creamos el metodo mostrarLista pa recibir la lista de telefonos y tirarla en pantalla bien organizada, gg ez
    public void mostrarLista(List<Celular> celulares) {
        // Aqui inserto esto y hago aquello para ello: imprimimos un encabezado chulo pa que se vea formal el inventario, wazaa
        System.out.println("\n--- LISTADO DE CELULARES ---");
        
        // Aca validamos si la lista esta vacia o si si hay celulares registrados, piolin pa q amarre
        if (celulares.isEmpty()) {
            // Si no hay ningun telefono guardado, soltamos el aviso sin armar show, chiclebomba xd
            System.out.println("No hay celulares registrados.");
        } else {
            // Si si hay mercancia en la bodega, aqui inserto esto y hago aquello para ello: recorremos la lista con un for-each, eso tilin
            for (Celular c : celulares) {
                // Imprimimos cada celular llamando a su metodo toString, bien limpio y ordenado, gg ez :V
                System.out.println(c);
            }
        }
        // Aqui inserto esto y hago aquello para ello: cerramos el listado con una linea divisoria bien elegante, piolin
        System.out.println("----------------------------");
    }
}