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
@Table(name = "historialtransacciones")
public class HistorialTransacciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaccion;
    @NonNull
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    @NonNull
    @Column(name = "formaPago", nullable = false, length = 45)
    private String formaPago;
    @NonNull
    @Column(name = "totalTiempo", nullable = false, length = 45)
    private String totalTiempo;
    @NonNull
    @Column(name = "totalPagar", nullable = false, length = 45)
    private double totalPagar;

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getTotalTiempo() {
        return totalTiempo;
    }

    public void setTotalTiempo(String totalTiempo) {
        this.totalTiempo = totalTiempo;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

}
