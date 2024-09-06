package com.adso.servicios.web.Servicios.Implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adso.servicios.web.Entidades.VehiculoRegistrado;
import com.adso.servicios.web.Repositorios.VehiculoRegistradoRepository;
import com.adso.servicios.web.Servicios.Interfaces.VehiculoRegistradoInt;

@Service
public class VehiculoRegistradoImp implements VehiculoRegistradoInt {
    @Autowired
    private VehiculoRegistradoRepository repository;

    @Override
    public List<VehiculoRegistrado> finAll() {

        return repository.findAll();
    }

    @Override
    public Optional<VehiculoRegistrado> findById(Integer id) {

        return repository.findById(id);
    }

    @Override
    public VehiculoRegistrado save(VehiculoRegistrado vehiculoRegistrado) {

        return repository.save(vehiculoRegistrado);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
