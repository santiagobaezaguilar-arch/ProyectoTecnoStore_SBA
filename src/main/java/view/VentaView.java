package view;

import java.util.Map;

// Aca arrancamos la clase VentaView, la encargada de manejar la interfaz visual en consola pa mostrar la factura detallada de la compra con todo el flow, bien pro pa q amarre :V xd
public class VentaView {

    // Aca creamos el metodo mostrarFactura pa recibir el nombre del cliente, del vendedor, el carrito de compras con los productos, el subtotal, el IVA y el total a pagar, gg ez
    public void mostrarFactura(String clienteNombre, String vendedorNombre, Map<String, Object[]> carrito, double subtotal, double iva, double total) {
        // Aqui inserto esto y hago aquello para ello: imprimimos los bordes superiores y el titulo formal de la factura de venta, wazaa
        System.out.println("\n==================================================");
        System.out.println("                FACTURA DE VENTA                  ");
        System.out.println("==================================================");
        
        // Imprimimos el nombre del cliente que realizo la compra y del vendedor que lo atendio en caja, piolin pa q amarre
        System.out.println("Cliente: " + clienteNombre);
        System.out.println("Atendido por Vendedor: " + vendedorNombre);
        System.out.println("--------------------------------------------------");
        
        // Aqui inserto esto y hago aquello para ello: armamos el encabezado de la tablita usando String.format pa alinear las columnas de modelo, cantidad y subtotal, eso tilin
        System.out.println(String.format("%-20s | %-8s | %-10s", "Modelo", "Cant", "Subtotal"));
        System.out.println("--------------------------------------------------");
        
        // Aca recorremos el Map del carrito de compras usando un ciclo for-each sobre entrySet() pa listar cada telefono comprado, chiclebomba xd
        for (Map.Entry<String, Object[]> entry : carrito.entrySet()) {
            // Sacamos el nombre del modelo de la llave del mapa, bien organizado
            String modelo = entry.getKey();
            // Casteamos la cantidad (posicion 0 del arreglo de objetos) a entero, gg ez
            int cantidad = (int) entry.getValue()[0];
            // Casteamos el subtotal (posicion 1 del arreglo de objetos) a double, piolin
            double sub = (double) entry.getValue()[1];
            
            // Imprimimos la fila de cada producto formateada a la perfeccion con sus barritas, eso tilin :V
            System.out.println(String.format("%-20s | %-8d | $%-10.2f", modelo, cantidad, sub));
        }
        
        // Aqui inserto esto y hago aquello para ello: separamos los productos de los totales finales con una linea limpia, wazaa
        System.out.println("--------------------------------------------------");
        
        // Imprimimos el subtotal sin impuestos, piolin pa q amarre
        System.out.println(String.format("Subtotal:      $%.2f", subtotal));
        // Imprimimos el valor correspondiente al IVA del 19%, gg ez
        System.out.println(String.format("IVA (19%%):    $%.2f", iva));
        // Imprimimos el TOTAL A PAGAR definitivo que suelta el tarjetazo o el efectivo, bien financiero xd
        System.out.println(String.format("TOTAL A PAGAR: $%.2f", total));
        
        // Cerramos la factura con broche de oro y doble linea divisoria bien chula, bien pro pa q amarre :V
        System.out.println("==================================================");
    }
}