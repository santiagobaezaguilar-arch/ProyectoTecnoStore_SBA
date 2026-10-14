package view.validarNumeros;

import java.util.Scanner;

// Aca arrancamos la clase Validaciones, la encargada de blindar las entradas por consola pa que ningun gracioso rompa el programa metiendo letras donde van numeros, bien pro pa q amarre :V xd
public class Validaciones {

    // Aca creamos el metodo validarDecimal pa pedir un numero con decimales y asegurar que el usuario no meta babosadas, gg ez
    public double validarDecimal(String mensaje) {
        // Imprimimos el mensaje que nos pasen por parametro pa que el usuario sepa que carajos debe escribir, wazaa
        System.out.println(mensaje);
        // Instanciamos el Scanner pa leer lo que el usuario tipee en la consola, bien seguro
        Scanner x = new Scanner(System.in);
        // Aqui inserto esto y hago aquello para ello: mientras lo que escriba no sea un double valido, entramos al ciclo a regañarlo, piolin pa q amarre
        while (!x.hasNextDouble()) {
            System.out.println("Error, se espera un valor decimal");
            // Ojo al piojo: aqui le falto un x.next() pa limpiar el buffer, asi que si ponen letras se queda en bucle infinito trolleando al usuario, gg ez :V
        }
        // Retornamos el numero decimal ya validado y listo pa usar, eso tilin xd
        return x.nextDouble();
    }

    // Aca creamos el metodo validarEntero pa pedir numeros enteros sin decimales, bien organizado
    public int validarEntero(String mensaje) {
        // Instanciamos el Scanner pa capturar la entrada por consola, bien aja
        Scanner x = new Scanner(System.in);
        // Imprimimos el mensaje indicador pa la cajera o cliente, wazaa
        System.out.println(mensaje);
        // Aqui inserto esto y hago aquello para ello: si el Scanner detecta que NO es un entero, se queda atrapado en el while soltando error, piolin pa q amarre
        while (!x.hasNextInt()) {
            System.out.println("Error, se espera un valor entero");
            // Aca si limpiamos el buffer con x.next() pa que no se arme un bucle infinito de terror, eso tilin :V
            x.next();
        }
        // Retornamos el entero flamante ya validado, gg ez xd
        return x.nextInt();
    }

    // Aca creamos el metodo validarEnteroGrande pa manejar numeros enteros largos tipo long por si las moscas, bien pro pa q amarre
    public long validarEnteroGrande(String mensaje) {
        // Instanciamos el Scanner pa leer la consola, bien limpio
        Scanner x = new Scanner(System.in);
        // Imprimimos el mensaje en pantalla, wazaa
        System.out.println(mensaje);
        // Aqui inserto esto y hago aquello para ello: evaluamos con hasNextLong si el valor digitado cumple con ser un entero largo, piolin
        while (!x.hasNextLong()) {
            System.out.println("Error, se espera un valor entero");
            // Limpiamos el buffer del Scanner sacando la basura con x.next(), chiclebomba pa q amarre
            x.next();
        }
        // Retornamos el long bien seguro y listo pa guardar en la base de datos, eso tilin :V xd
        return x.nextLong();
    }
}