// Función para eliminar usuarios
function eliminarUsuario(idAdministrador) {
    if (confirm("¿Estás seguro de que deseas eliminar este usuario?")) {
        $.ajax({
            url: `http://localhost:8081/api/administradores/eliminar/${idAdministrador}`,
            method: 'DELETE',
            success: function(response) {
                alert('Usuario eliminado exitosamente.');
                loadUsuarios(); // Refrescar la tabla de usuarios después de eliminar
            },
            error: function(xhr, status, error) {
                alert('Error al eliminar el usuario: ' + xhr.responseText);
            }
        });
    }
}

$(document).ready(function() {
    $('#form-nuevo-usuario').on('submit', function(e) {
        e.preventDefault();

        // Obtener los valores de los campos
        var nombre = $('#nombre').val();
        var email = $('#email').val();
        var confirmEmail = $('#confirm-email').val();
        var contrasena = $('#contrasena').val();
        var confirmContrasena = $('#confirm-contrasena').val();

        // Verificar que los correos sean iguales
        if (email !== confirmEmail) {
            alert('Los correos electrónicos no coinciden.');
            return;
        }

        // Verificar que las contraseñas sean iguales
        if (contrasena !== confirmContrasena) {
            alert('Las contraseñas no coinciden.');
            return;
        }

        // Realizar la llamada AJAX para crear el usuario
        $.ajax({
            url: 'http://localhost:8081/api/administradores/nuevo-admi',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                nombre: nombre,
                email: email,  // Aquí está la corrección
                contrasena: contrasena
            }),
            success: function(response) {
                alert('Usuario creado exitosamente.');
                // Redirigir o mostrar mensaje de éxito
            },
            error: function(xhr, status, error) {
                alert('Error al crear el usuario: ' + xhr.responseText);
            }
        });
    });

    // Función para eliminar usuarios

    $('#usuarioModal').on('show.bs.modal', function () {
        loadUsuarios(); // Cargar vehículos cuando se muestre el modal
    });

    function loadUsuarios() {
        $.ajax({
            url: 'http://localhost:8081/api/administradores/list',
            method: 'GET',
            success: function(response) {
                updateUsuarioTable(response);
            },
            error: function(xhr, status, error) {
                console.error('Error al cargar los usuarios:', error);
            }
        });
    }
    
    function updateUsuarioTable(usuarios) {
        const tablaBody = $('#usuarioTableBody'); // Apuntar al <tbody>;
        tablaBody.empty(); // Limpiar cualquier fila anterior
    
        usuarios.forEach(usuario => {
            const row = `
                <tr>
                    <td>${usuario.idAdministrador}</td>
                    <td>${usuario.nombre}</td>
                    <td>${usuario.email}</td>
                    <td>
                        <button class="btn btn-danger" onclick="eliminarUsuario('${usuario.idAdministrador}')">Eliminar</button>
                    </td>
                </tr>
            `;
            tablaBody.append(row); // Añadir fila a la tabla
        });
    }
    
}); 
