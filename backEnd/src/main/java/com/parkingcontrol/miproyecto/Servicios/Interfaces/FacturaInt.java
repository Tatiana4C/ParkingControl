package com.parkingcontrol.miproyecto.Servicios.Interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.parkingcontrol.miproyecto.Entidades.Factura;
import com.parkingcontrol.miproyecto.Entidades.Vehiculo;
import com.parkingcontrol.miproyecto.Entidades.FormaPago;
import com.parkingcontrol.miproyecto.Entidades.TipoPlan;


public interface FacturaInt {
    // Método para obtener todas las facturas
    List<Factura> findAll();

    // Método para buscar factura por id
    Factura findById(Integer id);

    // Método para guardar o modificar factura
    Factura save(Factura factura);

    // Método para eliminar factura
    void deleteById(Integer id);

    // Método para buscar factura segun su medio de pago
    List<Factura> findByFormaPago(FormaPago formaPago);

    // Método para buscar facturas segun la placa
    List<Factura> findByPlaca(String placa);

    // Método nuevo para crear factura
    Factura crearFactura(String placa);

    // Método para factura de plan
    Factura crearFacturaPlanPago(Vehiculo vechiculo, TipoPlan tipoPlan);
    
    // Método para calcular tarifa por duración
    Factura calcularTarifaPorDuracion(Vehiculo vehiculo);

    // Método para cambiar la forma de pago
    Factura cambiarFormaPago(Integer id, FormaPago nuevaFormaPago);

    // Método para obtener los ingresos
    BigDecimal obtenerIngresosDiarios();
    BigDecimal obtenerIngresosSemanales();
    BigDecimal obtenerIngresosMensuales();
    BigDecimal obtenerIngresosAnuales();

    // Método para obtener el total por el tipo de pago
    long obtenerTotalTransacciones();
    BigDecimal obtenerIngresosPorFormaPago(String formaPago);
    long obtenerTotalTransaccionesPorFormaPago(String formaPago);

    // Método para vehiculos recurrentes
    double obtenerPorcentajeClientesRecurrentes();

}


