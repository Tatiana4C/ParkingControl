package com.adso.servicios.web.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adso.servicios.web.Entidades.HistorialParqueadero;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface HistorialParquederoRepository extends JpaRepository<HistorialParqueadero, Integer> {

}
