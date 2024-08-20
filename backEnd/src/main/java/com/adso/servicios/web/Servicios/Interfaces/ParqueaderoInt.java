package com.adso.servicios.web.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

public interface ParqueaderoInt {

    public List<ParqueaderoInt> findAll();

    public Optional<ParqueaderoInt> findById(Integer id);

    public ParqueaderoInt save(ParqueaderoInt parqueadero);

    public void delete(Integer id);

}
