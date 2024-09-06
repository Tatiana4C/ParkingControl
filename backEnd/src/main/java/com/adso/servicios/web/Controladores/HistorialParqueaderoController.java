package com.adso.servicios.web.Controladores;

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
import org.springframework.web.bind.annotation.RestController;

import com.adso.servicios.web.Entidades.HistorialParqueadero;
import com.adso.servicios.web.Servicios.Interfaces.HistorialParqueaderoInt;

@RestController
@RequestMapping("/historialparquadero")
public class HistorialParqueaderoController {

    private HistorialParqueaderoInt servicio;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<?> listHistoryParking() {
        return ResponseEntity.ok(servicio.findAll());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<?> listHistoryParkingById(@PathVariable Integer id) {
        Optional<HistorialParqueadero> hOptional = Optional.empty();
        if (hOptional.isPresent()) {
            return ResponseEntity.ok(hOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> addHistoryParking(@RequestBody HistorialParqueadero historial) {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @CrossOrigin(origins = "*")
    @PutMapping
    public ResponseEntity<?> editHistoryParking(@RequestBody HistorialParqueadero historialParqueadero) {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHistoryParking(@RequestBody Integer id) {
        Optional<HistorialParqueadero> hOptional = Optional.empty();
        if (hOptional.isPresent()) {
            servicio.delete(id);
        }
        return ResponseEntity.ok().build();
    }
}