package com.parkingcontrol.miproyecto.Servicios.Interfaces;

import java.util.List;

import com.parkingcontrol.miproyecto.Entidades.Parqueadero;


public interface ParqueaderoInt {

    List<Parqueadero> findAll();
    Parqueadero findById(Integer id);
    Parqueadero save(Parqueadero parqueadero);
    void deleteById(Integer id);
}