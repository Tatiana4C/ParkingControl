package com.parkingcontrol.miproyecto.Entidades;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "parqueaderos")
public class Parqueadero {
    @Id
    @Column(name = "id_parqueadero", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParqueadero;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 15) 
    private String telefono;

    @Column(name = "capacidad_motos", nullable = false) 
    private int capacidadMotos;

    @Column(name = "capacidad_carros", nullable = false) 
    private int capacidadCarros;

    // OneToMany Un parqueadro tiene multiples puestos
    // mappedBy significa que Puesto manda, 
    // Parqueadro reflejar la relación en la entidad sin modificar la base de datos.
    @OneToMany(mappedBy = "parqueadero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Puesto> puestos;
    
    // Getter and Setters

    public Integer getIdParqueadero() {
        return idParqueadero;
    }

    public void setIdParqueadero(Integer idParqueadero) {
        this.idParqueadero = idParqueadero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCapacidadMotos() {
        return capacidadMotos;
    }

    public void setCapacidadMotos(int capacidadMotos) {
        this.capacidadMotos = capacidadMotos;
    }

    public int getCapacidadCarros() {
        return capacidadCarros;
    }

    public void setCapacidadCarros(int capacidadCarros) {
        this.capacidadCarros = capacidadCarros;
    }

    public List<Puesto> getPuestos() {
        return puestos;
    }

    public void setPuestos(List<Puesto> puestos) {
        this.puestos = puestos;
    }

}