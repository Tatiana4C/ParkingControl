package com.adso.servicios.web.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adso.servicios.web.Entidades.Vehiculo;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface VehiculoRepositoy extends JpaRepository<Vehiculo, Integer> {

}
