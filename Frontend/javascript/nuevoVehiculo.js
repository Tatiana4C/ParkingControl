$(document).ready(function() {
    // Mostrar la hora actual en el campo "Hora de Ingreso"
    var currentTime = new Date().toLocaleTimeString();
    $('#entry-time').text(currentTime);

    // Función para actualizar opciones de espacio
    function updateSpaceOptions(tipo) {
        var $puestoSelect = $('#puesto-number');
        $puestoSelect.empty(); // Limpiar opciones actuales
        var options = [];
        
        if (tipo === 'carro') {
            for (var i = 1; i <= 14; i++) {
                options.push('<option value="' + i + '">' + i + '</option>');
            }
        } else if (tipo === 'moto') {
            for (var i = 1; i <= 30; i++) {
                options.push('<option value="' + i + '">' + i + '</option>');
            }
        }
        
        $puestoSelect.append(options.join(''));
    }

    $('#vehiculo-tipo').on('change', function() {
        var selectedTipo = $(this).val();
        updateSpaceOptions(selectedTipo);
    });

    // Manejar el envío del formulario
    $('#form-vehiculo').on('submit', function(event) {
        event.preventDefault();

        // Obtener los valores del formulario
        var placa = $('#vehiculo-placa').val();
        var tipo = $('#vehiculo-tipo').val();
        var puesto = $('#puesto-number').val();
        var fechaIngreso = $('#entry-time').text();

        // Validar los campos
        if (!placa || !tipo || !puesto) {
            alert('Por favor, complete todos los campos.');
            return;
        }

        // Enviar los datos al backend (ajustar URL según tu configuración)
        $.ajax({
            url: '/api/vehiculosestacionados',  // Ajustar la URL a la ruta de tu backend
            method: 'POST',
            contentTipo: 'application/json',
            data: JSON.stringify({
                placa: placa,
                tipo: tipo,
                puesto: puesto,
                fechaIngreso: fechaIngreso
            }),
            success: function(response) {
                alert('Vehículo registrado con éxito.');
                // Limpiar el formulario
                $('#form-vehiculo')[0].reset();
                $('#puesto-number').empty();
            },
            error: function(xhr, status, error) {
                alert('Error al registrar el vehículo. Inténtelo de nuevo.');
            }
        });
    });
});
