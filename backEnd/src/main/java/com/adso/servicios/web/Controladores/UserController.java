package com.adso.servicios.web.Controladores;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adso.servicios.web.Entidades.UserLogin;
import com.adso.servicios.web.Servicios.Interfaces.UserInt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
// @RequestMapping("/api/usuario")
@RequestMapping("/usuario")
public class UserController {

    private UserInt servicio;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<?> listarUser() {
        return ResponseEntity.ok(servicio.finAll());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarUserById(@PathVariable Integer id) {
        Optional<UserLogin> userLogin = servicio.findById(id);
        if (userLogin.isPresent()) {
            return ResponseEntity.ok(servicio.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> crearUser(@RequestBody UserLogin userLogin) {
        return ResponseEntity.ok(servicio.save(userLogin));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        Optional<UserLogin> userLogin = servicio.findById(id);
        if (userLogin.isPresent()) {
            servicio.delete(id);
            return ResponseEntity.ok(userLogin);
        }
        return ResponseEntity.notFound().build();
    }

}