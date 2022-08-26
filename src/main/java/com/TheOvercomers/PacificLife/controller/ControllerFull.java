package com.TheOvercomers.PacificLife.controller;

import com.TheOvercomers.PacificLife.modelos.Empresa;
import com.TheOvercomers.PacificLife.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerFull {
    @Autowired
    EmpresaService empresaService;

    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresas(Model model) {
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("empList", listaEmpresas);
        return "verEmpresas"; //Llama al HTML
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model) { //crea un objeto de tipo empresa
        Empresa emp = new Empresa();
        model.addAttribute("emp", emp);
        return "agregarEmpresa";
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes) {
        if (empresaService.saveOrUpdateEmpresa(emp) == true) {
            return "redirect:/VerEmpresas";  //El redireccionamiento es hacia el servicio no hacia la pagina web principal
        }
        return "redirect:/AgregarEmpresa";
    }

   /* @GetMapping("/sprint2")
    public String test() {
        Empresa emp = new Empresa("Pacific Life", "Carrera 6 # 85 -68", "3213213211", "800221221-1");
        emp.setNombre("Pacific Life LTDA");
        System.out.println("Aqui ya se creo la empresa y se renombro");
        return emp.getNombre();
    }*/

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id) {
        Empresa emp = empresaService.getEmpresaById(id);
        model.addAttribute("emp", emp);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(Empresa emp) {
        if (empresaService.saveOrUpdateEmpresa(emp)) {
            return "redirect:/VerEmpresas";  //El redireccionamiento es hacia el servicio no hacia la pagina web principal
        }
        return "redirect:/EditarEmpresa";
    }

    @GetMapping("/EliminarEmpresa/{id}")
        public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
            if (empresaService.deleteEmpresa(id)==true){
                redirectAttributes.addFlashAttribute("mensaje","deleteOK");
                    return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerEmpresas";
    }

    }
