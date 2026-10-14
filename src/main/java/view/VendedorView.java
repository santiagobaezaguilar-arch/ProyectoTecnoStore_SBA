package view;

import model.Vendedor;
import java.util.List;

// Aca arrancamos la clase VendedorView, la encargada de manejar la interfaz visual en consola pa pintar la lista de cajeros y vendedores del local, bien pro pa q amarre :V xd
public class VendedorView {

    // Aca creamos el metodo mostrarLista pa recibir una lista de vendedores y tirarla en pantalla de forma ordenada, gg ez
    public void mostrarLista(List<Vendedor> vendedores) {
        // Aqui inserto esto y hago aquello para ello: imprimimos un encabezado formal pa que la consola muestre el reporte con buen estilo, wazaa
        System.out.println("\n--- LISTADO DE VENDEDORES ---");
        
        // Aca validamos si la lista esta vacia o si si hay empleados registrados en el sistema, piolin pa q amarre
        if (vendedores.isEmpty()) {
            // Si no hay ningun vendedor guardado, soltamos el aviso sin armar show, chiclebomba xd
            System.out.println("No hay vendedores registrados.");
        } else {
            // Si si hay personal fichado, aqui inserto esto y hago aquello para ello: recorremos la lista con un ciclo for-each, eso tilin
            for (Vendedor v : vendedores) {
                // Imprimimos el ID del vendedor y lo asociamos con el ID de su respectiva persona formateado con barritas, gg ez :V
                System.out.println("ID Vendedor: " + v.getId() + " | ID Persona Asociada: " + v.getIdPersona());
            }
        }
        // Aqui inserto esto y hago aquello para ello: cerramos el listado con una linea divisoria bien elegante pa terminar con broche de oro, piolin pa q amarre
        System.out.println("----------------------------");
    }
}