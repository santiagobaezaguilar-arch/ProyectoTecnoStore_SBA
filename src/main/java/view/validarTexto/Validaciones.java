package view.validarTexto;

import java.util.Scanner;

// Aca arrancamos la clase Validaciones del paquete de texto, la encargada de asegurar que nadie mande cadenas vacias ni correos inventados que armen cancer en la base de datos, bien pro pa q amarre :V xd
public class Validaciones {

    // Aca creamos el metodo validarTextoNoVacio pa pedir texto y asegurar que el usuario no se haga el gracioso mandando espacios en blanco, gg ez
    public String validarTextoNoVacio(String mensaje) {
        // Instanciamos el Scanner pa leer la consola, bien seguro
        Scanner scanner = new Scanner(System.in);
        // Declaramos la variable texto donde guardaremos lo que escriba el usuario, wazaa
        String texto;
        // Usamos un ciclo do-while pa obligar al usuario a digitar algo util y no puras babosadas, piolin pa q amarre
        do {
            // Imprimimos el mensaje indicador en pantalla, eso tilin
            System.out.print(mensaje);
            // Aqui inserto esto y hago aquello para ello: leemos la linea completa y le aplicamos trim() pa borrar espacios sobrantes al inicio y final, bien limpio xd
            texto = scanner.nextLine().trim(); // Elimina espacios sobrantes al inicio y final
            // Si el texto queda vacio despues de quitar espacios, aqui inserto esto y hago aquello para ello: regañamos al usuario pa que intente de nuevo, chiclebomba
            if (texto.isEmpty()) {
                System.out.println("¡Error! Este campo no puede estar vacío. Intente de nuevo.");
            }
        } while (texto.isEmpty()); // El ciclo se repite mientras la cadena siga vacia, gg ez :V
        // Retornamos el texto ya validado y nítido, bien pro pa q amarre
        return texto;
    }
    
    // Aca creamos el metodo validarCorreo pa verificar que el email ingresado por lo menos tenga su @ y no sea una cacorrada, wazaa
    public String validarCorreo(String mensaje) {
        // Instanciamos el Scanner pa capturar el correo por consola, bien organizado
        Scanner scanner = new Scanner(System.in);
        // Declaramos la variable correo, bien aja
        String correo;
        // Arrancamos un do-while pa insistir hasta que escriba un correo medio decente, piolin pa q amarre
        do {
            // Imprimimos el mensaje pidiendo el correo en pantalla, eso tilin
            System.out.print(mensaje);
            // Leemos la linea y le quitamos los espacios con trim(), gg ez xd
            correo = scanner.nextLine().trim();
            
            // Aqui inserto esto y hago aquello para ello: validamos con contains si el texto incluye el simbolo @; si no, soltamos error, chiclebomba
            if (!correo.contains("@")) {
                System.out.println("Error: El correo debe contener '@'. Intente de nuevo.");
            }
        } while (!correo.contains("@")); // Si no tiene @, se queda atrapado en el ciclo trolleandolo hasta que lo ponga bien, gg ez :V
        
        // Retornamos el correo validado y listo pa guardar, bien pro pa q amarre xd
        return correo;
    }   
}