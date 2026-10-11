package controller.Venta;

import config.ConexionDB;
import dao.impl.CelularDaoImpl;
import dao.impl.PersonaDaoImpl;
import dao.impl.VentaDaoImpl;
import model.Celular;
import model.DetalleVenta;
import model.Persona;
import model.Venta;
import view.VentaView;
import view.validarNumeros.Validaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentaController {

    private final Validaciones valNum = new Validaciones();
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    private final PersonaDaoImpl personaDao = new PersonaDaoImpl();
    private final CelularDaoImpl celularDao = new CelularDaoImpl();
    private final VentaDaoImpl ventaDao = new VentaDaoImpl();
    private final VentaView ventaView = new VentaView();
    
    private final int idVendedor;

    public VentaController(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public void iniciarProcesoVenta() {
        System.out.println("\n--- NUEVA VENTA ---");
        
        String identificacion = valTexto.validarTextoNoVacio("Ingrese la cedula o identificacion del cliente: ");
        Persona cliente = buscarClientePorIdentificacion(identificacion);

        if (cliente == null) {
            System.out.println("Cliente no encontrado en la base de datos. Iniciando proceso de registro...");
            cliente = registrarClienteNuevo(identificacion);
            if (cliente == null) {
                System.out.println("No se pudo registrar al cliente. Cancelando venta.");
                return;
            }
        } else {
            System.out.println("Cliente encontrado: " + cliente.getNombre());
        }

        Map<Integer, Object[]> carritoIds = new HashMap<>();
        Map<String, Object[]> carritoResumenVista = new HashMap<>();

        boolean seguirComprando = true;
        while (seguirComprando) {
            System.out.println("\n--- CELULARES DISPONIBLES ---");
            List<Celular> celulares = celularDao.obtenerCelulares();
            for (Celular c : celulares) {
                System.out.println(c);
            }

            int idCelular = valNum.validarEntero("Ingrese el ID del celular que desea comprar: ");
            Celular celularSeleccionado = celularDao.buscar(idCelular);

            if (celularSeleccionado == null) {
                System.out.println("Celular no encontrado con ese ID.");
                continue;
            }

            if (celularSeleccionado.getStock() <= 0) {
                System.out.println("Lo sentimos, el celular '" + celularSeleccionado.getModelo() + "' ya no esta disponible (Stock: 0).");
                continue;
            }

            int cantidad;
            do {
                cantidad = valNum.validarEntero("Ingrese la cantidad a comprar (Stock disponible: " + celularSeleccionado.getStock() + "): ");
                if (cantidad <= 0) {
                    System.out.println("La cantidad debe ser mayor a 0.");
                } else if (cantidad > celularSeleccionado.getStock()) {
                    System.out.println("No es posible. La cantidad supera el stock disponible (" + celularSeleccionado.getStock() + "). Reingrese la cantidad.");
                }
            } while (cantidad <= 0 || cantidad > celularSeleccionado.getStock());

            double subtotalItem = cantidad * celularSeleccionado.getPrecio();

            carritoIds.put(idCelular, new Object[]{idCelular, cantidad, subtotalItem});
            carritoResumenVista.put(celularSeleccionado.getModelo(), new Object[]{cantidad, subtotalItem});

            System.out.println("Producto agregado al carrito.");

            int opcionContinuar = valNum.validarEntero("""
                                                      
                                                      Que desea hacer?
                                                      1. Agregar otro celular al carrito
                                                      2. Finalizar compra
                                                      Seleccione una opcion: """);
            if (opcionContinuar == 2) {
                seguirComprando = false;
            }
        }

        double subtotalGeneral = 0;
        List<DetalleVenta> listaDetalles = new ArrayList<>();

        for (Map.Entry<Integer, Object[]> entry : carritoIds.entrySet()) {
            int idCel = entry.getKey();
            Object[] datos = entry.getValue();
            int cant = (int) datos[1];
            double sub = (double) datos[2];
            
            subtotalGeneral += sub;
            
            listaDetalles.add(new DetalleVenta(0, idCel, cant, sub));
        }

        double iva = subtotalGeneral * 0.19;
        double totalGeneral = subtotalGeneral + iva;

        Venta nuevaVenta = new Venta(cliente.getId(), this.idVendedor, totalGeneral);
        boolean exito = ventaDao.registrarVentaCompleta(nuevaVenta, listaDetalles);

        if (exito) {
            String vendedorNombre = obtenerNombreVendedor(this.idVendedor);
            ventaView.mostrarFactura(cliente.getNombre(), vendedorNombre, carritoResumenVista, subtotalGeneral, iva, totalGeneral);
        }
    }

    private Persona buscarClientePorIdentificacion(String identificacion) {
        String sql = "SELECT * FROM persona WHERE identificacion = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, identificacion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        rs.getString("telefono")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    private Persona registrarClienteNuevo(String identificacion) {
        System.out.println("\n--- REGISTRO RAPIDO DE CLIENTE ---");
        String nombre = valTexto.validarTextoNoVacio("Ingrese el nombre del cliente: ");
        String correo = valTexto.validarTextoNoVacio("Ingrese el correo del cliente: ");
        String telefono = valTexto.validarTextoNoVacio("Ingrese el telefono: ");
        String contraseña = null; 

        Persona nuevoCliente = new Persona(nombre, identificacion, correo, contraseña, telefono);
        personaDao.crear(nuevoCliente);

        return buscarClientePorIdentificacion(identificacion);
    }

    private String obtenerNombreVendedor(int idVd) {
        String sql = "SELECT p.nombre FROM persona p INNER JOIN vendedor v ON v.id_persona = p.id WHERE v.id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVd);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo nombre del vendedor: " + e.getMessage());
        }
        return "Desconocido";
    }
}
