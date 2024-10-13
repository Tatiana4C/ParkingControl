package com.parkingcontrol.miproyecto.Servicios.Implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.parkingcontrol.miproyecto.Entidades.Puesto;
import com.parkingcontrol.miproyecto.Entidades.Vehiculo;
import com.parkingcontrol.miproyecto.Repository.PuestoRepository;
import com.parkingcontrol.miproyecto.Repository.VehiculoRepository;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.VehiculoInt;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VehiculoImp implements VehiculoInt {

    private final VehiculoRepository vehiculoRepository;
    private final PuestoRepository puestoRepository;

    public VehiculoImp(VehiculoRepository vehiculoRepository, PuestoRepository puestoRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.puestoRepository = puestoRepository;
    }

    @Override
    public List<Vehiculo> findAll() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo findByPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa);
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public void deleteByPlaca(String placa) {
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa);
        if (vehiculo != null) {
            // Liberar el puesto asociado al vehículo
            Puesto puesto = vehiculo.getPuesto();
            if (puesto != null) {
                puesto.setEstaOcupado(false); // Marca el puesto como disponible
                puesto.setVehiculo(null); // Elimina la relación con el vehículo
                puestoRepository.save(puesto); // Actualiza el puesto
            }
            vehiculoRepository.delete(vehiculo); // Elimina el vehículo usando la entidad encontrada
        }
    }

    // Método para modificar uno o varios de los datos de un vehiculo
    @Override
    public Optional<Vehiculo> actualizarVehiculo(Integer id, Vehiculo vehiculoActualizado) {
        Optional<Vehiculo> vehiculoExistente = vehiculoRepository.findById(id);
        
        if (vehiculoExistente.isPresent()) {
            Vehiculo vehiculo = vehiculoExistente.get();
            
            // Actualizar campos del vehículo
            // Actualizar placa
            if (vehiculoActualizado.getPlaca() != null) {
                vehiculo.setPlaca(vehiculoActualizado.getPlaca());
            }
            
            // Actualizar TipoVehiculo
            if (vehiculoActualizado.getTipoVehiculo() != null) {
                vehiculo.setTipoVehiculo(vehiculoActualizado.getTipoVehiculo());
            }

            // Actualizar Color
            if (vehiculoActualizado.getColor() != null) {
                vehiculo.setColor(vehiculoActualizado.getColor());
            }

            // Actualizar Marca Vehiculo
            if (vehiculoActualizado.getMarcaVehiculo() != null) {
                vehiculo.setMarcaVehiculo(vehiculoActualizado.getMarcaVehiculo());
            }
            
            // Modificar el puesto si se proporciona uno 
            if (vehiculoActualizado.getPuesto() != null && 
            !vehiculoActualizado.getPuesto().equals(vehiculo.getPuesto())) {
                
                // Obtener el puesto actual y el nuevo puesto desde la base de datos
                Puesto puestoActual = vehiculo.getPuesto();
                Puesto nuevoPuesto = puestoRepository.findByNumeroPuesto(vehiculoActualizado.getPuesto().getNumeroPuesto())
                .orElseThrow(() -> new EntityNotFoundException("Puesto no encontrado"));
                
                // Marcar el puesto actual como no ocupado si no es null
                if (puestoActual != null) {
                    puestoActual.setEstaOcupado(false);
                    puestoRepository.save(puestoActual);
                }
                
                // Asignar el nuevo puesto al vehículo
                vehiculo.setPuesto(nuevoPuesto);
    
                // Marcar el nuevo puesto como ocupado
                nuevoPuesto.setEstaOcupado(true);
                puestoRepository.save(nuevoPuesto);
            }

            // Guardar el vehículo actualizado
            return Optional.of(vehiculoRepository.save(vehiculo));
        }
        return Optional.empty();
    }
    
}
