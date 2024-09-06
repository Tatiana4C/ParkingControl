package com.adso.servicios.web.Servicios.Implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adso.servicios.web.Entidades.HistorialParqueadero;
import com.adso.servicios.web.Repositorios.HistorialParquederoRepository;
import com.adso.servicios.web.Servicios.Interfaces.HistorialParqueaderoInt;

@Service
public class HistorialParqueaderoImp implements HistorialParqueaderoInt {

    @Autowired
    private HistorialParquederoRepository repository;

    @Override
    public List<HistorialParqueadero> finAll() {
        return (List<HistorialParqueadero>) repository.findAll();
    }

    @Override
    public void save(HistorialParqueadero HistorialParqueadero) {
        repository.save(HistorialParqueadero);
    }

    @Override
    public Optional<HistorialParqueadero> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
