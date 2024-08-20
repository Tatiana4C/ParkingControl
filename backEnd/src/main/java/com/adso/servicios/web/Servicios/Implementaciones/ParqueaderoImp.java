package com.adso.servicios.web.Servicios.Implementaciones;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.adso.servicios.web.Servicios.Interfaces.ParqueaderoInt;

@Service
public class ParqueaderoImp implements ParqueaderoInt {

    @Override
    public List<ParqueaderoInt> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<ParqueaderoInt> findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public ParqueaderoInt save(ParqueaderoInt parqueadero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
