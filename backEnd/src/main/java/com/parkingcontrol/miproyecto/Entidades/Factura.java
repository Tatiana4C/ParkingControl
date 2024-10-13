package com.parkingcontrol.miproyecto.Entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @Column(name = "id_factura", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idFactura;

    @Column(name = "placa", nullable = false, length = 6) 
    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo;  // Usamos el enum en vez de String

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pago") // Efectivo o tarjeta
    private FormaPago formaPago;

    @Column(name = "tiempo_estacionamiento", nullable = false) //Tiempo total en el parqueadero
    private int tiempoEstacionamiento;

    @Column(name = "fecha_facturacion", nullable = false)  //Fecha emición de la factura
    private LocalDateTime fechaFacturacion;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaIngreso;  // Fecha y hora de ingreso del vehículo (duplicada)

    @Column(name = "fecha_salida", nullable = true)   // Fecha y hora de salida del vehículo
    private LocalDateTime fechaSalida;   // Puede ser null ya que el vehiculo puede estar adentro del parqueadero y aun así genrarle factura

    @Column(name = "tipo_plan", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPlan tipoPlan; // Tarifa que se aplico en la factura Anual, Mensual, Duracion

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;  
    
    //Getter and Setters

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public int getTiempoEstacionamiento() {
        return tiempoEstacionamiento;
    }

    public void setTiempoEstacionamiento(int tiempoEstacionamiento) {
        this.tiempoEstacionamiento = tiempoEstacionamiento;
    }

    public LocalDateTime getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(LocalDateTime fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public TipoPlan getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(TipoPlan tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

   

}
