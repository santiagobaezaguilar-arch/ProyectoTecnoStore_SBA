package controller.Venta;

import config.ConexionDB;
import dao.impl.CelularDaoImpl;
import dao.impl.PersonaDaoImpl;
import dao.impl.VentaDaoImpl;
import model.Celular;
import model.DetalleVenta;
import model.Persona;
import model.Venta;
import model.DatosVentaArchivo;
import model.VentaRegistro;
import model.AuditoriaArchivo;
import view.VentaView;
import view.validarNumeros.Validaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Aca arrancamos la clase VentaController, el mero corazon de la facturacion y las ventas de TecnoStore, bien pro pa q amarre :V xd
public class VentaController {

    // Aca creamos el validador de numeros pa que el papulin no meta letras locas ni barrabasadas xd
    private final Validaciones valNum = new Validaciones();
    // Aca ponemos el validador de texto pa asegurar que los datos no queden con babosadas vacias, eso tilin
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    // Aca instanciamos el DAO de personas pa buscar y registrar clientes en MySQL, gg ez
    private final PersonaDaoImpl personaDao = new PersonaDaoImpl();
    // Aca traemos el DAO de celulares pa consultar el stock y los telefonos disponibles, piolin
    private final CelularDaoImpl celularDao = new CelularDaoImpl();
    // Aca ponemos el DAO de ventas pa registrar la factura completa y los detalles en la base de datos, wazaa
    private final VentaDaoImpl ventaDao = new VentaDaoImpl();
    // Aca armamos la vista de venta pa pintar la factura final en la consola, bien aja
    private final VentaView ventaView = new VentaView();
    
    // Aca declaramos el manejador del archivo binario .dat pa guardar una copia local de las ventas, chiclebomba
    private final DatosVentaArchivo datosVentaArchivo = new DatosVentaArchivo();
    // Aca ponemos el archivo de auditoria .dat pa dejar registro de cada venta o movimiento, eso tilin :V
    private final AuditoriaArchivo auditoriaArchivo = new AuditoriaArchivo();
    
    // Aca guardamos el ID del vendedor que inicio sesion pa saber quien esta facturando, bien asegurado xd
    private final int idVendedor;

