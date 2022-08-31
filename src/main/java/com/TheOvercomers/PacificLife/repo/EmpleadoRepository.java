package com.TheOvercomers.PacificLife.repo;

import com.TheOvercomers.PacificLife.modelos.Empleado;
import com.TheOvercomers.PacificLife.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {


}
