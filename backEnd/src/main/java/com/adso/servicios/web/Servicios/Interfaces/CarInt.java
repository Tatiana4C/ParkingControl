package com.adso.servicios.web.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

public interface CarInt {
    public List<CarInt> finAll();

    public Optional<CarInt> findById(Integer id);

    public ParqueaderoInt save(CarInt car);

    public void delete(Integer id);

}
