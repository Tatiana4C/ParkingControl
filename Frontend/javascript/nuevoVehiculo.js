$(document).ready(function() {
    // Mostrar la hora actual en el campo "Hora de Ingreso"
    var currentTime = new Date().toLocaleTimeString();
    $('#fechaIngreso').text(currentTime);

    // Función para actualizar opciones de marcaVehiculo según el tipo de vehículo
    function updateMarcaVehiculoOptions(tipoVehiculo) {
        var $marcaVehiculoSelect = $('#marcaVehiculo');
        $marcaVehiculoSelect.empty(); // Limpiar opciones actuales
        var options = [];

        if (tipoVehiculo === 'carro') {
            options = [
                'Audi', 'BMW', 'Chevrolet', 'Chrysler', 'Citroën', 'Fiat', 'Ford', 
                'Hyundai', 'Kia', 'Land Rover', 'Mazda', 'Mercedes-Benz', 'Nissan', 
                'Peugeot', 'Renault', 'Subaru', 'Suzuki', 'Toyota', 'Volkswagen', 'Volvo', 'Otro'
            ];
        } else if (tipoVehiculo === 'moto') {
            options = [
                'Honda', 'Yamaha', 'Suzuki', 'Kawasaki', 'Bajaj', 'Ducati', 'BMW Motorrad', 
                'KTM', 'Harley-Davidson', 'Royal Enfield', 'Piaggio', 'Vespa', 'Aprilia', 'Otro'
            ];
        }

        // Añadir opciones al select de marcas
        $marcaVehiculoSelect.append('<option value="">Seleccione</option>');
        options.forEach(function(marcaVehiculo) {
            $marcaVehiculoSelect.append('<option value="' + marcaVehiculo.toLowerCase().replace(/\s+/g, '-') + '">' + marcaVehiculo + '</option>');
        });
    }

    // Función para actualizar opciones de espacio según el tipo de vehículo
    function updateSpaceOptions(tipoVehiculo) {
        var $spaceSelect = $('#puesto');
        $spaceSelect.empty(); // Limpiar opciones actuales
        var options = [];

        if (tipoVehiculo === 'carro') {
            for (var i = 1; i <= 14; i++) {
                options.push('<option value="' + i + '">' + i + '</option>');
            }
        } else if (tipoVehiculo === 'moto') {
            for (var i = 1; i <= 30; i++) {
                options.push('<option value="' + i + '">' + i + '</option>');
            }
        }

        // Añadir opciones al select de puestos
        $spaceSelect.append('<option value="">Seleccione</option>');
        $spaceSelect.append(options.join(''));
    }

    // Manejo del cambio en el tipo de vehículo
    $('#tipoVehiculo').on('change', function() {
        var selectedType = $(this).val();
        updateMarcaVehiculoOptions(selectedType);
        updateSpaceOptions(selectedType);  // Cambiado a la función correcta
    });

    // Manejo del envío del formulario
    $('.btn-ingreso').on('click', function(event) {
        event.preventDefault(); // Prevenir el envío por defecto del formulario

        var placa = $('#placa').val();
        var tipoVehiculo = $('#tipoVehiculo').val();
        var marcaVehiculo = $('#marcaVehiculo').val();
        var color = $('#color').val();
        var puesto = $('#puesto').val();  // Asegúrate que este ID coincida con el input correcto
        var fechaIngreso = $('#fechaIngreso').text();

        if (!placa || tipoVehiculo === 'Seleccione' || !puesto || !marcaVehiculo || !color) {
            $('#responseMessage').text('Por favor, complete todos los campos.').addClass('text-danger');
            return;
        }

        // Envía una solicitud AJAX para registrar el vehículo
        $.ajax({
            url: '/api/vehiculos', // Cambia esta URL a la ruta de tu controlador en el backend
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                placa: placa,
                tipoVehiculo: tipoVehiculo,
                marcaVehiculo: marcaVehiculo,
                color: color,
                puesto: puesto,
                fechaIngreso: fechaIngreso
            }),
            success: function(response) {
                $('#responseMessage').text('Vehículo registrado exitosamente.').removeClass('text-danger').addClass('text-success');
                // Limpiar el formulario si es necesario
                $('#placa').val('');
                $('#tipoVehiculo').val('Seleccione');
                $('#marcaVehiculo').val('');
                $('#color').val('');
                $('#puesto').val('');  // Asegúrate de limpiar el campo correcto
                $('#fechaIngreso').text(new Date().toLocaleTimeString()); // Actualizar la hora de ingreso
            },
            error: function(xhr, status, error) {
                $('#responseMessage').text('Error al registrar el vehículo.').addClass('text-danger');
            }
        });
    });
});
