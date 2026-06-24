package com.facturalogic.service;

import com.facturalogic.model.Cliente;
import com.facturalogic.model.DetalleFactura;
import com.facturalogic.model.Factura;
import com.facturalogic.model.Producto;
import com.facturalogic.repository.ClienteRepository;
import com.facturalogic.repository.FacturaRepository;
import com.facturalogic.repository.ProductoRepository;

import java.util.List;

public class FacturacionService {

    // El servicio necesita interactuar con los tres repositorios
    private final ProductoRepository productoRepo = new ProductoRepository();
    private final ClienteRepository clienteRepo = new ClienteRepository();
    private final FacturaRepository facturaRepo = new FacturaRepository();

    public boolean procesarVenta(String documentoCliente, String numeroFactura, List<DetalleFactura> items) {
        System.out.println("\n--- Iniciando Procesamiento de Venta Lógica ---");

        // 1. Validar el Cliente
        Cliente cliente = clienteRepo.buscarPorDocumento(documentoCliente);
        if (cliente == null) {
            System.err.println("❌ Error de negocio: El cliente con documento " + documentoCliente + " no está registrado.");
            return false;
        }

        // 2. Crear el objeto Factura principal
        Factura factura = new Factura();
        factura.setNumeroFactura(numeroFactura);
        factura.setCliente(cliente);

        double totalFactura = 0.0;

        // 3. Validar y procesar cada artículo del carrito
        for (DetalleFactura item : items) {
            // Buscamos el estado real del producto en la BD usando su código de barras
            Producto productoReal = productoRepo.buscarPorCodigo(item.getProducto().getCodigoBarras());
            
            if (productoReal == null) {
                System.err.println("❌ Error: El producto con código " + item.getProducto().getCodigoBarras() + " no existe.");
                return false;
            }

            // Control de Stock (Lógica Analítica Fundamental)
            if (productoReal.getStock() < item.getCantidad()) {
                System.err.println("❌ Stock Insuficiente para: " + productoReal.getNombre() + 
                                   " (Disponible: " + productoReal.getStock() + ", Solicitado: " + item.getCantidad() + ")");
                return false;
            }

            // Realizamos los cálculos matemáticos del ítem
            item.setProducto(productoReal); // Vinculamos el producto real con su ID de la BD
            item.setPrecioUnitario(productoReal.getPrecioVenta());
            double subtotalItem = productoReal.getPrecioVenta() * item.getCantidad();
            item.setSubtotal(subtotalItem);

            totalFactura += subtotalItem;

            // Agregamos el detalle procesado a la factura
            factura.agregarDetalle(item);

            // Restamos temporalmente el stock en la memoria (Para luego actualizarlo si lo deseas extender)
            productoReal.setStock(productoReal.getStock() - item.getCantidad());
        }

        // Asignamos el gran total calculado de forma segura
        factura.setTotal(totalFactura);
        System.out.println("Calculado con éxito. Total de la Factura: $" + totalFactura);

        // 4. Mandar a guardar la transacción completa en MySQL
        boolean exitoGuardado = facturaRepo.guardar(factura);
        
        if (exitoGuardado) {
            System.out.println("¡Factura " + numeroFactura + " procesada y registrada impecablemente!");
            return true;
        } else {
            System.err.println("❌ Error crítico: Falló la persistencia de la factura en el servidor.");
            return false;
        }
    }
}