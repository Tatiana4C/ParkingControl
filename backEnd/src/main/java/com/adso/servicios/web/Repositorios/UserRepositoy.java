package com.adso.servicios.web.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adso.servicios.web.Entidades.UserLogin;

@Repository
public interface UserRepositoy extends JpaRepository<UserLogin, Integer> {
    public void guardarUsuario(UserLogin userLogin);

    public void eliminarUsuario(UserLogin userLogin);

    // public void buscarById(String nombre);

}
