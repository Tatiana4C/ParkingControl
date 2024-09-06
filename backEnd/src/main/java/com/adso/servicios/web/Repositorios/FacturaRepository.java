package com.adso.servicios.web.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adso.servicios.web.Entidades.Factura;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

}
