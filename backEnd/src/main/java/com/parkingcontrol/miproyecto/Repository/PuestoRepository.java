package com.parkingcontrol.miproyecto.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parkingcontrol.miproyecto.Entidades.Puesto;
import com.parkingcontrol.miproyecto.Entidades.TipoVehiculo;

@Repository
public interface PuestoRepository extends JpaRepository<Puesto, Integer> {
    // Método para buscar puestos por tipo de vehículo y disponibilidad
    List<Puesto> findByTipoVehiculoAndEstaOcupado(TipoVehiculo tipoVehiculo, boolean estaOcupado); 

    // Método para buscar un puesto por número de puesto
    Optional<Puesto> findByNumeroPuesto(String numeroPuesto);

    // Todos los puesto segun el tipo de vehiculo
    List<Puesto> findByTipoVehiculo(TipoVehiculo tipoVehiculo);

    // A partir de aquí escribiremos los métodos para calcular 
    // el % de ocupación el parquadero para la pagina estadisticas

    // Método para contar todos los puestos
    long count();

    // @Query me permite escribir consultas personalizadas.

    // Método para contar puestos ocupados por tipo de vehículo
    @Query("SELECT COUNT(p) FROM Puesto p WHERE p.estaOcupado = true AND p.tipoVehiculo = 'MOTO'")
    long contarPuestosOcupadosPorMotos();

    @Query("SELECT COUNT(p) FROM Puesto p WHERE p.estaOcupado = true AND p.tipoVehiculo = 'CARRO'")
    long contarPuestosOcupadosPorCarros();

    @Query("SELECT COUNT(p) FROM Puesto p WHERE p.estaOcupado = true")
    long contarPuestosOcupados();
}
