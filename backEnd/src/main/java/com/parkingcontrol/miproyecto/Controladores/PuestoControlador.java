package com.parkingcontrol.miproyecto.Controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parkingcontrol.miproyecto.Entidades.Puesto;
import com.parkingcontrol.miproyecto.Entidades.TipoVehiculo;
import com.parkingcontrol.miproyecto.Entidades.Vehiculo;
import com.parkingcontrol.miproyecto.Repository.PuestoRepository;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.PuestoInt;

@RestController
@CrossOrigin(origins = "*") // Permitir todas las solicitudes de cualquier origen
@RequestMapping("/api/puestos")
public class PuestoControlador {
    private final PuestoInt puestoInt; // Interfaz de servicio
    private final PuestoRepository puestoRepository; // Repositorio

    // Constructor para inyectar las dependencias
    public PuestoControlador(PuestoInt puestoInt, PuestoRepository puestoRepository) {
        this.puestoInt = puestoInt;
        this.puestoRepository = puestoRepository;
    }

    // Obtener todos los puestos
    @GetMapping
    public ResponseEntity<List<Puesto>> getAllPuestos() {
        List<Puesto> puestos = puestoInt.findAll();
        return ResponseEntity.ok(puestos);
    }

    // Obtener puestos disponibles por tipo de vehículo
    @GetMapping("/disponibles/{tipoVehiculo}")
    public ResponseEntity<List<Puesto>> getAvailablePuestos(@PathVariable TipoVehiculo tipoVehiculo) {
        List<Puesto> puestosDisponibles = puestoInt.findAvailableByTipoVehiculo(tipoVehiculo);
        if (!puestosDisponibles.isEmpty()) {
            return ResponseEntity.ok(puestosDisponibles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar puesto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Puesto> getPuestoById(@PathVariable Integer id) {
        Puesto puesto = puestoInt.findById(id);
        if (puesto != null) {
            return ResponseEntity.ok(puesto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/numeroPuesto/{numeroPuesto}")
    public ResponseEntity<Puesto> getPuestoByNumeroPuesto(@PathVariable String numeroPuesto) {
        // Busca el puesto por el número
        
        Optional<Puesto> puestoOpt = puestoInt.findByNumeroPuesto(numeroPuesto);
    
        // Verifica si el puesto está presente
        if (puestoOpt.isPresent()) {
            Puesto puesto = puestoOpt.get(); // Obtiene el puesto del Optional
            return ResponseEntity.ok(puesto); // Devuelve el puesto
        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 si no se encuentra
        }
    }


    // Crear o actualizar un puesto
    @PostMapping
    public ResponseEntity<Puesto> savePuesto(@RequestBody Puesto puesto) {
        Puesto nuevoPuesto = puestoInt.save(puesto);
        return ResponseEntity.status(201).body(nuevoPuesto);
    }

    // Eliminar un puesto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePuestoById(@PathVariable Integer id) {
        puestoInt.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener los datos del vehículo asociado al puesto
    @GetMapping("/numeroPuesto/vehiculo")
    public ResponseEntity<Vehiculo> obtenerVehiculoPorPuesto(@PathVariable String numeroPuesto) {
        Optional<Puesto> puesto = puestoInt.findByNumeroPuesto(numeroPuesto);
        
        // Si el puesto está ocupado, devolver los datos del vehículo
        if (puesto.isPresent() && puesto.get().isEstaOcupado()) {
            Vehiculo vehiculo = puesto.get().getVehiculo();
            return ResponseEntity.ok(vehiculo);
        }
        
        // Si el puesto no está ocupado o no existe
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/ocuparPuesto")
    public ResponseEntity<String> ocuparPuesto(@RequestParam String numeroPuesto) {
        // Buscar el puesto por el número de puesto
        Optional<Puesto> puestoOpt = puestoInt.findByNumeroPuesto(numeroPuesto);

        // Verificar si el puesto existe y si no está ocupado
        if (puestoOpt.isPresent()) {
            Puesto puesto = puestoOpt.get(); // Obtener el puesto del Optional
        
            if (!puesto.isEstaOcupado()) {
                // Marcar el puesto como ocupado
                puesto.setEstaOcupado(true);
                puestoInt.save(puesto);
                return ResponseEntity.ok("Puesto marcado como ocupado.");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El puesto ya está ocupado.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Puesto no encontrado.");
        }
    }

    @GetMapping("/puestos/{tipoVehiculo}")
    public List<Puesto> getPuestos(@PathVariable TipoVehiculo tipoVehiculo) {
        return puestoInt.findByTipoVehiculo(tipoVehiculo);
    }

    // A partir de aquí escribiremos los métodos para calcular 
    // el % de ocupación el parquadero para la pagina estadisticas

    @GetMapping("/ocupacion")
    public ResponseEntity<Map<String, Object>> obtenerOcupacionParqueadero() {
        Map<String, Object> estadisticas = new HashMap<>();

        long totalPuestos = puestoRepository.count();
        long puestosOcupados = puestoRepository.contarPuestosOcupados();
        long puestosOcupadosMotos = puestoRepository.contarPuestosOcupadosPorMotos();
        long puestosOcupadosCarros = puestoRepository.contarPuestosOcupadosPorCarros();

        // Calcular porcentajes
        double porcentajeOcupacion = (double) puestosOcupados / totalPuestos * 100;
        double porcentajeMotos = (double) puestosOcupadosMotos / totalPuestos * 100;
        double porcentajeCarros = (double) puestosOcupadosCarros / totalPuestos * 100;

        // Agregar datos al mapa de estadísticas
        estadisticas.put("totalPuestos", totalPuestos);   
        estadisticas.put("puestosOcupados", puestosOcupados);
        estadisticas.put("porcentajeOcupacion", porcentajeOcupacion);
        estadisticas.put("porcentajeMotos", porcentajeMotos);
        estadisticas.put("porcentajeCarros", porcentajeCarros);
        
        return ResponseEntity.ok(estadisticas);
    }   
}
