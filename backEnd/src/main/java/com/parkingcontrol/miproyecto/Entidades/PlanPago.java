package com.parkingcontrol.miproyecto.Entidades;
// Vehiculos que han pagado mesualidad o anualidad

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
import jakarta.persistence.Transient;

@Entity
@Table(name = "planes_pago")
public class PlanPago {
    @Id
    @Column(name = "id_planPago", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlanPago;

    @Column(name = "tipo_plan", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPlan tipoPlan;

    @Transient // No se guarda en la base de datos, ya que se calcula
    private BigDecimal costo;

    @Column(name = "duracion_dias", nullable = false)
    private int duracionDias; // Duración del plan en días (por ejemplo, 30 días para mensual)

    @Column(name = "placa", nullable = false, length = 6, unique = true) 
    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo;  // Usamos el enum en vez de String

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    @Column(name = "esta_activo", nullable = false)
    public boolean estaActivo() {
        return LocalDateTime.now().isBefore(fechaFin);
    } 
    
    // Getters y Setters

    public Integer getIdPlanPago() {
        return idPlanPago;
    }

    public void setIdPlanPago(Integer idPlanPago) {
        this.idPlanPago = idPlanPago;
    }

    public TipoPlan getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(TipoPlan tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
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

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

}
