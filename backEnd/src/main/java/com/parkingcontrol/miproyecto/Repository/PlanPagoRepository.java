package com.parkingcontrol.miproyecto.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkingcontrol.miproyecto.Entidades.PlanPago;
@Repository
public interface PlanPagoRepository extends JpaRepository<PlanPago, Integer> {
    // Método para buscar por placa si un vehiculo tiene un plan pago
    Optional<PlanPago> findByPlaca(String placa);
    
    // Método para encontrar los planes de pago cuya fecha de fin es antes de la fecha actual
    List<PlanPago> findByFechaFinBefore(LocalDateTime fecha);
}