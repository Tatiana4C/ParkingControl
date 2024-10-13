package com.parkingcontrol.miproyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkingcontrol.miproyecto.Entidades.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer>{
    // Este método buscará un Vehiculo por su placa
    Vehiculo findByPlaca(String placa);
}

