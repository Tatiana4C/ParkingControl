$(document).ready(function() {
    const tooltip = $('#tooltip');
    
    // Cargar puestos para un tipo de vehículo específico
    function loadPuestos(tipoVehiculo) {
        $.ajax({
            url: 'http://localhost:8081/api/puestos/puestos/' + tipoVehiculo, // Utiliza el tipo de vehículo en la URL
            method: 'GET',
            success: function(response) {
                updateTable(tipoVehiculo, response); // Actualiza la tabla correspondiente
            },
            error: function(xhr, status, error) {
                console.error('Error al cargar los puestos:', error);
            }
        });
    }

    function updateTable(tipoVehiculo, puestos) {
        const tableId = tipoVehiculo === 'MOTO' ? 'motoTable' : 'carTable'; // Determina la tabla a actualizar
        const tableBody = $('#' + tableId);
        tableBody.empty();
    
        // Iterar de dos en dos para generar dos celdas por fila
        for (let i = 0; i < puestos.length; i += 2) {
            let row = $('<tr>'); // Crear una nueva fila
    
            // Primera celda con el puesto actual
            let cell1 = $('<td>')
                .text(puestos[i].numeroPuesto)
                .addClass(puestos[i].estaOcupado ? 'occupied' : 'available')
                .on('mouseover', showTooltip)
                .on('mouseout', hideTooltip);
    
            row.append(cell1); // Agregar la primera celda a la fila
    
            // Verificar si hay un segundo puesto para agregar en la misma fila
            if (i + 1 < puestos.length) {
                let cell2 = $('<td>')
                    .text(puestos[i + 1].numeroPuesto)
                    .addClass(puestos[i + 1].estaOcupado ? 'occupied' : 'available')
                    .on('mouseover', showTooltip)
                    .on('mouseout', hideTooltip);
    
                row.append(cell2); // Agregar la segunda celda a la fila
            }
    
            tableBody.append(row); // Agregar la fila al cuerpo de la tabla
        }
    
        updateCounters(); // Actualizar los contadores
    }
    
    function updateCounters() {
        const availableMotos = $('#motoTable .available').length;
        const availableCars = $('#carTable .available').length;

        $('#motoCounter').text('Puestos Disponibles: ' + availableMotos);
        $('#carCounter').text('Puestos Disponibles: ' + availableCars);
    }

    function showTooltip(event) {
        const cell = $(this);
        tooltip.text(cell.text()) // Mostrar el número de puesto en el tooltip
            .css({
                top: event.pageY + 10,
                left: event.pageX + 10,
                display: 'block'
            });
    }

    function hideTooltip() {
        tooltip.css('display', 'none');
    }

    // Llama a la función loadPuestos para motos y carros
    loadPuestos('MOTO'); // Cargar puestos para motos
    loadPuestos('CARRO'); // Cargar puestos para carros


    // Ver tabla de vehiculos que estan en el parqueadero
    // En esta tabla se permitirá modificar los datos del vehiculo excepto la fecha de ingreso

    $('#vehicleModal').on('show.bs.modal', function () {
        loadVehicles(); // Cargar vehículos cuando se muestre el modal
    });
    

    function loadVehicles() {
        $.ajax({
            url: 'http://localhost:8081/api/vehiculos/list',
            method: 'GET',
            success: function(response) {
                updateVehicleTable(response); // Actualiza la tabla del modal
            },
            error: function(xhr, status, error) {
                console.error('Error al cargar los vehículos:', error);
            }
        });
    }
    
    function updateVehicleTable(vehiculos) {
        const vehicleTableBody = $('#vehicleTable');
        vehicleTableBody.empty();
    
        vehiculos.forEach(function(vehiculo) {
            let row = $('<tr>').attr('data-id', vehiculo.idVehiculo); 

            console.log("ID del vehículo:", vehiculo.idVehiculo); // Verifica el idVehiculo en consola
            
            // Campo editable de la placa
            let placaCell = $('<td>').append(
                $('<input>', {
                    type: 'text',
                    value: vehiculo.placa,
                    class: 'form-control placa-input'
                })
            );
    
            // Campo editable del tipo de vehículo
            let tipoVehiculoCell = $('<td>').append(
                $('<select>', {
                    class: 'form-select tipo-vehiculo-select'
                }).append(
                    $('<option>', { value: 'MOTO', text: 'MOTO', selected: vehiculo.tipoVehiculo === 'MOTO' }),
                    $('<option>', { value: 'CARRO', text: 'CARRO', selected: vehiculo.tipoVehiculo === 'CARRO' }),
                )
            );

            // Campos NO editable
            let horaIngresoCell = $('<td>').text(vehiculo.fechaIngreso);

            // Campo editable de la marca
            let marcaCell = $('<td>').append(
                $('<input>', {
                    type: 'text',
                    value: vehiculo.marcaVehiculo,
                    class: 'form-control marcaVehiculo-input'
                })
            );

            // Campo editable del color
            let colorCell = $('<td>').append(
                $('<input>', {
                    type: 'text',
                    value: vehiculo.color,
                    class: 'form-control color-input',
                    oninput: "this.value = this.value.toUpperCase()", // Convierte el texto a mayúsculas
                })
            );
    
            
            // Campo editable del puesto
            let puestoCell = $('<td>').append(
                $('<input>', {
                    type: 'text',
                    value: vehiculo.puesto.numeroPuesto,
                    class: 'form-control puesto-input'
                })
            );
            
            // Agregar las celdas a la fila
            row.append(placaCell, tipoVehiculoCell, horaIngresoCell, marcaCell, colorCell, puestoCell);
            
            // Agregar fila al cuerpo de la tabla
            vehicleTableBody.append(row);
        });

    }
    $('#saveChangesButton').click(function() {
        let vehiculos = []; // Inicializa el array para almacenar vehículos
    
        // Recorrer cada fila de la tabla
        $('#vehicleTable tr').each(function() {
            const idVehiculo = $(this).data('id'); // Capturamos el ID de cada vehículo
            // Capturar otros valores
            const placa = $(this).find('.placa-input').val().toUpperCase();
            const tipoVehiculo = $(this).find('.tipo-vehiculo-select').val();
            const fechaIngreso = $(this).find('td').eq(2).text();
            const marcaVehiculo = $(this).find('.marcaVehiculo-input').val();
            const color = $(this).find('.color-input').val(); // Cambia .placa-input a .color-input
            const puestoNumero = $(this).find('.puesto-input').val().toUpperCase(); // Obtener el número de puesto
    
            // Asegúrate de que los datos son válidos antes de agregar al array
            if (idVehiculo) {
                vehiculos.push({
                    idVehiculo,
                    placa,
                    tipoVehiculo,
                    fechaIngreso,
                    marcaVehiculo,
                    color,
                    puesto: { numeroPuesto: puestoNumero } //Puesto se debe enviar como objeto
                });

            } else {
                console.error("No se pudo obtener el idVehiculo de una fila.");
            }
        });
    
        // Aquí, una vez que todos los vehículos han sido capturados, envía los datos al servidor
        console.log("Vehículos a enviar:", JSON.stringify(vehiculos)); // Verifica qué estás enviando
    
        // Enviar cada vehículo individualmente
        vehiculos.forEach(vehiculo => {
            $.ajax({
                url: 'http://localhost:8081/api/vehiculos/cambiar/' + vehiculo.idVehiculo, // URL del servidor con el ID del vehículo
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(vehiculo), // Envía el objeto vehiculo completo, que incluye el puesto como objeto
            });
            console.log("Enviando vehículo:", JSON.stringify(vehiculo));
        });
    
    });    

});

$(document).ready(function() {
    $('#saveChangesButton').click(function() {
        location.reload(); // Esto recarga la página actual
    });
});
