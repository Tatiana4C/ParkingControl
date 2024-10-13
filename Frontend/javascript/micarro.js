$(document).ready(function() {
    // Obtener la placa del localStorage
    const placa = localStorage.getItem('placa');

    if (placa) {
        // Hacer la solicitud AJAX para obtener los datos del vehículo
        $.ajax({
            url: 'http://localhost:8081/api/vehiculos/' + placa,
            method: 'GET',
            data: { placa: placa },
            success: function(data) {
                // Actualiza la información en la página
                $('.info-container').find('div').eq(0).html(`<strong>Placa: </strong>${data.placa}`);
                $('.info-container').find('div').eq(1).html(`<strong>Puesto: </strong>${data.puesto.numeroPuesto}`);
                $('.info-container').find('div').eq(2).html(`<strong>Hora de ingreso: </strong><br>${data.fechaIngreso}`);
                
                
                // Calcular tiempo total estacionado
                const fechaIngreso = new Date(data.fechaIngreso);
                const fechaActual = new Date();
                const tiempoTotal = new Date(fechaActual - fechaIngreso).toISOString().substr(11, 8);
                $('.info-container').find('div').eq(3).html(`<strong>Total estacionado: </strong>${tiempoTotal}`);

                // Acceder a numeroPuesto dentro del objeto puesto
                const numeroPuesto = data.puesto ? data.puesto.numeroPuesto : undefined;

                // Seleccionar la cámara dependiendo del puesto
                const imagenUrl = seleccionarCamara(numeroPuesto);
                $('#camara-img').attr('src', imagenUrl);  // Actualizar el src de la imagen
            },
            error: function(xhr, status, error) {
                console.error('No se encontro el vehiculo', error);
                // Cambiar a la imagen por defecto si no se encuentra el vehículo
                $('#camara-img').attr('src', '../../Img/default.png'); 
            }
        });
    }
});

// Función que retorna la URL de la cámara dependiendo del puesto
function seleccionarCamara(puesto) {
    const camaras = {
        'C1': '../../Img/camara1.jpeg',
        'C2': '../../Img/camara1.jpeg',
        'C3': '../../Img/camara1.jpeg',
        'C4': '../../Img/camara1.jpeg',
        'C5': '../../Img/camara1.jpeg',
        'C6': '../../Img/camara2.jpeg',
        'C7': '../../Img/camara2.jpeg',
        'C8': '../../Img/camara2.jpeg',
        'C9': '../../Img/camara2.jpeg',
        'C10': '../../Img/camara2.jpeg',
        'C11': '../../Img/camara3.jpeg',
        'C12': '../../Img/camara3.jpeg',
        'C13': '../../Img/camara3.jpeg',
        'C14': '../../Img/camara3.jpeg',
        'M1': '../../Img/camara4.jpeg',
        'M2': '../../Img/camara4.jpeg',
        'M3': '../../Img/camara4.jpeg',
        'M4': '../../Img/camara4.jpeg',
        'M5': '../../Img/camara4.jpeg',
        'M6': '../../Img/camara4.jpeg',
        'M7': '../../Img/camara4.jpeg',
        'M8': '../../Img/camara4.jpeg',
        'M9': '../../Img/camara4.jpeg',
        'M10': '../../Img/camara4.jpeg',
        'M11': '../../Img/camara5.jpeg',
        'M12': '../../Img/camara5.jpeg',
        'M13': '../../Img/camara5.jpeg',
        'M14': '../../Img/camara5.jpeg',
        'M15': '../../Img/camara5.jpeg',
        'M16': '../../Img/camara5.jpeg',
        'M17': '../../Img/camara5.jpeg',
        'M18': '../../Img/camara5.jpeg',
        'M19': '../../Img/camara5.jpeg',
        'M20': '../../Img/camara5.jpeg',
        'M21': '../../Img/camara6.jpeg',
        'M22': '../../Img/camara5.jpeg',
        'M23': '../../Img/camara5.jpeg',
        'M24': '../../Img/camara5.jpeg',
        'M25': '../../Img/camara5.jpeg',
        'M26': '../../Img/camara5.jpeg',
        'M27': '../../Img/camara5.jpeg',
        'M28': '../../Img/camara5.jpeg',
        'M29': '../../Img/camara5.jpeg',
        'M30': '../../Img/camara5.jpeg',
    };
    return camaras[puesto] || '../../Img/default.png';  // Ruta por defecto si no hay asignada
}

