package com.TheOvercomers.PacificLife.controller;

import com.TheOvercomers.PacificLife.modelos.Empresa;
import com.TheOvercomers.PacificLife.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ControllerFull {
    @Autowired
    EmpresaService empresaService;

    @GetMapping ({"/Empresas","/VerEmpresas"})
    public String viewEmpresas(Model model) {
        List<Empresa> listaEmpresas=empresaService.getAllEmpresas();
        model.addAttribute("empList",listaEmpresas);
        return "verEmpresas";


    }
}
