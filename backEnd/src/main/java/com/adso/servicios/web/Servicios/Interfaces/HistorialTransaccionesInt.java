package com.adso.servicios.web.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import com.adso.servicios.web.Entidades.HistorialTransacciones;

public interface HistorialTransaccionesInt {
    public List<HistorialTransacciones> findAll();

    public void save(HistorialTransacciones historialTransacciones);

    public Optional<HistorialTransacciones> findById(Integer id);

    public void delete(Integer id);

}
