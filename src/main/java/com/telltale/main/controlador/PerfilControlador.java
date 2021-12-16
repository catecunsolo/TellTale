package com.telltale.main.controlador;

import com.telltale.main.entidad.Historia;
import com.telltale.main.entidad.Perfil;
import com.telltale.main.entidad.Usuario;
import com.telltale.main.servicio.PerfilServicio;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import static org.apache.coyote.http11.Constants.a;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/perfil")
public class PerfilControlador {

    @Autowired
    PerfilServicio perfilServicio;

    @GetMapping
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

    @PostMapping("/guardar")
    public RedirectView guardaPerfil(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String descripcion, @RequestParam Usuario usuario, RedirectAttributes a) throws Exception {
        RedirectView redirectView = new RedirectView("/perfil");
        try {
            perfilServicio.crearPerfil(nombre, apellido, descripcion, usuario);
            a.addFlashAttribute("success", "Perfil guardado exitosamente.");

        } catch (Exception exception) {
            a.addFlashAttribute("error", "Error --> Guardando perfil --> " + exception.getMessage());
            redirectView.setUrl("/perfil/crear");

        }
        return redirectView;

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

}
