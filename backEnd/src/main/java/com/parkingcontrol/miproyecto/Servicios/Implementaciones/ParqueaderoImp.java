package com.parkingcontrol.miproyecto.Servicios.Implementaciones;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parkingcontrol.miproyecto.Entidades.Parqueadero;
import com.parkingcontrol.miproyecto.Repository.ParqueaderoRepository;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.ParqueaderoInt;

@Service
public class ParqueaderoImp implements ParqueaderoInt {

    private final ParqueaderoRepository parqueaderoRepository;

    public ParqueaderoImp(ParqueaderoRepository parqueaderoRepository) {
        this.parqueaderoRepository = parqueaderoRepository;
    }

    @Override
    public List<Parqueadero> findAll() {
        return parqueaderoRepository.findAll();
    }

    @Override
    public Parqueadero findById(Integer id) {
        return parqueaderoRepository.findById(id).orElse(null);
    }

    @Override
    public Parqueadero save(Parqueadero parqueadero) {
        return parqueaderoRepository.save(parqueadero);
    }

    @Override
    public void deleteById(Integer id) {
        parqueaderoRepository.deleteById(id);
    }
}