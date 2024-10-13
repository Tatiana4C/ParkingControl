package com.parkingcontrol.miproyecto.Servicios.Interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.parkingcontrol.miproyecto.Entidades.Tarifa;
import com.parkingcontrol.miproyecto.Entidades.TipoPlan;
import com.parkingcontrol.miproyecto.Entidades.TipoVehiculo;

public interface TarifaInt {

    // Obtener todas las tarifas
    List<Tarifa> findAll();

    // Obtener una tarifa por su ID
    Optional<Tarifa> findById(Integer id);

    // Obtener una tarifa por el tipo de vehículo
    Optional<Tarifa> findByTipoVehiculo(TipoVehiculo tipoVehiculo);

    // Guardar una nueva tarifa o actualizar una existente
    Tarifa save(Tarifa tarifa);

    // Eliminar una tarifa por su ID
    void deleteById(Integer id);

    // Obtener una tarifa por el tipo de vehículo y el tipo de plan
    public BigDecimal obtenerTarifaPorTipoPlan(TipoVehiculo tipoVehiculo, TipoPlan tipoPlan);

    // Método para actualizar una tarifa
    public Tarifa actualizarTarifa(TipoVehiculo tipoVehiculo, Tarifa nuevaTarifa); 

}
