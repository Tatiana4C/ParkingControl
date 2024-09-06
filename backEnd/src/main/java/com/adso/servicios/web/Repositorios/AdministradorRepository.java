package com.adso.servicios.web.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adso.servicios.web.Entidades.Administradores;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AdministradorRepository extends JpaRepository<Administradores, Integer> {
    public void saveAdmin(Administradores administradores);

    public void deleteAdmin(Administradores administradores);
}
