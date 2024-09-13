package com.adso.servicios.web.Controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.adso.servicios.web.Entidades.Vehiculo;
import com.adso.servicios.web.Servicios.Interfaces.VehiculoInt;

@RestController
@RequestMapping("/api/vehiculosestacionados")
public class VehiculoController {

    @Autowired
    private VehiculoInt servicio;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<?> listCarParked() {
        return ResponseEntity.ok(servicio.findAll());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<?> listCarParkedById(@PathVariable(value = "id") Integer id) {
        Optional<Vehiculo> vehiculo = servicio.findById(id);
        if (vehiculo.isPresent()) {
            return ResponseEntity.ok(vehiculo);
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> addCarParked(@RequestBody Vehiculo vehiculo) {
        return ResponseEntity.ok(servicio.save(vehiculo));
    }

    @CrossOrigin(origins = "*")
    @PutMapping
    public ResponseEntity<?> editCarParked(@RequestBody Vehiculo vehiculo) {
        return ResponseEntity.ok(servicio.save(vehiculo));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarParked(@PathVariable(value = "id") Integer id) {
        Optional<Vehiculo> vehiculo = servicio.findById(id);
        if (vehiculo.isPresent()) {
            servicio.delete(id);
            return ResponseEntity.ok(vehiculo);
        }
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarVehiculoPorPlaca(@RequestParam String placa) {
        Optional<Vehiculo> vehiculo = servicio.findByPlaca(placa);
        if (vehiculo.isPresent()) {
            return ResponseEntity.ok(vehiculo);
        }
        return ResponseEntity.notFound().build();
    }
}