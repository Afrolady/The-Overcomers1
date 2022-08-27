package com.TheOvercomers.PacificLife.controller;

import com.TheOvercomers.PacificLife.modelos.Empresa;
import com.TheOvercomers.PacificLife.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerFull {
    @Autowired
    EmpresaService empresaService;

    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresas(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("empList", listaEmpresas);
        model.addAttribute("mensaje", mensaje);
        return "verEmpresas"; //Llama al HTML
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje) { //crea un objeto de tipo empresa
        Empresa emp = new Empresa();
        model.addAttribute("emp", emp);
        model.addAttribute("mensaje", mensaje);
        return "agregarEmpresa";
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes) {
        if (empresaService.saveOrUpdateEmpresa(emp) == true) {
            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/VerEmpresas";  //El redireccionamiento es hacia el servicio no hacia la pagina web principal
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
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
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        Empresa emp = empresaService.getEmpresaById(id);
        model.addAttribute("emp", emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(Empresa emp, RedirectAttributes redirectAttributes) {
        if (empresaService.saveOrUpdateEmpresa(emp)) {
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/VerEmpresas";  //El redireccionamiento es hacia el servicio no hacia la pagina web principal
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
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
