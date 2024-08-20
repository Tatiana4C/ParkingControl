package com.adso.servicios.web.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.adso.servicios.web.Entidades.Parqueadero;

public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Integer> {

     public void guardarParqueadero(Parqueadero parqueadero);

     public void eliminarParqueadero(Parqueadero parqueadero);
}
