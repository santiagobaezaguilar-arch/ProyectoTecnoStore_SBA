package view;

import model.Persona;
import java.util.List;

// Aca declaramos el paquete view donde vive esta clase, bien ordenado xd
public class PersonaView {
    // Aca creamos el metodo mostrarLista pa recibir la lista de personas y tirarla en pantalla bien elegante, piolin
    public void mostrarLista(List<Persona> personas) {
        // Aqui inserto esto y hago aquello para ello: imprimimos un encabezado chulo pa que se vea formal el reporte, wazaa
        System.out.println("\n--- LISTADO DE PERSONAS ---");
        
        // Aca validamos si la lista esta vacia o si si hay gente registrada, chiclebomba pa q amarre
        if (personas.isEmpty()) {
            // Si no hay nadie guardado en la base de datos, soltamos el aviso sin armar show, xd
            System.out.println("No hay personas registradas.");
        } else {
            // Si la lista si tiene personal guardado, aqui inserto esto y hago aquello para ello: entramos al bloque else, eso tilin
            for (Persona p : personas) {
                // Imprimimos cada persona llamando a su metodo toString, bien limpio y detallado, gg ez :V
                System.out.println(p);
            }
        }
        // Aqui inserto esto y hago aquello para ello: cerramos el listado con una linea divisoria bien pro, chiclebomba xd
        System.out.println("---------------------------");
    }
}