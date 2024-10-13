package com.parkingcontrol.miproyecto.Controladores;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkingcontrol.miproyecto.Entidades.Parqueadero;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.ParqueaderoInt;

@RestController
@CrossOrigin(origins = "*") // Permitir todas las solicitudes de cualquier origen
@RequestMapping("/api/parqueadero")
public class ParqueaderoControlador {
    private final ParqueaderoInt parqueaderoInt;

    public ParqueaderoControlador(ParqueaderoInt parqueaderoInt) {
        this.parqueaderoInt = parqueaderoInt;
    }

    @GetMapping
    public List<Parqueadero> getAllParqueaderos() {
        return parqueaderoInt.findAll();
    }

    @GetMapping("/{id}")
    public Parqueadero getParqueaderoById(@PathVariable Integer id) {
        return parqueaderoInt.findById(id);
    }

    @PostMapping
    public Parqueadero createParqueadero(@RequestBody Parqueadero parqueadero) {
        return parqueaderoInt.save(parqueadero);
    }

    @DeleteMapping("/{id}")
    public void deleteParqueadero(@PathVariable Integer id) {
        parqueaderoInt.deleteById(id);
    }

}


