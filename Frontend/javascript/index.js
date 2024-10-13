function buscarVehiculo() {
    // Captura el valor de la placa
    const placa = document.getElementById('placa').value;

    // Guarda la placa en el localStorage
    localStorage.setItem('placa', placa);

    // Verifica si el campo está vacío
    if (placa === '') {
        alert('Por favor, ingresa una placa válida.'); // Muestra un mensaje de error
        return; // Detiene la ejecución si está vacío
    }

    // Redirige a la página miCarro.html
    location.href = 'micarro.html';
}
