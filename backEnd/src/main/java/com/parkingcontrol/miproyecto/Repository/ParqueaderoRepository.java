package com.parkingcontrol.miproyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkingcontrol.miproyecto.Entidades.Parqueadero;

@Repository
public interface ParqueaderoRepository extends JpaRepository<Parqueadero,Integer>{

}

