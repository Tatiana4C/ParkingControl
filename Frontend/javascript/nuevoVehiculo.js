$(document).ready(function() {
    // Mostrar la hora actual en el campo "Hora de Ingreso" ajustada a la zona horaria local
    var now = new Date();
    var offset = now.getTimezoneOffset() * 60000; // Diferencia en milisegundos
    var localISOTime = new Date(now - offset).toISOString().slice(0, 19); // Ajustar a la zona horaria local
    $('#fechaIngreso').text(localISOTime); // Mostrar la hora ajustada en el formulario

    // Función para actualizar opciones de marcaVehiculo según el tipo de vehículo
    function updateMarcaVehiculoOptions(tipoVehiculo) {
        var $marcaVehiculoSelect = $('#marcaVehiculo');
        $marcaVehiculoSelect.empty(); // Limpiar opciones actuales
        var options = [];

        if (tipoVehiculo === 'CARRO') {
            options = [
                'Audi', 'BMW', 'Chevrolet', 'Chrysler', 'Citroën', 'Fiat', 'Ford', 
                'Hyundai', 'Kia', 'Land Rover', 'Mazda', 'Mercedes-Benz', 'Nissan', 
                'Peugeot', 'Renault', 'Subaru', 'Suzuki', 'Toyota', 'Volkswagen', 'Volvo', 'Otro'
            ];
        } else if (tipoVehiculo === 'MOTO') {
            options = [
                'Honda', 'Yamaha', 'Suzuki', 'Kawasaki', 'Bajaj', 'Ducati', 'BMW Motorrad', 
                'KTM', 'Harley-Davidson', 'Royal Enfield', 'Piaggio', 'Vespa', 'Aprilia', 'Otro'
            ];
        }

        // Añadir opciones al select
        $marcaVehiculoSelect.append('<option value="">Seleccione</option>');
        options.forEach(function(marcaVehiculo) {
            $marcaVehiculoSelect.append('<option value="' + marcaVehiculo.toLowerCase().replace(/\s+/g, '-') + '">' + marcaVehiculo + '</option>');
        });
    }

    // Me busca los puestos disponibles dependiendo del tipo de vehiculo, aquí obtenermos numeroPuesto
    function updatePuestoOptions(tipoVehiculo) {
        $.ajax({
            url: 'http://localhost:8081/api/puestos/disponibles/' + tipoVehiculo,
            method: 'GET',
            contentType: 'application/json',
            success: function(response) {
                var $puestoSelect = $('#puesto'); // Cambiar a 'puesto'
                $puestoSelect.empty(); // Limpiar opciones actuales
                var options = [];
                
                if (response.length > 0) { // Cambiar aquí
                    response.forEach(function(puesto) {
                        options.push('<option value="' + puesto.numeroPuesto + '">' + puesto.numeroPuesto + '</option>');
                        });
                    } else {
                        options.push('<option value="">No hay espacios disponibles</option>');
                    }
            
                    $puestoSelect.append(options.join(''));      
            },
            error: function(xhr, status, error) {

            $('#puesto').empty().append('<option value="">Error al cargar espacios</option>'); // Cambiado a 'puesto'
            }
        });
    }


    // Manejo del cambio en el tipo de vehículo
    $('#tipoVehiculo').on('change', function() {
        var selectedType = $(this).val();
        updateMarcaVehiculoOptions(selectedType);
        updatePuestoOptions(selectedType); // Llama a la función para actualizar los puestos
    });

    // Manejo del envío del formulario
    $('.btn-ingreso').on('click', function(event) {
        event.preventDefault(); // Prevenir el envío por defecto del formulario
        
        var placa = $('#placa').val().toUpperCase();  // Convertir a mayúsculas
        var tipoVehiculo = $('#tipoVehiculo').val();
        var marcaVehiculo = $('#marcaVehiculo').val();
        var color = $('#color').val();
        var puesto = $('#puesto').val(); // Cambiado para obtener el valor del select
        var fechaIngreso = $('#fechaIngreso').text();
    
        if (!placa || tipoVehiculo === 'null' || !puesto || !marcaVehiculo || !color) {
            $('#responseMessage').text('Por favor, complete todos los campos.').addClass('text-danger');
            return;
        }

        $.ajax({
            url: 'http://localhost:8081/api/vehiculos/guardarVehiculo',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                placa: placa,
                tipoVehiculo: tipoVehiculo,
                marcaVehiculo: marcaVehiculo,
                color: color,
                puesto: {
                    numeroPuesto: puesto // Asegúrate de que "numero" sea el nombre correcto según tu entidad
                },
                fechaIngreso: fechaIngreso
            }),            
            success: function(response) {
                $('#responseMessage').text('Vehículo registrado exitosamente.').removeClass('text-danger').addClass('text-success');
                alert('Vehículo registrado exitosamente.'); // Ventana emergente de éxito
                // Limpiar el formulario si es necesario
                $('#placa').val('');
                $('#tipoVehiculo').val('null');
                $('#marcaVehiculo').val('');
                $('#color').val('');
                $('#puesto').val(''); 
                $('#fechaIngreso').text(new Date(new Date() - offset).toISOString().slice(0, 19)); // Actualizar la fecha
            },
            error: function(xhr, status, error) {
                alert('No se pudo ingresar el vehiculo');
                $('#responseMessage').text('Error al registrar el vehículo.').addClass('text-danger');
            }
        });          
   
    });
});    