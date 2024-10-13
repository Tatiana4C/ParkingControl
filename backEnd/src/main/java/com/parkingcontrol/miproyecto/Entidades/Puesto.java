package com.parkingcontrol.miproyecto.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "puestos")
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_puesto", nullable = false)
    private Integer idPuesto;

    @Column(name = "numero_puesto", nullable = false, unique = true, length = 5)
    private String numeroPuesto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo;  // Carro o Moto

    @Column(name = "esta_ocupado", nullable = false)
    private boolean estaOcupado = false; // Inicialmente disponible

    @OneToOne(mappedBy = "puesto")  // Relación inversa con Vehiculo
    @JsonIgnore
    private Vehiculo vehiculo;
    
    // ManyToOne hay muchos puestos en un  parqueadero
    @ManyToOne
    @JoinColumn(name = "parqueadero_id", nullable = false)  // Relación con Parqueadero
    @JsonIgnore // Ignora el campo para evitar ciclos
    private Parqueadero parqueadero;

    // Constructor vacío
    public Puesto() {}

    // Constructor completo
    public Puesto(String numeroPuesto, TipoVehiculo tipoVehiculo, boolean estaOcupado, Parqueadero parqueadero) {
        this.numeroPuesto = numeroPuesto;
        this.tipoVehiculo = tipoVehiculo;
        this.estaOcupado = estaOcupado;
        this.parqueadero = parqueadero;  
    } 
    
    //Getter and Setters

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNumeroPuesto() {
        return numeroPuesto;
    }

    public void setNumeroPuesto(String numeroPuesto) {
        this.numeroPuesto = numeroPuesto;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public boolean isEstaOcupado() {
        return estaOcupado;
    }

    public void setEstaOcupado(boolean estaOcupado) {
        this.estaOcupado = estaOcupado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

}


