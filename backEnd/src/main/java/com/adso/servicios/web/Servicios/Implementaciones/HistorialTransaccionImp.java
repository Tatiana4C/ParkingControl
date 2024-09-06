package com.adso.servicios.web.Servicios.Implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adso.servicios.web.Entidades.HistorialTransacciones;
import com.adso.servicios.web.Repositorios.HistorialTransaccionesRepository;
import com.adso.servicios.web.Servicios.Interfaces.HistorialTransaccionesInt;

@Service
public class HistorialTransaccionImp implements HistorialTransaccionesInt {

    @Autowired
    private HistorialTransaccionesRepository repository;

    @Override
    public List<HistorialTransacciones> findAll() {
        return (List<HistorialTransacciones>) repository.findAll();
    }

    @Override
    public Optional<HistorialTransacciones> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void save(HistorialTransacciones historialTransacciones) {

        repository.save(historialTransacciones);
    }
}
