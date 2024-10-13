package com.parkingcontrol.miproyecto.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import com.parkingcontrol.miproyecto.Entidades.PlanPago;

public interface PlanPagoInt {
    // Método para crar plan pago
    PlanPago crearPlanPago(PlanPago planPago);

    // Método para buscar si un vehiculo tiene plan pago
    Optional<PlanPago> findByPlaca(String placa);

    // Lista de vehiculos que tienen plan pago
    List<PlanPago> findAll();

    // Eliminar planes expirados
    void eliminarPlanesExpirados(); 
    
}
