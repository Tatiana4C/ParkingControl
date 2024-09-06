package com.adso.servicios.web.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import com.adso.servicios.web.Entidades.Administradores;

public interface AdministradorInt {
    public List<Administradores> findAll();

    public void save(Administradores administrador);

    public Optional<Administradores> findById(Integer id);

    public void delete(Integer id);

    public void buscar(Administradores administrador);

}
