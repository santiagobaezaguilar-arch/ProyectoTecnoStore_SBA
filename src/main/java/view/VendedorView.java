package view;

import model.Vendedor;
import java.util.List;

public class VendedorView {

    public void mostrarLista(List<Vendedor> vendedores) {
        System.out.println("\n--- LISTADO DE VENDEDORES ---");
        if (vendedores.isEmpty()) {
            System.out.println("No hay vendedores registrados.");
        } else {
            for (Vendedor v : vendedores) {
                System.out.println("ID Vendedor: " + v.getId() + " | ID Persona Asociada: " + v.getIdPersona());
            }
        }
        System.out.println("----------------------------");
    }
}
