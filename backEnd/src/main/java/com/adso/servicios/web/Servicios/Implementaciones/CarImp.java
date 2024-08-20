package com.adso.servicios.web.Servicios.Implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.servicios.web.Servicios.Interfaces.CarInt;
import com.adso.servicios.web.Servicios.Interfaces.ParqueaderoInt;

@Service
public class CarImp implements CarInt {

    @Override
    public List<CarInt> finAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finAll'");
    }

    @Override
    public Optional<CarInt> findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public ParqueaderoInt save(CarInt car) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
