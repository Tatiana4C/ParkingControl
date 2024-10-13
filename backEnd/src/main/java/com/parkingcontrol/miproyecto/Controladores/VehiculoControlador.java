package com.parkingcontrol.miproyecto.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
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

import com.parkingcontrol.miproyecto.Entidades.Puesto;
import com.parkingcontrol.miproyecto.Entidades.Vehiculo;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.VehiculoInt;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.PuestoInt;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/vehiculos")
public class VehiculoControlador {

    private final VehiculoInt vehiculoInt; // Servicio de Vehiculos
    private final PuestoInt puestoInt;

    // Inyección de dependencias a través del constructor
    public VehiculoControlador(VehiculoInt vehiculoInt, PuestoInt puestoInt) {
        this.vehiculoInt = vehiculoInt;
        this.puestoInt = puestoInt;
    }

    // Obtener todos los vehículos
    @GetMapping("/list")
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoInt.findAll();
        return ResponseEntity.ok(vehiculos);
    }

    // Obtener un vehículo por placa
    @GetMapping("/{placa}")
    public ResponseEntity<Vehiculo> getVehiculoByPlaca(@PathVariable String placa) {
        Vehiculo vehiculo = vehiculoInt.findByPlaca(placa);
        if (vehiculo != null) {
            return ResponseEntity.ok(vehiculo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo vehículo y asignarle un puesto
    @PostMapping("/guardarVehiculo")
    public ResponseEntity<?> guardarVehiculo(@RequestBody Vehiculo vehiculo) {
        // Imprimir el número de puesto que se está buscando
        System.out.println("Buscando puesto con número: " + vehiculo.getPuesto().getNumeroPuesto());
        
        // Buscar el puesto en la base de datos
        Optional<Puesto> optionalPuesto = puestoInt.findByNumeroPuesto(vehiculo.getPuesto().getNumeroPuesto());
    
        // Verificar si el puesto está presente
        if (!optionalPuesto.isPresent()) {
            System.out.println("No se encontró el puesto con número: " + vehiculo.getPuesto().getNumeroPuesto());
            return ResponseEntity.badRequest().body("El puesto no existe.");
        }
        
        // Asociar el puesto al vehículo
        Puesto puesto = optionalPuesto.get();
        vehiculo.setPuesto(puesto);

        // Cambiar el estado del puesto a ocupado
        puesto.setEstaOcupado(true);
        vehiculo.setPuesto(puesto);

        // Guardar el vehículo en la base de datos
        vehiculoInt.save(vehiculo);
        return ResponseEntity.ok("Vehículo registrado exitosamente.");
    }

    // Eliminar un vehículo por placa
    @DeleteMapping("eliminar/{placa}")
    public ResponseEntity<Void> deleteVehiculoByPlaca(@PathVariable String placa) {
        vehiculoInt.deleteByPlaca(placa);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cambiar/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Integer id, @RequestBody Vehiculo vehiculo) {
        return vehiculoInt.actualizarVehiculo(id, vehiculo)
        .map(updatedVehiculo -> new ResponseEntity<>(updatedVehiculo, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
