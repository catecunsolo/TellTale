package com.telltale.main.controlador;

import com.telltale.main.entidad.Rol;
import com.telltale.main.servicio.RolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/rol")
public class RolControlador {

    @Autowired
    private RolServicio rolServicio;

    @GetMapping("/crear")
    public ModelAndView crearRol(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("rolformulario");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("success", flashMap.get("success"));
            modelAndView.addObject("error", flashMap.get("error"));
            modelAndView.addObject("rol", flashMap.get("rol"));
        } else {
            modelAndView.addObject("rol", new Rol());
        }
        return modelAndView;
    }

    @PostMapping("/guardar")
    public RedirectView guardarRol(@ModelAttribute Rol rol, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView("/rol");
        try {
            rolServicio.validarFormularioYCrear(rol.getNombre());
            redirectAttributes.addFlashAttribute("success", "El Rol se ha registrado exitosamente!");
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
            redirectAttributes.addFlashAttribute("rol", rol);
            redirectView.setUrl("/rol/crear");
        }
        return redirectView;
    }


}
