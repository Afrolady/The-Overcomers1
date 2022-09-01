package com.TheOvercomers.PacificLife.repo;


import com.TheOvercomers.PacificLife.modelos.Empleado;
import com.TheOvercomers.PacificLife.modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository //Anotación que le dice a Spring que esta clase es un repositorio
public interface EmpresaRepository extends JpaRepository <Empresa, Integer> {



}

