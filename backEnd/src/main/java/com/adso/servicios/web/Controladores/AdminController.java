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

import com.adso.servicios.web.Entidades.Administradores;

import com.adso.servicios.web.Servicios.Interfaces.AdministradorInt;

@RestController
@RequestMapping("/api/administradores")
public class AdminController {

    private AdministradorInt servicio;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<?> listarAdmin() {
        return ResponseEntity.ok(servicio.findAll());

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarAdminById(@PathVariable Integer id) {
        Optional<Administradores> admin = Optional.empty();
        if (admin.isPresent()) {
            return ResponseEntity.ok(servicio.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> crearAdmin(@RequestBody Administradores administrador) {
        return (ResponseEntity<?>) ResponseEntity.ok(administrador);

    }

    @CrossOrigin(origins = "*")
    @PutMapping
    public ResponseEntity<?> editarAdmin(@RequestBody Administradores administrador) {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAdmin(@PathVariable Integer id) {
        Optional<Administradores> administrador = Optional.empty();
        if (administrador.isPresent()) {
            servicio.delete(id);

        }
        return ResponseEntity.ok().build();
    }
}
