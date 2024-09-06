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
@Table(name = "historialparqueadero")
public class HistorialParqueadero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorial;
    @NonNull
    @Column(name = "fechaHoraIngreso", nullable = false, length = 45)
    private LocalDateTime fechaIngreso;
    @NonNull
    @Column(name = "fechaHoraSalida", nullable = false, length = 45)
    private LocalDateTime fechaSalida;
    @NonNull
    @Column(name = "totalTiempo", nullable = false, length = 45)
    private int totalTiempo;
    @NonNull
    @Column(name = "totalPagar", nullable = false, length = 45)
    private double totalPagar;

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
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

    public int getTotalTiempo() {
        return totalTiempo;
    }

    public void setTotalTiempo(int totalTiempo) {
        this.totalTiempo = totalTiempo;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

}
