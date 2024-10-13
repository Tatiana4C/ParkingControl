package com.parkingcontrol.miproyecto.Servicios.Implementaciones;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.parkingcontrol.miproyecto.Entidades.PlanPago;
import com.parkingcontrol.miproyecto.Repository.PlanPagoRepository;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.PlanPagoInt;

@Service
public class PlanPagoImp implements PlanPagoInt{

    private PlanPagoRepository planPagoRepository;

    public PlanPagoImp(PlanPagoRepository planPagoRepository) {
        this.planPagoRepository = planPagoRepository;
    }

    @Override
    public PlanPago crearPlanPago(PlanPago planPago) {
        return planPagoRepository.save(planPago);
    }

    @Override
    public Optional<PlanPago> findByPlaca(String placa) {
        return planPagoRepository.findByPlaca(placa);
    }

    @Override
    public List<PlanPago> findAll() {
        return planPagoRepository.findAll();
    }

    @Override
    // Método para eliminar planes de vehículos cuyo plan ha expirado
    public void eliminarPlanesExpirados() {
        LocalDateTime ahora = LocalDateTime.now();
        List<PlanPago> planesExpirados = planPagoRepository.findByFechaFinBefore(ahora);
        planPagoRepository.deleteAll(planesExpirados);
    }
}

