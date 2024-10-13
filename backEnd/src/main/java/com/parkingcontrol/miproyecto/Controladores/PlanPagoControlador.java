package com.parkingcontrol.miproyecto.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkingcontrol.miproyecto.Entidades.PlanPago;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.PlanPagoInt;

@RestController
@CrossOrigin(origins = "*") // Permitir todas las solicitudes de cualquier origen
@RequestMapping("/api/planesPagos")
public class PlanPagoControlador {

    private PlanPagoInt planPagoInt;
    
    public PlanPagoControlador(PlanPagoInt planPagoInt) {
        this.planPagoInt = planPagoInt;
    }

    // Crear un nuevo plan de pago
    @PostMapping("/crear")
    public ResponseEntity<PlanPago> crearPlanPago(@RequestBody PlanPago planPago) {
        PlanPago nuevoPlan = planPagoInt.crearPlanPago(planPago);
        return new ResponseEntity<>(nuevoPlan, HttpStatus.CREATED);
    }

    // Método para encontrar plan de pago por placa
    @GetMapping("/{placa}")
    public ResponseEntity<PlanPago> findByPlaca(@PathVariable String placa) {
        Optional<PlanPago> planPago = planPagoInt.findByPlaca(placa);
        if (planPago.isPresent()) {
            return new ResponseEntity<>(planPago.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Método para buscar todos los vehiculos con planes pagos
    @GetMapping("/todos")
    public List<PlanPago> findAll() {
        return planPagoInt.findAll();
    }

    // Eliminar planes expirados
    @DeleteMapping("/eliminarExpirados")
    public ResponseEntity<Void> eliminarPlanesExpirados() {
        planPagoInt.eliminarPlanesExpirados();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}