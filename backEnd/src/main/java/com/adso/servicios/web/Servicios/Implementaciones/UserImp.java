package com.adso.servicios.web.Servicios.Implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adso.servicios.web.Entidades.UserLogin;

import com.adso.servicios.web.Repositorios.UserRepositoy;
import com.adso.servicios.web.Servicios.Interfaces.UserInt;

@Service
public class UserImp implements UserInt {
    @Autowired
    private UserRepositoy repositorio;

    @Override
    public List<UserLogin> finAll() {
        return repositorio.findAll();
    }

    @Override
    public Optional<UserLogin> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public UserLogin save(UserLogin userLogin) {
        return repositorio.save(userLogin);
    }

    @Override
    public void delete(Integer id) {
        repositorio.deleteById(id);
    }
}