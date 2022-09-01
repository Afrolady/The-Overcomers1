package com.TheOvercomers.PacificLife.service;

import com.TheOvercomers.PacificLife.modelos.Empleado;
import com.TheOvercomers.PacificLife.repo.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    //Metodo para ver todos los empleados registrados.
    public List<Empleado> getAllEmpleado(){
        List<Empleado> empleadoList= new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    //Metodo para buscar empleados por ID
    public Optional<Empleado> getEmpleadoById(Integer id) {  //Optional se usa para indicar que retorne cualquier cosa que encuentre sin especificarle que sea un objeto o lista o por si no regresa nada.
        return empleadoRepository.findById(id);
    }

    //Metodo para buscar empleados por empresa
    public ArrayList<Empleado> obtenerPorEmpresa(Integer id) { //id de la empresa
        return empleadoRepository.findByEmpresa(id);
    }


    //Metodo para guardar o actualizar registros en Empleados
    public Empleado saveOrUpdateEmpleado(Empleado empleado){

        return empleadoRepository.save(empleado);
    }

    //Metodo para eliminar un registro de empleado por ID
    public boolean deleteEmpleado(Integer id){
        empleadoRepository.deleteById(id);
        if (this.empleadoRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }



}
