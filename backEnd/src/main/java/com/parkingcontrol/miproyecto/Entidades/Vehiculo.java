package com.parkingcontrol.miproyecto.Entidades;
// Vehiculos que esten dentro del parqueadero

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vehiculos")

public class Vehiculo {
    @Id // Llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se autogenera
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    @Column(name = "placa", nullable = false, length = 6, unique = true) 
    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo;  // Usamos el enum en vez de String

    @Column(name = "marca_vehiculo", nullable = false, length = 15)
    private String marcaVehiculo;

    @Column(name = "color", nullable = false, length = 10)
    private String color;

    @Column(name = "fecha_ingreso", nullable = false)
    @NotNull
    private LocalDateTime fechaIngreso;  // Fecha y hora de ingreso del vehículo

    @OneToOne
    @JoinColumn(name = "numero_puesto", referencedColumnName = "numero_puesto")
    private Puesto puesto; // Relaciona directamente con el puesto
    
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

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
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

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
        
}