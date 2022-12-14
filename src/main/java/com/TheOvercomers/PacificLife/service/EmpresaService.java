package com.TheOvercomers.PacificLife.service;

import com.TheOvercomers.PacificLife.modelos.Empresa;
import com.TheOvercomers.PacificLife.repo.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Le decimos a Spring que esta clase es de servicios
@Service
public class EmpresaService {
    @Autowired // Conectamos esta clase con el repository de Empresa
    EmpresaRepository empresaRepository; // Creamos un objeto tipo EmpresaRepository para poder usar los metodos de dicha clase

    //Metodo que retornara la lista de empresas usando metodos del JpaRepository
    public List<Empresa> getAllEmpresas() {
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa)); // recorremos el iterable que regresa el metodo findAll del JPA y lo guardamos en la lista.
        return empresaList;
    }

    //Metodo que me trae un objeto de tipo Empresa cuando cuento con el id de la misma.
    public Empresa getEmpresaById(Integer id) {

        return  empresaRepository.findById(id).get();
    }

    //Metodo para guardar o actualizar objetos de tipo Empresa
    public Empresa saveOrUpdateEmpresa(Empresa empresa) {
        Empresa emp=empresaRepository.save(empresa);
        return emp;
    }

    //Metodo para eliminar empresas registradas teniendo el id
    public boolean deleteEmpresa(Integer id) {
        empresaRepository.deleteById(id); //Eliminar
        if(empresaRepository.findById(id)==null) { //Verificacion del servicio eliminación
            return true; // >Si busco el producto y no lo encuentra (==null). me retorna true de que si lo elimine
        }
        return false; //Si lo encuentra devuelve false de que no lo elimino.
    }

}

