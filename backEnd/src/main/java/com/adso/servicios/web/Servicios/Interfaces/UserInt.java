package com.adso.servicios.web.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import com.adso.servicios.web.Entidades.UserLogin;

public interface UserInt {
    public List<UserLogin> finAll();

    public Optional<UserLogin> findById(Integer id);

    public UserLogin save(UserLogin userLogin);

    public void delete(Integer id);

    // public Object buscar(String nombre);

}