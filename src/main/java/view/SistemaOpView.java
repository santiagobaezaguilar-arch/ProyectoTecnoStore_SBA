package view;

import model.SistemOp;
import java.util.List;

public class SistemaOpView {
    public void mostrarLista(List<SistemOp> sistemas) {
        System.out.println("\n--- LISTADO DE SISTEMAS OPERATIVOS ---");
        if (sistemas.isEmpty()) {
            System.out.println("No hay sistemas operativos registrados.");
        } else {
            for (SistemOp so : sistemas) {
                System.out.println("ID: " + so.getId() + " | Nombre: " + so.getNombre());
            }
        }
        System.out.println("--------------------------------------");
    }
}
