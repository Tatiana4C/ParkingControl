package com.parkingcontrol.miproyecto.Controladores;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parkingcontrol.miproyecto.Entidades.Tarifa;
import com.parkingcontrol.miproyecto.Entidades.TipoPlan;
import com.parkingcontrol.miproyecto.Entidades.TipoVehiculo;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.TarifaInt;

@RestController
@CrossOrigin(origins = "*") // Permitir todas las solicitudes de cualquier origen
@RequestMapping("/api/tarifas")
public class TarifaControlador {

    private final TarifaInt tarifaInt;

    // Constructor para inyectar el servicio
    public TarifaControlador(TarifaInt tarifaInt) {
        this.tarifaInt = tarifaInt;
    }

    // Obtener todas las tarifas
    @GetMapping("/tarifa")
    public ResponseEntity<List<Tarifa>> getAllTarifas() {
        List<Tarifa> tarifas = tarifaInt.findAll();
        return ResponseEntity.ok(tarifas);
    }

    // Obtener una tarifa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarifa> getTarifaById(@PathVariable Integer id) {
        Optional<Tarifa> tarifa = tarifaInt.findById(id);
        return tarifa.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener una tarifa por tipo de vehículo
    @GetMapping("/tipo/{tipoVehiculo}")
    public ResponseEntity<Tarifa> getTarifaByTipoVehiculo(@RequestParam TipoVehiculo tipoVehiculo) {
        Optional<Tarifa> tarifa = tarifaInt.findByTipoVehiculo(tipoVehiculo);
        return tarifa.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());

    }

    // Crear una nueva tarifa
    @PostMapping("/nueva")
    public ResponseEntity<Tarifa> createTarifa(@RequestBody Tarifa tarifa) {
        Tarifa nuevaTarifa = tarifaInt.save(tarifa);
        return ResponseEntity.ok(nuevaTarifa);
    }

    // Actualizar una tarifa existente
    @PutMapping("/{id}")
    public ResponseEntity<Tarifa> updateTarifa(@PathVariable Integer id, @RequestBody Tarifa tarifa) {
        // Verifica si la tarifa con el ID existe antes de actualizar
        if (tarifaInt.findById(id).isPresent()) {
            tarifa.setIdTarifa(id); // Asegúrate de que el ID se mantenga
            Tarifa tarifaActualizada = tarifaInt.save(tarifa);
            return ResponseEntity.ok(tarifaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una tarifa por ID
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> deleteTarifaById(@PathVariable Integer id) {
        tarifaInt.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Tarifa por Tipo de vehiculo y Tipo de plan
    @GetMapping("/tipo/{tipoVehiculo}/{tipoPlan}")
    public ResponseEntity<BigDecimal> getTarifaByTipoVehiculoYTipoPlan(@PathVariable TipoVehiculo tipoVehiculo, @PathVariable TipoPlan tipoPlan) {
        BigDecimal tarifa = tarifaInt.obtenerTarifaPorTipoPlan(tipoVehiculo, tipoPlan);
        return ResponseEntity.ok(tarifa);
    }

    // Método para actualizar una tarifa
    @PutMapping("/tarifa/{tipoVehiculo}")
    public ResponseEntity<Tarifa> actualizarTarifa(@PathVariable TipoVehiculo tipoVehiculo, @RequestBody Tarifa nuevaTarifa) {
        Tarifa tarifaActualizada = tarifaInt.actualizarTarifa(tipoVehiculo, nuevaTarifa);
        
        if (tarifaActualizada != null) {
            return ResponseEntity.ok(tarifaActualizada); // Retorna la tarifa actualizada
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
        }
    }
}
