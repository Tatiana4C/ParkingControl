package com.parkingcontrol.miproyecto.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import com.parkingcontrol.miproyecto.Entidades.Vehiculo;

public interface VehiculoInt {
    // Método para obtener todos los vehículos
    List<Vehiculo> findAll();

    // Método para buscar un vehículo por su placa
    Vehiculo findByPlaca(String placa);  
    
    // Método para guardar un nuevo vehículo
    Vehiculo save(Vehiculo vehiculo); 
    
    // Método para eliminar un vehículo por placa
    void deleteByPlaca(String placa); 

    // Método para modificar un vehiculo
    public Optional<Vehiculo> actualizarVehiculo(Integer id, Vehiculo vehiculo);
}

