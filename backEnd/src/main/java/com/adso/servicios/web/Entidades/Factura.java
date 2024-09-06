package com.adso.servicios.web.Entidades;

import java.time.LocalDate;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_factura;

    @NonNull
    @Column(name = "total_hora", nullable = false, length = 11)
    private int totalhora;

    @NonNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @NonNull
    @Column(name = "total", nullable = false, length = 11)
    private double total;

    public Integer getId_factura() {
        return id_factura;
    }

    public void setId_factura(Integer id_factura) {
        this.id_factura = id_factura;
    }

    public int getTotalhora() {
        return totalhora;
    }

    public void setTotalhora(int totalhora) {
        this.totalhora = totalhora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
