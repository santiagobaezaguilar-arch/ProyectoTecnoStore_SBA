package view;

import model.Celular;
import java.util.List;

public class CelularView {
    public void mostrarLista(List<Celular> celulares) {
        System.out.println("\n--- LISTADO DE CELULARES ---");
        if (celulares.isEmpty()) {
            System.out.println("No hay celulares registrados.");
        } else {
            for (Celular c : celulares) {
                System.out.println(c);
            }
        }
        System.out.println("----------------------------");
    }
}
