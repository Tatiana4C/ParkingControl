$(document).ready(function () {
    // Cacular el porcentaje de ocupación del parqueadero
    $.ajax({
        url: "http://localhost:8081/api/puestos/ocupacion",
        method: "GET",
        success: function(data) {
            // Actualiza los elementos HTML con los datos obtenidos
            $('#ocupacion-motos').text(`${data.porcentajeMotos.toFixed(2)}%`);
            $('#ocupacion-carros').text(`${data.porcentajeCarros.toFixed(2)}%`);
            $('#porcentajeOcupacion').text(`${data.porcentajeOcupacion.toFixed(2)}%`);
            $('#totalPuestos').text(data.totalPuestos);
            $('#puestosOcupados').text(data.puestosOcupados);
        },
        error: function(err) {
            console.error("Error al obtener ocupación del parqueadero:", err);
        }
    });

    // El promedio de tiempo que permanece un vehículo en el parqueadero
    $.ajax({
        url: "http://localhost:8081/api/facturas/promedio-tiempo",
        method: "GET",
        success: function(data) {
            // Convertir minutos a horas y minutos
            const tiempoPromedioFormateado = convertirMinutosAHoras(data);
            $('#promedio-tiempo').text(tiempoPromedioFormateado);
        },
        error: function(err) {
            console.error("Error al obtener promedio de tiempo:", err);
        }
    });

    function convertirMinutosAHoras(minutos) {
        const horas = Math.floor(minutos / 60); // Dividir por 60 para obtener horas
        const minutosRestantes = Math.round(minutos % 60); // Obtener el resto de los minutos
        return `${horas} horas y ${minutosRestantes} minutos`;
    }

    // Calcular ingresos
    $.ajax({
        url: "http://localhost:8081/api/facturas/ingresos",
        method: "GET",
        success: function(data) {
            $('#ingresos-diarios').text(`$${data.diarios}`);
            $('#ingresos-semanales').text(`$${data.semanales}`);
            $('#ingresos-mensuales').text(`$${data.mensuales}`);
            $('#ingresos-anuales').text(`$${data.anuales}`);
        },
        error: function(err) {
            console.error("Error al obtener ingresos:", err);
        }
    });

    // Calcular ingresos por tipos de pago
    $.ajax({
        url: "http://localhost:8081/api/facturas/ingresos-tipo-pago",
        method: "GET",
        success: function(data) {
            // Mostrar los porcentajes de transacciones
            $('#transacciones-efectivo span:first').text(`${data.porcentajeEfectivo.toFixed(2)}%`);
            $('#transacciones-tarjeta span:first').text(`${data.porcentajeTarjeta.toFixed(2)}%`);

            // Mostrar los montos totales de las transacciones
            $('#transacciones-efectivo .monto').text(`Monto total: $${data.ingresosEfectivo.toFixed(0)}`);
            $('#transacciones-tarjeta .monto').text(`Monto total: $${data.ingresosTarjeta.toFixed(0)}`);
        },
        error: function(err) {
            console.error("Error al obtener transacciones por tipo de pago:", err);
        }
    });

    // Clientes recurrentes
    $(document).ready(function () {
        // Frecuencia de clientes recurrentes
        $.ajax({
            url: "http://localhost:8081/api/facturas/frecuencia-clientes-recurrentes",
            method: "GET",
            success: function(data) {
                $('#clientes-recurrentes').text(`${data.toFixed(2)}%`);
            },
            error: function(err) {
                console.error("Error al obtener frecuencia de clientes recurrentes:", err);
            }
        });
    });
    
});

