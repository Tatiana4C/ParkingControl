package com.adso.servicios.web.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import com.adso.servicios.web.Entidades.Parqueadero;

public interface ParqueaderoInt {

    public List<Parqueadero> findAll();

    public Optional<Parqueadero> findById(Integer id);

    public Parqueadero save(Parqueadero parqueadero);

    public void delete(Integer id);

}
