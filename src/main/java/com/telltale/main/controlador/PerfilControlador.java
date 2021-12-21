package com.telltale.main.controlador;

import com.telltale.main.entidad.Historia;
import com.telltale.main.entidad.Perfil;
import com.telltale.main.entidad.Rol;
import com.telltale.main.entidad.Usuario;
import com.telltale.main.servicio.PerfilServicio;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import static org.apache.coyote.http11.Constants.a;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/perfil")
public class PerfilControlador {

    @Autowired
    PerfilServicio perfilServicio;

    @GetMapping
    public ModelAndView verMiPerfil(HttpServletRequest request, @RequestParam(required = false) String error) {
        HttpSession session = (HttpSession) request.getSession();
        ModelAndView modelAndView = new ModelAndView("perfil");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("success", flashMap.get("success"));
            modelAndView.addObject("error", flashMap.get("error"));
            modelAndView.addObject("perfil", null);
        }
        if (error != null) {
            modelAndView.addObject("error", error);
            modelAndView.addObject("perfil", null);
        } else {
            try {
                modelAndView.addObject("action", "perfil");
                modelAndView.addObject("perfil", perfilServicio.buscarPerfilPorIdUsuario(Integer.parseInt(session.getAttribute("id_usuario").toString())));
                int cantidadDeHistorias = perfilServicio.buscarPerfilPorIdUsuario(Integer.parseInt(session.getAttribute("id_usuario").toString())).getHistorias().size();
                modelAndView.addObject("cantidadDeHistorias", cantidadDeHistorias);

            } catch (Exception excepcion) {
                modelAndView.addObject("error", excepcion.getMessage());
                modelAndView.setViewName("redirect:/");
            }
        }
        return modelAndView;
    }

    @GetMapping("/editar")
    public ModelAndView editarMiPerfil(HttpServletRequest request, @RequestParam(required = false) String error) {
        HttpSession session = (HttpSession) request.getSession();
        ModelAndView modelAndView = new ModelAndView("perfil");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("success", flashMap.get("success"));
            modelAndView.addObject("error", flashMap.get("error"));
            modelAndView.addObject("perfil", null);
        }
        if (error != null) {
            modelAndView.addObject("error", error);
            modelAndView.addObject("perfil", null);
        } else {
            try {
                modelAndView.addObject("action", "guardar");
                modelAndView.addObject("perfil", perfilServicio.buscarPerfilPorIdUsuario(Integer.parseInt(session.getAttribute("id_usuario").toString())));
            } catch (Exception excepcion) {
                modelAndView.addObject("error", excepcion.getMessage());
                modelAndView.setViewName("redirect:/");
            }
        }
        return modelAndView;
    }

    @PostMapping("/guardar")
    public RedirectView guardarCambiosMiPerfil(@ModelAttribute Perfil perfil, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView("/perfil");
        try {
            perfilServicio.modificarPerfil(perfil.getId_perfil(), perfil.getNombre(), perfil.getApellido(), perfil.getDescripcion());
            redirectAttributes.addFlashAttribute("success", "El Perfil se ha modificado exitosamente!");
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
            redirectAttributes.addFlashAttribute("perfil", perfil);
            redirectView.setUrl("/perfil/editar");
        }
        return redirectView;
    }

    @GetMapping("/todos")
    public ModelAndView verTodosPerfil(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("perfiles");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            mav.addObject("error", map.get("exito-name"));
        }
        mav.addObject("perfiles", perfilServicio.verTodosPerfil());

        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearPerfil(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("perfil-formulario");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            mav.addObject("error", map.get("error-name"));
        }
        mav.addObject("perfil", new Perfil());
        mav.addObject("titile", "Crear Perfil");
        mav.addObject("action", "guardae");
        return mav;
    }

    @PostMapping("/modificar")
    public RedirectView modificarPerfil(@RequestParam Integer id_perfil, @RequestParam String nombre,
            @RequestParam String apellido, @RequestParam String descripcion, RedirectAttributes a) throws Exception {
        try {
            perfilServicio.modificarPerfil(id_perfil, nombre, apellido, descripcion);
            a.addFlashAttribute("success", "Perfil modificado exitosamente.");
        } catch (Exception exception) {
            a.addFlashAttribute("error", "Error --> Modificando perfil--> " + exception.getMessage());
        }

        return new RedirectView("/perfil");

    }

    @PostMapping("/eliminar/{id_perfil}")
    public RedirectView elimiarPerfil(@PathVariable Integer id_perfil) {
        perfilServicio.eliminarPerfil(id_perfil);
        return new RedirectView("/perfil");
    }

    @GetMapping("/{id}")
    public ModelAndView verOtroPerfil(@PathVariable int id, HttpServletRequest request, @RequestParam(required = false) String error) {
        ModelAndView modelAndView = new ModelAndView("perfil");
        HttpSession session = (HttpSession) request.getSession();
        if (id == (int) session.getAttribute("id_usuario")) {
            modelAndView.setViewName("redirect:/perfil");
        } else {
            Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
            if (flashMap != null) {
                modelAndView.addObject("success", flashMap.get("success"));
                modelAndView.addObject("error", flashMap.get("error"));
                modelAndView.addObject("perfil", null);
            }
            if (error != null) {
                modelAndView.addObject("error", error);
                modelAndView.addObject("perfil", null);
            } else {
                try {
                    modelAndView.addObject("action", "perfil_id");
                    modelAndView.addObject("perfil", perfilServicio.buscarPerfilPorIdUsuario(id));
                    modelAndView.addObject("id_nuevo", id);
                    int cantidadDeHistorias = perfilServicio.buscarPerfilPorIdUsuario(id).getHistorias().size();
                modelAndView.addObject("cantidadDeHistorias", cantidadDeHistorias);

                } catch (Exception excepcion) {
                    modelAndView.addObject("error", excepcion.getMessage());
                    modelAndView.setViewName("redirect:/");
                }
            }
        }
        return modelAndView;
    }
}
