package com.adso.servicios.web.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import com.adso.servicios.web.Entidades.Vehiculo;

public interface VehiculoInt {
    public List<Vehiculo> findAll();

    public Optional<Vehiculo> findById(Integer id);

    public Vehiculo save(Vehiculo car);

    public void delete(Integer id);

}
