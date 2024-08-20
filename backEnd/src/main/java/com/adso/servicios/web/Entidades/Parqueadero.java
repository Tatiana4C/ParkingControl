package com.adso.servicios.web.Entidades;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parqueadero")
public class Parqueadero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @NonNull
    @Column(name = "direccion", nullable = false, length = 45)
    private String direccion;
    @NonNull
    @Column(name = "telefono", nullable = false, length = 45)
    private String telefono;
    @NonNull
    @Column(name = "Capacidad_total", nullable = false, length = 11)
    private int capacidad_total;
    @NonNull
    @Column(name = "capacidad_actual", nullable = false, length = 11)
    private int capacidad_actual;

}
