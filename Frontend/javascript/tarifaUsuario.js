$.ajax({
    url: 'http://localhost:8081/api/tarifas/tarifa',
    method: 'GET',
    dataType: 'json',
    success: function(data) {
        // Filtrar datos por tipo de vehículo
        const carro = data.find(tarifa => tarifa.tipoVehiculo === "CARRO");
        const moto = data.find(tarifa => tarifa.tipoVehiculo === "MOTO");

        // Construir la tabla
        const tabla = $('#tarifas-table');
        const rows = `
            <tr>
                <td>Hora y fracción</td>
                <td>$ ${carro.tarifaFraccion}</td>
                <td>$ ${moto.tarifaFraccion}</td>
            </tr>
            <tr>
                <td>Día</td>
                <td>$ ${carro.tarifaDia}</td>
                <td>$ ${moto.tarifaDia}</td>
            </tr>
            <tr>
                <td>Mensual</td>
                <td>$ ${carro.tarifaMes}</td>
                <td>$ ${moto.tarifaMes}</td>
            </tr>
            <tr>
                <td>Anual</td>
                <td>$ ${carro.tarifaAnual}</td>
                <td>$ ${moto.tarifaAnual}</td>
            </tr>
        `;

        // Agregar las filas a la tabla
        tabla.append(rows);
    },
    error: function(error) {
        console.error('Error al cargar tarifas:', error);
    }
});
