package com.facturalogic;

import com.facturalogic.model.Cliente;
import com.facturalogic.model.DetalleFactura;
import com.facturalogic.model.Producto;
import com.facturalogic.repository.ClienteRepository;
import com.facturalogic.service.FacturacionService;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("====== SISTEMA FACTURALOGIC INDEPENDIENTE ======");
        
        // 1. Instanciar los componentes que usaremos
        ClienteRepository clienteRepo = new ClienteRepository();
        FacturacionService facturacionService = new FacturacionService();

        // 2. Crear y registrar un cliente de prueba
        String documentoPrueba = "0614-250626-101-5"; // Formato DUI simulado
        Cliente clientePrueba = new Cliente();
        clientePrueba.setDocumentoIdentidad(documentoPrueba);
        clientePrueba.setNombreCompleto("Ricardo Godoy");
        clientePrueba.setCorreo("ricardo.godoy@facturalogic.com");

        System.out.println("Registrando cliente de prueba...");
        clienteRepo.guardar(clientePrueba);

        // Volvemos a buscar el cliente para jalar el ID que le asignó MySQL automáticamente
        Cliente clienteRegistrado = clienteRepo.buscarPorDocumento(documentoPrueba);

        // 3. Crear el Carrito de Compras (Simulación de Escaneo en Caja)
        List<DetalleFactura> carrito = new ArrayList<>();

        // Ítem 1: Vamos a comprar el 'Adaptador Dynamo 12V' que guardamos en la prueba anterior
        DetalleFactura item1 = new DetalleFactura();
        Producto p1 = new Producto();
        p1.setCodigoBarras("7501030495821"); // El código exacto del adaptador
        item1.setProducto(p1);
        item1.setCantidad(2); // Llevamos 2 unidades

        carrito.add(item1);

        // 4. Invocar al Servicio para Procesar y Validar la Venta de forma matemática
        String numeroFacturaSimulado = "FAC-2026-0001";
        
        boolean resultadoVenta = facturacionService.procesarVenta(
                clienteRegistrado.getDocumentoIdentidad(), 
                numeroFacturaSimulado, 
                carrito
        );

        if (resultadoVenta) {
            System.out.println("\n=======================================================");
            System.out.println("¡ENHORABUENA! El ciclo completo de arquitectura funciona.");
            System.out.println("Tu sistema de facturación nativo es 100% operativo.");
            System.out.println("=======================================================");
        } else {
            System.err.println("\n❌ La venta no pudo ser procesada. Revisa los mensajes de arriba.");
        }
    }
}