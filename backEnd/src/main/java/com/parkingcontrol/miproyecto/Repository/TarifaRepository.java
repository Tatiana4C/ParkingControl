package com.parkingcontrol.miproyecto.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkingcontrol.miproyecto.Entidades.Tarifa;
import com.parkingcontrol.miproyecto.Entidades.TipoVehiculo;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {

    Optional<Tarifa> findByTipoVehiculo(TipoVehiculo tipoVehiculo);

}

