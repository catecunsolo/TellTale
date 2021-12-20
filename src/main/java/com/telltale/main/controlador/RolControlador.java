package com.telltale.main.controlador;

import com.telltale.main.entidad.Rol;
import com.telltale.main.excepcion.MiExcepcion;
import com.telltale.main.servicio.RolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RolControlador {

    @Autowired
    private RolServicio rolServicio;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView verTodosRol(HttpServletRequest request, @RequestParam(required = false) String error) {
        ModelAndView modelAndView = new ModelAndView("admin-roles");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("success", flashMap.get("success"));
            modelAndView.addObject("error", flashMap.get("error"));
            modelAndView.addObject("listaRoles", null);
        }
        if (error != null) {
            modelAndView.addObject("error", error);
            modelAndView.addObject("listaRoles", null);
        } else {
            try {
                modelAndView.addObject("listaRoles", rolServicio.verTodosRol());
            } catch (Exception excepcion) {
                modelAndView.addObject("error", excepcion.getMessage());
                modelAndView.setViewName("redirect:/roles");
            }
        }
        return modelAndView;
    }

    @GetMapping("/crear")
    @PreAuthorize("hasRole('ADMIN')")
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
        modelAndView.addObject("title", "Creación de Rol");
        modelAndView.addObject("action", "guardar");
        return modelAndView;
    }

    @PostMapping("/guardar")
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/editar/{id_rol}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView editarRol(@PathVariable Integer id_rol, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("rolformulario");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("success", flashMap.get("success"));
            modelAndView.addObject("error", flashMap.get("error"));
            modelAndView.addObject("rol", flashMap.get("rol"));
        } else {
            modelAndView.addObject("rol", rolServicio.buscarRolPorId(id_rol));
        }
        modelAndView.addObject("title", "Modificación de Rol");
        modelAndView.addObject("action", "modificar");
        return modelAndView;
    }

    @PostMapping("/modificar")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView modificarRol(@ModelAttribute Rol rol, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView("/rol");
        try {
            rolServicio.validarFormularioYModificar(rol.getId_rol(), rol.getNombre());
            redirectAttributes.addFlashAttribute("success", "El Rol se ha modificado exitosamente!");
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
            redirectAttributes.addFlashAttribute("rol", rol);
            redirectView.setUrl("/rol/editar/" + rol.getId_rol());
        }
        return redirectView;
    }

    @PostMapping("/delete/{id_roles}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView eliminarRol(@PathVariable Integer id_rol, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView("/roles");
        try {
            rolServicio.eliminarRol(id_rol);
            redirectAttributes.addFlashAttribute("success", "El Rol ha sido eliminado exitosamente!");
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
        }
        return redirectView;
    }

}