    // Aca armamos el constructor de la clase recibiendo el ID del cajero o admin, gg ez
    public VentaController(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    // Aca creamos el metodo iniciarProcesoVenta pa llevar de la mano al usuario durante todo el proceso de compra, bien armado pa q amarre
    public void iniciarProcesoVenta() {
        System.out.println("\n--- NUEVA VENTA ---");
        
        // Aca pedimos la identificacion del cliente usando enteros grandes pa evitar errores, wazaa
        String identificacion = String.valueOf(valNum.validarEnteroGrande("Ingrese la cedula o identificacion del cliente: "));
        // Aca buscamos si el cliente ya existe en la base de datos con ese numero, piolin
        Persona cliente = buscarClientePorIdentificacion(identificacion);

        // Si el cliente no existe (es null), aqui inserto esto y hago aquello para ello: avisamos y arrancamos el registro rapido, chiclebomba
        if (cliente == null) {
            System.out.println("Cliente no encontrado en la base de datos. Iniciando proceso de registro...");
            cliente = registrarClienteNuevo(identificacion);
            // Si aun asi no se pudo registrar, cancelamos la venta pa que no explote nada, gg ez :V
            if (cliente == null) {
                System.out.println("No se pudo registrar al cliente. Cancelando venta.");
                return;
            }
        } else {
            // Si si aparecio el cliente, saludamos por su nombre, bien pro xd
            System.out.println("Cliente encontrado: " + cliente.getNombre());
        }

        // Aca armamos dos mapas: uno pa guardar los IDs y datos crudos pal carrito, y otro pa la vista resumida, bien organizados
        Map<Integer, Object[]> carritoIds = new HashMap<>();
        Map<String, Object[]> carritoResumenVista = new HashMap<>();

        // Aca declaramos un boolean pa controlar el ciclo de compra de varios telefonos, eso tilin
        boolean seguirComprando = true;
        while (seguirComprando) {
            System.out.println("\n--- CELULARES DISPONIBLES ---");
            // Aca traemos la lista de celulares activos desde la base de datos, bien aja
            List<Celular> celulares = celularDao.obtenerCelulares();
            for (Celular c : celulares) {
                System.out.println(c);
            }

            // Pedimos el ID del celular que el cliente quiere comprar, wazaa
            int idCelular = valNum.validarEntero("Ingrese el ID del celular que desea comprar: ");
            Celular celularSeleccionado = celularDao.buscar(idCelular);

            // Si el celular no existe con ese ID, avisamos y volvemos a empezar el ciclo, xd
            if (celularSeleccionado == null) {
                System.out.println("Celular no encontrado con ese ID.");
                continue;
            }

            // Si el stock esta en cero o menos, mostramos el aviso de agotado y repetimos el ciclo, piolin pa q amarre
            if (celularSeleccionado.getStock() <= 0) {
                System.out.println("Lo sentimos, el celular '" + celularSeleccionado.getModelo() + "' ya no esta disponible (Stock: 0).");
                continue;
            }

            // Aca pedimos la cantidad que quiere comprar validando que haya stock y que no meta numeros locos ni negativos, gg ez
            int cantidad;
            do {
                cantidad = valNum.validarEntero("Ingrese la cantidad a comprar (Stock disponible: " + celularSeleccionado.getStock() + "): ");
                if (cantidad <= 0) {
                    System.out.println("La cantidad debe ser mayor a 0.");
                } else if (cantidad > celularSeleccionado.getStock()) {
                    System.out.println("No es posible. La cantidad supera el stock disponible (" + celularSeleccionado.getStock() + "). Reingrese la cantidad.");
                }
            } while (cantidad <= 0 || cantidad > celularSeleccionado.getStock());

            // Aca calculamos el subtotal multiplicando la cantidad por el precio unitario del celular, bien financiero xd
            double subtotalItem = cantidad * celularSeleccionado.getPrecio();

            // Aca guardamos los datos del producto en los mapas del carrito, chiclebomba pa q amarre
            carritoIds.put(idCelular, new Object[]{idCelular, cantidad, subtotalItem});
            carritoResumenVista.put(celularSeleccionado.getModelo(), new Object[]{cantidad, subtotalItem});

            System.out.println("Producto agregado al carrito.");

            // Aca preguntamos si quiere llevar otro telefono o si ya quiere pagar y finalizar la compra, eso tilin :V
            int opcionContinuar = valNum.validarEntero("""
                                                      
                                                      Que desea hacer?
                                                      1. Agregar otro celular al carrito
                                                      2. Finalizar compra
                                                      Seleccione una opcion: """);
            if (opcionContinuar == 2) {
                seguirComprando = false;
            }
        }

        // Aca acumulamos el subtotal general y armamos la lista de detalles pa la base de datos, bien ordenado
        double subtotalGeneral = 0;
        List<DetalleVenta> listaDetalles = new ArrayList<>();

        for (Map.Entry<Integer, Object[]> entry : carritoIds.entrySet()) {
            int idCel = entry.getKey();
            Object[] datos = entry.getValue();
            int cant = (int) datos[1];
            double sub = (double) datos[2];
            
            subtotalGeneral += sub;
            
            // Aca agregamos cada detalle de la compra a la lista, gg ez
            listaDetalles.add(new DetalleVenta(0, idCel, cant, sub));
        }

        // Aca calculamos el IVA (19%) y el total general sumando el impuesto, wazaa
        double iva = subtotalGeneral * 0.19;
        double totalGeneral = subtotalGeneral + iva;

        // Aca armamos el objeto Venta con el ID del cliente, el vendedor y el total, bien pro xd
        Venta nuevaVenta = new Venta(cliente.getId(), this.idVendedor, totalGeneral);
        // Mandamos a registrar toda la venta y sus detalles de forma transaccional al DAO, piolin pa q amarre
        boolean exito = ventaDao.registrarVentaCompleta(nuevaVenta, listaDetalles);

        // Si el registro salio exitoso en la base de datos, aqui inserto esto y hago aquello para ello: imprimimos factura y guardamos respaldos, bien aja :V
        if (exito) {
            String vendedorNombre = obtenerNombreVendedor(this.idVendedor);
            ventaView.mostrarFactura(cliente.getNombre(), vendedorNombre, carritoResumenVista, subtotalGeneral, iva, totalGeneral);

            // 1. Guardamos una copia del registro en el archivo binario de ventas (.dat), chiclebomba
            datosVentaArchivo.guardar(new VentaRegistro(0, cliente.getNombre(), vendedorNombre, totalGeneral, LocalDateTime.now()));

            // 2. Registramos la transaccion en la bitacora de auditoria (.dat) pa que quede chismografo oficial, eso tilin
            auditoriaArchivo.registrarAccion("Vendedor ID: " + this.idVendedor, "VENTA", 
                    "Venta efectuada al cliente " + cliente.getNombre() + " por un total de $" + totalGeneral);
        }
    }

    // Aca creamos el metodo auxiliar buscarClientePorIdentificacion pa consultar si un cliente ya esta en MySQL, bien limpio xd
    private Persona buscarClientePorIdentificacion(String identificacion) {
        String sql = "SELECT * FROM persona WHERE identificacion = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, identificacion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Si lo encuentra, retornamos el objeto Persona completo, gg ez
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

    // Aca creamos el metodo registrarClienteNuevo pa dar de alta rapidito a un cliente que no estaba en el sistema, wazaa
    private Persona registrarClienteNuevo(String identificacion) {
        System.out.println("\n--- REGISTRO RAPIDO DE CLIENTE ---");
        String nombre = valTexto.validarTextoNoVacio("Ingrese el nombre del cliente: ");
        String correo = valTexto.validarCorreo("Ingrese el correo del cliente: ");
        String telefono = String.valueOf(valNum.validarEnteroGrande("Ingrese el telefono: "));
        String contraseña = null; 

        // Creamos el objeto cliente y lo mandamos a la base de datos con el DAO, piolin pa q amarre
        Persona nuevoCliente = new Persona(nombre, identificacion, correo, contraseña, telefono);
        personaDao.crear(nuevoCliente);

        // Registramos la accion de creacion rapida en el archivo .dat de auditoria, eso tilin :V
        auditoriaArchivo.registrarAccion("Sistema", "REGISTRO_CLIENTE", "Se registró rápidamente al cliente: " + nombre);

        // Retornamos al cliente ya guardado buscandolo de nuevo por su identificacion, gg ez
        return buscarClientePorIdentificacion(identificacion);
    }

    // Aca creamos el metodo obtenerNombreVendedor pa hacer un JOIN rapido y sacar el nombre del cajero segun su ID, bien pro xd
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