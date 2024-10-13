package com.parkingcontrol.miproyecto.Servicios.Implementaciones;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.parkingcontrol.miproyecto.Entidades.Tarifa;
import com.parkingcontrol.miproyecto.Entidades.TipoPlan;
import com.parkingcontrol.miproyecto.Entidades.TipoVehiculo;
import com.parkingcontrol.miproyecto.Repository.TarifaRepository;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.TarifaInt;

@Service
public class TarifaImp implements TarifaInt {

    private final TarifaRepository tarifaRepository;

    public TarifaImp(TarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }

    @Override
    public List<Tarifa> findAll() {
        return tarifaRepository.findAll();
    }

    @Override
    public Optional<Tarifa> findById(Integer id) {
        return tarifaRepository.findById(id);
    }

    @Override
    public Optional<Tarifa> findByTipoVehiculo(TipoVehiculo tipoVehiculo) {
        return tarifaRepository.findByTipoVehiculo(tipoVehiculo);
    }

    @Override
    public Tarifa save(Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    @Override
    public void deleteById(Integer id) {
        tarifaRepository.deleteById(id);
    }

    /**
     * Obtener la tarifa correspondiente según el tipo de vehículo y plan.
     *
     * @param tipoVehiculo El tipo de vehículo.
     * @param tipoPlan     El tipo de plan.
     * @return La tarifa correspondiente al tipo de plan.
     */
    public BigDecimal obtenerTarifaPorTipoPlan(TipoVehiculo tipoVehiculo, TipoPlan tipoPlan) {
        // Centralizamos la lógica de búsqueda de tarifa
        Tarifa tarifa = findTarifaByTipoVehiculo(tipoVehiculo);

        // Determinar la tarifa correcta según el tipo de plan
        switch (tipoPlan) {
            case MENSUAL:
                return tarifa.getTarifaMes();
            case ANUAL:
                return tarifa.getTarifaAnual();
            case DIARIO:
                return tarifa.getTarifaDia();
            case FRACCION:
                return tarifa.getTarifaFraccion();
            default:
                throw new IllegalArgumentException("Tipo de plan no válido: " + tipoPlan);
        }
    }

    /**
     * Modificar la tarifa de acuerdo con el tipo de vehículo y plan.
     *
     * @param tipoVehiculo El tipo de vehículo.
     * @param tipoPlan     El tipo de plan.
     * @param nuevaTarifa  El nuevo valor de la tarifa.
     * @return La tarifa actualizada.
     */
    public Tarifa modificarTarifa(TipoVehiculo tipoVehiculo, TipoPlan tipoPlan, BigDecimal nuevaTarifa) {
        Tarifa tarifa = findTarifaByTipoVehiculo(tipoVehiculo);

        // Modificar la tarifa según el tipo de plan
        switch (tipoPlan) {
            case MENSUAL:
                tarifa.setTarifaMes(nuevaTarifa);
                break;
            case ANUAL:
                tarifa.setTarifaAnual(nuevaTarifa);
                break;
            case DIARIO:
                tarifa.setTarifaDia(nuevaTarifa);
                break;
            case FRACCION:
                tarifa.setTarifaFraccion(nuevaTarifa);
                break;
            default:
                throw new IllegalArgumentException("Tipo de plan no válido: " + tipoPlan);
        }

        // Guardar los cambios en la base de datos
        return tarifaRepository.save(tarifa);
    }

    // Método privado para centralizar la búsqueda de tarifas
    private Tarifa findTarifaByTipoVehiculo(TipoVehiculo tipoVehiculo) {
        return tarifaRepository.findByTipoVehiculo(tipoVehiculo)
        .orElseThrow(() -> new RuntimeException("Tarifa no encontrada para el tipo de vehículo: " + tipoVehiculo));
    }

    //Método para modificar tarifas
    @Override
    public Tarifa actualizarTarifa(TipoVehiculo tipoVehiculo, Tarifa nuevaTarifa) {
        Optional<Tarifa> tarifaExistente = tarifaRepository.findByTipoVehiculo(tipoVehiculo);
        
        if (tarifaExistente.isPresent()) {
            Tarifa tarifa = tarifaExistente.get();
            // Solo actualiza los campos si no son null en nuevaTarifa
            if (nuevaTarifa.getTarifaFraccion() != null) {
                tarifa.setTarifaFraccion(nuevaTarifa.getTarifaFraccion());
            }
            
            if (nuevaTarifa.getTarifaDia() != null) {
                tarifa.setTarifaDia(nuevaTarifa.getTarifaDia());
            }
        
            if (nuevaTarifa.getTarifaMes() != null) {    
                tarifa.setTarifaMes(nuevaTarifa.getTarifaMes());
            }
        
            if (nuevaTarifa.getTarifaAnual() != null) {
                tarifa.setTarifaAnual(nuevaTarifa.getTarifaAnual());
            }
            
            // Guarda los cambios en la base de datos
            return tarifaRepository.save(tarifa);
        }
        
        return null; // Retorna null si no se encontró la tarifa
    }

}
