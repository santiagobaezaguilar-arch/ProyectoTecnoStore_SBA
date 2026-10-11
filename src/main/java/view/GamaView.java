package view;

import model.Gama;
import java.util.List;

public class GamaView {
    public void mostrarLista(List<Gama> gamas) {
        System.out.println("\n--- LISTADO DE GAMAS ---");
        if (gamas.isEmpty()) {
            System.out.println("No hay gamas registradas.");
        } else {
            for (Gama g : gamas) {
                System.out.println("ID: " + g.getId() + " | Nombre: " + g.getNombre());
            }
        }
        System.out.println("------------------------");
    }
}
