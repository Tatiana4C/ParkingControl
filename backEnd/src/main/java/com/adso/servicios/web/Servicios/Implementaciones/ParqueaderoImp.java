package com.adso.servicios.web.Servicios.Implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adso.servicios.web.Entidades.Parqueadero;
import com.adso.servicios.web.Repositorios.ParqueaderoRepository;
import com.adso.servicios.web.Servicios.Interfaces.ParqueaderoInt;

@Service
public class ParqueaderoImp implements ParqueaderoInt {

    @Autowired
    private ParqueaderoRepository repository;

    @Override
    public List<Parqueadero> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Parqueadero> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Parqueadero save(Parqueadero parqueadero) {
        return repository.save(parqueadero);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
