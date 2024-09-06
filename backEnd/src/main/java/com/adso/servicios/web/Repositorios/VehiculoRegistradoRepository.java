package com.adso.servicios.web.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adso.servicios.web.Entidades.VehiculoRegistrado;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface VehiculoRegistradoRepository extends JpaRepository<VehiculoRegistrado, Integer> {

}