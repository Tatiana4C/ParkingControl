$(document).ready(function() { 
    $('#buscarVehiculo').click(function() {
        var placa = $('#placa').val().toUpperCase();  // Convertir a mayúsculas
        if (!placa) {
            alert('Por favor ingrese la placa del vehículo.');
            return;
        }
        console.log('Placa ingresada:', placa);  // Muestra la placa en la consola

        // Llamada AJAX para verificar si el vehículo está en el parqueadero
        $.ajax({
            url: `http://localhost:8081/api/vehiculos/${placa}`,
            method: 'GET',
            success: function(response) {
                console.log("Esta el vehiculo" + response);
                if (response && response.placa && response.fechaIngreso) {
                    // Calcular tiempo total estacionado
                    const fechaIngreso = new Date(response.fechaIngreso);
                    const fechaActual = new Date();
                    const tiempoTotalMs  = fechaActual - fechaIngreso;

                    console.log("El tiempo total del vehículo en el parqueadero fue:", tiempoTotalMs);

                    // Convertir milisegundos a horas, minutos y segundos
                    const horas = Math.floor(tiempoTotalMs / (1000 * 60 * 60));
                    const minutos = Math.floor((tiempoTotalMs % (1000 * 60 * 60)) / (1000 * 60));
                    const segundos = Math.floor((tiempoTotalMs % (1000 * 60)) / 1000);

                    const tiempoTotalLegible = `${horas}h ${minutos}m ${segundos}s`;
                    
                    console.log("La fecha de ingreso fue" + response.fechaIngreso);
                    console.log("El tiempo total del vehiculo en el parqueadero fue:" + tiempoTotalLegible);
                    $('#tiempoEstacionado').html(`
                        <p>Tiempo en el parqueadero: ${tiempoTotalLegible}</p>
                    `);

                    manejarPlanPago(response);
                } else {
                    alert('El vehículo no se encuentra en el parqueadero. Placa: ' + placa);
                }
            },
            error: function(xhr, status, error) {
                alert('El vehículo no se encuentra en el parqueadero. Placa: ' + placa);
            }
        });
    });

    // Verifica si el vehiculo tiene un planPango
    function manejarPlanPago(vehiculo) {
        $.ajax({
            url: `http://localhost:8081/api/planesPagos/${vehiculo.placa}`,
            method: 'GET',
            success: function(response) {
                console.log(response);
                if (response && response.tipoPlan) {
                    // Mostrar detalles del plan de pago
                    $('#resultado').html(`
                        <h4>Detalles del Plan de Pago</h4>
                        <p>Tipo de Plan: ${response.tipoPlan}</p>
                        <p>Fecha de vencimiento: ${response.fechaFin}</p>
                    `);
    
                    // Verifica si el planPago esta activo
                    $.ajax({
                        url: `http://localhost:8081/api/facturas/tienePlan`,
                        method: 'POST',
                        contentType: 'text/plain', // Envia Texto
                        data: vehiculo.placa, // Envía placa
                        success: function(response, textStatus, jqXHR) {
                            console.log("Estado HTTP:", jqXHR.status);
                            console.log("Respuesta del servidor:", response);
   
                            // Crea factura por el valor de 0 si el plan esta activo
                            if (jqXHR.status === 201) {
                                alert('Factura con valor de 0 ha sido creada con éxito');
                            }
                        },
                        error: function(xhr) {
                            console.log("Estado HTTP (Error):", xhr.status);
                            console.log("Respuesta del servidor:", xhr.responseText);
   
                            // El plan esta vencido así que no genera factura
                            // Elimina de la base de datos el plan vencido y ofrece formas de pago
                            if (xhr.status === 400) {
                                alert('Su plan está vencido');
                                mostrarOpcionesDePlan(vehiculo);
                            } else {
                                alert('Error inesperado al crear la factura: ' + xhr.responseText);
                            }
                        }
                    });
                } else {
                    mostrarOpcionesDePlan(vehiculo);
                }
            },
            error: function(xhr, status, error) {
                if (xhr.status === 404) {
                    mostrarOpcionesDePlan(vehiculo);
                } else {
                    alert('Error al verificar el plan de pago: ' + xhr.responseText);
                }
            }
        });
    }
    
    function mostrarOpcionesDePlan(vehiculo) {
        $('#resultado').html(`
            <h4>No tiene un plan de pago.</h4>
            <p>¿Desea adquirir un plan mensual o anual?</p>
            <div class="d-flex justify-content-center">
                <button id="planMensual" class="btn btn-success mx-2">Mensual</button>
                <button id="planAnual" class="btn btn-success mx-2">Anual</button>
            </div>
            <p><br> O ¿prefieres facturar por tiempo?</p>
            <button id="facturaTiempo" class="btn btn-warning mx-2">Factura por Tiempo</button>
        `);
    
        $('#planMensual').click(function() {
            crearPlanPago('MENSUAL', vehiculo);
        });
    
        $('#planAnual').click(function() {
            crearPlanPago('ANUAL', vehiculo);
        });
    
        $('#facturaTiempo').click(function() {
            generarFacturaPorTiempo(vehiculo);
        });
    }
    
    // Función para crear factura de un plan mensual o anual
    function crearPlanPago(tipoPlan, vehiculo) {
        $.ajax({
            url: `http://localhost:8081/api/facturas/crearFacturaPlanPago`,
            method: 'POST',
            data: {
                placa: vehiculo.placa,
                tipoPlan: tipoPlan
            },
            contentType: 'application/x-www-form-urlencoded',
            success: function(response) {
                console.log(response); // Ver el response en consola para más detalles
                
                // Comprobación de la respuesta
                if (response.idFactura && response.valor) {
                    const idFactura = response.idFactura;
                    const valor = response.valor;
    
                    $('#resultado').html(`
                        <h4>Plan ${tipoPlan.charAt(0).toUpperCase() + tipoPlan.slice(1)} Creado</h4>
                        <p>Valor: ${valor}</p>
                        <p>Seleccione el medio de pago:</p>
                        <button class="btn btn-info" id="pagarEfectivo">Efectivo</button>
                        <button class="btn btn-info" id="pagarTarjeta">Tarjeta</button>
                    `);
    
                    $('#pagarEfectivo').click(function() {
                        procesarPago(idFactura, 'EFECTIVO', valor);
                    });
                    
                    $('#pagarTarjeta').click(function() {
                        procesarPago(idFactura, 'TARJETA', valor);
                    });
                } else {
                    alert("Respuesta del servidor no contiene los datos esperados.");
                }
            },
            error: function(xhr, status, error) {
                alert('Error al crear el plan de pago: ' + xhr.responseText);
            }
        });
    }

    // Factura por duración
    function generarFacturaPorTiempo(vehiculo) {
        const placa = vehiculo.placa;  // Solo extrae la placa del objeto vehiculo
        console.log("Placa enviada:", placa); // Asegúrate de que la placa tenga un valor válido
        $.ajax({
            url: `http://localhost:8081/api/facturas/calcularPorDuracion`,
            method: 'POST',
            data: {
                placa: placa  // Ahora se envía solo la placa como un parámetro
            },
            contentType: 'application/x-www-form-urlencoded',
            success: function(response) {
                console.log(response);

                // Ahora puedes obtener el idFactura
                const idFactura = response.idFactura; // Guarda el ID de la factura
                const valor = response.valor;

                $('#resultado').html(`
                    <h4>Factura por Tiempo</h4>
                    <p>Valor: ${valor}</p>
                    <p>Seleccione el medio de pago:</p>
                    <button class="btn btn-info" id="pagarEfectivo">Efectivo</button>
                    <button class="btn btn-info" id="pagarTarjeta">Tarjeta</button>
                `);

                $('#pagarEfectivo').click(function() {
                    procesarPago(idFactura, 'EFECTIVO', response.valor);  // Asegúrate de definir idFactura
                });
                
                $('#pagarTarjeta').click(function() {
                    procesarPago(idFactura, 'TARJETA', response.valor);  // Asegúrate de definir idFactura
                });                
            },
            error: function(xhr, status, error) {
                alert('Error al generar la factura por tiempo: ' + xhr.responseText);
            }
        });
    }

    function procesarPago(idFactura, formaPago, valor) {
        $.ajax({
            url: 'http://localhost:8081/api/facturas/cambiarFormaPago/' + formaPago,
            method: 'PUT',
            contentType: 'application/x-www-form-urlencoded', // Cambiado para que coincida con @RequestParam
            data: {
                id: idFactura,  // Pasar el ID de la factura
                nuevaFormaPago: formaPago // Asegúrate de que se envíe correctamente
            },
            success: function(response) {
                alert(`Pago de ${valor} realizado con éxito utilizando ${formaPago}.`);
            },
            error: function(xhr, status, error) {
                alert('Error al procesar el pago: ' + xhr.responseText);
            }
        });
    }
    // Función para limpiar la pagina
    function limpiarPagina() {
        // Aquí va el código para limpiar los elementos de la página
        $('#placa').val('');  // Limpiar el campo de placa
        $('#resultado').html('');  // Limpiar el área de resultados
        // Si hay otros campos o elementos que deseas limpiar, agrégales aquí
    }    
    // Botón al evento click para limpiar la página
    $('#limpiarPagina').click(function() {
        limpiarPagina();
    });
      
}); // Fin del $(document).ready
