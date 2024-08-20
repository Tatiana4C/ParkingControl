package com.adso.servicios.web.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adso.servicios.web.Entidades.Car;
import com.adso.servicios.web.Servicios.Interfaces.CarInt;

public interface CarRepositoy extends JpaRepository<CarInt, Integer> {
    public void saveCar(Car car);

    public void deleteCar(Car car);

}
