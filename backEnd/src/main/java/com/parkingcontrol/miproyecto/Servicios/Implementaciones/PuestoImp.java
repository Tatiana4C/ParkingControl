package com.parkingcontrol.miproyecto.Servicios.Implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.parkingcontrol.miproyecto.Entidades.Puesto;
import com.parkingcontrol.miproyecto.Entidades.TipoVehiculo;
import com.parkingcontrol.miproyecto.Repository.PuestoRepository;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.PuestoInt;

@Service
public class PuestoImp implements PuestoInt {

    private final PuestoRepository puestoRepository;

    public PuestoImp(PuestoRepository puestoRepository) {
        this.puestoRepository = puestoRepository;
    }

    @Override
    public List<Puesto> findAll() {
        return puestoRepository.findAll();
    }

    @Override
    public Puesto findById(Integer id) {
        return puestoRepository.findById(id).orElse(null);
    }

    @Override
    public Puesto save(Puesto puesto) {
        return puestoRepository.save(puesto);
    }

    @Override
    public void deleteById(Integer id) {
        puestoRepository.deleteById(id);
    }

    @Override
    public List<Puesto> findAvailableByTipoVehiculo(TipoVehiculo tipoVehiculo) {
         // Busca puestos por tipo de vehículo y que estén disponibles (estaOcupado = false)
        return puestoRepository.findByTipoVehiculoAndEstaOcupado(tipoVehiculo, false);
    }

    // Encontrar dependiendo del puesto
    @Override
    public Optional<Puesto> findByNumeroPuesto(String numeroPuesto) {
        return puestoRepository.findByNumeroPuesto(numeroPuesto);
    }

    // Puestos para TipoVehiculo
    @Override
    public List<Puesto> findByTipoVehiculo(TipoVehiculo tipoVehiculo) {
        return puestoRepository.findByTipoVehiculo(tipoVehiculo);
    }

}