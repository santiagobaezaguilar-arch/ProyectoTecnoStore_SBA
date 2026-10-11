package view.validarTexto;

import java.util.Scanner;

public class Validaciones {

    public String validarTextoNoVacio(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim(); // Elimina espacios sobrantes al inicio y final
            if (texto.isEmpty()) {
                System.out.println("¡Error! Este campo no puede estar vacío. Intente de nuevo.");
            }
        } while (texto.isEmpty());
        return texto;
    }
}