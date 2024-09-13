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
import org.springframework.web.bind.annotation.RestController;

import com.adso.servicios.web.Entidades.VehiculoRegistrado;

import com.adso.servicios.web.Servicios.Interfaces.VehiculoRegistradoInt;

@RestController
@RequestMapping("/api/vehiculosRegistrados")
public class VehiculoRegistradoController {
    @Autowired
    private VehiculoRegistradoInt servicio;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<?> listCarRegister() {
        return ResponseEntity.ok(servicio.findAll());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<?> listCarRegisterById(@PathVariable(value = "id") Integer id) {
        Optional<VehiculoRegistrado> vehiculoRegistrado = servicio.findById(id);
        if (vehiculoRegistrado.isPresent()) {
            return ResponseEntity.ok(vehiculoRegistrado);
        }

        return ResponseEntity.notFound().build();

    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> addCarRegister(@RequestBody VehiculoRegistrado vehiculoRegistrado) {
        return ResponseEntity.ok(servicio.save(vehiculoRegistrado));
    }

    @CrossOrigin(origins = "*")
    @PutMapping
    public ResponseEntity<?> editCarRegister(@RequestBody VehiculoRegistrado vehiculoRegistrado) {
        return ResponseEntity.ok(servicio.save(vehiculoRegistrado));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarRegister(@PathVariable(value = "id") Integer id) {
        Optional<VehiculoRegistrado> vehiculoRegistrado = servicio.findById(id);
        if (vehiculoRegistrado.isPresent()) {
            servicio.delete(id);
            return ResponseEntity.ok(vehiculoRegistrado);

        }
        return ResponseEntity.ok().build();
    }
}