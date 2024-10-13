// Código existente para cargar tarifas
$.ajax({ 
    url: 'http://localhost:8081/api/tarifas/tarifa',
    method: 'GET',
    dataType: 'json',
    success: function(data) {
        const carro = data.find(tarifa => tarifa.tipoVehiculo === "CARRO");
        const moto = data.find(tarifa => tarifa.tipoVehiculo === "MOTO");

        const tabla = $('#tarifas-table');
        const rows = `
            <tr>
                <td>Fracción</td>
                <td class="editable" data-tipo="fraccion" data-vehiculo="CARRO">$ ${carro.tarifaFraccion}</td>
                <td class="editable" data-tipo="fraccion" data-vehiculo="MOTO">$ ${moto.tarifaFraccion}</td>
            </tr>
            <tr>
                <td>Hora</td>
                <td class="editable" data-tipo="hora" data-vehiculo="CARRO">$ ${carro.tarifaDia}</td>
                <td class="editable" data-tipo="hora" data-vehiculo="MOTO">$ ${moto.tarifaDia}</td>
            </tr>
            <tr>
                <td>Mensual</td>
                <td class="editable" data-tipo="mensual" data-vehiculo="CARRO">$ ${carro.tarifaMes}</td>
                <td class="editable" data-tipo="mensual" data-vehiculo="MOTO">$ ${moto.tarifaMes}</td>
            </tr>
            <tr>
                <td>Anual</td>
                <td class="editable" data-tipo="anual" data-vehiculo="CARRO">$ ${carro.tarifaAnual}</td>
                <td class="editable" data-tipo="anual" data-vehiculo="MOTO">$ ${moto.tarifaAnual}</td>
            </tr>
        `;
        tabla.append(rows);
        
        // Agregar el evento de doble clic
        $('.editable').dblclick(function() {
            const originalText = $(this).text().trim().replace('$ ', '');
            const input = `<input type="number" class="form-control" value="${originalText}" />`;
            $(this).html(input);
            
            // Cuando el input pierde el foco, guarda el nuevo valor
            $(this).find('input').focus().blur(function() {
                const nuevoValor = $(this).val();
                const tipo = $(this).parent().data('tipo');
                const vehiculo = $(this).parent().data('vehiculo');

                // Actualiza la celda con el nuevo valor
                $(this).parent().html(`$ ${nuevoValor}`);

                // Llamar a la función para actualizar la tarifa en el backend
                actualizarTarifa(vehiculo, tipo, nuevoValor);
            });
        });
    },
    error: function(error) {
        console.error('Error al cargar tarifas:', error);
    }
});

// Función para actualizar la tarifa en el backend
function actualizarTarifa(tipoVehiculo, tipo, nuevoValor) {
    // Aquí conviertes el valor a número
    nuevoValor = parseFloat(nuevoValor);

    // Construir el objeto para enviar al backend
    const tarifaActualizada = {};
    if (tipo === "fraccion") tarifaActualizada.tarifaFraccion = nuevoValor;
    else if (tipo === "hora") tarifaActualizada.tarifaDia = nuevoValor;
    else if (tipo === "mensual") tarifaActualizada.tarifaMes = nuevoValor;
    else if (tipo === "anual") tarifaActualizada.tarifaAnual = nuevoValor;

    $.ajax({
        url: `http://localhost:8081/api/tarifas/tarifa/${tipoVehiculo}`,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(tarifaActualizada),
        success: function(response) {
            console.log('Tarifa actualizada:', response);
            alert('Tarifa actualizada.'); // Ventana emergente de éxito
        },
        error: function(error) {
            console.error('Error al actualizar tarifa:', error);
            alert('La tarifa no se pudo actualizar.'); // Ventana emergente de éxito
        }
    });
}
