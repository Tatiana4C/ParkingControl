package com.parkingcontrol.miproyecto.Servicios.Implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.parkingcontrol.miproyecto.Entidades.Administrador;
import com.parkingcontrol.miproyecto.Repository.AdmiRepository;
import com.parkingcontrol.miproyecto.Servicios.Interfaces.AdministradorInt;

@Service
public class AdministradorImp implements AdministradorInt {
    private final AdmiRepository admiRepository;

    public AdministradorImp(AdmiRepository admiRepository) {
        this.admiRepository = admiRepository;
    }

    
    @Override
    public List<Administrador> findAll() {
        return admiRepository.findAll();
    }

    @Override
    public Administrador findById(Integer id) {
        return admiRepository.findById(id).orElse(null);
    }

    // Verificar email y contraseña
    @Override
    public boolean verificarCredenciales(String email, String contrasena) {
        // Usamos Optional para manejar el caso de que no se encuentre el administrador
        Optional<Administrador> administradorOpt = admiRepository.findByEmailAndContrasena(email, contrasena);
        
        // Si existe, devolvemos true, de lo contrario false
        return administradorOpt.isPresent();
    }

    @Override
    // Verifica si el email existe en la base de datos
    public boolean verificarEmail(String email) {
        return admiRepository.findByEmail(email).isPresent();
    }

    @Override
    // Método para crear un nuevo administrador
    public Administrador crearAdministrador(Administrador administrador) {
        if (verificarEmail(administrador.getEmail())) {
            throw new RuntimeException("El email ya está registrado.");
        }
        return admiRepository.save(administrador);
    }

    @Override
    public void deleteById(Integer id) {
        admiRepository.deleteById(id);
    }

}
