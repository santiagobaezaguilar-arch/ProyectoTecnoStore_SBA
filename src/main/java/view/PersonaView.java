package view;

import model.Persona;
import java.util.List;

public class PersonaView {
    public void mostrarLista(List<Persona> personas) {
        System.out.println("\n--- LISTADO DE PERSONAS ---");
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            for (Persona p : personas) {
                System.out.println(p);
            }
        }
        System.out.println("---------------------------");
    }
}