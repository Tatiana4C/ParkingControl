package com.parkingcontrol.miproyecto.Controladores;

import java.util.List;

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

import com.parkingcontrol.miproyecto.Entidades.Administrador;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.AdministradorInt;

@RestController
@CrossOrigin(origins = "*") // Permitir todas las solicitudes de cualquier origen
@RequestMapping("/api/administradores")
public class AdmiControlador {

    private final AdministradorInt administradorInt;

    public AdmiControlador(AdministradorInt administradorInt) {
        this.administradorInt = administradorInt;
    }

    // Obtener todos los usuarios administrador
    @GetMapping("/list")
    public List<Administrador> getAllAdministradores() {
        return administradorInt.findAll();
    }

    @GetMapping("/{id}")
    public Administrador getAdministradorById(@PathVariable Integer id) {
        return administradorInt.findById(id);
    }

    // Endpoint para el login, verificar si el email y la contraseña son correctas
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Administrador administrador) {
        String email = administrador.getEmail();
        String contrasena = administrador.getContrasena();

        // Llamamos al servicio para verificar las credenciales
        boolean credencialesValidas = administradorInt.verificarCredenciales(email, contrasena);

        // Si las credenciales son correctas, retornamos un 200 OK
        if (credencialesValidas) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            // Si no son correctas, retornamos un 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    // Endpoint para verificar si el email está registrado
    @PostMapping("/olvide-contrasena")
    public ResponseEntity<String> verificarEmail(@RequestBody Administrador administrador) {
        String email = administrador.getEmail();

        boolean emailExiste = administradorInt.verificarEmail(email);

        if (emailExiste) {
            return ResponseEntity.ok("El email está registrado.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El email no está registrado.");
        }
    }


    @PostMapping("/nuevo-admi")
    public ResponseEntity<String> crearAdministrador(@RequestBody Administrador nuevoAdministrador) {
        try {
            administradorInt.crearAdministrador(nuevoAdministrador);
            return ResponseEntity.status(HttpStatus.CREATED).body("Administrador creado con éxito.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteAministrador(@PathVariable Integer id) {
        administradorInt.deleteById(id);
    }

}