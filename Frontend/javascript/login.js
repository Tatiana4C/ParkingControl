$(document).ready(function() {
    $('#loginForm').on('submit', function(e) {
        e.preventDefault();
        var email = $('#email').val();
        var contrasena = $('#contrasena').val();

        // Aquí puedes agregar lógica para autenticar al usuario
        $.ajax({
            url: '/api/administradores', // Cambia esta URL al endpoint correcto de tu backend
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                email: email,
                contrasena: contrasena
            }),
            success: function(response) {
                // Manejo del éxito, por ejemplo, redirigir a otra página
                alert('Inicio de sesión exitoso');
                window.location.href = '/dashboard'; // Redirige a la página de administración o dashboard
            },
            error: function(xhr, status, error) {
                alert('Error al iniciar sesión');
                console.error('Error:', error);
            }
        });
    });
});
