
package view.validarNumeros;

import java.util.Scanner;

public class Validaciones {
    public double validarDecimal(String mensaje) {
        System.out.println(mensaje);
        Scanner x = new Scanner(System.in);
        while (!x.hasNextDouble()) {
            System.out.println("Error, se espera un valor decimal");
        }
        return x.nextDouble();
    }

    public int validarEntero(String mensaje) {
        Scanner x = new Scanner(System.in);
        System.out.println(mensaje);
        while (!x.hasNextInt()) {
            System.out.println("Error, se espera un valor entero");
            x.next();
        }
        return x.nextInt();
    }

    public long validarEnteroGrande(String mensaje) {
        Scanner x = new Scanner(System.in);
        System.out.println(mensaje);
        while (!x.hasNextLong()) {
            System.out.println("Error, se espera un valor entero");
            x.next();
        }
        return x.nextLong();
    }
}

