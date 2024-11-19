package com.parkingcontrol.miproyecto.Servicios.Implementaciones;

import java.util.List;
import java.util.NoSuchElementException;
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
    public void deletePlanPagoByPlaca(String placa) {
        Optional<PlanPago> planPago = planPagoRepository.findByPlaca(placa);
        if (planPago.isPresent()) {
            // Elimina el plan de pago si existe
            planPagoRepository.delete(planPago.get());
        } else {
            throw new NoSuchElementException("No se encontró un plan de pago para la placa: " + placa);
        }
    }

}

