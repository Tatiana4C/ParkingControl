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
@Table(name = "vehiculosestacionados")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehiculo;

    @NonNull
    @Column(name = "tipoVehiculo", nullable = false, length = 45)
    private String tipo;
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
    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaIngreso;
    @NonNull
    @Column(name = "puesto", nullable = false, length = 5)
    private String puesto;

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

}
