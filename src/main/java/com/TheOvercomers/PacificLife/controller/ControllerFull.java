package com.TheOvercomers.PacificLife.controller;



import com.TheOvercomers.PacificLife.modelos.Empleado;
import com.TheOvercomers.PacificLife.modelos.Empresa;
import com.TheOvercomers.PacificLife.service.EmpleadoService;
import com.TheOvercomers.PacificLife.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class ControllerFull {
    @Autowired
    EmpresaService empresaService;
    @Autowired
    EmpleadoService empleadoService;


    //EMPRESAS
    @GetMapping("/enterprises") //Ver json de todas las empresas
    public List<Empresa> verEmpresas(){
        return empresaService.getAllEmpresas();

    }

    @PostMapping("/enterprises") // POST DE EMPRESA SPRINT 3
    public Empresa guardarEmpresa(@RequestBody Empresa emp){ //con Postman se puede agregar datos de una empresa como si fuera set.
        return this.empresaService.saveOrUpdateEmpresa(emp); //Guardar el json del body como una nueva empresa o registro en la base de datos.
    }

    @GetMapping(path = "enterprises/{id}")
    public Empresa empresaPorID(@PathVariable("id") Integer id) {
        return this.empresaService.getEmpresaById(id);
    }

    @PatchMapping("/enterprises/{id}") //Consulto una empresa, la empresa tiene una informacion, esa informacion debe ser actualizada. Los datos los saco del body .
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa) {
        Empresa emp= empresaService.getEmpresaById(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNIT(empresa.getNIT());
        return empresaService.saveOrUpdateEmpresa(emp); //Con el return se guarda la actualizacion

    }

    @DeleteMapping(path = "enterprises/{id}") //Eliminar registro de la base de datos
    public String DeleteEmpresa(@PathVariable("id") Integer id){
        boolean respuesta= this.empresaService.deleteEmpresa(id);
        if(respuesta){ //Si respuesta es true me dice:
            return "Se le elimino la empresa con id" +id;
        }
        else {
            return "No se pudo eliminar la empresa con id" +id;
        }
    }

    //EMPLEADOS
    @GetMapping("/empleados") //Ver json de todos los empleados
    public List<Empleado> verEmpleados() {
        return empleadoService.getAllEmpleado();
    }

    @PostMapping("/empleados") //Guardar un empleado nuevo
    public Optional<Empleado> guardarEmpleado(@RequestBody Empleado empl) {
        return Optional.ofNullable(this.empleadoService.saveOrUpdateEmpleado(empl));
    }

    @GetMapping(path = "empleados/{id}")//Consultar empleado por ID
    public Optional<Empleado> empleadoPorID(@PathVariable("id") Integer id){
        return this.empleadoService.getEmpleadoById(id);
    }

/*    @GetMapping("/enterprises/{id}/empleados")// Consultar empleados por empresa
    public ArrayList<Empleado> EmpleadoPorEmpresa(@PathVariable("id") Integer id){
        return this.empleadoService.obtenerPorEmpresa(id);
    }
*/
    }

