package com.parkingcontrol.miproyecto.Servicios.Implementaciones;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.parkingcontrol.miproyecto.Entidades.Factura;
import com.parkingcontrol.miproyecto.Entidades.FormaPago;
import com.parkingcontrol.miproyecto.Entidades.PlanPago;
import com.parkingcontrol.miproyecto.Entidades.Puesto;
import com.parkingcontrol.miproyecto.Entidades.TipoPlan;
import com.parkingcontrol.miproyecto.Entidades.Vehiculo;
import com.parkingcontrol.miproyecto.Repository.FacturaRepository;
import com.parkingcontrol.miproyecto.Repository.PlanPagoRepository;
import com.parkingcontrol.miproyecto.Repository.PuestoRepository;
import com.parkingcontrol.miproyecto.Repository.VehiculoRepository;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.FacturaInt;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.PlanPagoInt;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.TarifaInt;


@Service
public class FacturaImp implements FacturaInt {

    private final FacturaRepository facturaRepository; // Agrega el repositorio de Factura
    private final VehiculoRepository vehiculoRepository; // Agrega el repositorio de Vehículo
    private final PlanPagoRepository planPagoRepository; // Agrega el repositorio de PlanPago
    private final PuestoRepository puestoRepository; // Agrega el repositorio de Puesto

    private final PlanPagoInt planPagoInt; // Agrega interface de PlanPago
    private final TarifaInt tarifaInt; // Agrega interface de Tarifa

