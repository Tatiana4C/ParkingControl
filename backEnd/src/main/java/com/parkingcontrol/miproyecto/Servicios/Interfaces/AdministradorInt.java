package com.parkingcontrol.miproyecto.Servicios.Interfaces;

import java.util.List;

import com.parkingcontrol.miproyecto.Entidades.Administrador;

public interface AdministradorInt {
    // Método para obtener todos los usuarios administradores
    List<Administrador> findAll();

    // Método para buscar por Id
    Administrador findById(Integer id);

    // Método para eliminar usuario por ID
    void deleteById(Integer id);

    // Verifica que exista un administrador con ese email
    boolean verificarEmail(String email);

    // Me verifica que las credeanciales sean correctas
    boolean verificarCredenciales(String email, String contrasena);

    // Crea nuevo usuario Administrador
    Administrador crearAdministrador(Administrador administrador);
}