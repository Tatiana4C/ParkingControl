package com.parkingcontrol.miproyecto.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import com.parkingcontrol.miproyecto.Entidades.Puesto;
import com.parkingcontrol.miproyecto.Entidades.TipoVehiculo;

public interface PuestoInt {
    // Obtener todos los puestos
    List<Puesto> findAll(); 

    // Buscar un puesto por su ID
    Puesto findById(Integer id); 

    // Guardar o actualizar un puesto
    Puesto save(Puesto puesto); 

    // Eliminar un puesto por ID
    void deleteById(Integer id);

    // Obtener puestos disponibles por tipo de vehículo
    List<Puesto> findAvailableByTipoVehiculo(TipoVehiculo tipoVehiculo); 

    // Método derivado para encontrar un puesto por su número
    Optional<Puesto> findByNumeroPuesto(String numeroPuesto); 

    // Puestos segun el vehiculo
    List<Puesto> findByTipoVehiculo(TipoVehiculo tipoVehiculo);
}
