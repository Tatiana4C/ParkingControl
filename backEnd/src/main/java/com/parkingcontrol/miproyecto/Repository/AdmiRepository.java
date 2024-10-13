package com.parkingcontrol.miproyecto.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkingcontrol.miproyecto.Entidades.Administrador;

@Repository
public interface  AdmiRepository extends JpaRepository<Administrador, Integer>{
    // Método para encontrar un Administrador por su email
    Optional<Administrador> findByEmail(String email);
    
    // Método para encontrar un Administrador por su email y contraeña
    Optional<Administrador> findByEmailAndContrasena(String email, String contrasena);


    
}
