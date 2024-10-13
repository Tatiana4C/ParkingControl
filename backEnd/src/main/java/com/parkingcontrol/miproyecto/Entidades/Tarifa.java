package com.parkingcontrol.miproyecto.Entidades;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarifas")
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarifa", nullable = false)
    private Integer idTarifa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo;  // Usamos el enum en vez de String

    @Column(name = "tarifa_fraccion", nullable = false)
    private BigDecimal tarifaFraccion;

    @Column(name = "tarifa_dia", nullable = false)
    private BigDecimal tarifaDia;

    @Column(name = "tarifa_mes", nullable = false)
    private BigDecimal tarifaMes;

    @Column(name = "tarifa_anual", nullable = false)
    private BigDecimal tarifaAnual;

    // Constructor vacío
    public Tarifa() {}

    // Constructor completo
    public Tarifa(TipoVehiculo tipoVehiculo, BigDecimal tarifaFraccion, BigDecimal tarifaDia, BigDecimal tarifaMes, BigDecimal tarifaAnual) {
        this.tipoVehiculo = tipoVehiculo;
        this.tarifaFraccion = tarifaFraccion;
        this.tarifaDia = tarifaDia;
        this.tarifaMes = tarifaMes;
        this.tarifaAnual = tarifaAnual;
    } 
    
    //Getter and Setters

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public BigDecimal getTarifaFraccion() {
        return tarifaFraccion;
    }

    public void setTarifaFraccion(BigDecimal tarifaFraccion) {
        this.tarifaFraccion = tarifaFraccion;
    }

    public BigDecimal getTarifaDia() {
        return tarifaDia;
    }

    public void setTarifaDia(BigDecimal tarifaDia) {
        this.tarifaDia = tarifaDia;
    }

    public BigDecimal getTarifaMes() {
        return tarifaMes;
    }

    public void setTarifaMes(BigDecimal tarifaMes) {
        this.tarifaMes = tarifaMes;
    }

    public BigDecimal getTarifaAnual() {
        return tarifaAnual;
    }

    public void setTarifaAnual(BigDecimal tarifaAnual) {
        this.tarifaAnual = tarifaAnual;
    }

}
