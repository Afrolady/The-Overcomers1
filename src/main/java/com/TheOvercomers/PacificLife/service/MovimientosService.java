package com.TheOvercomers.PacificLife.service;

import com.TheOvercomers.PacificLife.modelos.Empresa;
import com.TheOvercomers.PacificLife.modelos.MovimientoDinero;
import com.TheOvercomers.PacificLife.repo.MovimientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientosService {
    @Autowired
    MovimientosRepository movimientosRepository;

   //Metodo que me muestra todos los movimientos sin ningun filtro.
    public List<MovimientoDinero> getAllMovimientos() {
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        movimientosRepository.findAll().forEach(movimiento -> movimientosList.add(movimiento)); // recorremos el iterable que regresa el metodo findAll del JPA y lo guardamos en la lista creada.
        return movimientosList;
    }


    public MovimientoDinero getMovimientoById(Integer id) { //Ver movimientos por id
        return movimientosRepository.findById(id).get();
    }

    public MovimientoDinero saveOrUpdateMovimiento(MovimientoDinero movimientoDinero) { //Guardar o actualizar elementos.
        MovimientoDinero mov = movimientosRepository.save(movimientoDinero);
        return mov;
    }

    public boolean deleteMovimiento(Integer id){ //Eliminar movimiento por id
        movimientosRepository.deleteById(id); //Eliminar usando el metodo que nos ofrece el repositorio
        if(this.movimientosRepository.findById(id).isPresent()){ //Si al buscar el movimiento lo encontramos, no se eliminó (false)
            return false;
        }
        return true; //Si al buscar el movimiento no lo encontramos, si lo eliminò (true)
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpleado(Integer id) { //Obtener teniendo en cuenta el id del empleado.
        return movimientosRepository.findByEmpleado(id);
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Integer id){//Obtener movimientos teniendo en cuenta el id de la empresa a la que pertenecen los empleados que la registraron.
        return movimientosRepository.findByEmpresa(id);
    }

}










