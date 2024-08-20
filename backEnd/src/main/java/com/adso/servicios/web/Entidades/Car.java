package com.adso.servicios.web.Entidades;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(name = "tipo", nullable = false, length = 45)
    private String tipo;
    @NonNull
    @Column(name = "placa", nullable = false, length = 45)
    private String placa;
    @NonNull
    @Column(name = "marca_vahiculo", nullable = false, length = 45)
    private String marca;
    @NonNull
    @Column(name = "modelo", nullable = false, length = 45)
    private String modelo;
    @NonNull
    @Column(name = "color", nullable = false, length = 45)
    private String color;
    @NonNull
    @Column(name = "fecha_ingreso", nullable = false, length = 45)
    private String fechaIngreso;
    @NonNull
    @Column(name = "fecha_salida", nullable = false, length = 45)
    private String fechaSalida;

}
