package com.adso.servicios.web.Entidades;

import java.time.LocalDateTime;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculosregistrados")
public class VehiculoRegistrado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehiculo;
    @NonNull
    @Column(name = "placa", nullable = false, length = 8)
    private String placa;
    @NonNull
    @Column(name = "marcaVehiculo", nullable = false, length = 45)
    private String marca;

    @NonNull
    @Column(name = "color", nullable = false, length = 45)
    private String color;
    @NonNull
    @Column(name = "tipoVehiculo", nullable = false, length = 45)
    private String tipoVehiculo;
    @NonNull
    @Column(name = "fechaRegistro", nullable = false)
    private LocalDateTime fechaRegistro;
    @NonNull
    @Column(name = "tipoPlan", nullable = false, length = 45)
    private String tipoPlan;
    @NonNull
    @Column(name = "fechaFacturacion", nullable = false)
    private LocalDateTime fechaFacturacion;

    @NonNull
    @Column(name = "fechaVencimiento", nullable = false)
    private LocalDateTime fechaVencimiento;
    // Getters y Setters

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public LocalDateTime getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(LocalDateTime fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

}
