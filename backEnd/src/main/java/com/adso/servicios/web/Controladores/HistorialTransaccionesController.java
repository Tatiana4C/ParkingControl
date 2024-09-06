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

import com.adso.servicios.web.Entidades.HistorialTransacciones;
import com.adso.servicios.web.Servicios.Interfaces.HistorialTransaccionesInt;

@RestController
@RequestMapping("/historialtransacciones")
public class HistorialTransaccionesController {

    private HistorialTransaccionesInt servicio;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<?> listHistoryTransaction() {
        return ResponseEntity.ok(servicio.findAll());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<?> listHistoryTransation(@PathVariable Integer id) {
        Optional<HistorialTransacciones> historial = Optional.empty();
        if (historial.isPresent()) {
            return ResponseEntity.ok(servicio.findAll());
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> addHistoryTransation(@RequestBody HistorialTransacciones historial) {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @CrossOrigin(origins = "*")
    @PutMapping
    public ResponseEntity<?> editHistoryTransation(@RequestBody HistorialTransacciones historial) {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarRegister(@RequestBody Integer id) {
        Optional<HistorialTransacciones> hOptional = Optional.empty();
        if (hOptional.isPresent()) {
            servicio.delete(id);

        }
        return ResponseEntity.ok().build();
    }
}
