$(document).ready(function() { 
    $('#loginForm').on('submit', function(e) {
        e.preventDefault(); // Evita que el formulario se envíe de forma tradicional

        var email = $('#email').val();
        var contrasena = $('#contrasena').val(); 

        // Aquí envías los datos al backend
        $.ajax({
            url: 'http://localhost:8081/api/administradores/login',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                email: email,
                contrasena: contrasena 
            }),
            success: function(response) {
                // Manejo del éxito, como redirigir a otra página
                alert('Inicio de sesión exitoso');
                window.location.href = 'bienvenido.html';
            },
            error: function(xhr, status, error) {
                alert('Error al iniciar sesión');
                console.error('Error:', error);
            }
        });
    });
});
