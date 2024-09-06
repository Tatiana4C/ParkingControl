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

import com.adso.servicios.web.Entidades.Factura;

import com.adso.servicios.web.Entidades.Vehiculo;
import com.adso.servicios.web.Servicios.Interfaces.FacturaInt;

@RestController
@RequestMapping("/factura")
public class FacturaController {
    private FacturaInt servicio;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<?> listarFactura() {
        return ResponseEntity.ok(servicio.findAll());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarFacturaById(@PathVariable Integer id) {
        Optional<Vehiculo> car = Optional.empty();
        if (car.isPresent()) {
            return ResponseEntity.ok(servicio.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> crearFactura(@RequestBody Factura factura) {
        return (ResponseEntity<?>) ResponseEntity.ok(factura);

    }

    @CrossOrigin(origins = "*")
    @PutMapping
    public ResponseEntity<?> editarFactura(@RequestBody Factura factura) {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarFactura(@PathVariable Integer id) {
        Optional<Factura> factura = Optional.empty();
        if (factura.isPresent()) {
            servicio.delete(factura.get());

        }
        return ResponseEntity.ok().build();
    }
}
