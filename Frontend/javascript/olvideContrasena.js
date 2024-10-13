$(document).ready(function() {
    // Maneja el envío del formulario
    $('#resetPasswordForm').on('submit', function(event) {
        event.preventDefault(); // Prevenir el envío por defecto del formulario

        // Recoge el correo electrónico del formulario
        var email = $('#email').val();

        // Valida el correo electrónico
        if (!validateEmail(email)) {
            $('#responseMessage').text('Por favor, ingrese un correo electrónico válido.').addClass('text-danger');
            return;
        }

        // Envía una solicitud AJAX al backend olvide-contrasena
        $.ajax({
            url: 'http://localhost:8081/api/administradores/olvide-contrasena',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ email: email }),
            success: function(response) {
                // Maneja la respuesta del servidor
                $('#responseMessage').text('Las instrucciones para restablecer tu contraseña han sido enviadas a tu correo electrónico.').removeClass('text-danger').addClass('text-success');
            },
            error: function(xhr, status, error) {
                // Maneja los errores
                $('#responseMessage').text('Hubo un problema al enviar el correo electrónico. Por favor, intenta de nuevo.').addClass('text-danger');
            }
        });
    });

    // Función para validar el formato del correo electrónico
    function validateEmail(email) {
        var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }
});
