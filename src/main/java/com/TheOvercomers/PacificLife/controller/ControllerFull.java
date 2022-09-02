package com.TheOvercomers.PacificLife.controller;



import com.TheOvercomers.PacificLife.modelos.Empleado;
import com.TheOvercomers.PacificLife.modelos.Empresa;
import com.TheOvercomers.PacificLife.modelos.MovimientoDinero;
import com.TheOvercomers.PacificLife.service.EmpleadoService;
import com.TheOvercomers.PacificLife.service.EmpresaService;
import com.TheOvercomers.PacificLife.service.MovimientosService;
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

    @Autowired
    MovimientosService movimientosService;


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

   @GetMapping("/enterprises/{id}/empleados")// Consultar empleados por empresa
    public ArrayList<Empleado> EmpleadoPorEmpresa(@PathVariable("id") Integer id){
        return this.empleadoService.obtenerPorEmpresa(id);
    }

    //Edicion parcial se hace con PATCH
    @PatchMapping("/empleados/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody Empleado empleado){
        Empleado empl=empleadoService.getEmpleadoById(id).get();
        empl.setNombre(empleado.getNombre());
        empl.setCorreo(empleado.getCorreo());
        empl.setEmpresa(empleado.getEmpresa());
        empl.setRol(empleado.getRol());
        return empleadoService.saveOrUpdateEmpleado(empl);
    }

    @DeleteMapping("/empleados/{id}") //Metodo para eliminar empleados por id
    public String DeleteEmpleado(@PathVariable("id") Integer id) {
        boolean respuesta = empleadoService.deleteEmpleado(id); //eliminamos usando el servicio de nuestro service
        if (respuesta) { //si la respuesta booleana es true, si se eliminò
            return "Se pudo eliminar correctamente el empleado con id " + id;
        }//Si la respuesta booleana es false, no se eliminó
        return "No se puedo eliminar correctamente el empleado con id " + id;
    }


    //MOVIMIENTOS

    @GetMapping("/movimientos") //Consultar todos los movimientos
    public List<MovimientoDinero> verMovimientos(){
        return movimientosService.getAllMovimientos();
    }

    @PostMapping("/movimientos")
    public MovimientoDinero guardarMovimiento(@RequestBody MovimientoDinero movimiento){
        return movimientosService.saveOrUpdateMovimiento(movimiento);
    }

    @GetMapping("/movimientos/{id}")
    public MovimientoDinero movimientoPorId(@PathVariable("id") Integer id) {
        return movimientosService.getMovimientoById(id);
    }

    @PatchMapping("/movimientos/{id}")//Editar o actualizar un movimiento
    public MovimientoDinero actualizarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimiento) {
        MovimientoDinero mov = movimientosService.getMovimientoById(id);
        mov.setConcepto(movimiento.getConcepto());
        mov.setMonto(movimiento.getMonto());
        mov.setUsuario(movimiento.getUsuario());
        return movimientosService.saveOrUpdateMovimiento(mov);
    }

    }