    public FacturaImp(FacturaRepository facturaRepository, PlanPagoInt planPagoInt, TarifaInt tarifaInt, 
            VehiculoRepository vehiculoRepository, PlanPagoRepository planPagoRepository, PuestoRepository puestoRepository) {
        this.facturaRepository = facturaRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.planPagoRepository = planPagoRepository;
        this.puestoRepository = puestoRepository;
        this.planPagoInt = planPagoInt;
        this.tarifaInt = tarifaInt;
    }

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findById(Integer id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public void deleteById(Integer id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public List<Factura> findByFormaPago(FormaPago formaPago) {
        return facturaRepository.findByFormaPago(formaPago);
    }

    @Override
    public List<Factura> findByPlaca(String placa) {
        return facturaRepository.findByPlaca(placa);
    }

    // Método para crear factura de un vehiculo que tiene planPago
    @Override
    public Factura crearFactura(String placa) {
        // 1. Buscar el vehículo por placa
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa);
        if (vehiculo == null) {
            throw new NoSuchElementException("El vehículo con placa " + placa + " no fue encontrado.");
        }
    
        LocalDateTime horaSalida = LocalDateTime.now();
        Duration duracion = Duration.between(vehiculo.getFechaIngreso(), horaSalida);
        int duracionEnMinutos = (int) duracion.toMinutes();
    
        // 2. Verificar si el vehículo tiene un plan de pago activo
        Optional<PlanPago> planPago = planPagoInt.findByPlaca(placa);
    
        Factura factura = new Factura();
        factura.setPlaca(vehiculo.getPlaca());
        factura.setTipoVehiculo(vehiculo.getTipoVehiculo());
        factura.setFechaIngreso(vehiculo.getFechaIngreso());
        factura.setFechaSalida(horaSalida);
        factura.setFechaFacturacion(horaSalida);
        factura.setTiempoEstacionamiento(duracionEnMinutos);
    
        if (planPago.isPresent() && LocalDateTime.now().isBefore(planPago.get().getFechaFin())) {
            // Si el plan está activo
            factura.setValor(BigDecimal.ZERO);
            factura.setTipoPlan(planPago.get().getTipoPlan());
            factura.setFormaPago(null);
            facturaRepository.save(factura);

            deleteByPlaca(vehiculo.getPlaca()); // Eliminamos el vehiculo
            return factura; // Fin de la operación
        }
        else {
            // Si el plan está vencido, elimínalo
            delatePlanPagoByPlaca(placa);
            return null;
        }
    }

    // Método para eliminar vehiculo con planPago vencido de la base de datos
    public void delatePlanPagoByPlaca(String placa) {
        // Buscar el plan por la placa
        Optional<PlanPago> planPago = planPagoRepository.findByPlaca(placa);
        if (planPago.isPresent()) {
            planPagoRepository.delete(planPago.get());
        } else {
            throw new NoSuchElementException("No se encontró un plan de pago para la placa: " + placa);
        }
    }


    // Método para facturar Planes mensuales y anuales
    @Override
    public Factura crearFacturaPlanPago(Vehiculo vehiculo, TipoPlan tipoPlan) {
        // 1. Obtener la tarifa correspondiente al tipo de plan y vehículo
        BigDecimal valorTarifa = tarifaInt.obtenerTarifaPorTipoPlan(vehiculo.getTipoVehiculo(), tipoPlan);
    
        // 2. Calcular fechas de inicio y fin del plan
        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin;
        int duracionDias;
    
        if (tipoPlan.equals(TipoPlan.MENSUAL)) {
            fechaFin = fechaInicio.plusDays(30);
            duracionDias = 30;
        } else { // TipoPlan.ANUAL
            fechaFin = fechaInicio.plusDays(365);
            duracionDias = 365;
        }
    
        // 3. Crear y guardar el Plan de Pago
        PlanPago planPago = new PlanPago();
        planPago.setPlaca(vehiculo.getPlaca());
        planPago.setTipoVehiculo(vehiculo.getTipoVehiculo());
        planPago.setTipoPlan(tipoPlan);
        planPago.setFechaInicio(fechaInicio);
        planPago.setFechaFin(fechaFin);
        planPago.setDuracionDias(duracionDias);
        planPagoRepository.save(planPago);  // Guardamos el plan en la base de datos

        // 4. Crear la Factura
        Factura factura = new Factura();
        factura.setPlaca(vehiculo.getPlaca());
        factura.setTipoVehiculo(vehiculo.getTipoVehiculo());
        factura.setFechaIngreso(vehiculo.getFechaIngreso()); // Ingreso registrado previamente
        factura.setFechaSalida(null);  // No aplica para un plan de pago
        factura.setFechaFacturacion(LocalDateTime.now()); // Fecha de emisión de la factura
        factura.setTiempoEstacionamiento(0);  // No es relevante en planes de pago
        factura.setValor(valorTarifa);  // El valor es la tarifa correspondiente al plan
        factura.setTipoPlan(tipoPlan);  // Establecemos el tipo de plan en la factura
        factura.setFormaPago(null); // Puedes cambiar esto si es necesario registrar la forma de pago
        facturaRepository.save(factura);  // Guardamos la factura en la base de datos

        return factura; // Devolvemos la factura creada
    }

    @Override
    public Factura calcularTarifaPorDuracion(Vehiculo vehiculo) {
        BigDecimal tarifaFraccion = tarifaInt.obtenerTarifaPorTipoPlan(vehiculo.getTipoVehiculo(), TipoPlan.FRACCION);
        BigDecimal tarifaDiaria = tarifaInt.obtenerTarifaPorTipoPlan(vehiculo.getTipoVehiculo(), TipoPlan.DIARIO);
    
        // Verificar que las tarifas no sean negativas
        if (tarifaFraccion.compareTo(BigDecimal.ZERO) < 0 || tarifaDiaria.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Las tarifas no pueden ser negativas.");
        }

        LocalDateTime horaSalida = LocalDateTime.now();
    
        // Verificar que la fecha de ingreso no sea futura
        if (vehiculo.getFechaIngreso().isAfter(horaSalida)) {
            throw new RuntimeException("La fecha de ingreso no puede ser posterior a la fecha de salida.");
        }

        Duration duracion = Duration.between(vehiculo.getFechaIngreso(), horaSalida);
        long horas = duracion.toHours();
        int duracionEnMinutos = (int) duracion.toMinutes();

        BigDecimal total;

        if (horas < 12) {

            long horasACobrar = horas + (duracion.toMinutesPart() > 15 ? 1 : 0);
            total = tarifaFraccion.multiply(BigDecimal.valueOf(horasACobrar));
        
        } else {

            long dias = horas / 24;
            long horasRestantes = horas % 24;

            total = tarifaDiaria.multiply(BigDecimal.valueOf(dias))
            .add(tarifaFraccion.multiply(BigDecimal.valueOf(horasRestantes)));
        }
        
        System.out.println("Tarifa Fracción: " + tarifaFraccion);
        System.out.println("Tarifa Diaria: " + tarifaDiaria);
        System.out.println("Total calculado: " + total);
        
        Factura factura = new Factura();
        factura.setPlaca(vehiculo.getPlaca());
        factura.setTipoVehiculo(vehiculo.getTipoVehiculo());
        factura.setFechaIngreso(vehiculo.getFechaIngreso());
        factura.setFechaSalida(horaSalida);
        factura.setFechaFacturacion(horaSalida);
        factura.setTiempoEstacionamiento(duracionEnMinutos);
        factura.setValor(total);
        factura.setTipoPlan(TipoPlan.DURACION);
        factura.setFormaPago(null);
        facturaRepository.save(factura);

        deleteByPlaca(vehiculo.getPlaca());
        return factura;

    }

    // Método para cambiar la forma de pago
    @Override
    public Factura cambiarFormaPago(Integer id, FormaPago nuevaFormaPago) {
        // Buscar la factura en el repositorio usando el ID de la factura
        Optional<Factura> facturaOptional = facturaRepository.findById(id);
    
        if (!facturaOptional.isPresent()) {
            throw new RuntimeException("Factura con ID " + id + "no encontrada.");
        }

        Factura factura = facturaOptional.get();

        // Actualizar la forma de pago de la factura
        factura.setFormaPago(nuevaFormaPago);

        // Guardar la factura actualizada en el repositorio
        facturaRepository.save(factura);

        return factura;
    }

    // Método para eliminar el vehículo
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
            vehiculoRepository.delete(vehiculo); // Elimina el vehículo
        }
    }

    // Métodos para obtener los ingresos
    @Override
    public BigDecimal obtenerIngresosDiarios() {
        LocalDateTime inicio = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime fin = inicio.plusDays(1);
        return facturaRepository.calcularIngresosDiarios(inicio, fin);
    }
    
    @Override
    public BigDecimal obtenerIngresosSemanales() {
        LocalDateTime inicio = LocalDateTime.now().minusDays(7).with(LocalTime.MIN);
        LocalDateTime fin = LocalDateTime.now().plusDays(1).toLocalDate().atStartOfDay();
        return facturaRepository.calcularIngresosSemanales(inicio, fin);
    }

    @Override
    public BigDecimal obtenerIngresosMensuales() {
        LocalDateTime inicio = LocalDateTime.now().withDayOfMonth(1).with(LocalTime.MIN);
        LocalDateTime fin = LocalDateTime.now().plusMonths(1).withDayOfMonth(1).with(LocalTime.MIN);
        return facturaRepository.calcularIngresosMensuales(inicio, fin);
    }

    @Override
    public BigDecimal obtenerIngresosAnuales() {
        LocalDateTime inicio = LocalDateTime.now().withDayOfYear(1).with(LocalTime.MIN);
        LocalDateTime fin = LocalDateTime.now().plusYears(1).withDayOfYear(1).with(LocalTime.MIN);
        return facturaRepository.calcularIngresosAnuales(inicio, fin);
    }

    // Métodos para obtener ingresos por forma de pago
    @Override
    public long obtenerTotalTransacciones() {
        return facturaRepository.countTotalTransacciones();
    }

    @Override
    public BigDecimal obtenerIngresosPorFormaPago(FormaPago formaPago) {
        return facturaRepository.sumTotalByFormaPago(formaPago);
    }

    @Override
    public long obtenerTotalTransaccionesPorFormaPago(FormaPago formaPago) {
        // Consulta el total de transacciones por tipo de pago
        return facturaRepository.countByFormaPago(formaPago);
    }

    // Método para ver la recurrencia de vehiculos
    public double obtenerPorcentajeClientesRecurrentes() {
        Map<String, Long> resultado = facturaRepository.obtenerFrecuenciaClientesRecurrentes();
        long totalClientes = resultado.get("totalClientes");
        long clientesRecurrentes = resultado.get("clientesRecurrentes");
        
        if (totalClientes == 0) {
            return 0;
        }
        
        return (double) clientesRecurrentes / totalClientes * 100;
    }

}