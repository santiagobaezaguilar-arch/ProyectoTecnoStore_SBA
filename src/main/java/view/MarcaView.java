package view;

import model.Marca;
import java.util.List;

public class MarcaView {

    public void mostrarLista(List<Marca> marcas) {
        System.out.println("\n--- LISTADO DE MARCAS ---");
        if (marcas.isEmpty()) {
            System.out.println("No hay marcas registradas.");
        } else {
            for (Marca m : marcas) {
                System.out.println("ID: " + m.getId() + " | Nombre: " + m.getNombre());
            }
        }
        System.out.println("-------------------------");
    }
}
