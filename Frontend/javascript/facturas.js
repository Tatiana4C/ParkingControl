// Función para convertir minutos a horas y minutos
function convertirTiempo(minutos) {
    const horas = Math.floor(minutos / 60); // Obtener la parte entera de horas
    const minutosRestantes = minutos % 60;  // Obtener los minutos restantes
    return `${horas}h ${minutosRestantes}min`; // Formato deseado
}

// Supongamos que aquí agregas las filas de tu tabla
$.ajax({
    url: 'http://localhost:8081/api/facturas/lista',
    method: 'GET',
    success: function(response) {
        const tabla = $('#facturas-table tbody');
        tabla.empty(); // Limpiar la tabla antes de agregar los datos

        response.forEach(factura => {
            const tiempoFormateado = convertirTiempo(factura.tiempoEstacionamiento); // Convertir tiempo

            const fila = `
                <tr>
                    <td>${factura.idFactura}</td>
                    <td>${factura.placa}</td>
                    <td>${factura.tipoVehiculo}</td>
                    <td>${factura.fechaFacturacion}</td>
                    <td>${factura.fechaIngreso}</td>
                    <td>${factura.fechaSalida}</td>
                    <td>${tiempoFormateado}</td> <!-- Aquí usas el tiempo convertido -->
                    <td>$ ${factura.valor}</td>
                    <td>${factura.formaPago}</td>
                    <td>${factura.tipoPlan}</td>
                </tr>
            `;
            tabla.append(fila); // Agregar la fila a la tabla
        });
    },
    error: function(xhr, status, error) {
        console.error('Error al cargar las facturas:', error);
    }
});

function buscarFactura() {
    var input = document.getElementById("buscador").value.toLowerCase();
    var table = document.getElementById("facturas-table").getElementsByTagName("tbody")[0];
    var tr = table.getElementsByTagName("tr");

    for (var i = 0; i < tr.length; i++) {
        var td = tr[i].getElementsByTagName("td");
        var encontrado = false;

        for (var j = 0; j < td.length; j++) {
            if (td[j]) {
                var txtValue = td[j].textContent || td[j].innerText;
                if (txtValue.toLowerCase().indexOf(input) > -1) {
                    encontrado = true;
                    break;
                }
            }
        }
        
        tr[i].style.display = encontrado ? "" : "none";
    }
}
function convertirMinutosAHoras(minutos) {
    const horas = Math.floor(minutos / 60); // Dividir por 60 para obtener horas
    const minutosRestantes = Math.round(minutos % 60); // Obtener el resto de los minutos
    return `${horas} horas y ${minutosRestantes} minutos`;
}