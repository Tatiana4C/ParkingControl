package com.adso.servicios.web.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import com.adso.servicios.web.Entidades.VehiculoRegistrado;

public interface VehiculoRegistradoInt {

    public List<VehiculoRegistrado> findAll();

    public Optional<VehiculoRegistrado> findById(Integer id);

    public VehiculoRegistrado save(VehiculoRegistrado vehiculoRegistrado);

    public void delete(Integer id);

}
