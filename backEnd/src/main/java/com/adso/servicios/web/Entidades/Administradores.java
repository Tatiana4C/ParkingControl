package com.adso.servicios.web.Entidades;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administradores")
public class Administradores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdministrador;
    @NonNull
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @NonNull
    @Column(name = "email", nullable = false, length = 100)
    private String correo;
    @NonNull
    @Column(name = "contraseña", nullable = false, length = 255)
    private String contraseña;

    // Getters y Setters
    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
