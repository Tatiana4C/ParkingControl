package com.parkingcontrol.miproyecto.Controladores;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parkingcontrol.miproyecto.Entidades.Factura;
import com.parkingcontrol.miproyecto.Entidades.FormaPago;
import com.parkingcontrol.miproyecto.Entidades.TipoPlan;
import com.parkingcontrol.miproyecto.Entidades.Vehiculo;
import com.parkingcontrol.miproyecto.Repository.FacturaRepository;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.FacturaInt;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.VehiculoInt;

@RestController
@CrossOrigin(origins = "*") // Permitir todas las solicitudes de cualquier origen
@RequestMapping("/api/facturas")
public class FacturaControlador {

    private final FacturaInt facturaInt;
    private final FacturaRepository facturaRepository;
    private final VehiculoInt vehiculoInt;

    public FacturaControlador(FacturaInt facturaInt, VehiculoInt vehiculoInt, FacturaRepository facturaRepository) {
        this.facturaInt = facturaInt;
        this.vehiculoInt = vehiculoInt;
        this.facturaRepository = facturaRepository;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Factura>> obtenerTodasLasFacturas() {
        List<Factura> facturas = facturaInt.findAll();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerFacturaPorId(@PathVariable Integer id) {
        Factura factura = facturaInt.findById(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura);
    }

    // Método para crear factura por 0 de un vehiculo que tiene un planPago
    @PostMapping("/tienePlan")
    public ResponseEntity<String> crearFactura(@RequestBody String placa) {
        try {
            // Intentamos crear la factura llamando al servicio
            Factura factura = facturaInt.crearFactura(placa);
            
            // Si la factura es null, significa que el plan de pago está vencido
            if (factura == null) {
                // Respondemos con un BAD_REQUEST si el plan está vencido
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El plan de pago está vencido.");
            }
            
            // Si la factura no es null, significa que se creó correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Factura creada con éxito.");
        } catch (RuntimeException e) {
            // En caso de un error inesperado (por ejemplo, vehículo no encontrado), respondemos con CONFLICT
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    

    // Método para crear factura de mensualidad o anualidad
    @PostMapping("/crearFacturaPlanPago")
    public ResponseEntity<Map<String, Object>> crearFacturaPlanPago(@RequestParam("placa") String placa,
                                                               @RequestParam("tipoPlan") TipoPlan tipoPlan) {
        try {
            // 1. Buscar el vehículo por placa
            Vehiculo vehiculo = vehiculoInt.findByPlaca(placa);
            if (vehiculo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("mensaje", "Vehículo con placa " + placa + " no encontrado."));
            }

            // 2. Crear factura con plan de pago
            Factura factura = facturaInt.crearFacturaPlanPago(vehiculo, tipoPlan);

            Map<String, Object> response = new HashMap<>();
            response.put("idFactura", factura.getIdFactura()); // Incluye el ID de la factura
            response.put("valor", factura.getValor()); // Incluye el valor de la factura

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("mensaje", e.getMessage()));
        }
    }

    // Método para facturar por duración
    @PostMapping("/calcularPorDuracion")
    public ResponseEntity<Map<String, Object>> calcularTarifaPorDuracion(@RequestParam("placa") String placa) {
        try {
            Vehiculo vehiculo = vehiculoInt.findByPlaca(placa);
            if (vehiculo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("mensaje", "Vehículo con placa " + placa + " no encontrado."));
            }
        
            Factura factura = facturaInt.calcularTarifaPorDuracion(vehiculo);
        
            // Crear el mapa para la respuesta
            Map<String, Object> response = new HashMap<>();
            response.put("idFactura", factura.getIdFactura()); // ID de la factura
            response.put("valor", factura.getValor()); // Total de la factura

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } 
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("mensaje", e.getMessage())); // Mensaje de error
        }
    }


    // Actualizar forma de pago
    @PutMapping("/cambiarFormaPago/{formaPago}")
    public ResponseEntity<String> cambiarFormaPago(
        @RequestParam("id") Integer id,
        @RequestParam("nuevaFormaPago") FormaPago nuevaFormaPago) {
            try {
                Factura factura = facturaInt.cambiarFormaPago(id, nuevaFormaPago);

                return ResponseEntity.ok("Forma de pago actualizada con éxito para la factura ID: " + factura.getIdFactura());
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

            }
    }

    // Buscar factura por placa
    @GetMapping("/placa")
    public ResponseEntity<List<Factura>> obtenerFacturasPorPlaca(@PathVariable String placa) {
        List<Factura> facturas = facturaInt.findByPlaca(placa);
        return ResponseEntity.ok(facturas);
    }

    // Eliminar factura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Integer id) {
        facturaInt.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Método para encontrar el promedio de tiempo en el estacionamiento
    @GetMapping("/promedio-tiempo")
    public Double obtenerPromedioTiempoEstacionamiento() {
        return facturaRepository.calcularPromedioTiempoEstacionamiento();
    }
    
    // Método para obtener ingresos
    @GetMapping("/ingresos")
    public Map<String, BigDecimal> obtenerIngresos() {
        Map<String, BigDecimal> ingresos = new HashMap<>();
        ingresos.put("diarios", facturaInt.obtenerIngresosDiarios());
        ingresos.put("semanales", facturaInt.obtenerIngresosSemanales());
        ingresos.put("mensuales", facturaInt.obtenerIngresosMensuales());
        ingresos.put("anuales", facturaInt.obtenerIngresosAnuales());
        return ingresos;
    }

    // Método para obtener ingresos por forma de pago
    @GetMapping("/ingresos-tipo-pago")
    public Map<String, Object> obtenerIngresosPorFormaPago() {
        Map<String, Object> respuesta = new HashMap<>();
    
        // Convertir las cadenas a FormaPago enum
        FormaPago formaPagoEfectivo = FormaPago.valueOf("EFECTIVO");
        FormaPago formaPagoTarjeta = FormaPago.valueOf("TARJETA");
        
        // Llamar a los métodos con el enum
        BigDecimal ingresosEfectivo = facturaInt.obtenerIngresosPorFormaPago(formaPagoEfectivo);
        BigDecimal ingresosTarjeta = facturaInt.obtenerIngresosPorFormaPago(formaPagoTarjeta);

        long totalTransacciones = facturaInt.obtenerTotalTransacciones();
        long transaccionesEfectivo = facturaInt.obtenerTotalTransaccionesPorFormaPago(formaPagoEfectivo);
        long transaccionesTarjeta = facturaInt.obtenerTotalTransaccionesPorFormaPago(formaPagoTarjeta);


        // Calcular el porcentaje
        double porcentajeEfectivo = (double) transaccionesEfectivo / totalTransacciones * 100;
        double porcentajeTarjeta = (double) transaccionesTarjeta / totalTransacciones * 100;

        // Agregar los resultados al mapa de respuesta
        respuesta.put("ingresosEfectivo", ingresosEfectivo);
        respuesta.put("ingresosTarjeta", ingresosTarjeta);
        respuesta.put("porcentajeEfectivo", porcentajeEfectivo);
        respuesta.put("porcentajeTarjeta", porcentajeTarjeta);

        return respuesta;
    }

    // Método para saber cuantos cliente frecuentes hay
    @GetMapping("/frecuencia-clientes-recurrentes")
    public ResponseEntity<Double> obtenerFrecuenciaClientesRecurrentes() {
        double porcentaje = facturaInt.obtenerPorcentajeClientesRecurrentes();
        return ResponseEntity.ok(porcentaje);
    }
}