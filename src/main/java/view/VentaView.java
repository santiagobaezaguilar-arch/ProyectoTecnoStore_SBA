package view;

import java.util.Map;

public class VentaView {
    public void mostrarFactura(String clienteNombre, String vendedorNombre, Map<String, Object[]> carrito, double subtotal, double iva, double total) {
        System.out.println("\n==================================================");
        System.out.println("                FACTURA DE VENTA                  ");
        System.out.println("==================================================");
        System.out.println("Cliente: " + clienteNombre);
        System.out.println("Atendido por Vendedor: " + vendedorNombre);
        System.out.println("--------------------------------------------------");
        System.out.println(String.format("%-20s | %-8s | %-10s", "Modelo", "Cant", "Subtotal"));
        System.out.println("--------------------------------------------------");
        
        for (Map.Entry<String, Object[]> entry : carrito.entrySet()) {
            String modelo = entry.getKey();
            int cantidad = (int) entry.getValue()[0];
            double sub = (double) entry.getValue()[1];
            System.out.println(String.format("%-20s | %-8d | $%-10.2f", modelo, cantidad, sub));
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println(String.format("Subtotal:     $%.2f", subtotal));
        System.out.println(String.format("IVA (19%%):    $%.2f", iva));
        System.out.println(String.format("TOTAL A PAGAR: $%.2f", total));
        System.out.println("==================================================");
    }
}
