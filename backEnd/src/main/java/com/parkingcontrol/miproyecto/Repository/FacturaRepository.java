package com.parkingcontrol.miproyecto.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parkingcontrol.miproyecto.Entidades.Factura;
import com.parkingcontrol.miproyecto.Entidades.FormaPago;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    // Encontrar facturas por su forma de pago
    List<Factura> findByFormaPago(FormaPago formaPago);

    // Encontrar facturas por la placa del vehiculo
    List<Factura> findByPlaca(String placa);

    // A partir de aquí escribiremos los métodos para calcular estadisticas
    // Promedio de tiempo que tiene un vehiculo en el parqueadero
    @Query("SELECT AVG(f.tiempoEstacionamiento) FROM Factura f")
    Double calcularPromedioTiempoEstacionamiento();

    // Calcular ingresos 
    @Query("SELECT SUM(f.valor) FROM Factura f WHERE f.fechaFacturacion >= :inicio AND f.fechaFacturacion < :fin")
    BigDecimal calcularIngresosDiarios(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    @Query("SELECT SUM(f.valor) FROM Factura f WHERE f.fechaFacturacion >= :inicio AND f.fechaFacturacion < :fin")
    BigDecimal calcularIngresosSemanales(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
    
    @Query("SELECT SUM(f.valor) FROM Factura f WHERE f.fechaFacturacion >= :inicio AND f.fechaFacturacion < :fin")
    BigDecimal calcularIngresosMensuales(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    @Query("SELECT SUM(f.valor) FROM Factura f WHERE f.fechaFacturacion >= :inicio AND f.fechaFacturacion < :fin")
    BigDecimal calcularIngresosAnuales(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    // Método para calcular la cantidad que se pago segun la forma de pago
    @Query("SELECT COUNT(f) FROM Factura f")
    long countTotalTransacciones();
    
    @Query("SELECT SUM(f.valor) FROM Factura f WHERE f.formaPago = :formaPago")
    BigDecimal sumTotalByFormaPago(FormaPago formaPago);
    
    @Query("SELECT COUNT(f) FROM Factura f WHERE f.formaPago = :formaPago")
    long countByFormaPago(FormaPago formaPago);

    // Método calcular clientes recurrentes
    // Map me permite que me devulva mas de un dato
    @Query("SELECT COUNT(DISTINCT f.placa) AS totalClientes, " +
       "COUNT(DISTINCT CASE WHEN (SELECT COUNT(f2) FROM Factura f2 WHERE f2.placa = f.placa) > 1 THEN f.placa END) AS clientesRecurrentes " +
       "FROM Factura f")
       Map<String, Long> obtenerFrecuenciaClientesRecurrentes();


}
